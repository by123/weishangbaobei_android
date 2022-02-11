package com.scwang.smartrefresh.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.SparseArray;
import com.scwang.smartrefresh.header.fungame.FunGameView;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FunGameBattleCityHeader extends FunGameView {
    protected static final int DEFAULT_BULLET_NUM_SPACING = 360;
    protected static final int DEFAULT_ENEMY_TANK_NUM_SPACING = 60;
    protected static final int DEFAULT_TANK_MAGIC_TOTAL_NUM = 8;
    protected static final float TANK_BARREL_RATIO = 0.33333334f;
    protected static int TANK_ROW_NUM = 3;
    protected int barrelSize;
    protected float bulletRadius;
    protected int bulletSpace;
    protected int bulletSpeed;
    protected SparseArray<Queue<RectF>> eTankSparseArray;
    protected int enemySpeed;
    protected int enemyTankSpace;
    protected int levelNum;
    protected Queue<Point> mBulletList;
    protected int offsetETankX;
    protected int offsetMBulletX;
    protected boolean once;
    protected int overstepNum;
    protected Random random;
    protected Point usedBullet;
    protected int wipeOutNum;

    public FunGameBattleCityHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public FunGameBattleCityHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunGameBattleCityHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.enemySpeed = 1;
        this.bulletSpeed = 4;
        this.once = true;
        this.random = new Random();
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.controllerSize = i / TANK_ROW_NUM;
        this.barrelSize = (int) Math.floor((double) ((((float) this.controllerSize) * TANK_BARREL_RATIO) + 0.5f));
        this.bulletRadius = (((float) this.barrelSize) - (this.DIVIDING_LINE_SIZE * 2.0f)) * 0.5f;
        super.onInitialized(refreshKernel, i, i2);
    }

    /* access modifiers changed from: protected */
    public void resetConfigParams() {
        this.status = 0;
        this.controllerPosition = this.DIVIDING_LINE_SIZE;
        this.enemySpeed = DensityUtil.dp2px(1.0f);
        this.bulletSpeed = DensityUtil.dp2px(4.0f);
        this.levelNum = 8;
        this.wipeOutNum = 0;
        this.once = true;
        this.enemyTankSpace = this.controllerSize + this.barrelSize + 60;
        this.bulletSpace = DEFAULT_BULLET_NUM_SPACING;
        this.eTankSparseArray = new SparseArray<>();
        for (int i = 0; i < TANK_ROW_NUM; i++) {
            this.eTankSparseArray.put(i, new LinkedList());
        }
        this.mBulletList = new LinkedList();
    }

    /* access modifiers changed from: protected */
    public RectF generateEnemyTank(int i) {
        float f = (float) (-(this.controllerSize + this.barrelSize));
        float f2 = ((float) (i * this.controllerSize)) + this.DIVIDING_LINE_SIZE;
        return new RectF(f, f2, (((float) this.barrelSize) * 2.5f) + f, ((float) this.controllerSize) + f2);
    }

    /* access modifiers changed from: protected */
    public int getTrackIndex(int i) {
        int i2 = i / (this.mHeaderHeight / TANK_ROW_NUM);
        if (i2 >= TANK_ROW_NUM) {
            i2 = TANK_ROW_NUM - 1;
        }
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public boolean checkWipeOutETank(Point point) {
        int trackIndex = getTrackIndex(point.y);
        RectF rectF = (RectF) this.eTankSparseArray.get(trackIndex).peek();
        if (rectF == null || !rectF.contains((float) point.x, (float) point.y)) {
            return false;
        }
        int i = this.wipeOutNum + 1;
        this.wipeOutNum = i;
        if (i == this.levelNum) {
            upLevel();
        }
        this.eTankSparseArray.get(trackIndex).poll();
        return true;
    }

    /* access modifiers changed from: protected */
    public void upLevel() {
        this.levelNum += 8;
        this.enemySpeed += DensityUtil.dp2px(1.0f);
        this.bulletSpeed += DensityUtil.dp2px(1.0f);
        this.wipeOutNum = 0;
        if (this.enemyTankSpace > 12) {
            this.enemyTankSpace -= 12;
        }
        if (this.bulletSpace > 30) {
            this.bulletSpace -= 30;
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkTankCrash(int i, float f, float f2) {
        RectF rectF = (RectF) this.eTankSparseArray.get(i).peek();
        return rectF != null && rectF.contains(f, f2);
    }

    /* access modifiers changed from: protected */
    public int appearanceOption() {
        return this.random.nextInt(TANK_ROW_NUM);
    }

    /* access modifiers changed from: protected */
    public void drawGame(Canvas canvas, int i, int i2) {
        drawSelfTank(canvas, i);
        if (this.status == 1 || this.status == 3 || this.status == 4) {
            drawEnemyTank(canvas, i);
            drawBulletPath(canvas, i);
        }
        if (isInEditMode()) {
            drawTank(canvas, new RectF((float) this.controllerSize, CropImageView.DEFAULT_ASPECT_RATIO, (float) (this.controllerSize * 2), (float) this.controllerSize));
            drawTank(canvas, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, (float) this.controllerSize, (float) this.controllerSize, (float) (this.controllerSize * 2)));
            drawTank(canvas, new RectF((float) (this.controllerSize * 3), (float) (this.controllerSize * 2), (float) (this.controllerSize * 4), (float) (this.controllerSize * 3)));
        }
    }

    /* access modifiers changed from: protected */
    public void drawBulletPath(Canvas canvas, int i) {
        this.mPaint.setColor(this.mModelColor);
        this.offsetMBulletX += this.bulletSpeed;
        boolean z = false;
        if (this.offsetMBulletX / this.bulletSpace == 1) {
            this.offsetMBulletX = 0;
        }
        if (this.offsetMBulletX == 0) {
            Point point = new Point();
            point.x = (i - this.controllerSize) - this.barrelSize;
            point.y = (int) (this.controllerPosition + (((float) this.controllerSize) * 0.5f));
            this.mBulletList.offer(point);
        }
        for (Point point2 : this.mBulletList) {
            if (checkWipeOutETank(point2)) {
                this.usedBullet = point2;
            } else {
                if (((float) point2.x) + this.bulletRadius <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    z = true;
                }
                drawBullet(canvas, point2);
            }
        }
        if (z) {
            this.mBulletList.poll();
        }
        this.mBulletList.remove(this.usedBullet);
        this.usedBullet = null;
    }

    /* access modifiers changed from: protected */
    public void drawBullet(Canvas canvas, Point point) {
        point.x -= this.bulletSpeed;
        canvas.drawCircle((float) point.x, (float) point.y, this.bulletRadius, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void drawSelfTank(Canvas canvas, int i) {
        this.mPaint.setColor(this.rModelColor);
        boolean checkTankCrash = checkTankCrash(getTrackIndex((int) this.controllerPosition), (float) (i - this.controllerSize), this.controllerPosition);
        boolean checkTankCrash2 = checkTankCrash(getTrackIndex((int) (this.controllerPosition + ((float) this.controllerSize))), (float) (i - this.controllerSize), this.controllerPosition + ((float) this.controllerSize));
        if (checkTankCrash || checkTankCrash2) {
            this.status = 2;
        }
        canvas.drawRect((float) (i - this.controllerSize), this.controllerPosition + this.DIVIDING_LINE_SIZE, (float) i, this.controllerPosition + ((float) this.controllerSize) + this.DIVIDING_LINE_SIZE, this.mPaint);
        Canvas canvas2 = canvas;
        canvas2.drawRect((float) ((i - this.controllerSize) - this.barrelSize), this.controllerPosition + (((float) (this.controllerSize - this.barrelSize)) * 0.5f), (float) (i - this.controllerSize), this.controllerPosition + (((float) (this.controllerSize - this.barrelSize)) * 0.5f) + ((float) this.barrelSize), this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void drawEnemyTank(Canvas canvas, int i) {
        this.mPaint.setColor(this.lModelColor);
        this.offsetETankX += this.enemySpeed;
        if (this.offsetETankX / this.enemyTankSpace == 1 || this.once) {
            this.offsetETankX = 0;
            this.once = false;
        }
        int appearanceOption = appearanceOption();
        boolean z = false;
        for (int i2 = 0; i2 < TANK_ROW_NUM; i2++) {
            Queue queue = this.eTankSparseArray.get(i2);
            if (this.offsetETankX == 0 && i2 == appearanceOption) {
                queue.offer(generateEnemyTank(i2));
            }
            Iterator it = queue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RectF rectF = (RectF) it.next();
                if (rectF.left >= ((float) i)) {
                    int i3 = this.overstepNum + 1;
                    this.overstepNum = i3;
                    if (i3 >= 8) {
                        this.status = 2;
                        z = true;
                        break;
                    }
                    z = true;
                } else {
                    drawTank(canvas, rectF);
                }
            }
            if (this.status == 2) {
                break;
            }
            if (z) {
                queue.poll();
                z = false;
            }
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void drawTank(Canvas canvas, RectF rectF) {
        rectF.set(rectF.left + ((float) this.enemySpeed), rectF.top, rectF.right + ((float) this.enemySpeed), rectF.bottom);
        canvas.drawRect(rectF, this.mPaint);
        float f = rectF.top + (((float) (this.controllerSize - this.barrelSize)) * 0.5f);
        canvas.drawRect(rectF.right, f, rectF.right + ((float) this.barrelSize), f + ((float) this.barrelSize), this.mPaint);
    }
}
