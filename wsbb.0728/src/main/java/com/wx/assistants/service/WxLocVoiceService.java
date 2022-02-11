package com.wx.assistants.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import com.weicheng.amrconvert.AmrConvertUtils;
import com.wx.assistants.Enity.RecordBean;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"Registered"})
public class WxLocVoiceService extends IntentService {
    public static OnProgressResultListener onProgressResultListener;
    /* access modifiers changed from: private */
    public int count = 0;
    /* access modifiers changed from: private */
    public int countMp3 = 0;
    /* access modifiers changed from: private */
    public MyHandler handler = new MyHandler();
    /* access modifiers changed from: private */
    public boolean isCanRefreshData = true;
    /* access modifiers changed from: private */
    public boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isFirst2 = true;
    /* access modifiers changed from: private */
    public List<File> listFiles = null;
    private List<RecordBean> listRecordBeans = null;
    /* access modifiers changed from: private */
    public List<RecordBean> mRecords = new ArrayList();

    private class Music {
        String DATA;
        String DISPLAY_NAME;
        long DURATION;
        String TITLE;

        private Music() {
        }
    }

    class MyHandler extends Handler {
        MyHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (WxLocVoiceService.this.count == WxLocVoiceService.this.listFiles.size() && message.what == 0 && WxLocVoiceService.this.isFirst) {
                boolean unused = WxLocVoiceService.this.isFirst = false;
                new Thread(new Runnable() {
                    public void run() {
                        int i = 0;
                        if (WxLocVoiceService.onProgressResultListener != null) {
                            WxLocVoiceService.onProgressResultListener.isCanRefreshData(false);
                        }
                        List unused = WxLocVoiceService.this.mRecords = SQLiteUtils.getInstance().getAllWXRecord();
                        LogUtils.log("WS_BABY_COUNT_A_MP3_XXXX" + WxLocVoiceService.this.countMp3 + "@" + WxLocVoiceService.this.mRecords.size());
                        if (WxLocVoiceService.this.mRecords != null && WxLocVoiceService.this.mRecords.size() > 0) {
                            while (true) {
                                int i2 = i;
                                if (i2 < WxLocVoiceService.this.mRecords.size()) {
                                    RecordBean recordBean = (RecordBean) WxLocVoiceService.this.mRecords.get(i2);
                                    WxLocVoiceService.this.convertAmrFile(recordBean.getPath(), recordBean);
                                    i = i2 + 1;
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }).start();
            } else if (message.what == 1 && WxLocVoiceService.this.countMp3 == WxLocVoiceService.this.mRecords.size() && WxLocVoiceService.this.isFirst2) {
                boolean unused2 = WxLocVoiceService.this.isFirst2 = false;
                if (WxLocVoiceService.onProgressResultListener != null) {
                    boolean unused3 = WxLocVoiceService.this.isCanRefreshData = true;
                    WxLocVoiceService.onProgressResultListener.showPageEnd();
                    WxLocVoiceService.onProgressResultListener.updateAdapterData(WxLocVoiceService.this.mRecords);
                    WxLocVoiceService.onProgressResultListener.isCanRefreshData(WxLocVoiceService.this.isCanRefreshData);
                }
            }
        }
    }

    public interface OnProgressResultListener {
        void isCanRefreshData(boolean z);

        void showPageEnd();

        void updateAdapterData(List<RecordBean> list);
    }

    interface OnScanCompletedListener {
        void onScanCompleted(String str);
    }

    public WxLocVoiceService() {
        super("WxLocVoiceService");
    }

    static /* synthetic */ int access$408(WxLocVoiceService wxLocVoiceService) {
        int i = wxLocVoiceService.countMp3;
        wxLocVoiceService.countMp3 = i + 1;
        return i;
    }

    private void privateMusics() {
        Cursor query = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "title", "duration", "_data", "_display_name"}, "is_music = 1 AND duration > 999 ", (String[]) null, "title_key");
        LogUtils.log("WS_BABY_MP3_COUNT.privateMusics=: " + query.getCount());
        query.moveToFirst();
        while (!query.isAfterLast()) {
            Music music = new Music();
            music.DISPLAY_NAME = query.getString(query.getColumnIndex("_display_name"));
            music.DATA = query.getString(query.getColumnIndex("_data"));
            music.DURATION = query.getLong(query.getColumnIndex("duration"));
            music.TITLE = query.getString(query.getColumnIndex("title"));
            LogUtils.log("WS_BABY_print_MP3_data=: " + music.DATA);
            LogUtils.log("WS_BABY_print_MP3_duration=: " + music.DURATION);
            query.moveToNext();
        }
        query.close();
    }

