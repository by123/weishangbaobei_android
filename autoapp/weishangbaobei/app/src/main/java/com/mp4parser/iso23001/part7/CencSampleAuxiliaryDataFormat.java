package com.mp4parser.iso23001.part7;

import com.coremedia.iso.Hex;
import java.math.BigInteger;
import java.util.Arrays;

public class CencSampleAuxiliaryDataFormat {
    public byte[] iv = new byte[0];
    public Pair[] pairs = null;

    public interface Pair {
        int clear();

        long encrypted();
    }

    public int getSize() {
        int length = this.iv.length;
        return (this.pairs == null || this.pairs.length <= 0) ? length : length + 2 + (this.pairs.length * 6);
    }

    public Pair createPair(int i, long j) {
        if (i <= 127) {
            if (j <= 127) {
                return new ByteBytePair(i, j);
            }
            if (j <= 32767) {
                return new ByteShortPair(i, j);
            }
            if (j <= 2147483647L) {
                return new ByteIntPair(i, j);
            }
            return new ByteLongPair(i, j);
        } else if (i <= 32767) {
            if (j <= 127) {
                return new ShortBytePair(i, j);
            }
            if (j <= 32767) {
                return new ShortShortPair(i, j);
            }
            if (j <= 2147483647L) {
                return new ShortIntPair(i, j);
            }
            return new ShortLongPair(i, j);
        } else if (j <= 127) {
            return new IntBytePair(i, j);
        } else {
            if (j <= 32767) {
                return new IntShortPair(i, j);
            }
            if (j <= 2147483647L) {
                return new IntIntPair(i, j);
            }
            return new IntLongPair(i, j);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = (CencSampleAuxiliaryDataFormat) obj;
        if (!new BigInteger(this.iv).equals(new BigInteger(cencSampleAuxiliaryDataFormat.iv))) {
            return false;
        }
        return this.pairs == null ? cencSampleAuxiliaryDataFormat.pairs == null : Arrays.equals(this.pairs, cencSampleAuxiliaryDataFormat.pairs);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.iv != null ? Arrays.hashCode(this.iv) : 0) * 31;
        if (this.pairs != null) {
            i = Arrays.hashCode(this.pairs);
        }
        return hashCode + i;
    }

    public String toString() {
        return "Entry{iv=" + Hex.encodeHex(this.iv) + ", pairs=" + Arrays.toString(this.pairs) + '}';
    }

    private class ByteBytePair extends AbstractPair {
        private byte clear;
        private byte encrypted;

        public ByteBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (byte) i;
            this.encrypted = (byte) ((int) j);
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class ByteShortPair extends AbstractPair {
        private byte clear;
        private short encrypted;

        public ByteShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (byte) i;
            this.encrypted = (short) ((int) j);
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class ByteIntPair extends AbstractPair {
        private byte clear;
        private int encrypted;

        public ByteIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (byte) i;
            this.encrypted = (int) j;
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class ByteLongPair extends AbstractPair {
        private byte clear;
        private long encrypted;

        public ByteLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (byte) i;
            this.encrypted = j;
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return this.encrypted;
        }
    }

    private class ShortBytePair extends AbstractPair {
        private short clear;
        private byte encrypted;

        public ShortBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (short) i;
            this.encrypted = (byte) ((int) j);
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class ShortShortPair extends AbstractPair {
        private short clear;
        private short encrypted;

        public ShortShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (short) i;
            this.encrypted = (short) ((int) j);
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class ShortIntPair extends AbstractPair {
        private short clear;
        private int encrypted;

        public ShortIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (short) i;
            this.encrypted = (int) j;
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class ShortLongPair extends AbstractPair {
        private short clear;
        private long encrypted;

        public ShortLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = (short) i;
            this.encrypted = j;
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return this.encrypted;
        }
    }

    private class IntBytePair extends AbstractPair {
        private int clear;
        private byte encrypted;

        public IntBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = i;
            this.encrypted = (byte) ((int) j);
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class IntShortPair extends AbstractPair {
        private int clear;
        private short encrypted;

        public IntShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = i;
            this.encrypted = (short) ((int) j);
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class IntIntPair extends AbstractPair {
        private int clear;
        private int encrypted;

        public IntIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = i;
            this.encrypted = (int) j;
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return (long) this.encrypted;
        }
    }

    private class IntLongPair extends AbstractPair {
        private int clear;
        private long encrypted;

        public IntLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, (AbstractPair) null);
            this.clear = i;
            this.encrypted = j;
        }

        public int clear() {
            return this.clear;
        }

        public long encrypted() {
            return this.encrypted;
        }
    }

    private abstract class AbstractPair implements Pair {
        private AbstractPair() {
        }

        /* synthetic */ AbstractPair(CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, AbstractPair abstractPair) {
            this();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return clear() == pair.clear() && encrypted() == pair.encrypted();
        }

        public String toString() {
            return "P(" + clear() + "|" + encrypted() + ")";
        }
    }
}
