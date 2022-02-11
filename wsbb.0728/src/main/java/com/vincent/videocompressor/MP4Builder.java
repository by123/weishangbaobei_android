package com.vincent.videocompressor;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.DataEntryUrlBox;
import com.coremedia.iso.boxes.DataInformationBox;
import com.coremedia.iso.boxes.DataReferenceBox;
import com.coremedia.iso.boxes.FileTypeBox;
import com.coremedia.iso.boxes.HandlerBox;
import com.coremedia.iso.boxes.MediaBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MediaInformationBox;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SampleToChunkBox;
import com.coremedia.iso.boxes.StaticChunkOffsetBox;
import com.coremedia.iso.boxes.SyncSampleBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.Matrix;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

@TargetApi(16)
public class MP4Builder {
    private Mp4Movie currentMp4Movie = null;
    private long dataOffset = 0;
    private FileChannel fc = null;
    private FileOutputStream fos = null;
    private InterleaveChunkMdat mdat = null;
    private ByteBuffer sizeBuffer = null;
    private HashMap<Track, long[]> track2SampleSizes = new HashMap<>();
    private boolean writeNewMdat = true;
    private long writedSinceLastMdat = 0;

    private class InterleaveChunkMdat implements Box {
        private long contentSize;
        private long dataOffset;
        private Container parent;

        private InterleaveChunkMdat() {
            this.contentSize = 1073741824;
            this.dataOffset = 0;
        }

        private boolean isSmallBox(long j) {
            return 8 + j < 4294967296L;
        }

        public void getBox(WritableByteChannel writableByteChannel) throws IOException {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            long size = getSize();
            if (isSmallBox(size)) {
                IsoTypeWriter.writeUInt32(allocate, size);
            } else {
                IsoTypeWriter.writeUInt32(allocate, 1);
            }
            allocate.put(IsoFile.fourCCtoBytes("mdat"));
            if (isSmallBox(size)) {
                allocate.put(new byte[8]);
            } else {
                IsoTypeWriter.writeUInt64(allocate, size);
            }
            allocate.rewind();
            writableByteChannel.write(allocate);
        }

        public long getContentSize() {
            return this.contentSize;
        }

        public long getOffset() {
            return this.dataOffset;
        }

        public Container getParent() {
            return this.parent;
        }

        public long getSize() {
            return this.contentSize + 16;
        }

        public String getType() {
            return "mdat";
        }

        public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        }

        public void setContentSize(long j) {
            this.contentSize = j;
        }

        public void setDataOffset(long j) {
            this.dataOffset = j;
        }

