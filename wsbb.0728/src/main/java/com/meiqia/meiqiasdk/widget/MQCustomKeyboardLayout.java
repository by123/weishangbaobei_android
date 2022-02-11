package com.meiqia.meiqiasdk.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.util.MQEmotionUtil;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQEmotionKeyboardLayout;
import com.meiqia.meiqiasdk.widget.MQRecorderKeyboardLayout;

public class MQCustomKeyboardLayout extends MQBaseCustomCompositeView {
    private static final int WHAT_CHANGE_TO_EMOTION_KEYBOARD = 2;
    private static final int WHAT_CHANGE_TO_VOICE_KEYBOARD = 3;
    private static final int WHAT_SCROLL_CONTENT_TO_BOTTOM = 1;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public Callback mCallback;
    /* access modifiers changed from: private */
    public EditText mContentEt;
    private MQEmotionKeyboardLayout mEmotionKeyboardLayout;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    MQCustomKeyboardLayout.this.mCallback.scrollContentToBottom();
                    return;
                case 2:
                    MQCustomKeyboardLayout.this.showEmotionKeyboard();
                    return;
                case 3:
                    MQCustomKeyboardLayout.this.showVoiceKeyboard();
                    return;
                default:
                    return;
            }
        }
    };
    private MQRecorderKeyboardLayout mRecorderKeyboardLayout;

    public interface Callback {
        void onAudioRecorderFinish(int i, String str);

        void onAudioRecorderNoPermission();

        void onAudioRecorderTooShort();

        void scrollContentToBottom();
    }

    public MQCustomKeyboardLayout(Context context) {
        super(context);
    }

    public MQCustomKeyboardLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MQCustomKeyboardLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: private */
    public void sendScrollContentToBottomMsg() {
        this.mHandler.sendEmptyMessageDelayed(1, 300);
    }

    /* access modifiers changed from: private */
    public void showEmotionKeyboard() {
        this.mEmotionKeyboardLayout.setVisibility(0);
        sendScrollContentToBottomMsg();
        closeVoiceKeyboard();
    }

    /* access modifiers changed from: private */
    public void showVoiceKeyboard() {
        this.mRecorderKeyboardLayout.setVisibility(0);
        sendScrollContentToBottomMsg();
        closeEmotionKeyboard();
    }

    public void changeToEmotionKeyboard() {
        if (!this.mContentEt.isFocused()) {
            this.mContentEt.requestFocus();
            this.mContentEt.setSelection(this.mContentEt.getText().toString().length());
        }
        MQUtils.closeKeyboard(this.mActivity);
        if (isCustomKeyboardVisible()) {
            showEmotionKeyboard();
        } else {
            this.mHandler.sendEmptyMessageDelayed(2, 300);
        }
    }

    public void changeToOriginalKeyboard() {
        closeCustomKeyboard();
        MQUtils.openKeyboard(this.mContentEt);
        this.mHandler.sendEmptyMessageDelayed(1, 600);
    }

    public void changeToVoiceKeyboard() {
        MQUtils.closeKeyboard(this.mActivity);
        if (isCustomKeyboardVisible()) {
            showVoiceKeyboard();
        } else {
            this.mHandler.sendEmptyMessageDelayed(3, 300);
        }
    }

    public void closeAllKeyboard() {
        closeCustomKeyboard();
        MQUtils.closeKeyboard(this.mActivity);
    }

    public void closeCustomKeyboard() {
        closeEmotionKeyboard();
        closeVoiceKeyboard();
    }

    public void closeEmotionKeyboard() {
        this.mEmotionKeyboardLayout.setVisibility(8);
    }

    public void closeVoiceKeyboard() {
        this.mRecorderKeyboardLayout.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public int[] getAttrs() {
        return new int[0];
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_layout_custom_keyboard;
    }

    /* access modifiers changed from: protected */
    public <VT extends View> VT getViewById(@IdRes int i) {
        return findViewById(i);
    }

    public void init(Activity activity, EditText editText, Callback callback) {
        if (activity == null || editText == null || callback == null) {
            throw new RuntimeException(MQCustomKeyboardLayout.class.getSimpleName() + "的init方法的参数均不能为null");
        }
        this.mActivity = activity;
        this.mContentEt = editText;
        this.mCallback = callback;
        this.mContentEt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MQCustomKeyboardLayout.this.isCustomKeyboardVisible()) {
                    MQCustomKeyboardLayout.this.closeCustomKeyboard();
                }
                MQCustomKeyboardLayout.this.sendScrollContentToBottomMsg();
            }
        });
        this.mContentEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    MQCustomKeyboardLayout.this.closeAllKeyboard();
                } else {
                    MQCustomKeyboardLayout.this.sendScrollContentToBottomMsg();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initAttr(int i, TypedArray typedArray) {
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mEmotionKeyboardLayout = (MQEmotionKeyboardLayout) getViewById(R.id.emotionKeyboardLayout);
        this.mRecorderKeyboardLayout = (MQRecorderKeyboardLayout) getViewById(R.id.recorderKeyboardLayout);
    }

    public boolean isCustomKeyboardVisible() {
        return isEmotionKeyboardVisible() || isVoiceKeyboardVisible();
    }

    public boolean isEmotionKeyboardVisible() {
        return this.mEmotionKeyboardLayout.getVisibility() == 0;
    }

    public boolean isRecording() {
        return this.mRecorderKeyboardLayout.isRecording();
    }

    public boolean isVoiceKeyboardVisible() {
        return this.mRecorderKeyboardLayout.getVisibility() == 0;
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mEmotionKeyboardLayout.setCallback(new MQEmotionKeyboardLayout.Callback() {
            public void onDelete() {
                MQCustomKeyboardLayout.this.mContentEt.dispatchKeyEvent(new KeyEvent(0, 67));
            }

            public void onInsert(String str) {
                int selectionStart = MQCustomKeyboardLayout.this.mContentEt.getSelectionStart();
                StringBuilder sb = new StringBuilder(MQCustomKeyboardLayout.this.mContentEt.getText());
                sb.insert(selectionStart, str);
                MQCustomKeyboardLayout.this.mContentEt.setText(MQEmotionUtil.getEmotionText(MQCustomKeyboardLayout.this.getContext(), sb.toString(), 20));
                MQCustomKeyboardLayout.this.mContentEt.setSelection(selectionStart + str.length());
            }
        });
        this.mRecorderKeyboardLayout.setCallback(new MQRecorderKeyboardLayout.Callback() {
            public void onAudioRecorderFinish(int i, String str) {
                if (MQCustomKeyboardLayout.this.mCallback != null) {
                    MQCustomKeyboardLayout.this.mCallback.onAudioRecorderFinish(i, str);
                }
            }

            public void onAudioRecorderNoPermission() {
                if (MQCustomKeyboardLayout.this.mCallback != null) {
                    MQCustomKeyboardLayout.this.mCallback.onAudioRecorderNoPermission();
                }
            }

            public void onAudioRecorderTooShort() {
                if (MQCustomKeyboardLayout.this.mCallback != null) {
                    MQCustomKeyboardLayout.this.mCallback.onAudioRecorderTooShort();
                }
            }
        });
    }

    public void toggleEmotionOriginKeyboard() {
        if (isEmotionKeyboardVisible()) {
            changeToOriginalKeyboard();
        } else {
            changeToEmotionKeyboard();
        }
    }

    public void toggleVoiceOriginKeyboard() {
        if (isVoiceKeyboardVisible()) {
            changeToOriginalKeyboard();
        } else {
            changeToVoiceKeyboard();
        }
    }
}
