package com.wx.assistants.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

public class Code {
    private static final int BASE_PADDING_LEFT = 10;
    private static final int BASE_PADDING_TOP = 15;
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final int DEFAULT_CODE_LENGTH = 4;
    private static final int DEFAULT_FONT_SIZE = 23;
    private static final int DEFAULT_HEIGHT = 45;
    private static final int DEFAULT_LINE_NUMBER = 3;
    private static final int DEFAULT_WIDTH = 100;
    private static final int RANGE_PADDING_LEFT = 15;
    private static final int RANGE_PADDING_TOP = 15;
    private static Code bmpCode;
    private int base_padding_left = 10;
    private int base_padding_top = 15;
    private String code;
    private int codeLength = 4;
    private int font_size = 23;
    private int height = 45;
    private int line_number = 3;
    private int padding_left;
    private int padding_top;
    private Random random = new Random();
    private int range_padding_left = 15;
    private int range_padding_top = 15;
    private int width = 100;

    private String createCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.codeLength; i++) {
            sb.append(CHARS[this.random.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }

    private void drawLine(Canvas canvas, Paint paint) {
        int randomColor = randomColor();
        int nextInt = this.random.nextInt(this.width);
        int nextInt2 = this.random.nextInt(this.height);
        int nextInt3 = this.random.nextInt(this.width);
        int nextInt4 = this.random.nextInt(this.height);
        paint.setStrokeWidth(1.0f);
        paint.setColor(randomColor);
        canvas.drawLine((float) nextInt, (float) nextInt2, (float) nextInt3, (float) nextInt4, paint);
    }

    public static Code getInstance() {
        if (bmpCode == null) {
            bmpCode = new Code();
        }
        return bmpCode;
    }

    private int randomColor() {
        return randomColor(1);
    }

    private int randomColor(int i) {
        return Color.rgb(this.random.nextInt(256) / i, this.random.nextInt(256) / i, this.random.nextInt(256) / i);
    }

    private void randomPadding() {
        this.padding_left += this.base_padding_left + this.random.nextInt(this.range_padding_left);
        this.padding_top = this.base_padding_top + this.random.nextInt(this.range_padding_top);
    }

    private void randomTextStyle(Paint paint) {
        paint.setColor(randomColor());
        paint.setFakeBoldText(true);
        float nextInt = (float) (this.random.nextInt(11) / 10);
        if (!this.random.nextBoolean()) {
            nextInt = -nextInt;
        }
        paint.setTextSkewX(nextInt);
    }

    public Bitmap createBitmap() {
        this.padding_left = 0;
        Bitmap createBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        this.code = createCode();
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize((float) this.font_size);
        for (int i = 0; i < this.code.length(); i++) {
            randomTextStyle(paint);
            randomPadding();
            canvas.drawText(this.code.charAt(i) + "", (float) this.padding_left, (float) this.padding_top, paint);
        }
        for (int i2 = 0; i2 < this.line_number; i2++) {
            drawLine(canvas, paint);
        }
        canvas.save();
        canvas.restore();
        return createBitmap;
    }

    public String getCode() {
        return this.code;
    }
}
