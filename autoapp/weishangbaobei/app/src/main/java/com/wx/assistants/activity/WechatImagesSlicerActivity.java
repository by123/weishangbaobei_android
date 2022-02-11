package com.wx.assistants.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.UiThread;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.samon.wechatimageslicer.slicer.BitmapSlicer;
import com.samon.wechatimageslicer.slicer.FourPicBitmapSlicer;
import com.samon.wechatimageslicer.slicer.NinePicBitmapSlicer;
import com.samon.wechatimageslicer.slicer.SixPicBitmapSlicer;
import com.samon.wechatimageslicer.slicer.ThreePicBitmapSlicer;
import com.samon.wechatimageslicer.slicer.TowPicBitmapSlicer;
import com.stub.StubApp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wx.assistants.activity.WechatImagesSlicerActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service_utils.OneKeyWxImagesSlicerUtils;
import com.wx.assistants.utils.CheckRuleValidateUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.yalantis.ucrop.UCrop;
import gdut.bsx.share2.ShareContentType;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import permission.rom.FloatManager;

public class WechatImagesSlicerActivity extends BaseActivity implements View.OnClickListener {
    private static final int MENU_2_PIC = 0;
    private static final int MENU_3_PIC = 1;
    private static final int MENU_4_PIC = 2;
    private static final int MENU_6_PIC = 3;
    private static final int MENU_9_PIC = 4;
    private static final int REQUEST_CODE_CUT = 1;
    private static final int REQUEST_CODE_PICK = 0;
    private static final int REQUEST_PERMISSION = 2;
    private static final String baseDir = (Environment.getExternalStorageDirectory() + "/tencent/MicroMsg/WeiXin/Slices");
    private static final File tempFile = new File(baseDir, "crop_temp");
    private BitmapSlicer.BitmapSliceListener bitmapSliceListener = new BitmapSlicer.BitmapSliceListener() {
        public void onSliceSuccess(Bitmap bitmap, List<Bitmap> list) {
            bitmap.recycle();
            WechatImagesSlicerActivity.this.bitmapSlicer.setSrcBitmap((Bitmap) null);
            for (ImageView imageView : WechatImagesSlicerActivity.this.ninePicImageViews) {
                imageView.setImageBitmap((Bitmap) null);
                imageView.setVisibility(8);
            }
            if (WechatImagesSlicerActivity.this.lastDesBitmaps != null) {
                for (Bitmap recycle : WechatImagesSlicerActivity.this.lastDesBitmaps) {
                    recycle.recycle();
                }
            }
            List unused = WechatImagesSlicerActivity.this.lastDesBitmaps = null;
            for (int i = 0; i < WechatImagesSlicerActivity.this.currentImageViewList.size(); i++) {
                ((ImageView) WechatImagesSlicerActivity.this.currentImageViewList.get(i)).setImageBitmap(list.get(i));
                ((ImageView) WechatImagesSlicerActivity.this.currentImageViewList.get(i)).setVisibility(0);
            }
            List unused2 = WechatImagesSlicerActivity.this.lastDesBitmaps = list;
            WechatImagesSlicerActivity.this.progressView.setVisibility(8);
            WechatImagesSlicerActivity.this.resultView.setVisibility(8);
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.activity.WechatImagesSlicerActivity, android.content.Context] */
        public void onSliceFailed() {
            Toast.makeText(WechatImagesSlicerActivity.this, "切片失败", 0).show();
            WechatImagesSlicerActivity.this.progressView.setVisibility(8);
            WechatImagesSlicerActivity.this.resultView.setVisibility(8);
        }
    };
    /* access modifiers changed from: private */
    public BitmapSlicer bitmapSlicer = this.ninePicBitmapSlicer;
    /* access modifiers changed from: private */
    public List<ImageView> currentImageViewList = this.ninePicImageViews;
    private BitmapSlicer fourPicBitmapSlicer = new FourPicBitmapSlicer();
    private List<ImageView> fourPickImageViews = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isCheckJumpWX = false;
    /* access modifiers changed from: private */
    public boolean isExecuteVoiceSend = false;
    /* access modifiers changed from: private */
    public boolean isPause = false;
    private ImageView iv_user_head;
    /* access modifiers changed from: private */
    public List<Bitmap> lastDesBitmaps;
    private Toast mToast;
    private LinearLayout navBack;
    private TextView navTitle;
    private BitmapSlicer ninePicBitmapSlicer = new NinePicBitmapSlicer();
    /* access modifiers changed from: private */
    public List<ImageView> ninePicImageViews = new ArrayList();
    /* access modifiers changed from: private */
    public View progressView;
    private TextView resultTv;
    /* access modifiers changed from: private */
    public View resultView;
    private Button sharedBtn;
    private BitmapSlicer sixPicBitmapSlicer = new SixPicBitmapSlicer();
    private List<ImageView> sixPickImageViews = new ArrayList();
    /* access modifiers changed from: private */
    public ArrayList<File> slices;
    private BitmapSlicer threePicBitmapSlicer = new ThreePicBitmapSlicer();
    private List<ImageView> threePickImageViews = new ArrayList();
    private BitmapSlicer towPicBitmapSlicer = new TowPicBitmapSlicer();
    private List<ImageView> towPickImageViews = new ArrayList();

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public native void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    static {
        StubApp.interface11(9407);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.isPause = false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.isExecuteVoiceSend) {
            this.isPause = true;
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("选择切图类型");
        contextMenu.add(0, 0, 0, "二图");
        contextMenu.add(0, 1, 0, "三图");
        contextMenu.add(0, 2, 0, "四图");
        contextMenu.add(0, 3, 0, "六图");
        contextMenu.add(0, 4, 0, "九图");
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 0:
                this.bitmapSlicer = this.towPicBitmapSlicer;
                this.currentImageViewList = this.towPickImageViews;
                break;
            case 1:
                this.bitmapSlicer = this.threePicBitmapSlicer;
                this.currentImageViewList = this.threePickImageViews;
                break;
            case 2:
                this.bitmapSlicer = this.fourPicBitmapSlicer;
                this.currentImageViewList = this.fourPickImageViews;
                break;
            case 3:
                this.bitmapSlicer = this.sixPicBitmapSlicer;
                this.currentImageViewList = this.sixPickImageViews;
                break;
            case 4:
                this.bitmapSlicer = this.ninePicBitmapSlicer;
                this.currentImageViewList = this.ninePicImageViews;
                break;
            default:
                return false;
        }
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ShareContentType.IMAGE);
        startActivityForResult(intent, 0);
        return true;
    }

    private void initImageViews() {
        this.ninePicImageViews.add(findViewById(2131296847));
        this.ninePicImageViews.add(findViewById(2131296848));
        this.ninePicImageViews.add(findViewById(2131296849));
        this.ninePicImageViews.add(findViewById(2131296850));
        this.ninePicImageViews.add(findViewById(2131296851));
        this.ninePicImageViews.add(findViewById(2131296852));
        this.ninePicImageViews.add(findViewById(2131296853));
        this.ninePicImageViews.add(findViewById(2131296854));
        this.ninePicImageViews.add(findViewById(2131296855));
        this.towPickImageViews.add(this.ninePicImageViews.get(0));
        this.towPickImageViews.add(this.ninePicImageViews.get(1));
        this.threePickImageViews.add(this.ninePicImageViews.get(0));
        this.threePickImageViews.add(this.ninePicImageViews.get(1));
        this.threePickImageViews.add(this.ninePicImageViews.get(2));
        this.fourPickImageViews.add(this.ninePicImageViews.get(0));
        this.fourPickImageViews.add(this.ninePicImageViews.get(1));
        this.fourPickImageViews.add(this.ninePicImageViews.get(3));
        this.fourPickImageViews.add(this.ninePicImageViews.get(4));
        this.sixPickImageViews.add(this.ninePicImageViews.get(0));
        this.sixPickImageViews.add(this.ninePicImageViews.get(1));
        this.sixPickImageViews.add(this.ninePicImageViews.get(2));
        this.sixPickImageViews.add(this.ninePicImageViews.get(3));
        this.sixPickImageViews.add(this.ninePicImageViews.get(4));
        this.sixPickImageViews.add(this.ninePicImageViews.get(5));
    }

    public void choose(View view) {
        view.showContextMenu();
    }

    public void save(View view) {
        if (this.lastDesBitmaps == null) {
            showToast("请先选择图片");
            return;
        }
        this.progressView.setVisibility(0);
        File file = new File(baseDir);
        String str = System.currentTimeMillis() + "";
        ArrayList arrayList = new ArrayList();
        if (!file.exists()) {
            file.mkdirs();
        }
        Observable.fromArray(this.lastDesBitmaps.toArray(new Bitmap[0])).map(new Function(file, str) {
            private final /* synthetic */ File f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object apply(Object obj) {
                return WechatImagesSlicerActivity.lambda$save$0(WechatImagesSlicerActivity.this, this.f$1, this.f$2, (Bitmap) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer(arrayList) {
            private final /* synthetic */ ArrayList f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                WechatImagesSlicerActivity.lambda$save$1(WechatImagesSlicerActivity.this, this.f$1, (File) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                WechatImagesSlicerActivity.lambda$save$2(WechatImagesSlicerActivity.this, (Throwable) obj);
            }
        }, new Action(file, arrayList) {
            private final /* synthetic */ File f$1;
            private final /* synthetic */ ArrayList f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WechatImagesSlicerActivity.lambda$save$3(WechatImagesSlicerActivity.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ File lambda$save$0(WechatImagesSlicerActivity wechatImagesSlicerActivity, File file, String str, Bitmap bitmap) throws Exception {
        int indexOf = wechatImagesSlicerActivity.lastDesBitmaps.indexOf(bitmap);
        File file2 = new File(file, str + "_" + (indexOf + 1) + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.close();
        return file2;
    }

    public static /* synthetic */ void lambda$save$1(WechatImagesSlicerActivity wechatImagesSlicerActivity, ArrayList arrayList, File file) throws Exception {
        Uri fromFile = Uri.fromFile(file);
        Log.d("xsm-save-files", fromFile.toString());
        arrayList.add(file);
        wechatImagesSlicerActivity.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", fromFile));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.activity.WechatImagesSlicerActivity, android.content.Context] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$save$2(com.wx.assistants.activity.WechatImagesSlicerActivity r1, java.lang.Throwable r2) throws java.lang.Exception {
        /*
            r2.printStackTrace()
            java.lang.String r2 = "导出失败"
            r0 = 0
            android.widget.Toast r2 = android.widget.Toast.makeText(r1, r2, r0)
            r2.show()
            android.view.View r2 = r1.progressView
            r0 = 8
            r2.setVisibility(r0)
            android.view.View r2 = r1.resultView
            r2.setVisibility(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.WechatImagesSlicerActivity.lambda$save$2(com.wx.assistants.activity.WechatImagesSlicerActivity, java.lang.Throwable):void");
    }

    public static /* synthetic */ void lambda$save$3(WechatImagesSlicerActivity wechatImagesSlicerActivity, File file, ArrayList arrayList) throws Exception {
        wechatImagesSlicerActivity.progressView.setVisibility(8);
        wechatImagesSlicerActivity.resultView.setVisibility(0);
        TextView textView = wechatImagesSlicerActivity.resultTv;
        textView.setText(Html.fromHtml("<font color=\"#868686\">切片已保存在</font><font color=\"#33a24e\">" + file.getAbsolutePath() + "</font><font color=\"#868686\">，可分享到朋友圈</font>"));
        wechatImagesSlicerActivity.sharedBtn.setTag(arrayList);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.WechatImagesSlicerActivity, android.content.Context] */
    @UiThread
    private void showToast(String str) {
        if (this.mToast == null) {
            this.mToast = Toast.makeText(this, str, 0);
        } else {
            this.mToast.setText(str);
            this.mToast.setDuration(0);
        }
        this.mToast.show();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.WechatImagesSlicerActivity, com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 0) {
            Uri data = intent.getData();
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                int height = decodeStream.getHeight();
                int width = decodeStream.getWidth();
                decodeStream.recycle();
                tempFile.delete();
                UCrop.of(data, Uri.fromFile(tempFile)).withAspectRatio((float) this.bitmapSlicer.getAspectX(), (float) this.bitmapSlicer.getAspectY()).withMaxResultSize(this.bitmapSlicer.calculateOutputX(width, height), this.bitmapSlicer.calculateOutputY(width, height)).start(this);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "无法读取图片", 0).show();
            }
        } else if (i == 69) {
            try {
                FileInputStream fileInputStream = new FileInputStream(tempFile);
                Bitmap decodeStream2 = BitmapFactory.decodeStream(fileInputStream);
                fileInputStream.close();
                tempFile.delete();
                this.bitmapSlicer.setSrcBitmap(decodeStream2).registerListener(this.bitmapSliceListener).slice();
                this.progressView.setVisibility(0);
            } catch (Exception e2) {
                e2.printStackTrace();
                Toast.makeText(this, "无法读取图片", 0).show();
                this.progressView.setVisibility(8);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.WechatImagesSlicerActivity, android.content.Context, android.app.Activity] */
    public void startWXShared(final ArrayList<File> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            new ShareAction(this).withMedia(new UMImage((Context) this, arrayList.get(0))).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform).setCallback(new UMShareListener() {
                public void onCancel(SHARE_MEDIA share_media) {
                }

                public void onError(SHARE_MEDIA share_media, Throwable th) {
                }

                public void onResult(SHARE_MEDIA share_media) {
                }

                public void onStart(SHARE_MEDIA share_media) {
                    boolean unused = WechatImagesSlicerActivity.this.isPause = false;
                    final OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("47");
                    operationParameterModel.setDeleteMode(1);
                    operationParameterModel.setMaterialPicCount(arrayList.size() - 1);
                    operationParameterModel.setAutoSend(false);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    WechatImagesSlicerActivity.this.runWx(operationParameterModel);
                    boolean unused2 = WechatImagesSlicerActivity.this.isExecuteVoiceSend = true;
                    new Thread(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 1000; i++) {
                                if (WechatImagesSlicerActivity.this.isPause) {
                                    LogUtils.log("WS_BABY_auto_service_isPause");
                                    return;
                                }
                                AutoService autoService = MyApplication.instance.getAutoService();
                                LogUtils.log("WS_BABY_auto_service_autoService" + autoService);
                                if (autoService != null) {
                                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "发表");
                                    LogUtils.log("WS_BABY_auto_service_autoService" + autoService + ",fabiao=" + findNodeIsExistByText);
                                    if (findNodeIsExistByText) {
                                        boolean unused = WechatImagesSlicerActivity.this.isCheckJumpWX = true;
                                        MyApplication.enforceable = true;
                                        OneKeyWxImagesSlicerUtils.getInstance().initData(operationParameterModel);
                                        OneKeyWxImagesSlicerUtils.getInstance().froward(operationParameterModel);
                                        return;
                                    }
                                }
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
            }).share();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.WechatImagesSlicerActivity, android.content.Context] */
    public void onClick(View view) {
        if (view.getId() == 2131297364) {
            this.slices = (ArrayList) view.getTag();
            if (!isAccessibilitySettingsOn()) {
                Log.d(BaseActivity.TAG, "辅助功能未开启！");
                openAlertDialog();
            } else if (FloatManager.getInstance().checkPermission(this)) {
                OperationParameterModel operationParameterModel = new OperationParameterModel();
                operationParameterModel.setTaskNum("47");
                operationParameterModel.setDeleteMode(1);
                operationParameterModel.setAutoSend(false);
                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                checkUserRule(operationParameterModel, new CheckRuleValidateUtils.CheckResultListener2() {
                    public void permitExecution(Object obj) {
                        final ArrayList arrayList = new ArrayList();
                        Observable.fromArray(WechatImagesSlicerActivity.this.slices.toArray(new File[0])).map(new Function() {
                            public final Object apply(Object obj) {
                                return WechatImagesSlicerActivity.AnonymousClass4.lambda$permitExecution$0(WechatImagesSlicerActivity.AnonymousClass4.this, (File) obj);
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Uri>() {
                            public void onSubscribe(Disposable disposable) {
                                WechatImagesSlicerActivity.this.progressView.setVisibility(0);
                            }

                            public void onNext(Uri uri) {
                                Log.d("xsm-collect-slice-uri", uri.toString());
                                arrayList.add(uri);
                            }

                            public void onError(Throwable th) {
                                WechatImagesSlicerActivity.this.progressView.setVisibility(8);
                            }

                            public void onComplete() {
                                Log.d("xsm-start-wechat", "start wechat with " + WechatImagesSlicerActivity.this.slices.size() + " pictures.");
                                WechatImagesSlicerActivity.this.progressView.setVisibility(8);
                                try {
                                    Context conText = MyApplication.getConText();
                                    AlbumUtil.insertImageToMediaStore(conText, FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/Slices");
                                } catch (Exception unused) {
                                }
                                LogUtils.log("WS_BABY_WEIXIN" + WechatImagesSlicerActivity.this.slices);
                                WechatImagesSlicerActivity.this.startWXShared(WechatImagesSlicerActivity.this.slices);
                            }
                        });
                    }

                    public static /* synthetic */ Uri lambda$permitExecution$0(AnonymousClass4 r7, File file) throws Exception {
                        Cursor query = WechatImagesSlicerActivity.this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, "_data=?", new String[]{file.getAbsolutePath()}, (String) null);
                        if (query == null) {
                            Log.e("xsm-read-media-database", "cursor is null");
                            return null;
                        } else if (query.getCount() != 0) {
                            query.moveToFirst();
                            int i = query.getInt(query.getColumnIndex("_id"));
                            String string = query.getString(query.getColumnIndex("_data"));
                            query.close();
                            Log.d("xsm-read-media-database", "id = " + i + ", path = " + string);
                            return Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString() + "/" + i);
                        } else {
                            query.close();
                            Log.w("xsm-read-media-database", "cursor is empty");
                            return null;
                        }
                    }
                });
            } else {
                toastFloatWindowOpenDialog(this);
            }
        }
    }
}
