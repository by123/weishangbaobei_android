package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class DiskLruCache implements Closeable, Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Runnable cleanupRunnable = new 1(this);
    boolean closed;
    final File directory;
    private final Executor executor;
    final FileSystem fileSystem;
    boolean hasJournalErrors;
    boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    BufferedSink journalWriter;
    final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    boolean mostRecentRebuildFailed;
    boolean mostRecentTrimFailed;
    private long nextSequenceNumber = 0;
    int redundantOpCount;
    private long size = 0;
    final int valueCount;

    public final class Snapshot implements Closeable {
        /* access modifiers changed from: private */
        public final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;

        Snapshot(String str, long j, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = sourceArr;
            this.lengths = jArr;
        }

        public void close() {
            for (Source closeQuietly : this.sources) {
                Util.closeQuietly((Closeable) closeQuietly);
            }
        }

        @Nullable
        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        public Source getSource(int i) {
            return this.sources[i];
        }

        public String key() {
            return this.key;
        }
    }

    DiskLruCache(FileSystem fileSystem2, File file, int i, int i2, long j, Executor executor2) {
        this.fileSystem = fileSystem2;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor2;
    }

    private void checkNotClosed() {
        synchronized (this) {
            if (isClosed()) {
                throw new IllegalStateException("cache is closed");
            }
        }
    }

    public static DiskLruCache create(FileSystem fileSystem2, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            return new DiskLruCache(fileSystem2, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new 2(this, this.fileSystem.appendingSink(this.journalFile)));
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.currentEditor == null) {
                for (int i = 0; i < this.valueCount; i++) {
                    this.size += next.lengths[i];
                }
            } else {
                next.currentEditor = null;
                for (int i2 = 0; i2 < this.valueCount; i2++) {
                    this.fileSystem.delete(next.cleanFiles[i2]);
                    this.fileSystem.delete(next.dirtyFiles[i2]);
                }
                it.remove();
            }
        }
    }

    private void readJournal() throws IOException {
        int i;
        BufferedSource buffer = Okio.buffer(this.fileSystem.source(this.journalFile));
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if (!MAGIC.equals(readUtf8LineStrict) || !VERSION_1.equals(readUtf8LineStrict2) || !Integer.toString(this.appVersion).equals(readUtf8LineStrict3) || !Integer.toString(this.valueCount).equals(readUtf8LineStrict4) || !"".equals(readUtf8LineStrict5)) {
                throw new IOException("unexpected journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + "]");
            }
            i = 0;
            while (true) {
                readJournalLine(buffer.readUtf8LineStrict());
                i++;
            }
        } catch (EOFException e) {
            this.redundantOpCount = i - this.lruEntries.size();
            if (!buffer.exhausted()) {
                rebuildJournal();
            } else {
                this.journalWriter = newJournalWriter();
            }
            Util.closeQuietly((Closeable) buffer);
        } catch (Throwable th) {
            Util.closeQuietly((Closeable) buffer);
            throw th;
        }
    }

    private void readJournalLine(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                String substring = str.substring(i);
                if (indexOf != REMOVE.length() || !str.startsWith(REMOVE)) {
                    str2 = substring;
                } else {
                    this.lruEntries.remove(substring);
                    return;
                }
            } else {
                str2 = str.substring(i, indexOf2);
            }
            Entry entry = this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(this, str2);
                this.lruEntries.put(str2, entry);
            }
            if (indexOf2 != -1 && indexOf == CLEAN.length() && str.startsWith(CLEAN)) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                entry.readable = true;
                entry.currentEditor = null;
                entry.setLengths(split);
            } else if (indexOf2 == -1 && indexOf == DIRTY.length() && str.startsWith(DIRTY)) {
                entry.currentEditor = new Editor(this, entry);
            } else if (indexOf2 != -1 || indexOf != READ.length() || !str.startsWith(READ)) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public void close() throws IOException {
        synchronized (this) {
            if (!this.initialized || this.closed) {
                this.closed = true;
                return;
            }
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
            this.closed = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return;
     */
    public void completeEdit(Editor editor, boolean z) throws IOException {
        synchronized (this) {
            Entry entry = editor.entry;
            if (entry.currentEditor == editor) {
                if (z && !entry.readable) {
                    int i = 0;
                    while (i < this.valueCount) {
                        if (!editor.written[i]) {
                            editor.abort();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                        } else if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                            editor.abort();
                            return;
                        } else {
                            i++;
                        }
                    }
                }
                for (int i2 = 0; i2 < this.valueCount; i2++) {
                    File file = entry.dirtyFiles[i2];
                    if (!z) {
                        this.fileSystem.delete(file);
                    } else if (this.fileSystem.exists(file)) {
                        File file2 = entry.cleanFiles[i2];
                        this.fileSystem.rename(file, file2);
                        long j = entry.lengths[i2];
                        long size2 = this.fileSystem.size(file2);
                        entry.lengths[i2] = size2;
                        this.size = (this.size - j) + size2;
                    }
                }
                this.redundantOpCount++;
                entry.currentEditor = null;
                if (entry.readable || z) {
                    entry.readable = true;
                    this.journalWriter.writeUtf8(CLEAN).writeByte(32);
                    this.journalWriter.writeUtf8(entry.key);
                    entry.writeLengths(this.journalWriter);
                    this.journalWriter.writeByte(10);
                    if (z) {
                        long j2 = this.nextSequenceNumber;
                        this.nextSequenceNumber = 1 + j2;
                        entry.sequenceNumber = j2;
                    }
                } else {
                    this.lruEntries.remove(entry.key);
                    this.journalWriter.writeUtf8(REMOVE).writeByte(32);
                    this.journalWriter.writeUtf8(entry.key);
                    this.journalWriter.writeByte(10);
                }
                this.journalWriter.flush();
                if (this.size > this.maxSize || journalRebuildRequired()) {
                    this.executor.execute(this.cleanupRunnable);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    @Nullable
    public Editor edit(String str) throws IOException {
        return edit(str, ANY_SEQUENCE_NUMBER);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return null;
     */
    public Editor edit(String str, long j) throws IOException {
        Entry entry;
        synchronized (this) {
            initialize();
            checkNotClosed();
            validateKey(str);
            Entry entry2 = this.lruEntries.get(str);
            if (j == ANY_SEQUENCE_NUMBER || (entry2 != null && entry2.sequenceNumber == j)) {
                if (entry2 != null) {
                    if (entry2.currentEditor != null) {
                        return null;
                    }
                }
                if (this.mostRecentTrimFailed || this.mostRecentRebuildFailed) {
                    this.executor.execute(this.cleanupRunnable);
                    return null;
                }
                this.journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(str).writeByte(10);
                this.journalWriter.flush();
                if (this.hasJournalErrors) {
                    return null;
                }
                if (entry2 == null) {
                    Entry entry3 = new Entry(this, str);
                    this.lruEntries.put(str, entry3);
                    entry = entry3;
                } else {
                    entry = entry2;
                }
                Editor editor = new Editor(this, entry);
                entry.currentEditor = editor;
                return editor;
            }
        }
    }

    public void evictAll() throws IOException {
        synchronized (this) {
            initialize();
            for (Entry removeEntry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                removeEntry(removeEntry);
            }
            this.mostRecentTrimFailed = false;
        }
    }

    public void flush() throws IOException {
        synchronized (this) {
            if (this.initialized) {
                checkNotClosed();
                trimToSize();
                this.journalWriter.flush();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return r0;
     */
    public Snapshot get(String str) throws IOException {
        synchronized (this) {
            initialize();
            checkNotClosed();
            validateKey(str);
            Entry entry = this.lruEntries.get(str);
            if (entry != null && entry.readable) {
                Snapshot snapshot = entry.snapshot();
                if (snapshot == null) {
                    return null;
                }
                this.redundantOpCount++;
                this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(str).writeByte(10);
                if (journalRebuildRequired()) {
                    this.executor.execute(this.cleanupRunnable);
                }
            }
        }
    }

    public File getDirectory() {
        return this.directory;
    }

    public long getMaxSize() {
        long j;
        synchronized (this) {
            j = this.maxSize;
        }
        return j;
    }

    /* JADX INFO: finally extract failed */
    public void initialize() throws IOException {
        synchronized (this) {
            if (!this.initialized) {
                if (this.fileSystem.exists(this.journalFileBackup)) {
                    if (this.fileSystem.exists(this.journalFile)) {
                        this.fileSystem.delete(this.journalFileBackup);
                    } else {
                        this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                    }
                }
                if (this.fileSystem.exists(this.journalFile)) {
                    try {
                        readJournal();
                        processJournal();
                        this.initialized = true;
                        return;
                    } catch (IOException e) {
                        Platform platform = Platform.get();
                        platform.log(5, "DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing", e);
                        delete();
                        this.closed = false;
                    } catch (Throwable th) {
                        this.closed = false;
                        throw th;
                    }
                }
                rebuildJournal();
                this.initialized = true;
            }
        }
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.closed;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    /* access modifiers changed from: package-private */
    public void rebuildJournal() throws IOException {
        synchronized (this) {
            if (this.journalWriter != null) {
                this.journalWriter.close();
            }
            BufferedSink buffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
            try {
                buffer.writeUtf8(MAGIC).writeByte(10);
                buffer.writeUtf8(VERSION_1).writeByte(10);
                buffer.writeDecimalLong((long) this.appVersion).writeByte(10);
                buffer.writeDecimalLong((long) this.valueCount).writeByte(10);
                buffer.writeByte(10);
                for (Entry next : this.lruEntries.values()) {
                    if (next.currentEditor != null) {
                        buffer.writeUtf8(DIRTY).writeByte(32);
                        buffer.writeUtf8(next.key);
                        buffer.writeByte(10);
                    } else {
                        buffer.writeUtf8(CLEAN).writeByte(32);
                        buffer.writeUtf8(next.key);
                        next.writeLengths(buffer);
                        buffer.writeByte(10);
                    }
                }
                buffer.close();
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.rename(this.journalFile, this.journalFileBackup);
                }
                this.fileSystem.rename(this.journalFileTmp, this.journalFile);
                this.fileSystem.delete(this.journalFileBackup);
                this.journalWriter = newJournalWriter();
                this.hasJournalErrors = false;
                this.mostRecentRebuildFailed = false;
            } catch (Throwable th) {
                buffer.close();
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return r0;
     */
    public boolean remove(String str) throws IOException {
        synchronized (this) {
            initialize();
            checkNotClosed();
            validateKey(str);
            Entry entry = this.lruEntries.get(str);
            if (entry == null) {
                return false;
            }
            boolean removeEntry = removeEntry(entry);
            if (removeEntry && this.size <= this.maxSize) {
                this.mostRecentTrimFailed = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.detach();
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8(REMOVE).writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (!journalRebuildRequired()) {
            return true;
        }
        this.executor.execute(this.cleanupRunnable);
        return true;
    }

    public void setMaxSize(long j) {
        synchronized (this) {
            this.maxSize = j;
            if (this.initialized) {
                this.executor.execute(this.cleanupRunnable);
            }
        }
    }

    public long size() throws IOException {
        long j;
        synchronized (this) {
            initialize();
            j = this.size;
        }
        return j;
    }

    public Iterator<Snapshot> snapshots() throws IOException {
        3 r0;
        synchronized (this) {
            initialize();
            r0 = new 3(this);
        }
        return r0;
    }

    /* access modifiers changed from: package-private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }
}
