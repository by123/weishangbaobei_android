package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.wx.assistants.utils.SPUtils;
import com.zhouyou.view.seekbar.SignSeekBar;
import java.io.PrintStream;

public class CustomSpeedLayout extends LinearLayout {
    public static int DEFAULT_SPEED = 100;
    private FUNCTION function = FUNCTION.GROUP_BATCH_AUTO;
    /* access modifiers changed from: private */
    public OnSignSeekBarListener listener;
    private Context mContext;
    private SignSeekBar signSeekBar;

    public enum FUNCTION {
        GROUP_BATCH_MANUAL,
        GROUP_BATCH_AUTO,
        PULL_FRIEND_TO_GROUP,
        PULL_TAG_FRIEND_TO_GROUP,
        CARD_FRIEND,
        COLLECTION_FRIEND,
        VOICE_FRIEND,
        LIKE_COMMENT_FRIEND,
        ONE_KEY_COPY_WXH
    }

    public interface OnSignSeekBarListener {
        void progress(int i);
    }

    public CustomSpeedLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomSpeedLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CustomSpeedLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public void setData(int i) {
        if (this.function == FUNCTION.GROUP_BATCH_AUTO) {
            try {
                SPUtils.put(this.mContext, "speed_group_batch_auto", Integer.valueOf(i));
            } catch (Exception e) {
            }
        } else if (this.function == FUNCTION.CARD_FRIEND) {
            SPUtils.put(this.mContext, "speed_card_friend", Integer.valueOf(i));
        } else if (this.function == FUNCTION.COLLECTION_FRIEND) {
            SPUtils.put(this.mContext, "speed_collection_friend", Integer.valueOf(i));
        } else if (this.function == FUNCTION.GROUP_BATCH_MANUAL) {
            SPUtils.put(this.mContext, "speed_batch_manual", Integer.valueOf(i));
        } else if (this.function == FUNCTION.LIKE_COMMENT_FRIEND) {
            SPUtils.put(this.mContext, "speed_comment_friend", Integer.valueOf(i));
        } else if (this.function == FUNCTION.ONE_KEY_COPY_WXH) {
            SPUtils.put(this.mContext, "speed_one_key_copy_wxh", Integer.valueOf(i));
        } else if (this.function == FUNCTION.PULL_FRIEND_TO_GROUP) {
            SPUtils.put(this.mContext, "speed_pull_friend_to_group", Integer.valueOf(i));
        } else if (this.function == FUNCTION.PULL_TAG_FRIEND_TO_GROUP) {
            SPUtils.put(this.mContext, "speed_pull_tag_friend_to_group", Integer.valueOf(i));
        } else if (this.function == FUNCTION.VOICE_FRIEND) {
            SPUtils.put(this.mContext, "speed_voice_friend", Integer.valueOf(i));
        }
    }

    private void setDefaultProgress(int i) {
        this.signSeekBar.setProgress((float) i);
        if (this.listener != null) {
            this.listener.progress(i);
        }
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427747, this, true);
    }

    public void initData() {
        if (this.function == FUNCTION.GROUP_BATCH_AUTO) {
            try {
                setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_group_batch_auto", Integer.valueOf(DEFAULT_SPEED))).intValue());
            } catch (Exception e) {
            }
        } else if (this.function == FUNCTION.CARD_FRIEND) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_card_friend", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.COLLECTION_FRIEND) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_collection_friend", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.GROUP_BATCH_MANUAL) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_batch_manual", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.LIKE_COMMENT_FRIEND) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_comment_friend", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.ONE_KEY_COPY_WXH) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_one_key_copy_wxh", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.PULL_FRIEND_TO_GROUP) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_pull_friend_to_group", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.PULL_TAG_FRIEND_TO_GROUP) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_pull_tag_friend_to_group", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else if (this.function == FUNCTION.VOICE_FRIEND) {
            setDefaultProgress(((Integer) SPUtils.get(this.mContext, "speed_voice_friend", Integer.valueOf(DEFAULT_SPEED))).intValue());
        } else {
            setDefaultProgress(DEFAULT_SPEED);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.signSeekBar = (SignSeekBar) findViewById(2131297382);
        setDefaultProgress(DEFAULT_SPEED);
        this.signSeekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int i, float f) {
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_PROGRESS1_" + i);
                if (CustomSpeedLayout.this.listener != null) {
                    CustomSpeedLayout.this.listener.progress(i);
                    CustomSpeedLayout.this.setData(i);
                }
            }

            public void getProgressOnFinally(SignSeekBar signSeekBar, int i, float f, boolean z) {
            }

            public void onProgressChanged(SignSeekBar signSeekBar, int i, float f, boolean z) {
            }
        });
    }

    public void setFunction(FUNCTION function2) {
        this.function = function2;
        initData();
    }

    public void setOnSignSeekBarListener(OnSignSeekBarListener onSignSeekBarListener) {
        if (onSignSeekBarListener != null) {
            this.listener = onSignSeekBarListener;
            initData();
        }
    }
}
