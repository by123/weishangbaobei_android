package com.xiasuhuei321.loadingdialog.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiasuhuei321.loadingdialog.R;
import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.yalantis.ucrop.view.CropImageView;

public class LoadingDialog implements FinishDrawListener {
    private static StyleManager s = StyleManager.getDefault();
    /* access modifiers changed from: private */
    public DismissListener d;
    @SuppressLint({"HandlerLeak"})
    private Handler h = new Handler() {
        public void handleMessage(Message message) {
            LoadingDialog.this.close();
            if (LoadingDialog.this.o != null) {
                LoadingDialog.this.o.onFinish();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean interceptBack = true;
    private LinearLayout layout;
    private TextView loadingText;
    private Dialog mLoadingDialog;
    /* access modifiers changed from: private */
    public OnFinshListener o;
    private long time = 1000;

    public interface DismissListener {
        void dimiss();
    }

    public interface OnFinshListener {
        void onFinish();
    }

    public enum Speed {
        SPEED_ONE,
        SPEED_TWO
    }

    public LoadingDialog(Context context) {
        initView(LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, (ViewGroup) null));
        this.mLoadingDialog = new Dialog(context, R.style.loading_dialog) {
            public void onBackPressed() {
                if (!LoadingDialog.this.interceptBack) {
                    LoadingDialog.this.close();
                }
            }
        };
        this.mLoadingDialog.setCancelable(!this.interceptBack);
        this.mLoadingDialog.setContentView(this.layout, new LinearLayout.LayoutParams(-1, -1));
        this.mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (LoadingDialog.this.d != null) {
                    LoadingDialog.this.d.dimiss();
                }
            }
        });
        initStyle();
    }

    private void initStyle() {
        if (s != null) {
            setInterceptBack(s.isInterceptBack());
            setTextSize((float) s.getTextSize());
            setShowTime(s.getShowTime());
            setLoadingText(s.getLoadText());
        }
    }

    public static void initStyle(StyleManager styleManager) {
        if (styleManager != null) {
            s = styleManager;
        }
    }

    private void initView(View view) {
        this.layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        this.loadingText = (TextView) view.findViewById(R.id.loading_text);
    }

    public void close() {
        try {
            this.h.removeCallbacksAndMessages((Object) null);
            if (this.mLoadingDialog != null) {
                this.mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public void dispatchFinishEvent(View view) {
        if (view instanceof WrongDiaView) {
            this.h.sendEmptyMessageDelayed(2, this.time);
        } else {
            this.h.sendEmptyMessageDelayed(1, this.time);
        }
    }

    public boolean getInterceptBack() {
        return this.interceptBack;
    }

    public LoadingDialog setDimissListener(DismissListener dismissListener) {
        this.d = dismissListener;
        return this;
    }

    public LoadingDialog setInterceptBack(boolean z) {
        this.interceptBack = z;
        this.mLoadingDialog.setCancelable(!z);
        return this;
    }

    public LoadingDialog setLoadingText(String str) {
        if (str != null) {
            this.loadingText.setVisibility(0);
            this.loadingText.setText(str);
        } else {
            this.loadingText.setVisibility(8);
        }
        return this;
    }

    public void setOnFinishListener(OnFinshListener onFinshListener) {
        this.o = onFinshListener;
    }

    public LoadingDialog setShowTime(long j) {
        if (j >= 0) {
            this.time = j;
        }
        return this;
    }

    public LoadingDialog setTextSize(float f) {
        if (f >= CropImageView.DEFAULT_ASPECT_RATIO) {
            this.loadingText.setTextSize(2, f);
        }
        return this;
    }

    public void show() {
        if (this.mLoadingDialog != null) {
            this.mLoadingDialog.show();
        }
    }
}
