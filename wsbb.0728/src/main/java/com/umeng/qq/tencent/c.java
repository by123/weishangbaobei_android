package com.umeng.qq.tencent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.stub.StubApp;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

class c implements j {
    j a;
    final /* synthetic */ b b;

    public c(b bVar, j jVar) {
        this.b = bVar;
        this.a = jVar;
    }

    private Drawable a(String str, Context context) {
        NinePatchDrawable ninePatchDrawable;
        Bitmap bitmap;
        Drawable drawable = null;
        try {
            InputStream open = StubApp.getOrigApplicationContext(context.getApplicationContext()).getAssets().open(str);
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                try {
                    bitmap = BitmapFactory.decodeStream(open);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    bitmap = null;
                }
                if (bitmap == null) {
                    return null;
                }
                byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                NinePatch.isNinePatchChunk(ninePatchChunk);
                ninePatchDrawable = new NinePatchDrawable(bitmap, ninePatchChunk, new Rect(), (String) null);
            } else {
                drawable = Drawable.createFromStream(open, str);
                try {
                    open.close();
                    ninePatchDrawable = drawable;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    ninePatchDrawable = drawable;
                    return ninePatchDrawable;
                }
            }
            return ninePatchDrawable;
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            ninePatchDrawable = drawable;
            return ninePatchDrawable;
        }
    }

    private View a(Context context, Drawable drawable, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        RelativeLayout relativeLayout = new RelativeLayout(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setId(1);
        int i = (int) (60.0f * f);
        int i2 = (int) (18.0f * f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, i2, (int) (6.0f * f), i2);
        relativeLayout.addView(imageView, layoutParams);
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(14.0f);
        textView.setGravity(3);
        textView.setIncludeFontPadding(false);
        textView.setPadding(0, 0, 0, 0);
        textView.setLines(2);
        textView.setId(5);
        textView.setMinWidth((int) (185.0f * f));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, 1);
        layoutParams2.addRule(6, 1);
        float f2 = 5.0f * f;
        layoutParams2.setMargins(0, 0, (int) f2, 0);
        relativeLayout.addView(textView, layoutParams2);
        View view = new View(context);
        view.setBackgroundColor(Color.rgb(214, 214, 214));
        view.setId(3);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 2);
        layoutParams3.addRule(3, 1);
        layoutParams3.addRule(5, 1);
        layoutParams3.addRule(7, 5);
        int i3 = (int) (12.0f * f);
        layoutParams3.setMargins(0, 0, 0, i3);
        relativeLayout.addView(view, layoutParams3);
        LinearLayout linearLayout = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(5, 1);
        layoutParams4.addRule(7, 5);
        layoutParams4.addRule(3, 3);
        Button button = new Button(context);
        button.setText("跳过");
        button.setBackgroundDrawable(a("buttonNegt.png", context));
        button.setTextColor(Color.rgb(36, 97, 131));
        button.setTextSize(20.0f);
        button.setOnClickListener(onClickListener2);
        button.setId(4);
        int i4 = (int) (45.0f * f);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, i4);
        int i5 = (int) (14.0f * f);
        layoutParams5.rightMargin = i5;
        int i6 = (int) (4.0f * f);
        layoutParams5.leftMargin = i6;
        layoutParams5.weight = 1.0f;
        linearLayout.addView(button, layoutParams5);
        Button button2 = new Button(context);
        button2.setText("确定");
        button2.setTextSize(20.0f);
        button2.setTextColor(Color.rgb(255, 255, 255));
        button2.setBackgroundDrawable(a("buttonPost.png", context));
        button2.setOnClickListener(onClickListener);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, i4);
        layoutParams6.weight = 1.0f;
        layoutParams6.rightMargin = i6;
        linearLayout.addView(button2, layoutParams6);
        relativeLayout.addView(linearLayout, layoutParams4);
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (f * 163.0f));
        relativeLayout.setPadding(i5, 0, i3, i3);
        relativeLayout.setLayoutParams(layoutParams7);
        relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
        PaintDrawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
        paintDrawable.setCornerRadius(f2);
        relativeLayout.setBackgroundDrawable(paintDrawable);
        return relativeLayout;
    }

    private void a(String str, j jVar, Object obj) {
        PackageInfo packageInfo;
        Drawable drawable = null;
        Activity activity = (Activity) this.b.h.get();
        if (activity != null) {
            Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(1);
            PackageManager packageManager = activity.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                packageInfo = null;
            }
            if (packageInfo != null) {
                drawable = packageInfo.applicationInfo.loadIcon(packageManager);
            }
            d dVar = new d(this, dialog, jVar, obj);
            e eVar = new e(this, dialog, jVar, obj);
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setAlpha(0);
            dialog.getWindow().setBackgroundDrawable(colorDrawable);
            dialog.setContentView(a(activity, drawable, str, dVar, eVar));
            dialog.setOnCancelListener(new f(this, jVar, obj));
            if (activity != null && !activity.isFinishing()) {
                dialog.show();
            }
        }
    }

    public void a(r rVar) {
        if (this.a != null) {
            this.a.a(rVar);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002e  */
    public void a(Object obj) {
        JSONObject jSONObject;
        String str;
        String decode;
        boolean z = true;
        if (obj != null && (jSONObject = (JSONObject) obj) != null) {
            try {
                if (jSONObject.getInt("sendinstall") != 1) {
                    z = false;
                }
                try {
                    str = jSONObject.getString("installwording");
                } catch (JSONException e) {
                }
            } catch (JSONException e2) {
                z = false;
            }
            decode = URLDecoder.decode(str);
            if (z && !TextUtils.isEmpty(decode)) {
                a(decode, this.a, obj);
                return;
            } else if (this.a != null) {
                this.a.a(obj);
                return;
            } else {
                return;
            }
        } else {
            return;
        }
        str = "";
        decode = URLDecoder.decode(str);
        if (z || !TextUtils.isEmpty(decode)) {
        }
    }

    public void onCancel() {
        if (this.a != null) {
            this.a.onCancel();
        }
    }
}
