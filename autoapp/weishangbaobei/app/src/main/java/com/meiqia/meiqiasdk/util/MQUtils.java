package com.meiqia.meiqiasdk.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void runInThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void runInUIThread(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void runInUIThread(Runnable runnable, long j) {
        sHandler.postDelayed(runnable, j);
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

    private static int getItemType(MQMessage mQMessage) {
        if (TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, mQMessage.getFrom_type())) {
            if (TextUtils.equals("hybrid", mQMessage.getContent_type())) {
                return 10;
            }
            return 5;
        } else if (TextUtils.equals("hybrid", mQMessage.getContent_type())) {
            return 10;
        } else {
            if ("client".equals(mQMessage.getFrom_type())) {
                return 0;
            }
            return "rich_text".equals(mQMessage.getContent_type()) ? 9 : 1;
        }
    }

    public static BaseMessage parseMQMessageToBaseMessage(MQMessage mQMessage) {
        HybridMessage hybridMessage;
        if (TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, mQMessage.getFrom_type())) {
            if (TextUtils.equals(mQMessage.getContent_type(), "hybrid")) {
                HybridMessage hybridMessage2 = new HybridMessage();
                hybridMessage2.setContent(mQMessage.getContent());
                hybridMessage = hybridMessage2;
            } else {
                RobotMessage robotMessage = new RobotMessage();
                robotMessage.setContentRobot(mQMessage.getContent_robot());
                robotMessage.setContent(mQMessage.getContent());
                robotMessage.setSubType(mQMessage.getSub_type());
                robotMessage.setQuestionId(mQMessage.getQuestion_id());
                robotMessage.setAlreadyFeedback(mQMessage.isAlreadyFeedback());
                robotMessage.setExtra(mQMessage.getExtra());
                hybridMessage = robotMessage;
            }
        } else if (TextUtils.equals(mQMessage.getContent_type(), "hybrid")) {
            HybridMessage hybridMessage3 = new HybridMessage();
            hybridMessage3.setContent(mQMessage.getContent());
            hybridMessage = hybridMessage3;
        } else if ("text".equals(mQMessage.getContent_type())) {
            TextMessage textMessage = new TextMessage(mQMessage.getContent());
            textMessage.setContent(mQMessage.getContent());
            try {
                hybridMessage = textMessage;
                if (!TextUtils.isEmpty(mQMessage.getExtra())) {
                    JSONObject jSONObject = new JSONObject(mQMessage.getExtra());
                    boolean optBoolean = jSONObject.optBoolean("contains_sensitive_words", false);
                    String optString = jSONObject.optString("replaced_content");
                    TextMessage textMessage2 = textMessage;
                    textMessage2.setContainsSensitiveWords(optBoolean);
                    textMessage2.setReplaceContent(optString);
                    hybridMessage = textMessage;
                }
            } catch (Exception e) {
                Log.e("meiqia_log", e.toString());
                hybridMessage = textMessage;
            }
        } else if ("photo".equals(mQMessage.getContent_type())) {
            PhotoMessage photoMessage = new PhotoMessage();
            if (isLocalPath(mQMessage.getMedia_url())) {
                photoMessage.setLocalPath(mQMessage.getMedia_url());
            } else {
                photoMessage.setUrl(mQMessage.getMedia_url());
            }
            photoMessage.setContent("[photo]");
            hybridMessage = photoMessage;
        } else if ("audio".equals(mQMessage.getContent_type())) {
            VoiceMessage voiceMessage = new VoiceMessage(mQMessage.getMedia_url());
            if (isLocalPath(mQMessage.getMedia_url())) {
                voiceMessage.setLocalPath(mQMessage.getMedia_url());
            } else {
                voiceMessage.setUrl(mQMessage.getMedia_url());
            }
            voiceMessage.setContent("[voice]");
            hybridMessage = voiceMessage;
        } else if ("file".equals(mQMessage.getContent_type())) {
            FileMessage fileMessage = new FileMessage(mQMessage.getMedia_url());
            if (isLocalPath(mQMessage.getMedia_url())) {
                fileMessage.setLocalPath(mQMessage.getMedia_url());
            } else {
                fileMessage.setUrl(mQMessage.getMedia_url());
            }
            FileMessage fileMessage2 = fileMessage;
            fileMessage2.setExtra(mQMessage.getExtra());
            fileMessage.setContent("[file]");
            fileMessage.setId(mQMessage.getId());
            updateFileState(fileMessage2);
            hybridMessage = fileMessage;
        } else if ("rich_text".equals(mQMessage.getContent_type())) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                JSONObject jSONObject3 = new JSONObject(mQMessage.getExtra());
                jSONObject2.put("type", "rich_text");
                jSONObject2.put("body", jSONObject3.opt("content"));
                jSONArray.put(jSONObject2);
            } catch (Exception unused) {
            }
            HybridMessage hybridMessage4 = new HybridMessage();
            hybridMessage4.setContent(jSONArray.toString());
            hybridMessage = hybridMessage4;
        } else {
            TextMessage textMessage3 = new TextMessage(mQMessage.getContent());
            textMessage3.setContentType("unknown");
            hybridMessage = textMessage3;
        }
        hybridMessage.setConversationId(mQMessage.getConversation_id());
        hybridMessage.setStatus(mQMessage.getStatus());
        hybridMessage.setItemViewType(getItemType(mQMessage));
        hybridMessage.setContentType(mQMessage.getContent_type());
        hybridMessage.setType(mQMessage.getType());
        hybridMessage.setStatus(mQMessage.getStatus());
        hybridMessage.setId(mQMessage.getId());
        hybridMessage.setAgentNickname(mQMessage.getAgent_nickname());
        hybridMessage.setCreatedOn(mQMessage.getCreated_on());
        hybridMessage.setAvatar(mQMessage.getAvatar());
        hybridMessage.setIsRead(mQMessage.is_read());
        return hybridMessage;
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

    public static List<BaseMessage> parseMQMessageToChatBaseList(List<MQMessage> list) {
        ArrayList arrayList = new ArrayList();
        for (MQMessage parseMQMessageToBaseMessage : list) {
            arrayList.add(parseMQMessageToBaseMessage(parseMQMessageToBaseMessage));
        }
        return arrayList;
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

    private static boolean isLocalPath(String str) {
        return !TextUtils.isEmpty(str) && !str.startsWith("http");
    }

    public static synchronized boolean isFastClick() {
        synchronized (MQUtils.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastClickTime < 500) {
                return true;
            }
            lastClickTime = currentTimeMillis;
            return false;
        }
    }

    public static void applyCustomUITextAndImageColor(int i, int i2, ImageView imageView, TextView... textViewArr) {
        Context context = imageView != null ? imageView.getContext() : null;
        if (textViewArr != null && textViewArr.length > 0) {
            context = textViewArr[0].getContext();
        }
        if (context != null) {
            if (-1 != i2) {
                i = i2;
            }
            int color = context.getResources().getColor(i);
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

    public static void applyCustomUITitleGravity(TextView textView, TextView textView2) {
        if (MQConfig.ui.MQTitleGravity.LEFT == MQConfig.ui.titleGravity) {
            ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).addRule(1, R.id.back_rl);
            textView2.setGravity(19);
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
    }

    public static void applyCustomUITintDrawable(View view, int i, int i2, int i3) {
        Context context = view.getContext();
        if (-1 != i3) {
            i2 = i3;
        }
        if (context.getResources().getColor(i2) != context.getResources().getColor(i)) {
            setBackground(view, tintDrawable(context, view.getBackground(), i2));
        }
    }

    public static Drawable tintDrawable(Context context, Drawable drawable, @ColorRes int i) {
        if (drawable == null) {
            return null;
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, context.getResources().getColor(i));
        return wrap;
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
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void tintPressedIndicator(ImageView imageView, @DrawableRes int i, @DrawableRes int i2) {
        imageView.setImageDrawable(getPressedSelectorDrawable(imageView.getResources().getDrawable(i), tintDrawable(imageView.getContext(), imageView.getResources().getDrawable(i2), R.color.mq_indicator_selected)));
    }

    public static void tintCompoundButton(CompoundButton compoundButton, @DrawableRes int i, @DrawableRes int i2) {
        compoundButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getCheckedSelectorDrawable(tintDrawable(compoundButton.getContext(), compoundButton.getResources().getDrawable(i), R.color.mq_form_et_bg_normal), tintDrawable(compoundButton.getContext(), compoundButton.getResources().getDrawable(i2), R.color.mq_indicator_selected)), (Drawable) null);
    }

    public static StateListDrawable getPressedSelectorDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919, 16842910}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static StateListDrawable getCheckedSelectorDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912, 16842910}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static boolean isSdcardAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
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

    public static boolean isPhone(String str) {
        if (!TextUtils.isEmpty(str) && str.length() >= 10 && str.length() <= 18) {
            return TextUtils.isEmpty(Pattern.compile("[-0-9]", 2).matcher(str).replaceAll(""));
        }
        return false;
    }

    public static boolean isEmailValid(String str) {
        return Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", 2).matcher(str).matches();
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean isFileExist(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean updateFileState(FileMessage fileMessage) {
        boolean isFileExist = isFileExist(getFileMessageFilePath(fileMessage));
        if (isFileExist) {
            fileMessage.setFileState(0);
        }
        return isFileExist;
    }

    public static void delFile(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception unused) {
            }
        }
    }

    public static String getFileMessageFilePath(FileMessage fileMessage) {
        String str;
        try {
            String optString = new JSONObject(fileMessage.getExtra()).optString("filename");
            int lastIndexOf = optString.lastIndexOf(".");
            String str2 = optString.substring(0, lastIndexOf) + fileMessage.getId() + optString.substring(lastIndexOf, optString.length());
            if (Build.VERSION.SDK_INT >= 29) {
                str = DOWNLOAD_DIR;
            } else {
                str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            }
            return str + "/" + str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
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

    public static void show(Context context, @StringRes int i) {
        show(context, (CharSequence) context.getResources().getString(i));
    }

    public static void showSafe(final Context context, final CharSequence charSequence) {
        runInUIThread(new Runnable() {
            public void run() {
                MQUtils.show(context, charSequence);
            }
        });
    }

    public static void showSafe(Context context, @StringRes int i) {
        showSafe(context, (CharSequence) context.getResources().getString(i));
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

    public static void openKeyboard(final EditText editText) {
        runInUIThread(new Runnable() {
            public void run() {
                editText.requestFocus();
                editText.setSelection(editText.getText().toString().length());
                ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 2);
            }
        }, 300);
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

    public static String getRealPathByUri(Context context, Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return uri.getPath();
        }
        try {
            Cursor query = MediaStore.Images.Media.query(context.getContentResolver(), uri, new String[]{"_data"});
            String str = null;
            if (query != null) {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                if (query.getCount() > 0 && query.moveToFirst()) {
                    str = query.getString(columnIndexOrThrow);
                }
                query.close();
            }
            return str;
        } catch (Exception unused) {
            return uri.getPath();
        }
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
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
        if (!file.exists()) {
            return null;
        }
        return Drawable.createFromPath(file.getAbsolutePath());
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static void setUnSendTextMessage(Context context, String str, String str2) {
        putString(context, "mq_un_send_text_msg" + str, str2);
    }

    public static String getUnSendTextMessage(Context context, String str) {
        return getString(context, "mq_un_send_text_msg" + str, "");
    }

    public static void putString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("MeiqiaSDK", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences("MeiqiaSDK", 0).getString(str, str2);
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
                    return Uri.withAppendedPath(parse, "" + i);
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
            if (query != null) {
                query.close();
            }
            return insert;
        }
        if (query != null) {
            query.close();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0075 A[SYNTHETIC, Splitter:B:40:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007d A[Catch:{ IOException -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0088 A[SYNTHETIC, Splitter:B:49:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0090 A[Catch:{ IOException -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    @android.annotation.TargetApi(29)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyToDownloadAndroidQ(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "_display_name"
            r0.put(r1, r4)
            java.lang.String r4 = "mime_type"
            java.lang.String r1 = "*/*"
            r0.put(r4, r1)
            java.lang.String r4 = "relative_path"
            java.lang.String r1 = android.os.Environment.DIRECTORY_DOWNLOADS
            r0.put(r4, r1)
            android.net.Uri r4 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI
            android.content.ContentResolver r2 = r2.getContentResolver()
            android.net.Uri r4 = r2.insert(r4, r0)
            if (r4 != 0) goto L_0x0025
            return
        L_0x0025:
            r0 = 0
            java.io.OutputStream r2 = r2.openOutputStream(r4)     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            if (r2 != 0) goto L_0x0037
            if (r2 == 0) goto L_0x0036
            r2.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0036:
            return
        L_0x0037:
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0069 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0069 }
            boolean r3 = r4.exists()     // Catch:{ Exception -> 0x0069 }
            if (r3 == 0) goto L_0x005d
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0069 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0069 }
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
        L_0x004b:
            int r0 = r3.read(r4)     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
            r1 = -1
            if (r0 == r1) goto L_0x005e
            r1 = 0
            r2.write(r4, r1, r0)     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
            goto L_0x004b
        L_0x0057:
            r4 = move-exception
            r0 = r3
            goto L_0x0086
        L_0x005a:
            r4 = move-exception
            r0 = r3
            goto L_0x0070
        L_0x005d:
            r3 = r0
        L_0x005e:
            if (r3 == 0) goto L_0x0063
            r3.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0063:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0084
        L_0x0069:
            r4 = move-exception
            goto L_0x0070
        L_0x006b:
            r4 = move-exception
            r2 = r0
            goto L_0x0086
        L_0x006e:
            r4 = move-exception
            r2 = r0
        L_0x0070:
            r4.printStackTrace()     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x007b
            r0.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x007b
        L_0x0079:
            r2 = move-exception
            goto L_0x0081
        L_0x007b:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0084
        L_0x0081:
            r2.printStackTrace()
        L_0x0084:
            return
        L_0x0085:
            r4 = move-exception
        L_0x0086:
            if (r0 == 0) goto L_0x008e
            r0.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x008e
        L_0x008c:
            r2 = move-exception
            goto L_0x0094
        L_0x008e:
            if (r2 == 0) goto L_0x0097
            r2.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0097
        L_0x0094:
            r2.printStackTrace()
        L_0x0097:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.util.MQUtils.copyToDownloadAndroidQ(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0075 A[SYNTHETIC, Splitter:B:40:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007d A[Catch:{ IOException -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0088 A[SYNTHETIC, Splitter:B:49:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0090 A[Catch:{ IOException -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    @android.support.annotation.RequiresApi(api = 29)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyToPictureAndroidQ(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "_display_name"
            r0.put(r1, r4)
            java.lang.String r4 = "mime_type"
            java.lang.String r1 = "image/png"
            r0.put(r4, r1)
            java.lang.String r4 = "relative_path"
            java.lang.String r1 = android.os.Environment.DIRECTORY_PICTURES
            r0.put(r4, r1)
            android.net.Uri r4 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI
            android.content.ContentResolver r2 = r2.getContentResolver()
            android.net.Uri r4 = r2.insert(r4, r0)
            if (r4 != 0) goto L_0x0025
            return
        L_0x0025:
            r0 = 0
            java.io.OutputStream r2 = r2.openOutputStream(r4)     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            if (r2 != 0) goto L_0x0037
            if (r2 == 0) goto L_0x0036
            r2.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0036:
            return
        L_0x0037:
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0069 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0069 }
            boolean r3 = r4.exists()     // Catch:{ Exception -> 0x0069 }
            if (r3 == 0) goto L_0x005d
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0069 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0069 }
            r4 = 1444(0x5a4, float:2.023E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
        L_0x004b:
            int r0 = r3.read(r4)     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
            r1 = -1
            if (r0 == r1) goto L_0x005e
            r1 = 0
            r2.write(r4, r1, r0)     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
            goto L_0x004b
        L_0x0057:
            r4 = move-exception
            r0 = r3
            goto L_0x0086
        L_0x005a:
            r4 = move-exception
            r0 = r3
            goto L_0x0070
        L_0x005d:
            r3 = r0
        L_0x005e:
            if (r3 == 0) goto L_0x0063
            r3.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0063:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0084
        L_0x0069:
            r4 = move-exception
            goto L_0x0070
        L_0x006b:
            r4 = move-exception
            r2 = r0
            goto L_0x0086
        L_0x006e:
            r4 = move-exception
            r2 = r0
        L_0x0070:
            r4.printStackTrace()     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x007b
            r0.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x007b
        L_0x0079:
            r2 = move-exception
            goto L_0x0081
        L_0x007b:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0084
        L_0x0081:
            r2.printStackTrace()
        L_0x0084:
            return
        L_0x0085:
            r4 = move-exception
        L_0x0086:
            if (r0 == 0) goto L_0x008e
            r0.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x008e
        L_0x008c:
            r2 = move-exception
            goto L_0x0094
        L_0x008e:
            if (r2 == 0) goto L_0x0097
            r2.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0097
        L_0x0094:
            r2.printStackTrace()
        L_0x0097:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.util.MQUtils.copyToPictureAndroidQ(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.BufferedInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f A[SYNTHETIC, Splitter:B:25:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0077 A[Catch:{ IOException -> 0x0073 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0082 A[SYNTHETIC, Splitter:B:37:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0087 A[Catch:{ IOException -> 0x0061 }] */
    @android.support.annotation.RequiresApi(api = 29)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean saveImageWithAndroidQ(android.content.Context r4, java.io.File r5, java.lang.String r6) {
        /*
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "description"
            java.lang.String r2 = "This is an image"
            r0.put(r1, r2)
            java.lang.String r1 = "_display_name"
            r0.put(r1, r6)
            java.lang.String r6 = "mime_type"
            java.lang.String r1 = "image/*"
            r0.put(r6, r1)
            java.lang.String r6 = "title"
            java.lang.String r1 = "image"
            r0.put(r6, r1)
            java.lang.String r6 = "relative_path"
            java.lang.String r1 = "Pictures"
            r0.put(r6, r1)
            android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            android.content.ContentResolver r4 = r4.getContentResolver()
            android.net.Uri r6 = r4.insert(r6, r0)
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x007f, all -> 0x006b }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x007f, all -> 0x006b }
            r3.<init>(r5)     // Catch:{ IOException -> 0x007f, all -> 0x006b }
            r2.<init>(r3)     // Catch:{ IOException -> 0x007f, all -> 0x006b }
            if (r6 == 0) goto L_0x0046
            java.io.OutputStream r4 = r4.openOutputStream(r6)     // Catch:{ IOException -> 0x0080, all -> 0x0044 }
            r1 = r4
            goto L_0x0046
        L_0x0044:
            r4 = move-exception
            goto L_0x006d
        L_0x0046:
            if (r1 == 0) goto L_0x005a
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x0080, all -> 0x0044 }
        L_0x004c:
            int r5 = r2.read(r4)     // Catch:{ IOException -> 0x0080, all -> 0x0044 }
            r6 = -1
            if (r5 == r6) goto L_0x0057
            r1.write(r4, r0, r5)     // Catch:{ IOException -> 0x0080, all -> 0x0044 }
            goto L_0x004c
        L_0x0057:
            r1.flush()     // Catch:{ IOException -> 0x0080, all -> 0x0044 }
        L_0x005a:
            r0 = 1
            if (r1 == 0) goto L_0x0063
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0063
        L_0x0061:
            r4 = move-exception
            goto L_0x0067
        L_0x0063:
            r2.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x008a
        L_0x0067:
            r4.printStackTrace()
            goto L_0x008a
        L_0x006b:
            r4 = move-exception
            r2 = r1
        L_0x006d:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0075
        L_0x0073:
            r5 = move-exception
            goto L_0x007b
        L_0x0075:
            if (r2 == 0) goto L_0x007e
            r2.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x007e
        L_0x007b:
            r5.printStackTrace()
        L_0x007e:
            throw r4
        L_0x007f:
            r2 = r1
        L_0x0080:
            if (r1 == 0) goto L_0x0085
            r1.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0085:
            if (r2 == 0) goto L_0x008a
            r2.close()     // Catch:{ IOException -> 0x0061 }
        L_0x008a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.util.MQUtils.saveImageWithAndroidQ(android.content.Context, java.io.File, java.lang.String):boolean");
    }
}
