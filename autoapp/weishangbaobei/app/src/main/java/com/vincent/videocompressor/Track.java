package com.vincent.videocompressor;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import com.coremedia.iso.boxes.AbstractMediaHeaderBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SoundMediaHeaderBox;
import com.coremedia.iso.boxes.VideoMediaHeaderBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import com.mp4parser.iso14496.part15.AvcConfigurationBox;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@TargetApi(16)
public class Track {
    private static Map<Integer, Integer> samplingFrequencyIndexMap = new HashMap();
    private Date creationTime = new Date();
    private long duration = 0;
    private boolean first = true;
    private String handler;
    private AbstractMediaHeaderBox headerBox = null;
    private int height;
    private boolean isAudio = false;
    private long lastPresentationTimeUs = 0;
    private SampleDescriptionBox sampleDescriptionBox = null;
    private ArrayList<Long> sampleDurations = new ArrayList<>();
    private ArrayList<Sample> samples = new ArrayList<>();
    private LinkedList<Integer> syncSamples = null;
    private int timeScale;
    private long trackId = 0;
    private float volume = CropImageView.DEFAULT_ASPECT_RATIO;
    private int width;

    static {
        samplingFrequencyIndexMap.put(96000, 0);
        samplingFrequencyIndexMap.put(88200, 1);
        samplingFrequencyIndexMap.put(64000, 2);
        samplingFrequencyIndexMap.put(48000, 3);
        samplingFrequencyIndexMap.put(44100, 4);
        samplingFrequencyIndexMap.put(32000, 5);
        samplingFrequencyIndexMap.put(24000, 6);
        samplingFrequencyIndexMap.put(22050, 7);
        samplingFrequencyIndexMap.put(16000, 8);
        samplingFrequencyIndexMap.put(12000, 9);
        samplingFrequencyIndexMap.put(11025, 10);
        samplingFrequencyIndexMap.put(8000, 11);
    }

