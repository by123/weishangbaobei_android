package com.googlecode.mp4parser.contentprotection;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PlayReadyHeader extends ProtectionSpecificHeader {
    public static UUID PROTECTION_SYSTEM_ID = UUID.fromString("9A04F079-9840-4286-AB92-E65BE0885F95");
    private long length;
    private List<PlayReadyRecord> records;

    public static abstract class PlayReadyRecord {
        int type;

        public static class DefaulPlayReadyRecord extends PlayReadyRecord {
            ByteBuffer value;

            public DefaulPlayReadyRecord(int i) {
                super(i);
            }

            public ByteBuffer getValue() {
                return this.value;
            }

            public void parse(ByteBuffer byteBuffer) {
                this.value = byteBuffer.duplicate();
            }
        }

        public static class EmeddedLicenseStore extends PlayReadyRecord {
            ByteBuffer value;

            public EmeddedLicenseStore() {
                super(3);
            }

            public ByteBuffer getValue() {
                return this.value;
            }

            public void parse(ByteBuffer byteBuffer) {
                this.value = byteBuffer.duplicate();
            }

            public String toString() {
                return "EmeddedLicenseStore" + "{length=" + getValue().limit() + '}';
            }
        }

        public static class RMHeader extends PlayReadyRecord {
            String header;

            public RMHeader() {
                super(1);
            }

            public String getHeader() {
                return this.header;
            }

            public ByteBuffer getValue() {
                try {
                    return ByteBuffer.wrap(this.header.getBytes("UTF-16LE"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            public void parse(ByteBuffer byteBuffer) {
                try {
                    byte[] bArr = new byte[byteBuffer.slice().limit()];
                    byteBuffer.get(bArr);
                    this.header = new String(bArr, "UTF-16LE");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            public void setHeader(String str) {
                this.header = str;
            }

            public String toString() {
                return "RMHeader" + "{length=" + getValue().limit() + ", header='" + this.header + '\'' + '}';
            }
        }

        public PlayReadyRecord(int i) {
            this.type = i;
        }

        public static List<PlayReadyRecord> createFor(ByteBuffer byteBuffer, int i) {
            PlayReadyRecord rMHeader;
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int readUInt16BE = IsoTypeReader.readUInt16BE(byteBuffer);
                int readUInt16BE2 = IsoTypeReader.readUInt16BE(byteBuffer);
                switch (readUInt16BE) {
                    case 1:
                        rMHeader = new RMHeader();
                        break;
                    case 2:
                        rMHeader = new DefaulPlayReadyRecord(2);
                        break;
                    case 3:
                        rMHeader = new EmeddedLicenseStore();
                        break;
                    default:
                        rMHeader = new DefaulPlayReadyRecord(readUInt16BE);
                        break;
                }
                PlayReadyRecord playReadyRecord = rMHeader;
                playReadyRecord.parse((ByteBuffer) byteBuffer.slice().limit(readUInt16BE2));
                byteBuffer.position(byteBuffer.position() + readUInt16BE2);
                arrayList.add(playReadyRecord);
            }
            return arrayList;
        }

        public abstract ByteBuffer getValue();

        public abstract void parse(ByteBuffer byteBuffer);

        public String toString() {
            return "PlayReadyRecord" + "{type=" + this.type + ", length=" + getValue().limit() + '}';
        }
    }

    static {
        ProtectionSpecificHeader.uuidRegistry.put(PROTECTION_SYSTEM_ID, PlayReadyHeader.class);
    }

    public ByteBuffer getData() {
        int i = 6;
        for (PlayReadyRecord value : this.records) {
            i = value.getValue().rewind().limit() + i + 4;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        IsoTypeWriter.writeUInt32BE(allocate, (long) i);
        IsoTypeWriter.writeUInt16BE(allocate, this.records.size());
        for (PlayReadyRecord next : this.records) {
            IsoTypeWriter.writeUInt16BE(allocate, next.type);
            IsoTypeWriter.writeUInt16BE(allocate, next.getValue().limit());
            allocate.put(next.getValue());
        }
        return allocate;
    }

    public List<PlayReadyRecord> getRecords() {
        return Collections.unmodifiableList(this.records);
    }

    public UUID getSystemId() {
        return PROTECTION_SYSTEM_ID;
    }

    public void parse(ByteBuffer byteBuffer) {
        this.length = IsoTypeReader.readUInt32BE(byteBuffer);
        this.records = PlayReadyRecord.createFor(byteBuffer, IsoTypeReader.readUInt16BE(byteBuffer));
    }

    public void setRecords(List<PlayReadyRecord> list) {
        this.records = list;
    }

    public String toString() {
        return "PlayReadyHeader" + "{length=" + this.length + ", recordCount=" + this.records.size() + ", records=" + this.records + '}';
    }
}
