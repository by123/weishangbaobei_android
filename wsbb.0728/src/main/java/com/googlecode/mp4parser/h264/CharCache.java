package com.googlecode.mp4parser.h264;

public class CharCache {
    private char[] cache;
    private int pos;

    public CharCache(int i) {
        this.cache = new char[i];
    }

    public void append(char c) {
        if (this.pos < this.cache.length - 1) {
            this.cache[this.pos] = c;
            this.pos++;
        }
    }

    public void append(String str) {
        char[] charArray = str.toCharArray();
        int length = this.cache.length - this.pos;
        if (charArray.length < length) {
            length = charArray.length;
        }
        System.arraycopy(charArray, 0, this.cache, this.pos, length);
        this.pos = length + this.pos;
    }

    public void clear() {
        this.pos = 0;
    }

    public int length() {
        return this.pos;
    }

    public String toString() {
        return new String(this.cache, 0, this.pos);
    }
}
