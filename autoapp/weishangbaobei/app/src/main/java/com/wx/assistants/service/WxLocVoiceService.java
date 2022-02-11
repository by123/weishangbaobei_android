package com.wx.assistants.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
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

    public interface OnProgressResultListener {
        void isCanRefreshData(boolean z);

        void showPageEnd();

        void updateAdapterData(List<RecordBean> list);
    }

    interface OnScanCompletedListener {
        void onScanCompleted(String str);
    }

    static /* synthetic */ int access$408(WxLocVoiceService wxLocVoiceService) {
        int i = wxLocVoiceService.countMp3;
        wxLocVoiceService.countMp3 = i + 1;
        return i;
    }

    public WxLocVoiceService() {
        super("WxLocVoiceService");
    }

    public static void startWith(Context context, OnProgressResultListener onProgressResultListener2) {
        try {
            context.startService(new Intent(context, WxLocVoiceService.class));
            if (onProgressResultListener2 != null) {
                onProgressResultListener = onProgressResultListener2;
            }
        } catch (Exception unused) {
        }
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

    public void getAllAmr(File file) {
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
            for (int i = 0; i < this.listFiles.size(); i++) {
                LogUtils.log("WS_BABY_LOC=" + this.listFiles.get(i).getAbsolutePath());
                getRelyAmrFile(this.listFiles.get(i));
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

    class MyHandler extends Handler {
        MyHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (WxLocVoiceService.this.count == WxLocVoiceService.this.listFiles.size() && message.what == 0 && WxLocVoiceService.this.isFirst) {
                boolean unused = WxLocVoiceService.this.isFirst = false;
                new Thread(new Runnable() {
                    public void run() {
                        if (WxLocVoiceService.onProgressResultListener != null) {
                            WxLocVoiceService.onProgressResultListener.isCanRefreshData(false);
                        }
                        List unused = WxLocVoiceService.this.mRecords = SQLiteUtils.getInstance().getAllWXRecord();
                        LogUtils.log("WS_BABY_COUNT_A_MP3_XXXX" + WxLocVoiceService.this.countMp3 + "@" + WxLocVoiceService.this.mRecords.size());
                        if (WxLocVoiceService.this.mRecords != null && WxLocVoiceService.this.mRecords.size() > 0) {
                            for (int i = 0; i < WxLocVoiceService.this.mRecords.size(); i++) {
                                RecordBean recordBean = (RecordBean) WxLocVoiceService.this.mRecords.get(i);
                                WxLocVoiceService.this.convertAmrFile(recordBean.getPath(), recordBean);
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

    /* JADX WARNING: Can't wrap try/catch for region: R(2:21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r5.countMp3++;
        r6 = new android.os.Message();
        r6.what = 1;
        r5.handler.sendMessage(r6);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00ea */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void convertAmrFile(java.lang.String r6, final com.wx.assistants.Enity.RecordBean r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r7.getMp3LocPath()     // Catch:{ all -> 0x00fd }
            r1 = 1
            if (r0 == 0) goto L_0x006c
            java.lang.String r2 = ""
            boolean r0 = r2.equals(r0)     // Catch:{ all -> 0x00fd }
            if (r0 != 0) goto L_0x006c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fd }
            r6.<init>()     // Catch:{ all -> 0x00fd }
            java.lang.String r0 = "WS_BABY_COUNT_A_MP3_XXXXX_SECOND."
            r6.append(r0)     // Catch:{ all -> 0x00fd }
            int r0 = r7.getSecond()     // Catch:{ all -> 0x00fd }
            r6.append(r0)     // Catch:{ all -> 0x00fd }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00fd }
            com.wx.assistants.utils.LogUtils.log(r6)     // Catch:{ all -> 0x00fd }
            int r6 = r7.getSecond()     // Catch:{ all -> 0x00fd }
            if (r6 > 0) goto L_0x0035
            java.lang.String r6 = r7.getMp3LocPath()     // Catch:{ all -> 0x00fd }
            r5.getSecond(r6, r7)     // Catch:{ all -> 0x00fd }
        L_0x0035:
            int r6 = r5.countMp3     // Catch:{ all -> 0x00fd }
            int r6 = r6 + r1
            r5.countMp3 = r6     // Catch:{ all -> 0x00fd }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fd }
            r6.<init>()     // Catch:{ all -> 0x00fd }
            java.lang.String r7 = "WS_BABY_COUNT_A_MP3_HAS."
            r6.append(r7)     // Catch:{ all -> 0x00fd }
            int r7 = r5.countMp3     // Catch:{ all -> 0x00fd }
            r6.append(r7)     // Catch:{ all -> 0x00fd }
            java.lang.String r7 = "@"
            r6.append(r7)     // Catch:{ all -> 0x00fd }
            java.util.List<com.wx.assistants.Enity.RecordBean> r7 = r5.mRecords     // Catch:{ all -> 0x00fd }
            int r7 = r7.size()     // Catch:{ all -> 0x00fd }
            r6.append(r7)     // Catch:{ all -> 0x00fd }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00fd }
            com.wx.assistants.utils.LogUtils.log(r6)     // Catch:{ all -> 0x00fd }
            android.os.Message r6 = new android.os.Message     // Catch:{ all -> 0x00fd }
            r6.<init>()     // Catch:{ all -> 0x00fd }
            r6.what = r1     // Catch:{ all -> 0x00fd }
            com.wx.assistants.service.WxLocVoiceService$MyHandler r7 = r5.handler     // Catch:{ all -> 0x00fd }
            r7.sendMessage(r6)     // Catch:{ all -> 0x00fd }
            goto L_0x00fb
        L_0x006c:
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x00ea }
            r0.<init>(r6)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r2 = r0.getName()     // Catch:{ Exception -> 0x00ea }
            r3 = 0
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x00ea }
            java.lang.String r4 = ".amr"
            int r0 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r0 = r2.substring(r3, r0)     // Catch:{ Exception -> 0x00ea }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ea }
            r2.<init>()     // Catch:{ Exception -> 0x00ea }
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00ea }
            r2.append(r3)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = "/wsBabyMp3/"
            r2.append(r3)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00ea }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x00ea }
            r3.<init>(r2)     // Catch:{ Exception -> 0x00ea }
            boolean r4 = r3.exists()     // Catch:{ Exception -> 0x00ea }
            if (r4 != 0) goto L_0x00a7
            r3.mkdirs()     // Catch:{ Exception -> 0x00ea }
        L_0x00a7:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ea }
            r3.<init>()     // Catch:{ Exception -> 0x00ea }
            r3.append(r2)     // Catch:{ Exception -> 0x00ea }
            r3.append(r0)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r0 = ".mp3"
            r3.append(r0)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x00ea }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00ea }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00ea }
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x00ea }
            if (r3 == 0) goto L_0x00c9
            r2.delete()     // Catch:{ Exception -> 0x00ea }
        L_0x00c9:
            boolean r6 = com.weicheng.amrconvert.AmrConvertUtils.amr2Mp3((android.content.Context) r5, (java.lang.String) r6, (java.lang.String) r0)     // Catch:{ Exception -> 0x00ea }
            if (r6 == 0) goto L_0x00d8
            com.wx.assistants.service.WxLocVoiceService$1 r6 = new com.wx.assistants.service.WxLocVoiceService$1     // Catch:{ Exception -> 0x00ea }
            r6.<init>(r7, r0)     // Catch:{ Exception -> 0x00ea }
            updateMedia(r5, r0, r6)     // Catch:{ Exception -> 0x00ea }
            goto L_0x00fb
        L_0x00d8:
            int r6 = r5.countMp3     // Catch:{ Exception -> 0x00ea }
            int r6 = r6 + r1
            r5.countMp3 = r6     // Catch:{ Exception -> 0x00ea }
            android.os.Message r6 = new android.os.Message     // Catch:{ Exception -> 0x00ea }
            r6.<init>()     // Catch:{ Exception -> 0x00ea }
            r6.what = r1     // Catch:{ Exception -> 0x00ea }
            com.wx.assistants.service.WxLocVoiceService$MyHandler r7 = r5.handler     // Catch:{ Exception -> 0x00ea }
            r7.sendMessage(r6)     // Catch:{ Exception -> 0x00ea }
            goto L_0x00fb
        L_0x00ea:
            int r6 = r5.countMp3     // Catch:{ all -> 0x00fd }
            int r6 = r6 + r1
            r5.countMp3 = r6     // Catch:{ all -> 0x00fd }
            android.os.Message r6 = new android.os.Message     // Catch:{ all -> 0x00fd }
            r6.<init>()     // Catch:{ all -> 0x00fd }
            r6.what = r1     // Catch:{ all -> 0x00fd }
            com.wx.assistants.service.WxLocVoiceService$MyHandler r7 = r5.handler     // Catch:{ all -> 0x00fd }
            r7.sendMessage(r6)     // Catch:{ all -> 0x00fd }
        L_0x00fb:
            monitor-exit(r5)
            return
        L_0x00fd:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service.WxLocVoiceService.convertAmrFile(java.lang.String, com.wx.assistants.Enity.RecordBean):void");
    }

    public synchronized void getSecond(String str, RecordBean recordBean) {
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
        } catch (Exception unused) {
            LogUtils.log("WS_BABY_zzzzzzzzzz=");
        }
        return;
    }

    private class Music {
        String DATA;
        String DISPLAY_NAME;
        long DURATION;
        String TITLE;

        private Music() {
        }
    }

    public static void updateMedia(final Context context, String str, final OnScanCompletedListener onScanCompletedListener) {
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                MediaScannerConnection.scanFile(context, new String[]{str}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
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
        } catch (Exception unused) {
        }
    }

    private synchronized ArrayList<Music> queryMusics(String str) {
        ArrayList<Music> arrayList;
        LogUtils.log("WS_BABY_MP3_path.queryMusics=" + str);
        arrayList = new ArrayList<>();
        Cursor query = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "title", "duration", "_data", "_display_name", "mime_type"}, "is_music = 1 AND duration > 999 AND _data = '" + str + "'", (String[]) null, "title_key");
        StringBuilder sb = new StringBuilder();
        sb.append("WS_BABY_MP3_COUNT.queryMusics=: ");
        sb.append(query.getCount());
        LogUtils.log(sb.toString());
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
        return arrayList;
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
}
