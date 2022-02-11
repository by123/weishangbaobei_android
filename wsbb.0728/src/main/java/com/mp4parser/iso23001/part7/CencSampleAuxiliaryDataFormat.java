package com.mp4parser.iso23001.part7;

import com.coremedia.iso.Hex;
import java.math.BigInteger;
import java.util.Arrays;

public class CencSampleAuxiliaryDataFormat {
    public byte[] iv = new byte[0];
    public Pair[] pairs = null;

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
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            if (clear() != pair.clear()) {
                return false;
            }
            return encrypted() == pair.encrypted();
        }

        public String toString() {
            return "P(" + clear() + "|" + encrypted() + ")";
        }
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

    public interface Pair {
        int clear();

        long encrypted();
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

    public Pair createPair(int i, long j) {
        return i <= 127 ? j <= 127 ? new ByteBytePair(i, j) : j <= 32767 ? new ByteShortPair(i, j) : j <= 2147483647L ? new ByteIntPair(i, j) : new ByteLongPair(i, j) : i <= 32767 ? j <= 127 ? new ShortBytePair(i, j) : j <= 32767 ? new ShortShortPair(i, j) : j <= 2147483647L ? new ShortIntPair(i, j) : new ShortLongPair(i, j) : j <= 127 ? new IntBytePair(i, j) : j <= 32767 ? new IntShortPair(i, j) : j <= 2147483647L ? new IntIntPair(i, j) : new IntLongPair(i, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = (CencSampleAuxiliaryDataFormat) obj;
        if (!new BigInteger(this.iv).equals(new BigInteger(cencSampleAuxiliaryDataFormat.iv))) {
            return false;
        }
        return this.pairs != null ? Arrays.equals(this.pairs, cencSampleAuxiliaryDataFormat.pairs) : cencSampleAuxiliaryDataFormat.pairs == null;
    }

    public int getSize() {
        int length = this.iv.length;
        return (this.pairs == null || this.pairs.length <= 0) ? length : length + 2 + (this.pairs.length * 6);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = this.iv != null ? Arrays.hashCode(this.iv) : 0;
        if (this.pairs != null) {
            i = Arrays.hashCode(this.pairs);
        }
        return (hashCode * 31) + i;
    }

    public String toString() {
        return "Entry{iv=" + Hex.encodeHex(this.iv) + ", pairs=" + Arrays.toString(this.pairs) + '}';
    }
}
