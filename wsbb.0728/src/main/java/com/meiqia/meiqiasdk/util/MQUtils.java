package com.meiqia.meiqiasdk.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.Agent;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.model.ClueCardMessage;
import com.meiqia.meiqiasdk.model.FileMessage;
import com.meiqia.meiqiasdk.model.HybridMessage;
import com.meiqia.meiqiasdk.model.PhotoMessage;
import com.meiqia.meiqiasdk.model.RobotMessage;
import com.meiqia.meiqiasdk.model.TextMessage;
import com.meiqia.meiqiasdk.model.VoiceMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.stub.StubApp;
import gdut.bsx.share2.ShareContentType;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class MQUtils {
    public static String DOWNLOAD_DIR = null;
    public static final int KEYBOARD_CHANGE_DELAY = 300;
    private static long lastClickTime;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void applyCustomUITextAndImageColor(int i, int i2, ImageView imageView, TextView... textViewArr) {
        Context context = imageView != null ? imageView.getContext() : null;
        if (textViewArr != null && textViewArr.length > 0) {
            context = textViewArr[0].getContext();
        }
        if (context != null) {
            if (-1 == i2) {
                i2 = i;
            }
            int color = context.getResources().getColor(i2);
            if (imageView != null) {
                imageView.setColorFilter(color);
            }
            if (textViewArr != null) {
                for (TextView textColor : textViewArr) {
                    textColor.setTextColor(color);
                }
            }
        }
    }

    public static void applyCustomUITintDrawable(View view, int i, int i2, int i3) {
        Context context = view.getContext();
        if (-1 == i3) {
            i3 = i2;
        }
        if (context.getResources().getColor(i3) != context.getResources().getColor(i)) {
            setBackground(view, tintDrawable(context, view.getBackground(), i3));
        }
    }

    public static void applyCustomUITitleGravity(TextView textView, TextView textView2) {
        if (MQConfig.ui.MQTitleGravity.LEFT == MQConfig.ui.titleGravity) {
            ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).addRule(1, R.id.back_rl);
            textView2.setGravity(19);
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
    }

    public static void clip(Context context, String str) {
        if (Build.VERSION.SDK_INT < 11) {
            ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
        } else {
            ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("mq_content", str));
        }
    }

    public static void closeKeyboard(Activity activity) {
        View peekDecorView;
        if (activity != null && (peekDecorView = activity.getWindow().peekDecorView()) != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public static void closeKeyboard(Dialog dialog) {
        View peekDecorView = dialog.getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) dialog.getContext().getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d A[SYNTHETIC, Splitter:B:24:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[Catch:{ IOException -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f A[SYNTHETIC, Splitter:B:33:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0074 A[Catch:{ IOException -> 0x0094 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    @TargetApi(29)
    public static void copyToDownloadAndroidQ(Context context, String str, String str2) {
        OutputStream outputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str2);
        contentValues.put("mime_type", ShareContentType.FILE);
        contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = context.getContentResolver();
        Uri insert = contentResolver.insert(uri, contentValues);
        if (insert != null) {
            try {
                outputStream = contentResolver.openOutputStream(insert);
                if (outputStream != null) {
                    try {
                        File file = new File(str);
                        if (file.exists()) {
                            fileInputStream = new FileInputStream(file);
                            try {
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStream.write(bArr, 0, read);
                                }
                            } catch (Exception e) {
                                e = e;
                                try {
                                    e.printStackTrace();
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                            return;
                                        }
                                    }
                                    if (outputStream == null) {
                                        outputStream.close();
                                        return;
                                    }
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileInputStream2 = fileInputStream;
                                    fileInputStream = fileInputStream2;
                                    th = th;
                                    if (fileInputStream != null) {
                                    }
                                    if (outputStream != null) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                throw th;
                            }
                        } else {
                            fileInputStream = null;
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Exception e4) {
                        e = e4;
                        fileInputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        if (outputStream == null) {
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = fileInputStream2;
                        th = th;
                        if (fileInputStream != null) {
                        }
                        if (outputStream != null) {
                        }
                        throw th;
                    }
                } else if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileInputStream = null;
                outputStream = null;
            } catch (Throwable th5) {
                fileInputStream = null;
                th = th5;
                outputStream = null;
                if (fileInputStream != null) {
                }
                if (outputStream != null) {
                }
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d A[SYNTHETIC, Splitter:B:24:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[Catch:{ IOException -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f A[SYNTHETIC, Splitter:B:33:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0074 A[Catch:{ IOException -> 0x0094 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    @RequiresApi(api = 29)
    public static void copyToPictureAndroidQ(Context context, String str, String str2) {
        OutputStream outputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str2);
        contentValues.put("mime_type", "image/png");
        contentValues.put("relative_path", Environment.DIRECTORY_PICTURES);
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = context.getContentResolver();
        Uri insert = contentResolver.insert(uri, contentValues);
        if (insert != null) {
            try {
                outputStream = contentResolver.openOutputStream(insert);
                if (outputStream != null) {
                    try {
                        File file = new File(str);
                        if (file.exists()) {
                            fileInputStream = new FileInputStream(file);
                            try {
                                byte[] bArr = new byte[1444];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStream.write(bArr, 0, read);
                                }
                            } catch (Exception e) {
                                e = e;
                                try {
                                    e.printStackTrace();
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                            return;
                                        }
                                    }
                                    if (outputStream == null) {
                                        outputStream.close();
                                        return;
                                    }
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileInputStream2 = fileInputStream;
                                    fileInputStream = fileInputStream2;
                                    th = th;
                                    if (fileInputStream != null) {
                                    }
                                    if (outputStream != null) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                throw th;
                            }
                        } else {
                            fileInputStream = null;
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Exception e4) {
                        e = e4;
                        fileInputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        if (outputStream == null) {
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = fileInputStream2;
                        th = th;
                        if (fileInputStream != null) {
                        }
                        if (outputStream != null) {
                        }
                        throw th;
                    }
                } else if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileInputStream = null;
                outputStream = null;
            } catch (Throwable th5) {
                fileInputStream = null;
                th = th5;
                outputStream = null;
                if (fileInputStream != null) {
                }
                if (outputStream != null) {
                }
                throw th;
            }
        }
    }

    public static void delFile(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
            }
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static StateListDrawable getCheckedSelectorDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912, 16842910}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static Drawable getDrawableFromFile(Context context, String str) {
        File externalCacheDir;
        if (!isExternalStorageWritable() || (externalCacheDir = context.getExternalCacheDir()) == null) {
            return null;
        }
        String stringToMD5 = stringToMD5(str);
        if (TextUtils.isEmpty(stringToMD5)) {
            return null;
        }
        File file = new File(externalCacheDir.getAbsolutePath() + "/" + stringToMD5);
        if (file.exists()) {
            return Drawable.createFromPath(file.getAbsolutePath());
        }
        return null;
    }

    public static String getFileMessageFilePath(FileMessage fileMessage) {
        try {
            String optString = new JSONObject(fileMessage.getExtra()).optString("filename");
            int lastIndexOf = optString.lastIndexOf(".");
            String str = optString.substring(0, lastIndexOf) + fileMessage.getId() + optString.substring(lastIndexOf, optString.length());
            return (Build.VERSION.SDK_INT >= 29 ? DOWNLOAD_DIR : Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()) + "/" + str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri getImageContentUri(Context context, String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
            return Uri.parse(str);
        }
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i = query.getInt(query.getColumnIndex("_id"));
                    Uri parse = Uri.parse("content://media/external/images/media");
                    Uri withAppendedPath = Uri.withAppendedPath(parse, "" + i);
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        if (new File(str).exists()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            Uri insert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (query == null) {
                return insert;
            }
            query.close();
            return insert;
        } else if (query == null) {
            return null;
        } else {
            query.close();
            return null;
        }
    }

    public static File getImageDir(Context context) {
        if (isExternalStorageWritable()) {
            String charSequence = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file = new File(externalStoragePublicDirectory, "MeiqiaSDK" + File.separator + charSequence);
            if (file.exists()) {
                return file;
            }
            file.mkdirs();
            return file;
        }
        showSafe(context, R.string.mq_no_sdcard);
        return null;
    }

    private static int getItemType(MQMessage mQMessage) {
        if (TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, mQMessage.getFrom_type())) {
            return TextUtils.equals("hybrid", mQMessage.getContent_type()) ? 10 : 5;
        }
        if (TextUtils.equals("hybrid", mQMessage.getContent_type())) {
            return 10;
        }
        if ("client".equals(mQMessage.getFrom_type())) {
            return 0;
        }
        return "rich_text".equals(mQMessage.getContent_type()) ? 9 : 1;
    }

    public static String getPicStorePath(Context context) {
        File externalFilesDir = context.getExternalFilesDir((String) null);
        if (externalFilesDir == null) {
            externalFilesDir = context.getFilesDir();
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdir();
        }
        File file = new File(externalFilesDir.getAbsolutePath() + "/mq");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static StateListDrawable getPressedSelectorDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919, 16842910}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static String getRealFilePath(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }

    public static String getRealPathByUri(Context context, Uri uri) {
        String str = null;
        if ("file".equals(uri.getScheme())) {
            return uri.getPath();
        }
        try {
            Cursor query = MediaStore.Images.Media.query(context.getContentResolver(), uri, new String[]{"_data"});
            if (query == null) {
                return null;
            }
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
            if (query.getCount() > 0 && query.moveToFirst()) {
                str = query.getString(columnIndexOrThrow);
            }
            query.close();
            return str;
        } catch (Exception e) {
            return uri.getPath();
        }
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences("MeiqiaSDK", 0).getString(str, str2);
    }

    public static String getUnSendTextMessage(Context context, String str) {
        return getString(context, "mq_un_send_text_msg" + str, "");
    }

    public static boolean isEmailValid(String str) {
        return Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", 2).matcher(str).matches();
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isFastClick() {
        synchronized (MQUtils.class) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - lastClickTime < 500) {
                    return true;
                }
                lastClickTime = currentTimeMillis;
                return false;
            } catch (Throwable th) {
                Class<MQUtils> cls = MQUtils.class;
                throw th;
            }
        }
    }

    public static boolean isFileExist(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isLocalPath(String str) {
        return !TextUtils.isEmpty(str) && !str.startsWith("http");
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public static boolean isPhone(String str) {
        if (!TextUtils.isEmpty(str) && str.length() >= 10 && str.length() <= 18) {
            return TextUtils.isEmpty(Pattern.compile("[-0-9]", 2).matcher(str).replaceAll(""));
        }
        return false;
    }

    public static boolean isQQ(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 5 || str.length() > 11 || str.startsWith("0")) {
            return false;
        }
        for (char isDigit : str.toCharArray()) {
            if (!Character.isDigit(isDigit)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSdcardAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void openKeyboard(final EditText editText) {
        runInUIThread(new Runnable() {
            public void run() {
                editText.requestFocus();
                editText.setSelection(editText.getText().toString().length());
                ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 2);
            }
        }, 300);
    }

    public static MQMessage parseBaseMessageToMQMessage(BaseMessage baseMessage) {
        MQMessage mQMessage = new MQMessage(baseMessage.getContentType());
        mQMessage.setConversation_id(baseMessage.getConversationId());
        mQMessage.setStatus(baseMessage.getStatus());
        mQMessage.setContent_type(baseMessage.getContentType());
        mQMessage.setType(baseMessage.getType());
        mQMessage.setStatus(baseMessage.getStatus());
        mQMessage.setId(baseMessage.getId());
        mQMessage.setAgent_nickname(baseMessage.getAgentNickname());
        mQMessage.setCreated_on(baseMessage.getCreatedOn());
        mQMessage.setAvatar(baseMessage.getAvatar());
        if (baseMessage instanceof FileMessage) {
            FileMessage fileMessage = (FileMessage) baseMessage;
            mQMessage.setExtra(fileMessage.getExtra());
            mQMessage.setMedia_url(fileMessage.getUrl());
        }
        return mQMessage;
    }

    public static Agent parseMQAgentToAgent(MQAgent mQAgent) {
        if (mQAgent == null) {
            return null;
        }
        Agent agent = new Agent();
        agent.setId(mQAgent.getId());
        agent.setNickname(mQAgent.getNickname());
        agent.setStatus(mQAgent.getStatus());
        agent.setIsOnline(mQAgent.isOnLine());
        agent.setPrivilege(mQAgent.getPrivilege());
        agent.setAvatar(mQAgent.getAvatar());
        agent.setSignature(mQAgent.getSignature());
        return agent;
    }

    public static BaseMessage parseMQMessageIntoBaseMessage(MQMessage mQMessage, BaseMessage baseMessage) {
        baseMessage.setStatus(mQMessage.getStatus());
        baseMessage.setItemViewType(getItemType(mQMessage));
        baseMessage.setContent(mQMessage.getContent());
        baseMessage.setContentType(mQMessage.getContent_type());
        baseMessage.setStatus(mQMessage.getStatus());
        baseMessage.setId(mQMessage.getId());
        baseMessage.setType(mQMessage.getType());
        baseMessage.setConversationId(mQMessage.getConversation_id());
        baseMessage.setAgentNickname(mQMessage.getAgent_nickname());
        baseMessage.setCreatedOn(mQMessage.getCreated_on());
        baseMessage.setAvatar(mQMessage.getAvatar());
        baseMessage.setIsRead(mQMessage.is_read());
        if ("photo".equals(mQMessage.getContent_type())) {
            ((PhotoMessage) baseMessage).setUrl(mQMessage.getMedia_url());
        } else if ("audio".equals(mQMessage.getContent_type())) {
            ((VoiceMessage) baseMessage).setUrl(mQMessage.getMedia_url());
        } else if ("file".equals(mQMessage.getContent_type())) {
            FileMessage fileMessage = (FileMessage) baseMessage;
            fileMessage.setUrl(mQMessage.getMedia_url());
            fileMessage.setExtra(mQMessage.getExtra());
            updateFileState(fileMessage);
        } else if ("text".endsWith(mQMessage.getContent_type())) {
            try {
                if (!TextUtils.isEmpty(mQMessage.getExtra())) {
                    JSONObject jSONObject = new JSONObject(mQMessage.getExtra());
                    boolean optBoolean = jSONObject.optBoolean("contains_sensitive_words", false);
                    String optString = jSONObject.optString("replaced_content");
                    TextMessage textMessage = (TextMessage) baseMessage;
                    textMessage.setContainsSensitiveWords(optBoolean);
                    textMessage.setReplaceContent(optString);
                }
            } catch (Exception e) {
                Log.e("meiqia_log", e.toString());
            }
        }
        return baseMessage;
    }

    public static BaseMessage parseMQMessageToBaseMessage(MQMessage mQMessage) {
        RobotMessage robotMessage;
        if (TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, mQMessage.getFrom_type())) {
            if (TextUtils.equals(mQMessage.getContent_type(), "hybrid")) {
                BaseMessage hybridMessage = new HybridMessage();
                hybridMessage.setContent(mQMessage.getContent());
                robotMessage = hybridMessage;
            } else {
                RobotMessage robotMessage2 = new RobotMessage();
                robotMessage2.setContentRobot(mQMessage.getContent_robot());
                robotMessage2.setContent(mQMessage.getContent());
                robotMessage2.setSubType(mQMessage.getSub_type());
                robotMessage2.setQuestionId(mQMessage.getQuestion_id());
                robotMessage2.setAlreadyFeedback(mQMessage.isAlreadyFeedback());
                robotMessage2.setExtra(mQMessage.getExtra());
                robotMessage = robotMessage2;
            }
        } else if (TextUtils.equals(mQMessage.getContent_type(), "hybrid")) {
            BaseMessage hybridMessage2 = new HybridMessage();
            hybridMessage2.setContent(mQMessage.getContent());
            robotMessage = hybridMessage2;
        } else if ("text".equals(mQMessage.getContent_type())) {
            BaseMessage textMessage = new TextMessage(mQMessage.getContent());
            textMessage.setContent(mQMessage.getContent());
            try {
                robotMessage = textMessage;
                if (!TextUtils.isEmpty(mQMessage.getExtra())) {
                    JSONObject jSONObject = new JSONObject(mQMessage.getExtra());
                    boolean optBoolean = jSONObject.optBoolean("contains_sensitive_words", false);
                    String optString = jSONObject.optString("replaced_content");
                    TextMessage textMessage2 = (TextMessage) textMessage;
                    textMessage2.setContainsSensitiveWords(optBoolean);
                    textMessage2.setReplaceContent(optString);
                    robotMessage = textMessage;
                }
            } catch (Exception e) {
                Log.e("meiqia_log", e.toString());
                robotMessage = textMessage;
            }
        } else if ("photo".equals(mQMessage.getContent_type())) {
            BaseMessage photoMessage = new PhotoMessage();
            if (isLocalPath(mQMessage.getMedia_url())) {
                ((PhotoMessage) photoMessage).setLocalPath(mQMessage.getMedia_url());
            } else {
                ((PhotoMessage) photoMessage).setUrl(mQMessage.getMedia_url());
            }
            photoMessage.setContent("[photo]");
            robotMessage = photoMessage;
        } else if ("audio".equals(mQMessage.getContent_type())) {
            BaseMessage voiceMessage = new VoiceMessage(mQMessage.getMedia_url());
            if (isLocalPath(mQMessage.getMedia_url())) {
                ((VoiceMessage) voiceMessage).setLocalPath(mQMessage.getMedia_url());
            } else {
                ((VoiceMessage) voiceMessage).setUrl(mQMessage.getMedia_url());
            }
            voiceMessage.setContent("[voice]");
            robotMessage = voiceMessage;
        } else if ("file".equals(mQMessage.getContent_type())) {
            BaseMessage fileMessage = new FileMessage(mQMessage.getMedia_url());
            if (isLocalPath(mQMessage.getMedia_url())) {
                ((FileMessage) fileMessage).setLocalPath(mQMessage.getMedia_url());
            } else {
                ((FileMessage) fileMessage).setUrl(mQMessage.getMedia_url());
            }
            FileMessage fileMessage2 = (FileMessage) fileMessage;
            fileMessage2.setExtra(mQMessage.getExtra());
            fileMessage.setContent("[file]");
            fileMessage.setId(mQMessage.getId());
            updateFileState(fileMessage2);
            robotMessage = fileMessage;
        } else if ("rich_text".equals(mQMessage.getContent_type())) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                JSONObject jSONObject3 = new JSONObject(mQMessage.getExtra());
                jSONObject2.put("type", "rich_text");
                jSONObject2.put("body", jSONObject3.opt("content"));
                jSONArray.put(jSONObject2);
            } catch (Exception e2) {
            }
            BaseMessage hybridMessage3 = new HybridMessage();
            hybridMessage3.setContent(jSONArray.toString());
            robotMessage = hybridMessage3;
        } else {
            BaseMessage textMessage3 = new TextMessage(mQMessage.getContent());
            textMessage3.setContentType("unknown");
            robotMessage = textMessage3;
        }
        robotMessage.setConversationId(mQMessage.getConversation_id());
        robotMessage.setStatus(mQMessage.getStatus());
        robotMessage.setItemViewType(getItemType(mQMessage));
        robotMessage.setContentType(mQMessage.getContent_type());
        robotMessage.setType(mQMessage.getType());
        robotMessage.setStatus(mQMessage.getStatus());
        robotMessage.setId(mQMessage.getId());
        robotMessage.setAgentNickname(mQMessage.getAgent_nickname());
        robotMessage.setCreatedOn(mQMessage.getCreated_on());
        robotMessage.setAvatar(mQMessage.getAvatar());
        robotMessage.setIsRead(mQMessage.is_read());
        return robotMessage;
    }

    public static List<BaseMessage> parseMQMessageToChatBaseList(List<MQMessage> list) {
        ArrayList arrayList = new ArrayList();
        for (MQMessage parseMQMessageToBaseMessage : list) {
            arrayList.add(parseMQMessageToBaseMessage(parseMQMessageToBaseMessage));
        }
        return arrayList;
    }

    public static BaseMessage parseMQMessageToClueCardMessage(MQMessage mQMessage) {
        ClueCardMessage clueCardMessage = new ClueCardMessage();
        clueCardMessage.setStatus(mQMessage.getStatus());
        clueCardMessage.setContent(mQMessage.getContent());
        clueCardMessage.setContentType(mQMessage.getContent_type());
        clueCardMessage.setStatus(mQMessage.getStatus());
        clueCardMessage.setId(mQMessage.getId());
        clueCardMessage.setType(mQMessage.getType());
        clueCardMessage.setConversationId(mQMessage.getConversation_id());
        clueCardMessage.setAgentNickname(mQMessage.getAgent_nickname());
        clueCardMessage.setCreatedOn(mQMessage.getCreated_on());
        clueCardMessage.setAvatar(mQMessage.getAvatar());
        return clueCardMessage;
    }

    public static void putString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("MeiqiaSDK", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void runInThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void runInUIThread(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void runInUIThread(Runnable runnable, long j) {
        sHandler.postDelayed(runnable, j);
    }

    public static void saveBitmap(Context context, String str, Bitmap bitmap) {
        File externalCacheDir;
        if (isExternalStorageWritable() && (externalCacheDir = context.getExternalCacheDir()) != null) {
            String stringToMD5 = stringToMD5(str);
            if (!TextUtils.isEmpty(stringToMD5)) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(externalCacheDir, stringToMD5));
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0080, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0084, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0085, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008e, code lost:
        r1 = r3;
        r4 = r2;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0096, code lost:
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007b A[SYNTHETIC, Splitter:B:33:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0080 A[Catch:{ IOException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008d A[ExcHandler: all (r0v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
  PHI: (r2v3 java.io.OutputStream) = (r2v4 java.io.OutputStream), (r2v4 java.io.OutputStream), (r2v4 java.io.OutputStream), (r2v0 java.io.OutputStream) binds: [B:8:0x0047, B:21:0x0063, B:22:?, B:4:0x003f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:4:0x003f] */
    @RequiresApi(api = 29)
    public static boolean saveImageWithAndroidQ(Context context, File file, String str) {
        BufferedInputStream bufferedInputStream;
        OutputStream outputStream;
        boolean z = true;
        OutputStream outputStream2 = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MQInquireForm.KEY_MENUS_ASSIGNMENTS_DESCRIPTION, "This is an image");
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", ShareContentType.IMAGE);
        contentValues.put("title", "image");
        contentValues.put("relative_path", "Pictures");
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = context.getContentResolver();
        Uri insert = contentResolver.insert(uri, contentValues);
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            if (insert != null) {
                try {
                    outputStream2 = contentResolver.openOutputStream(insert);
                } catch (IOException e) {
                    outputStream = outputStream2;
                } catch (Throwable th) {
                }
            }
            if (outputStream2 != null) {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream2.write(bArr, 0, read);
                }
                outputStream2.flush();
            }
            if (outputStream2 != null) {
                try {
                    outputStream2.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    return z;
                }
            }
            bufferedInputStream.close();
            return true;
        } catch (IOException e3) {
            bufferedInputStream = null;
            outputStream = null;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e4) {
                    e = e4;
                    z = false;
                    e.printStackTrace();
                    return z;
                }
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            return false;
        } catch (Throwable th2) {
            BufferedInputStream bufferedInputStream2 = null;
            OutputStream outputStream3 = null;
            Throwable th3 = th2;
            if (outputStream3 != null) {
            }
            if (bufferedInputStream2 != null) {
            }
            throw th3;
        }
    }

    public static void scrollListViewToBottom(final AbsListView absListView) {
        if (absListView != null && absListView.getAdapter() != null && ((ListAdapter) absListView.getAdapter()).getCount() > 0) {
            absListView.post(new Runnable() {
                public void run() {
                    absListView.setSelection(((ListAdapter) absListView.getAdapter()).getCount() - 1);
                }
            });
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setUnSendTextMessage(Context context, String str, String str2) {
        putString(context, "mq_un_send_text_msg" + str, str2);
    }

    public static void show(Context context, @StringRes int i) {
        show(context, (CharSequence) context.getResources().getString(i));
    }

    public static void show(Context context, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        if (charSequence.length() < 10) {
            Toast.makeText(context, charSequence, 0).show();
        } else {
            Toast.makeText(context, charSequence, 1).show();
        }
    }

    public static void showSafe(Context context, @StringRes int i) {
        showSafe(context, (CharSequence) context.getResources().getString(i));
    }

    public static void showSafe(final Context context, final CharSequence charSequence) {
        runInUIThread(new Runnable() {
            public void run() {
                MQUtils.show(context, charSequence);
            }
        });
    }

    public static String stringToMD5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void tintCompoundButton(CompoundButton compoundButton, @DrawableRes int i, @DrawableRes int i2) {
        compoundButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getCheckedSelectorDrawable(tintDrawable(compoundButton.getContext(), compoundButton.getResources().getDrawable(i), R.color.mq_form_et_bg_normal), tintDrawable(compoundButton.getContext(), compoundButton.getResources().getDrawable(i2), R.color.mq_indicator_selected)), (Drawable) null);
    }

    public static Drawable tintDrawable(Context context, Drawable drawable, @ColorRes int i) {
        if (drawable == null) {
            return null;
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, context.getResources().getColor(i));
        return wrap;
    }

    public static void tintPressedIndicator(ImageView imageView, @DrawableRes int i, @DrawableRes int i2) {
        imageView.setImageDrawable(getPressedSelectorDrawable(imageView.getResources().getDrawable(i), tintDrawable(imageView.getContext(), imageView.getResources().getDrawable(i2), R.color.mq_indicator_selected)));
    }

    public static boolean updateFileState(FileMessage fileMessage) {
        boolean isFileExist = isFileExist(getFileMessageFilePath(fileMessage));
        if (isFileExist) {
            fileMessage.setFileState(0);
        }
        return isFileExist;
    }
}