    public Track(int i, MediaFormat mediaFormat, boolean z) throws Exception {
        this.trackId = (long) i;
        if (!z) {
            this.sampleDurations.add(3015L);
            this.duration = 3015;
            this.width = mediaFormat.getInteger(SocializeProtocolConstants.WIDTH);
            this.height = mediaFormat.getInteger(SocializeProtocolConstants.HEIGHT);
            this.timeScale = 90000;
            this.syncSamples = new LinkedList<>();
            this.handler = "vide";
            this.headerBox = new VideoMediaHeaderBox();
            this.sampleDescriptionBox = new SampleDescriptionBox();
            String string = mediaFormat.getString("mime");
            if (string.equals(VideoController.MIME_TYPE)) {
                VisualSampleEntry visualSampleEntry = new VisualSampleEntry("avc1");
                visualSampleEntry.setDataReferenceIndex(1);
                visualSampleEntry.setDepth(24);
                visualSampleEntry.setFrameCount(1);
                visualSampleEntry.setHorizresolution(72.0d);
                visualSampleEntry.setVertresolution(72.0d);
                visualSampleEntry.setWidth(this.width);
                visualSampleEntry.setHeight(this.height);
                AvcConfigurationBox avcConfigurationBox = new AvcConfigurationBox();
                if (mediaFormat.getByteBuffer("csd-0") != null) {
                    ArrayList arrayList = new ArrayList();
                    ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-0");
                    byteBuffer.position(4);
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    arrayList.add(bArr);
                    ArrayList arrayList2 = new ArrayList();
                    ByteBuffer byteBuffer2 = mediaFormat.getByteBuffer("csd-1");
                    byteBuffer2.position(4);
                    byte[] bArr2 = new byte[byteBuffer2.remaining()];
                    byteBuffer2.get(bArr2);
                    arrayList2.add(bArr2);
                    avcConfigurationBox.setSequenceParameterSets(arrayList);
                    avcConfigurationBox.setPictureParameterSets(arrayList2);
                }
                avcConfigurationBox.setAvcLevelIndication(13);
                avcConfigurationBox.setAvcProfileIndication(100);
                avcConfigurationBox.setBitDepthLumaMinus8(-1);
                avcConfigurationBox.setBitDepthChromaMinus8(-1);
                avcConfigurationBox.setChromaFormat(-1);
                avcConfigurationBox.setConfigurationVersion(1);
                avcConfigurationBox.setLengthSizeMinusOne(3);
                avcConfigurationBox.setProfileCompatibility(0);
                visualSampleEntry.addBox(avcConfigurationBox);
                this.sampleDescriptionBox.addBox(visualSampleEntry);
            } else if (string.equals("video/mp4v")) {
                VisualSampleEntry visualSampleEntry2 = new VisualSampleEntry("mp4v");
                visualSampleEntry2.setDataReferenceIndex(1);
                visualSampleEntry2.setDepth(24);
                visualSampleEntry2.setFrameCount(1);
                visualSampleEntry2.setHorizresolution(72.0d);
                visualSampleEntry2.setVertresolution(72.0d);
                visualSampleEntry2.setWidth(this.width);
                visualSampleEntry2.setHeight(this.height);
                this.sampleDescriptionBox.addBox(visualSampleEntry2);
            }
        } else {
            this.sampleDurations.add(Long.valueOf(ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS));
            this.duration = ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS;
            this.volume = 1.0f;
            this.timeScale = mediaFormat.getInteger("sample-rate");
            this.handler = "soun";
            this.headerBox = new SoundMediaHeaderBox();
            this.sampleDescriptionBox = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry("mp4a");
            audioSampleEntry.setChannelCount(mediaFormat.getInteger("channel-count"));
            audioSampleEntry.setSampleRate((long) mediaFormat.getInteger("sample-rate"));
            audioSampleEntry.setDataReferenceIndex(1);
            audioSampleEntry.setSampleSize(16);
            ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
            ESDescriptor eSDescriptor = new ESDescriptor();
            eSDescriptor.setEsId(0);
            SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
            sLConfigDescriptor.setPredefined(2);
            eSDescriptor.setSlConfigDescriptor(sLConfigDescriptor);
            DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
            decoderConfigDescriptor.setObjectTypeIndication(64);
            decoderConfigDescriptor.setStreamType(5);
            decoderConfigDescriptor.setBufferSizeDB(1536);
            decoderConfigDescriptor.setMaxBitRate(96000);
            decoderConfigDescriptor.setAvgBitRate(96000);
            AudioSpecificConfig audioSpecificConfig = new AudioSpecificConfig();
            audioSpecificConfig.setAudioObjectType(2);
            audioSpecificConfig.setSamplingFrequencyIndex(samplingFrequencyIndexMap.get(Integer.valueOf((int) audioSampleEntry.getSampleRate())).intValue());
            audioSpecificConfig.setChannelConfiguration(audioSampleEntry.getChannelCount());
            decoderConfigDescriptor.setAudioSpecificInfo(audioSpecificConfig);
            eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
            ByteBuffer serialize = eSDescriptor.serialize();
            eSDescriptorBox.setEsDescriptor(eSDescriptor);
            eSDescriptorBox.setData(serialize);
            audioSampleEntry.addBox(eSDescriptorBox);
            this.sampleDescriptionBox.addBox(audioSampleEntry);
        }
    }

    public long getTrackId() {
        return this.trackId;
    }

    public void addSample(long j, MediaCodec.BufferInfo bufferInfo) {
        boolean z = !this.isAudio && (bufferInfo.flags & 1) != 0;
        this.samples.add(new Sample(j, (long) bufferInfo.size));
        if (this.syncSamples != null && z) {
            this.syncSamples.add(Integer.valueOf(this.samples.size()));
        }
        long j2 = bufferInfo.presentationTimeUs - this.lastPresentationTimeUs;
        this.lastPresentationTimeUs = bufferInfo.presentationTimeUs;
        long j3 = ((j2 * ((long) this.timeScale)) + 500000) / 1000000;
        if (!this.first) {
            this.sampleDurations.add(this.sampleDurations.size() - 1, Long.valueOf(j3));
            this.duration += j3;
        }
        this.first = false;
    }

    public ArrayList<Sample> getSamples() {
        return this.samples;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getHandler() {
        return this.handler;
    }

    public AbstractMediaHeaderBox getMediaHeaderBox() {
        return this.headerBox;
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    public long[] getSyncSamples() {
        if (this.syncSamples == null || this.syncSamples.isEmpty()) {
            return null;
        }
        long[] jArr = new long[this.syncSamples.size()];
        for (int i = 0; i < this.syncSamples.size(); i++) {
            jArr[i] = (long) this.syncSamples.get(i).intValue();
        }
        return jArr;
    }

    public int getTimeScale() {
        return this.timeScale;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public float getVolume() {
        return this.volume;
    }

    public ArrayList<Long> getSampleDurations() {
        return this.sampleDurations;
    }

    public boolean isAudio() {
        return this.isAudio;
    }
}