        public void setParent(Container container) {
            this.parent = container;
        }
    }

    private void flushCurrentMdat() throws Exception {
        long position = this.fc.position();
        this.fc.position(this.mdat.getOffset());
        this.mdat.getBox(this.fc);
        this.fc.position(position);
        this.mdat.setDataOffset(0);
        this.mdat.setContentSize(0);
        this.fos.flush();
    }

    public static long gcd(long j, long j2) {
        return j2 == 0 ? j : gcd(j2, j % j2);
    }

    public int addTrack(MediaFormat mediaFormat, boolean z) throws Exception {
        return this.currentMp4Movie.addTrack(mediaFormat, z);
    }

    /* access modifiers changed from: protected */
    public FileTypeBox createFileTypeBox() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("isom");
        linkedList.add("3gp4");
        return new FileTypeBox("isom", 0, linkedList);
    }

    public MP4Builder createMovie(Mp4Movie mp4Movie) throws Exception {
        this.currentMp4Movie = mp4Movie;
        this.fos = new FileOutputStream(mp4Movie.getCacheFile());
        this.fc = this.fos.getChannel();
        FileTypeBox createFileTypeBox = createFileTypeBox();
        createFileTypeBox.getBox(this.fc);
        this.dataOffset = createFileTypeBox.getSize() + this.dataOffset;
        this.writedSinceLastMdat += this.dataOffset;
        this.mdat = new InterleaveChunkMdat();
        this.sizeBuffer = ByteBuffer.allocateDirect(4);
        return this;
    }

    /* access modifiers changed from: protected */
    public MovieBox createMovieBox(Mp4Movie mp4Movie) {
        MovieBox movieBox = new MovieBox();
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setCreationTime(new Date());
        movieHeaderBox.setModificationTime(new Date());
        movieHeaderBox.setMatrix(Matrix.ROTATE_0);
        long timescale = getTimescale(mp4Movie);
        Iterator<Track> it = mp4Movie.getTracks().iterator();
        long j = 0;
        while (it.hasNext()) {
            Track next = it.next();
            long duration = (next.getDuration() * timescale) / ((long) next.getTimeScale());
            if (duration > j) {
                j = duration;
            }
        }
        movieHeaderBox.setDuration(j);
        movieHeaderBox.setTimescale(timescale);
        movieHeaderBox.setNextTrackId((long) (mp4Movie.getTracks().size() + 1));
        movieBox.addBox(movieHeaderBox);
        Iterator<Track> it2 = mp4Movie.getTracks().iterator();
        while (it2.hasNext()) {
            movieBox.addBox(createTrackBox(it2.next(), mp4Movie));
        }
        return movieBox;
    }

    /* access modifiers changed from: protected */
    public Box createStbl(Track track) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        createStsd(track, sampleTableBox);
        createStts(track, sampleTableBox);
        createStss(track, sampleTableBox);
        createStsc(track, sampleTableBox);
        createStsz(track, sampleTableBox);
        createStco(track, sampleTableBox);
        return sampleTableBox;
    }

    /* access modifiers changed from: protected */
    public void createStco(Track track, SampleTableBox sampleTableBox) {
        ArrayList arrayList = new ArrayList();
        Iterator<Sample> it = track.getSamples().iterator();
        long j = -1;
        while (it.hasNext()) {
            Sample next = it.next();
            long offset = next.getOffset();
            if (!(j == -1 || j == offset)) {
                j = -1;
            }
            if (j == -1) {
                arrayList.add(Long.valueOf(offset));
            }
            j = next.getSize() + offset;
        }
        long[] jArr = new long[arrayList.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < arrayList.size()) {
                jArr[i2] = ((Long) arrayList.get(i2)).longValue();
                i = i2 + 1;
            } else {
                StaticChunkOffsetBox staticChunkOffsetBox = new StaticChunkOffsetBox();
                staticChunkOffsetBox.setChunkOffsets(jArr);
                sampleTableBox.addBox(staticChunkOffsetBox);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void createStsc(Track track, SampleTableBox sampleTableBox) {
        int i;
        int i2;
        SampleToChunkBox sampleToChunkBox = new SampleToChunkBox();
        sampleToChunkBox.setEntries(new LinkedList());
        int size = track.getSamples().size();
        int i3 = 0;
        int i4 = -1;
        int i5 = 1;
        int i6 = 0;
        while (i6 < size) {
            Sample sample = track.getSamples().get(i6);
            i3++;
            if (i6 == size + -1 || sample.getOffset() + sample.getSize() != track.getSamples().get(i6 + 1).getOffset()) {
                if (i4 != i3) {
                    sampleToChunkBox.getEntries().add(new SampleToChunkBox.Entry((long) i5, (long) i3, 1));
                    i = i3;
                } else {
                    i = i4;
                }
                i3 = 0;
                i2 = i5 + 1;
            } else {
                i = i4;
                i2 = i5;
            }
            i5 = i2;
            i6++;
            i4 = i;
        }
        sampleTableBox.addBox(sampleToChunkBox);
    }

    /* access modifiers changed from: protected */
    public void createStsd(Track track, SampleTableBox sampleTableBox) {
        sampleTableBox.addBox(track.getSampleDescriptionBox());
    }

    /* access modifiers changed from: protected */
    public void createStss(Track track, SampleTableBox sampleTableBox) {
        long[] syncSamples = track.getSyncSamples();
        if (syncSamples != null && syncSamples.length > 0) {
            SyncSampleBox syncSampleBox = new SyncSampleBox();
            syncSampleBox.setSampleNumber(syncSamples);
            sampleTableBox.addBox(syncSampleBox);
        }
    }

    /* access modifiers changed from: protected */
    public void createStsz(Track track, SampleTableBox sampleTableBox) {
        SampleSizeBox sampleSizeBox = new SampleSizeBox();
        sampleSizeBox.setSampleSizes(this.track2SampleSizes.get(track));
        sampleTableBox.addBox(sampleSizeBox);
    }

    /* access modifiers changed from: protected */
    public void createStts(Track track, SampleTableBox sampleTableBox) {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = track.getSampleDurations().iterator();
        TimeToSampleBox.Entry entry = null;
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            if (entry == null || entry.getDelta() != longValue) {
                TimeToSampleBox.Entry entry2 = new TimeToSampleBox.Entry(1, longValue);
                arrayList.add(entry2);
                entry = entry2;
            } else {
                entry.setCount(entry.getCount() + 1);
            }
        }
        TimeToSampleBox timeToSampleBox = new TimeToSampleBox();
        timeToSampleBox.setEntries(arrayList);
        sampleTableBox.addBox(timeToSampleBox);
    }

    /* access modifiers changed from: protected */
    public TrackBox createTrackBox(Track track, Mp4Movie mp4Movie) {
        TrackBox trackBox = new TrackBox();
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.setEnabled(true);
        trackHeaderBox.setInMovie(true);
        trackHeaderBox.setInPreview(true);
        if (track.isAudio()) {
            trackHeaderBox.setMatrix(Matrix.ROTATE_0);
        } else {
            trackHeaderBox.setMatrix(mp4Movie.getMatrix());
        }
        trackHeaderBox.setAlternateGroup(0);
        trackHeaderBox.setCreationTime(track.getCreationTime());
        trackHeaderBox.setDuration((track.getDuration() * getTimescale(mp4Movie)) / ((long) track.getTimeScale()));
        trackHeaderBox.setHeight((double) track.getHeight());
        trackHeaderBox.setWidth((double) track.getWidth());
        trackHeaderBox.setLayer(0);
        trackHeaderBox.setModificationTime(new Date());
        trackHeaderBox.setTrackId(track.getTrackId() + 1);
        trackHeaderBox.setVolume(track.getVolume());
        trackBox.addBox(trackHeaderBox);
        MediaBox mediaBox = new MediaBox();
        trackBox.addBox(mediaBox);
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(track.getCreationTime());
        mediaHeaderBox.setDuration(track.getDuration());
        mediaHeaderBox.setTimescale((long) track.getTimeScale());
        mediaHeaderBox.setLanguage("eng");
        mediaBox.addBox(mediaHeaderBox);
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.setName(track.isAudio() ? "SoundHandle" : "VideoHandle");
        handlerBox.setHandlerType(track.getHandler());
        mediaBox.addBox(handlerBox);
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        mediaInformationBox.addBox(track.getMediaHeaderBox());
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.addBox(dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.setFlags(1);
        dataReferenceBox.addBox(dataEntryUrlBox);
        mediaInformationBox.addBox(dataInformationBox);
        mediaInformationBox.addBox(createStbl(track));
        mediaBox.addBox(mediaInformationBox);
        return trackBox;
    }

    public void finishMovie(boolean z) throws Exception {
        if (this.mdat.getContentSize() != 0) {
            flushCurrentMdat();
        }
        Iterator<Track> it = this.currentMp4Movie.getTracks().iterator();
        while (it.hasNext()) {
            Track next = it.next();
            ArrayList<Sample> samples = next.getSamples();
            long[] jArr = new long[samples.size()];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= jArr.length) {
                    break;
                }
                jArr[i2] = samples.get(i2).getSize();
                i = i2 + 1;
            }
            this.track2SampleSizes.put(next, jArr);
        }
        createMovieBox(this.currentMp4Movie).getBox(this.fc);
        this.fos.flush();
        this.fc.close();
        this.fos.close();
    }

    public long getTimescale(Mp4Movie mp4Movie) {
        long timeScale = !mp4Movie.getTracks().isEmpty() ? (long) mp4Movie.getTracks().iterator().next().getTimeScale() : 0;
        Iterator<Track> it = mp4Movie.getTracks().iterator();
        while (true) {
            long j = timeScale;
            if (!it.hasNext()) {
                return j;
            }
            timeScale = gcd((long) it.next().getTimeScale(), j);
        }
    }

    public boolean writeSampleData(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, boolean z) throws Exception {
        boolean z2 = true;
        if (this.writeNewMdat) {
            this.mdat.setContentSize(0);
            this.mdat.getBox(this.fc);
            this.mdat.setDataOffset(this.dataOffset);
            this.dataOffset += 16;
            this.writedSinceLastMdat += 16;
            this.writeNewMdat = false;
        }
        this.mdat.setContentSize(this.mdat.getContentSize() + ((long) bufferInfo.size));
        this.writedSinceLastMdat += (long) bufferInfo.size;
        if (this.writedSinceLastMdat >= 32768) {
            flushCurrentMdat();
            this.writeNewMdat = true;
            this.writedSinceLastMdat -= 32768;
        } else {
            z2 = false;
        }
        this.currentMp4Movie.addSample(i, this.dataOffset, bufferInfo);
        byteBuffer.position((z ? 0 : 4) + bufferInfo.offset);
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        if (!z) {
            this.sizeBuffer.position(0);
            this.sizeBuffer.putInt(bufferInfo.size - 4);
            this.sizeBuffer.position(0);
            this.fc.write(this.sizeBuffer);
        }
        this.fc.write(byteBuffer);
        this.dataOffset += (long) bufferInfo.size;
        if (z2) {
            this.fos.flush();
        }
        return z2;
    }
}
