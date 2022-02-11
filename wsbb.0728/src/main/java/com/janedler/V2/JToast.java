package com.janedler.V2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.janedler.V2.JToastManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JToast {
    private static final int ANIMATION_DURATION = 250;
    private static final int ANIMATION_FADE_DURATION = 180;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    private static final int MSG_DISMISS = 1;
    private static final int MSG_SHOW = 0;
    /* access modifiers changed from: private */
    public static final Handler sHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    ((JToast) message.obj).showView();
                    return true;
                case 1:
                    ((JToast) message.obj).hideView(message.arg1);
                    return true;
                default:
                    return false;
            }
        }
    });
    private Callback mCallback;
    private final Context mContext;
    private int mDuration;
    private final JToastManager.Callback mManagerCallback = new JToastManager.Callback() {
        public void dismiss(int i) {
            JToast.sHandler.sendMessage(JToast.sHandler.obtainMessage(1, i, 0, JToast.this));
        }

        public void show() {
            JToast.sHandler.sendMessage(JToast.sHandler.obtainMessage(0, JToast.this));
        }
    };
    private final TextView mMessageView;
    private final ViewGroup mParent;
    private final LinearLayout mView;

    public static abstract class Callback {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public void onDismissed(JToast jToast, int i) {
        }

        public void onShown(JToast jToast) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    private JToast(ViewGroup viewGroup) {
        this.mParent = viewGroup;
        this.mContext = viewGroup.getContext();
        this.mView = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.ui_v2_toast_layout, this.mParent, false);
        this.mMessageView = (TextView) this.mView.findViewById(R.id.message);
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup viewGroup = null;
        View view2 = view;
        while (!(view2 instanceof CoordinatorLayout)) {
            if (view2 instanceof FrameLayout) {
                if (view2.getId() == 16908290) {
                    return (ViewGroup) view2;
                }
                viewGroup = (ViewGroup) view2;
            }
            if (view2 != null) {
                ViewParent parent = view2.getParent();
                if (parent instanceof View) {
                    view2 = (View) parent;
                    continue;
                } else {
                    view2 = null;
                    continue;
                }
            }
            if (view2 == null) {
                return viewGroup;
            }
        }
        return (ViewGroup) view2;
    }

    @NonNull
    public static JToast make(@NonNull View view, @StringRes int i, int i2) {
        return make(view, view.getResources().getText(i), i2);
    }

    @NonNull
    public static JToast make(@NonNull View view, @NonNull CharSequence charSequence, int i) {
        JToast jToast = new JToast(findSuitableParent(view));
        jToast.setText(charSequence);
        jToast.setDuration(i);
        return jToast;
    }

    /* access modifiers changed from: package-private */
    public final void hideView(int i) {
        this.mParent.removeView(this.mView);
        if (this.mCallback != null) {
            this.mCallback.onDismissed(this, i);
        }
        JToastManager.getInstance().onDismissed(this.mManagerCallback);
    }

    @NonNull
    public JToast setDuration(int i) {
        this.mDuration = i;
        return this;
    }

    @NonNull
    public JToast setText(@StringRes int i) {
        return setText(this.mContext.getText(i));
    }

    @NonNull
    public JToast setText(@NonNull CharSequence charSequence) {
        this.mMessageView.setText(charSequence);
        return this;
    }

    public void show() {
        JToastManager.getInstance().show(this.mDuration, this.mManagerCallback);
    }

    /* access modifiers changed from: package-private */
    public final void showView() {
        this.mParent.addView(this.mView);
        JToastManager.getInstance().onShown(this.mManagerCallback);
    }
}