    private ArrayList<Music> queryMusics(String str) {
        ArrayList<Music> arrayList;
        synchronized (this) {
            LogUtils.log("WS_BABY_MP3_path.queryMusics=" + str);
            arrayList = new ArrayList<>();
            Cursor query = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "title", "duration", "_data", "_display_name", "mime_type"}, "is_music = 1 AND duration > 999 AND _data = '" + str + "'", (String[]) null, "title_key");
            LogUtils.log("WS_BABY_MP3_COUNT.queryMusics=: " + query.getCount());
            query.moveToFirst();
            while (!query.isAfterLast()) {
                Music music = new Music();
                music.DISPLAY_NAME = query.getString(query.getColumnIndex("_display_name"));
                music.DATA = query.getString(query.getColumnIndex("_data"));
                music.DURATION = query.getLong(query.getColumnIndex("duration"));
                music.TITLE = query.getString(query.getColumnIndex("title"));
                arrayList.add(music);
                query.moveToNext();
            }
            query.close();
        }
        return arrayList;
    }

    public static void startWith(Context context, OnProgressResultListener onProgressResultListener2) {
        try {
            context.startService(new Intent(context, WxLocVoiceService.class));
            if (onProgressResultListener2 != null) {
                onProgressResultListener = onProgressResultListener2;
            }
        } catch (Exception e) {
        }
    }

    public static void updateMedia(final Context context, String str, final OnScanCompletedListener onScanCompletedListener) {
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                String[] strArr = {str};
                MediaScannerConnection.scanFile(context, strArr, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String str, Uri uri) {
                        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                        intent.setData(uri);
                        context.sendBroadcast(intent);
                        if (onScanCompletedListener != null) {
                            onScanCompletedListener.onScanCompleted(str);
                        }
                    }
                });
                return;
            }
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(new File(str).getParentFile())));
            if (onScanCompletedListener != null) {
                onScanCompletedListener.onScanCompleted(str);
            }
        } catch (Exception e) {
        }
    }

    public void convertAmrFile(String str, final RecordBean recordBean) {
        synchronized (this) {
            String mp3LocPath = recordBean.getMp3LocPath();
            if (mp3LocPath == null || "".equals(mp3LocPath)) {
                try {
                    File file = new File(str);
                    String substring = file.getName().substring(0, file.getName().indexOf(".amr"));
                    String str2 = Environment.getExternalStorageDirectory() + "/wsBabyMp3/";
                    File file2 = new File(str2);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    final String str3 = str2 + substring + ".mp3";
                    File file3 = new File(str3);
                    if (file3.exists()) {
                        file3.delete();
                    }
                    if (AmrConvertUtils.amr2Mp3((Context) this, str, str3)) {
                        updateMedia(this, str3, new OnScanCompletedListener() {
                            public void onScanCompleted(String str) {
                                WxLocVoiceService.access$408(WxLocVoiceService.this);
                                recordBean.setMp3LocPath(str3);
                                SQLiteUtils.getInstance().updateRecord(recordBean);
                                WxLocVoiceService.this.getSecond(str3, recordBean);
                                Message message = new Message();
                                message.what = 1;
                                WxLocVoiceService.this.handler.sendMessage(message);
                            }
                        });
                    } else {
                        this.countMp3++;
                        Message message = new Message();
                        message.what = 1;
                        this.handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    this.countMp3++;
                    Message message2 = new Message();
                    message2.what = 1;
                    this.handler.sendMessage(message2);
                }
            } else {
                LogUtils.log("WS_BABY_COUNT_A_MP3_XXXXX_SECOND." + recordBean.getSecond());
                if (recordBean.getSecond() <= 0) {
                    getSecond(recordBean.getMp3LocPath(), recordBean);
                }
                this.countMp3++;
                LogUtils.log("WS_BABY_COUNT_A_MP3_HAS." + this.countMp3 + "@" + this.mRecords.size());
                Message message3 = new Message();
                message3.what = 1;
                this.handler.sendMessage(message3);
            }
        }
        return;
    }

    public void getAllAmr(File file) {
        int i = 0;
        if (!file.isDirectory()) {
            System.out.println("指定路径不是一个合法的目录！");
            return;
        }
        File[] listFiles2 = file.listFiles();
        if (listFiles2 != null) {
            for (File file2 : listFiles2) {
                if (file2.isDirectory() && file2.getName().length() == 32) {
                    for (File file3 : file2.listFiles()) {
                        if (file3.getName().equals("voice2")) {
                            this.listFiles.add(file3);
                        }
                    }
                }
            }
        }
        if (this.listFiles != null && this.listFiles.size() > 0) {
            while (true) {
                int i2 = i;
                if (i2 < this.listFiles.size()) {
                    LogUtils.log("WS_BABY_LOC=" + this.listFiles.get(i2).getAbsolutePath());
                    getRelyAmrFile(this.listFiles.get(i2));
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void getRelyAmrFile(File file) {
        File[] listFiles2 = file.listFiles();
        if (listFiles2 != null) {
            for (File file2 : listFiles2) {
                if (file2.isDirectory()) {
                    File[] listFiles3 = file2.listFiles();
                    if (listFiles3 != null) {
                        for (File file3 : listFiles3) {
                            if (file3.isDirectory()) {
                                File[] listFiles4 = file3.listFiles();
                                if (listFiles4 != null) {
                                    for (File file4 : listFiles4) {
                                        if (file4.isDirectory()) {
                                            File[] listFiles5 = file4.listFiles();
                                            if (listFiles5 != null) {
                                                for (File file5 : listFiles5) {
                                                    if (file5.isDirectory()) {
                                                        getRelyAmrFile(file5);
                                                    } else if (file5.getName().endsWith(".amr")) {
                                                        saveFile(file5);
                                                    }
                                                }
                                            }
                                        } else if (file4.getName().endsWith(".amr")) {
                                            saveFile(file4);
                                        }
                                    }
                                }
                            } else if (file3.getName().endsWith(".amr")) {
                                saveFile(file3);
                            }
                        }
                    }
                } else if (file2.getName().endsWith(".amr")) {
                    saveFile(file2);
                }
            }
        }
        this.count++;
        LogUtils.log("WS_BABY_COUNT_YYY." + this.count + "@" + this.listFiles.size());
        Message message = new Message();
        message.what = 0;
        this.handler.sendMessage(message);
    }

    public void getSecond(String str, RecordBean recordBean) {
        synchronized (this) {
            try {
                ArrayList<Music> queryMusics = queryMusics(str);
                if (queryMusics != null && queryMusics.size() > 0) {
                    Music music = queryMusics.get(0);
                    LogUtils.log("WS_BABY_XXX_DISPLAY_NAME@" + music.DISPLAY_NAME);
                    LogUtils.log("WS_BABY_XXX_DATA@" + music.DATA);
                    LogUtils.log("WS_BABY_XXX_TITLE@" + music.TITLE);
                    LogUtils.log("WS_BABY_XXX_DURATION@" + music.DURATION);
                    StringBuilder sb = new StringBuilder();
                    sb.append("WS_BABY_second=");
                    double d = (double) music.DURATION;
                    Double.isNaN(d);
                    sb.append(Math.round(d / 1000.0d));
                    LogUtils.log(sb.toString());
                    double d2 = (double) music.DURATION;
                    Double.isNaN(d2);
                    recordBean.setSecond((int) Math.round(d2 / 1000.0d));
                    SQLiteUtils.getInstance().updateRecord(recordBean);
                }
            } catch (Exception e) {
                LogUtils.log("WS_BABY_zzzzzzzzzz=");
            }
        }
        return;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        this.count = 0;
        this.countMp3 = 0;
        this.isFirst = true;
        this.isFirst2 = true;
        this.listFiles = new ArrayList();
        this.listRecordBeans = new ArrayList();
        getAllAmr(new File(FileUtils.getSDPath() + "/tencent/microMsg/"));
    }

    public void saveFile(File file) {
        RecordBean record = SQLiteUtils.getInstance().getRecord(file);
        if (record == null) {
            record = new RecordBean();
            record.setSecond(0);
            record.setPath(file.getAbsolutePath());
            record.setIsPlayed(false);
            record.setIsCollection(false);
            record.setIsFromWx(true);
            record.setVoiceTag(1);
            record.setCreateTimeLong(file.lastModified());
        }
        this.listRecordBeans.add(record);
        SQLiteUtils.getInstance().addRecord(record);
    }
}
