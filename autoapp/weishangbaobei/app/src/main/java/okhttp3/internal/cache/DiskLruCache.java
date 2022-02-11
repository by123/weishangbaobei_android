package okhttp3.internal.cache;

import java.io.Closeable;
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

    public synchronized void initialize() throws IOException {
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

    public static DiskLruCache create(FileSystem fileSystem2, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            return new DiskLruCache(fileSystem2, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.redundantOpCount = r1 - r8.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        if (r0.exhausted() == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        rebuildJournal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        r8.journalWriter = newJournalWriter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0077, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005b */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0078=Splitter:B:23:0x0078, B:16:0x005b=Splitter:B:16:0x005b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournal() throws java.io.IOException {
        /*
            r8 = this;
            okhttp3.internal.io.FileSystem r0 = r8.fileSystem
            java.io.File r1 = r8.journalFile
            okio.Source r0 = r0.source(r1)
            okio.BufferedSource r0 = okio.Okio.buffer(r0)
            java.lang.String r1 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r2 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r3 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r4 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r5 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x00ac }
            if (r6 == 0) goto L_0x0078
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x00ac }
            if (r6 == 0) goto L_0x0078
            int r6 = r8.appVersion     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x00ac }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0078
            int r3 = r8.valueCount     // Catch:{ all -> 0x00ac }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x00ac }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0078
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0078
            r1 = 0
        L_0x0051:
            java.lang.String r2 = r0.readUtf8LineStrict()     // Catch:{ EOFException -> 0x005b }
            r8.readJournalLine(r2)     // Catch:{ EOFException -> 0x005b }
            int r1 = r1 + 1
            goto L_0x0051
        L_0x005b:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r2 = r8.lruEntries     // Catch:{ all -> 0x00ac }
            int r2 = r2.size()     // Catch:{ all -> 0x00ac }
            int r1 = r1 - r2
            r8.redundantOpCount = r1     // Catch:{ all -> 0x00ac }
            boolean r1 = r0.exhausted()     // Catch:{ all -> 0x00ac }
            if (r1 != 0) goto L_0x006e
            r8.rebuildJournal()     // Catch:{ all -> 0x00ac }
            goto L_0x0074
        L_0x006e:
            okio.BufferedSink r1 = r8.newJournalWriter()     // Catch:{ all -> 0x00ac }
            r8.journalWriter = r1     // Catch:{ all -> 0x00ac }
        L_0x0074:
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
            return
        L_0x0078:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x00ac }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ac }
            r6.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x00ac }
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            r6.append(r2)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            r6.append(r4)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            r6.append(r5)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x00ac }
            r3.<init>(r1)     // Catch:{ all -> 0x00ac }
            throw r3     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r1 = move-exception
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.readJournal():void");
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new 2(this, this.fileSystem.appendingSink(this.journalFile)));
    }

    private void readJournalLine(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                str2 = str.substring(i);
                if (indexOf == REMOVE.length() && str.startsWith(REMOVE)) {
                    this.lruEntries.remove(str2);
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

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            } else {
                next.currentEditor = null;
                while (i < this.valueCount) {
                    this.fileSystem.delete(next.cleanFiles[i]);
                    this.fileSystem.delete(next.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public synchronized void rebuildJournal() throws IOException {
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

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized okhttp3.internal.cache.DiskLruCache.Snapshot get(java.lang.String r4) throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.initialize()     // Catch:{ all -> 0x0050 }
            r3.checkNotClosed()     // Catch:{ all -> 0x0050 }
            r3.validateKey(r4)     // Catch:{ all -> 0x0050 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r3.lruEntries     // Catch:{ all -> 0x0050 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0050 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0050 }
            r1 = 0
            if (r0 == 0) goto L_0x004e
            boolean r2 = r0.readable     // Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x001a
            goto L_0x004e
        L_0x001a:
            okhttp3.internal.cache.DiskLruCache$Snapshot r0 = r0.snapshot()     // Catch:{ all -> 0x0050 }
            if (r0 != 0) goto L_0x0022
            monitor-exit(r3)
            return r1
        L_0x0022:
            int r1 = r3.redundantOpCount     // Catch:{ all -> 0x0050 }
            int r1 = r1 + 1
            r3.redundantOpCount = r1     // Catch:{ all -> 0x0050 }
            okio.BufferedSink r1 = r3.journalWriter     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "READ"
            okio.BufferedSink r1 = r1.writeUtf8(r2)     // Catch:{ all -> 0x0050 }
            r2 = 32
            okio.BufferedSink r1 = r1.writeByte(r2)     // Catch:{ all -> 0x0050 }
            okio.BufferedSink r4 = r1.writeUtf8(r4)     // Catch:{ all -> 0x0050 }
            r1 = 10
            r4.writeByte(r1)     // Catch:{ all -> 0x0050 }
            boolean r4 = r3.journalRebuildRequired()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x004c
            java.util.concurrent.Executor r4 = r3.executor     // Catch:{ all -> 0x0050 }
            java.lang.Runnable r1 = r3.cleanupRunnable     // Catch:{ all -> 0x0050 }
            r4.execute(r1)     // Catch:{ all -> 0x0050 }
        L_0x004c:
            monitor-exit(r3)
            return r0
        L_0x004e:
            monitor-exit(r3)
            return r1
        L_0x0050:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.get(java.lang.String):okhttp3.internal.cache.DiskLruCache$Snapshot");
    }

    @Nullable
    public Editor edit(String str) throws IOException {
        return edit(str, ANY_SEQUENCE_NUMBER);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized okhttp3.internal.cache.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.initialize()     // Catch:{ all -> 0x0074 }
            r5.checkNotClosed()     // Catch:{ all -> 0x0074 }
            r5.validateKey(r6)     // Catch:{ all -> 0x0074 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x0074 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0074 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0074 }
            r1 = -1
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x0023
            if (r0 == 0) goto L_0x0021
            long r1 = r0.sequenceNumber     // Catch:{ all -> 0x0074 }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0023
        L_0x0021:
            monitor-exit(r5)
            return r3
        L_0x0023:
            if (r0 == 0) goto L_0x002b
            okhttp3.internal.cache.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x0074 }
            if (r7 == 0) goto L_0x002b
            monitor-exit(r5)
            return r3
        L_0x002b:
            boolean r7 = r5.mostRecentTrimFailed     // Catch:{ all -> 0x0074 }
            if (r7 != 0) goto L_0x006b
            boolean r7 = r5.mostRecentRebuildFailed     // Catch:{ all -> 0x0074 }
            if (r7 == 0) goto L_0x0034
            goto L_0x006b
        L_0x0034:
            okio.BufferedSink r7 = r5.journalWriter     // Catch:{ all -> 0x0074 }
            java.lang.String r8 = "DIRTY"
            okio.BufferedSink r7 = r7.writeUtf8(r8)     // Catch:{ all -> 0x0074 }
            r8 = 32
            okio.BufferedSink r7 = r7.writeByte(r8)     // Catch:{ all -> 0x0074 }
            okio.BufferedSink r7 = r7.writeUtf8(r6)     // Catch:{ all -> 0x0074 }
            r8 = 10
            r7.writeByte(r8)     // Catch:{ all -> 0x0074 }
            okio.BufferedSink r7 = r5.journalWriter     // Catch:{ all -> 0x0074 }
            r7.flush()     // Catch:{ all -> 0x0074 }
            boolean r7 = r5.hasJournalErrors     // Catch:{ all -> 0x0074 }
            if (r7 == 0) goto L_0x0056
            monitor-exit(r5)
            return r3
        L_0x0056:
            if (r0 != 0) goto L_0x0062
            okhttp3.internal.cache.DiskLruCache$Entry r0 = new okhttp3.internal.cache.DiskLruCache$Entry     // Catch:{ all -> 0x0074 }
            r0.<init>(r5, r6)     // Catch:{ all -> 0x0074 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x0074 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0074 }
        L_0x0062:
            okhttp3.internal.cache.DiskLruCache$Editor r6 = new okhttp3.internal.cache.DiskLruCache$Editor     // Catch:{ all -> 0x0074 }
            r6.<init>(r5, r0)     // Catch:{ all -> 0x0074 }
            r0.currentEditor = r6     // Catch:{ all -> 0x0074 }
            monitor-exit(r5)
            return r6
        L_0x006b:
            java.util.concurrent.Executor r6 = r5.executor     // Catch:{ all -> 0x0074 }
            java.lang.Runnable r7 = r5.cleanupRunnable     // Catch:{ all -> 0x0074 }
            r6.execute(r7)     // Catch:{ all -> 0x0074 }
            monitor-exit(r5)
            return r3
        L_0x0074:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.edit(java.lang.String, long):okhttp3.internal.cache.DiskLruCache$Editor");
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void completeEdit(okhttp3.internal.cache.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            okhttp3.internal.cache.DiskLruCache$Entry r0 = r10.entry     // Catch:{ all -> 0x00fc }
            okhttp3.internal.cache.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x00fc }
            if (r1 != r10) goto L_0x00f6
            r1 = 0
            if (r11 == 0) goto L_0x0047
            boolean r2 = r0.readable     // Catch:{ all -> 0x00fc }
            if (r2 != 0) goto L_0x0047
            r2 = 0
        L_0x000f:
            int r3 = r9.valueCount     // Catch:{ all -> 0x00fc }
            if (r2 >= r3) goto L_0x0047
            boolean[] r3 = r10.written     // Catch:{ all -> 0x00fc }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x00fc }
            if (r3 == 0) goto L_0x002d
            okhttp3.internal.io.FileSystem r3 = r9.fileSystem     // Catch:{ all -> 0x00fc }
            java.io.File[] r4 = r0.dirtyFiles     // Catch:{ all -> 0x00fc }
            r4 = r4[r2]     // Catch:{ all -> 0x00fc }
            boolean r3 = r3.exists(r4)     // Catch:{ all -> 0x00fc }
            if (r3 != 0) goto L_0x002a
            r10.abort()     // Catch:{ all -> 0x00fc }
            monitor-exit(r9)
            return
        L_0x002a:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x002d:
            r10.abort()     // Catch:{ all -> 0x00fc }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fc }
            r11.<init>()     // Catch:{ all -> 0x00fc }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x00fc }
            r11.append(r2)     // Catch:{ all -> 0x00fc }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00fc }
            r10.<init>(r11)     // Catch:{ all -> 0x00fc }
            throw r10     // Catch:{ all -> 0x00fc }
        L_0x0047:
            int r10 = r9.valueCount     // Catch:{ all -> 0x00fc }
            if (r1 >= r10) goto L_0x0080
            java.io.File[] r10 = r0.dirtyFiles     // Catch:{ all -> 0x00fc }
            r10 = r10[r1]     // Catch:{ all -> 0x00fc }
            if (r11 == 0) goto L_0x0078
            okhttp3.internal.io.FileSystem r2 = r9.fileSystem     // Catch:{ all -> 0x00fc }
            boolean r2 = r2.exists(r10)     // Catch:{ all -> 0x00fc }
            if (r2 == 0) goto L_0x007d
            java.io.File[] r2 = r0.cleanFiles     // Catch:{ all -> 0x00fc }
            r2 = r2[r1]     // Catch:{ all -> 0x00fc }
            okhttp3.internal.io.FileSystem r3 = r9.fileSystem     // Catch:{ all -> 0x00fc }
            r3.rename(r10, r2)     // Catch:{ all -> 0x00fc }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x00fc }
            r3 = r10[r1]     // Catch:{ all -> 0x00fc }
            okhttp3.internal.io.FileSystem r10 = r9.fileSystem     // Catch:{ all -> 0x00fc }
            long r5 = r10.size(r2)     // Catch:{ all -> 0x00fc }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x00fc }
            r10[r1] = r5     // Catch:{ all -> 0x00fc }
            long r7 = r9.size     // Catch:{ all -> 0x00fc }
            r10 = 0
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.size = r7     // Catch:{ all -> 0x00fc }
            goto L_0x007d
        L_0x0078:
            okhttp3.internal.io.FileSystem r2 = r9.fileSystem     // Catch:{ all -> 0x00fc }
            r2.delete(r10)     // Catch:{ all -> 0x00fc }
        L_0x007d:
            int r1 = r1 + 1
            goto L_0x0047
        L_0x0080:
            int r10 = r9.redundantOpCount     // Catch:{ all -> 0x00fc }
            r1 = 1
            int r10 = r10 + r1
            r9.redundantOpCount = r10     // Catch:{ all -> 0x00fc }
            r10 = 0
            r0.currentEditor = r10     // Catch:{ all -> 0x00fc }
            boolean r10 = r0.readable     // Catch:{ all -> 0x00fc }
            r10 = r10 | r11
            r2 = 10
            r3 = 32
            if (r10 == 0) goto L_0x00bc
            r0.readable = r1     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            java.lang.String r1 = "CLEAN"
            okio.BufferedSink r10 = r10.writeUtf8(r1)     // Catch:{ all -> 0x00fc }
            r10.writeByte(r3)     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            java.lang.String r1 = r0.key     // Catch:{ all -> 0x00fc }
            r10.writeUtf8(r1)     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            r0.writeLengths(r10)     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            r10.writeByte(r2)     // Catch:{ all -> 0x00fc }
            if (r11 == 0) goto L_0x00da
            long r10 = r9.nextSequenceNumber     // Catch:{ all -> 0x00fc }
            r1 = 1
            long r1 = r1 + r10
            r9.nextSequenceNumber = r1     // Catch:{ all -> 0x00fc }
            r0.sequenceNumber = r10     // Catch:{ all -> 0x00fc }
            goto L_0x00da
        L_0x00bc:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r10 = r9.lruEntries     // Catch:{ all -> 0x00fc }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x00fc }
            r10.remove(r11)     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            java.lang.String r11 = "REMOVE"
            okio.BufferedSink r10 = r10.writeUtf8(r11)     // Catch:{ all -> 0x00fc }
            r10.writeByte(r3)     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x00fc }
            r10.writeUtf8(r11)     // Catch:{ all -> 0x00fc }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            r10.writeByte(r2)     // Catch:{ all -> 0x00fc }
        L_0x00da:
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x00fc }
            r10.flush()     // Catch:{ all -> 0x00fc }
            long r10 = r9.size     // Catch:{ all -> 0x00fc }
            long r0 = r9.maxSize     // Catch:{ all -> 0x00fc }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x00ed
            boolean r10 = r9.journalRebuildRequired()     // Catch:{ all -> 0x00fc }
            if (r10 == 0) goto L_0x00f4
        L_0x00ed:
            java.util.concurrent.Executor r10 = r9.executor     // Catch:{ all -> 0x00fc }
            java.lang.Runnable r11 = r9.cleanupRunnable     // Catch:{ all -> 0x00fc }
            r10.execute(r11)     // Catch:{ all -> 0x00fc }
        L_0x00f4:
            monitor-exit(r9)
            return
        L_0x00f6:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fc }
            r10.<init>()     // Catch:{ all -> 0x00fc }
            throw r10     // Catch:{ all -> 0x00fc }
        L_0x00fc:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.completeEdit(okhttp3.internal.cache.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            r6.initialize()     // Catch:{ all -> 0x0029 }
            r6.checkNotClosed()     // Catch:{ all -> 0x0029 }
            r6.validateKey(r7)     // Catch:{ all -> 0x0029 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0029 }
            java.lang.Object r7 = r0.get(r7)     // Catch:{ all -> 0x0029 }
            okhttp3.internal.cache.DiskLruCache$Entry r7 = (okhttp3.internal.cache.DiskLruCache.Entry) r7     // Catch:{ all -> 0x0029 }
            r0 = 0
            if (r7 != 0) goto L_0x0017
            monitor-exit(r6)
            return r0
        L_0x0017:
            boolean r7 = r6.removeEntry(r7)     // Catch:{ all -> 0x0029 }
            if (r7 == 0) goto L_0x0027
            long r1 = r6.size     // Catch:{ all -> 0x0029 }
            long r3 = r6.maxSize     // Catch:{ all -> 0x0029 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0027
            r6.mostRecentTrimFailed = r0     // Catch:{ all -> 0x0029 }
        L_0x0027:
            monitor-exit(r6)
            return r7
        L_0x0029:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.remove(java.lang.String):boolean");
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
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    public synchronized void close() throws IOException {
        if (this.initialized) {
            if (!this.closed) {
                for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                    if (entry.currentEditor != null) {
                        entry.currentEditor.abort();
                    }
                }
                trimToSize();
                this.journalWriter.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
        }
        this.closed = true;
    }

    /* access modifiers changed from: package-private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public synchronized void evictAll() throws IOException {
        initialize();
        for (Entry removeEntry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            removeEntry(removeEntry);
        }
        this.mostRecentTrimFailed = false;
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new 3(this);
    }

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

        public String key() {
            return this.key;
        }

        @Nullable
        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public Source getSource(int i) {
            return this.sources[i];
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        public void close() {
            for (Source closeQuietly : this.sources) {
                Util.closeQuietly((Closeable) closeQuietly);
            }
        }
    }
}
