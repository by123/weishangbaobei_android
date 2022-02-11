package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.HotMaterialTextBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.SendOrderLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendBatchActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296508)
    LinearLayout cleanImage;
    @BindView(2131296585)
    TextView descText;
    @BindView(2131296616)
    EditTextWithScrollView editLeavingMessage;
    @BindView(2131296617)
    LinearLayout editLeavingMessage2;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    @BindView(2131296731)
    LinearLayout graphicExplanationLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    @BindView(2131296950)
    LinearLayout linearLayoutTemplate;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
    /* access modifiers changed from: private */
    public int msgType = 0;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297065)
    LinearLayout noSendLabLayout;
    @BindView(2131297066)
    TextView noSendLabText;
    private String noSendStr = "";
    /* access modifiers changed from: private */
    public String sayContent = "";
    @BindView(2131297311)
    ImageView selectImg;
    /* access modifiers changed from: private */
    public ArrayList<String> selectImgs = new ArrayList<>();
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    @BindView(2131297335)
    SendOrderLayout sendOrderLayout;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public TagSelectAdapter tagSelectAdapter;
    private LinkedHashSet<String> tags = new LinkedHashSet<>();
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6722);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendBatchActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendBatchActivity] */
    private void initView() {
        this.navTitle.setText("分批群发图文消息");
        this.startWx.setText("启动微信开始群发");
        this.descText.setText("1.分批群发：借助微信群发助手每次最多只能勾选200人\n2.自动分批好友，支持文字、图片或视频同时发送，分批完成后，您可继续选择下一批进行批量发送\n3.如果提示，操作太频繁，请稍后再试！这种情况下只需等待10分钟左右就可以正常群发消息。注：群发消息时间间隔不要太短，否则会提示操作太频繁\n");
        this.mList = new ArrayList<>();
        this.mList.add("1-200");
        this.mList.add("201-400");
        this.mList.add("401-600");
        this.mList.add("601-800");
        this.mList.add("801-1000");
        this.mList.add("1001-1200");
        this.mList.add("1201-1400");
        this.mList.add("1401-1600");
        this.mList.add("1601-1800");
        this.mList.add("1801-2000");
        this.mList.add("2001-2200");
        this.mList.add("2201-2400");
        this.mList.add("2401-2600");
        this.mList.add("2601-2800");
        this.mList.add("2801-3000");
        this.mList.add("更多");
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                String str = (String) GroupSendBatchActivity.this.mList.get(i);
                if ("更多".equals(str)) {
                    if ("2801-3000".equals((String) GroupSendBatchActivity.this.mList.get(GroupSendBatchActivity.this.mList.size() - 2))) {
                        GroupSendBatchActivity.this.mList.remove(GroupSendBatchActivity.this.mList.size() - 1);
                        GroupSendBatchActivity.this.mList.add("3001-3200");
                        GroupSendBatchActivity.this.mList.add("3201-3400");
                        GroupSendBatchActivity.this.mList.add("3401-3600");
                        GroupSendBatchActivity.this.mList.add("3601-3800");
                        GroupSendBatchActivity.this.mList.add("3801-4000");
                        GroupSendBatchActivity.this.mList.add("4001-4200");
                        GroupSendBatchActivity.this.mList.add("4201-4400");
                        GroupSendBatchActivity.this.mList.add("4401-4600");
                        GroupSendBatchActivity.this.mList.add("4601-4800");
                        GroupSendBatchActivity.this.mList.add("4801-5000");
                    }
                    GroupSendBatchActivity.this.tagSelectAdapter.notifyDataSetChanged();
                    return;
                }
                try {
                    int unused = GroupSendBatchActivity.this.startIndex = Integer.parseInt(str.split("-")[0]);
                } catch (Exception e) {
                    LogUtils.log("WS_BABY_Catch_31");
                    e.printStackTrace();
                }
            }
        });
        this.selectImg.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                GroupSendBatchActivity.this.imgLongClickPreview(GroupSendBatchActivity.this.selectImgs);
                return false;
            }
        });
        this.cleanImage.setVisibility(8);
        this.sendOrderLayout.setOnSendOrderListener(new SendOrderLayout.OnSendOrderListener() {
            public void sendOrder(int i) {
                int unused = GroupSendBatchActivity.this.sendOrder = i;
                SPUtils.put(MyApplication.getConText(), "group_send_batch_text_img_order", Integer.valueOf(GroupSendBatchActivity.this.sendOrder));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "batch_say_content", "");
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "batch_img_path", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
                if (str2 == null || "".equals(str2)) {
                    this.sendOrderLayout.setVisibility(8);
                } else {
                    this.sendOrderLayout.setVisibility(0);
                }
            }
            if (str2 != null && !"".equals(str2)) {
                this.selectImgs = new ArrayList<>();
                this.selectImgs.add(str2);
                this.editLeavingMessage2.setVisibility(8);
                this.selectImg.setVisibility(0);
                this.cleanImage.setVisibility(0);
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectImg, str2, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            GroupSendBatchActivity.this.cleanImage.performClick();
                        }
                    }
                });
                if (str == null || "".equals(str)) {
                    this.sendOrderLayout.setVisibility(8);
                } else {
                    this.sendOrderLayout.setVisibility(0);
                }
            }
            this.sendOrder = ((Integer) SPUtils.get(MyApplication.getConText(), "group_send_batch_text_img_order", 0)).intValue();
            this.sendOrderLayout.setSendOrder(this.sendOrder);
        } catch (Exception unused) {
            LogUtils.log("WS_BABY_Catch_32");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.GroupSendBatchActivity] */
    @OnClick({2131296507, 2131296508, 2131297065, 2131297049, 2131297425, 2131297636, 2131296617, 2131296950, 2131296681, 2131297311})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.editLeavingMessage.setText("");
                this.sendOrderLayout.setVisibility(8);
                return;
            case 2131296508:
                this.cleanImage.setVisibility(8);
                this.msgType = 0;
                this.selectImgs = null;
                this.editLeavingMessage2.setVisibility(0);
                this.selectImg.setVisibility(8);
                SPUtils.put(MyApplication.getConText(), "batch_img_path", "");
                this.sendOrderLayout.setVisibility(8);
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectImg, "", new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            GroupSendBatchActivity.this.cleanImage.performClick();
                        }
                    }
                });
                return;
            case 2131296617:
                selectSingleImageVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            ArrayList unused = GroupSendBatchActivity.this.selectImgs = arrayList;
                            GroupSendBatchActivity.this.editLeavingMessage2.setVisibility(8);
                            GroupSendBatchActivity.this.selectImg.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "batch_img_path", GroupSendBatchActivity.this.selectImgs.get(0));
                            GroupSendBatchActivity.this.cleanImage.setVisibility(0);
                            GlideUtils.showImageViewGone(MyApplication.getConText(), GroupSendBatchActivity.this.selectImg, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        GroupSendBatchActivity.this.cleanImage.performClick();
                                    }
                                }
                            });
                            String obj = GroupSendBatchActivity.this.editLeavingMessage.getText().toString();
                            if (obj != null && !"".equals(obj)) {
                                GroupSendBatchActivity.this.sendOrderLayout.setVisibility(0);
                            }
                        }
                    }
                });
                return;
            case 2131296950:
                WebViewActivity.startActivity(this, "热门群发模版", QBangApis.HOT_MASTER_PLATE_URL, false);
                return;
            case 2131297049:
                back();
                return;
            case 2131297065:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.noSendStr);
                startActivity(intent);
                return;
            case 2131297311:
                selectSingleImageVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            ArrayList unused = GroupSendBatchActivity.this.selectImgs = arrayList;
                            GroupSendBatchActivity.this.editLeavingMessage2.setVisibility(8);
                            GroupSendBatchActivity.this.selectImg.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "batch_img_path", GroupSendBatchActivity.this.selectImgs.get(0));
                            GroupSendBatchActivity.this.cleanImage.setVisibility(0);
                            GlideUtils.showImageViewGone(MyApplication.getConText(), GroupSendBatchActivity.this.selectImg, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        GroupSendBatchActivity.this.cleanImage.performClick();
                                    }
                                }
                            });
                            String obj = GroupSendBatchActivity.this.editLeavingMessage.getText().toString();
                            if (obj != null && !"".equals(obj)) {
                                GroupSendBatchActivity.this.sendOrderLayout.setVisibility(0);
                            }
                        }
                    }
                });
                return;
            case 2131297425:
                if (!PerformClickUtils.isFastClick()) {
                    this.sayContent = this.editLeavingMessage.getText().toString();
                    if (this.sayContent != null && !"".equals(this.sayContent)) {
                        SPUtils.put(MyApplication.getConText(), "batch_say_content", this.sayContent);
                    }
                    if ((this.selectImgs == null || this.selectImgs.size() <= 0) && (this.sayContent == null || "".equals(this.sayContent))) {
                        ToastUtils.showToast(this, "请设置发送内容/图片");
                        return;
                    } else {
                        startCheck("4", true, new BaseActivity.OnStartCheckListener() {
                            public void checkEnd() {
                                if (GroupSendBatchActivity.this.selectImgs != null && GroupSendBatchActivity.this.selectImgs.size() > 0 && ((String) GroupSendBatchActivity.this.selectImgs.get(0)).endsWith(PictureFileUtils.POST_VIDEO)) {
                                    GroupSendBatchActivity.this.showLoadingDialog("正在处理视频", false);
                                    GroupSendBatchActivity.this.compressSaveVideo((List<String>) GroupSendBatchActivity.this.selectImgs, (FileUtil.OnConvertMp4Listener) new FileUtil.OnConvertMp4Listener() {
                                        public void convertSuccess(String str) {
                                            GroupSendBatchActivity.this.dismissLoadingDialog();
                                            if (GroupSendBatchActivity.this.selectImgs != null && GroupSendBatchActivity.this.selectImgs.size() > 0 && GroupSendBatchActivity.this.sayContent != null && !"".equals(GroupSendBatchActivity.this.sayContent)) {
                                                int unused = GroupSendBatchActivity.this.msgType = 2;
                                                SPUtils.put(MyApplication.getConText(), "batch_img_path", GroupSendBatchActivity.this.selectImgs.get(0));
                                            } else if (GroupSendBatchActivity.this.selectImgs == null || GroupSendBatchActivity.this.selectImgs.size() <= 0) {
                                                int unused2 = GroupSendBatchActivity.this.msgType = 0;
                                                SPUtils.put(MyApplication.getConText(), "batch_img_path", "");
                                            } else {
                                                int unused3 = GroupSendBatchActivity.this.msgType = 1;
                                                SPUtils.put(MyApplication.getConText(), "batch_img_path", GroupSendBatchActivity.this.selectImgs.get(0));
                                            }
                                            OperationParameterModel operationParameterModel = new OperationParameterModel();
                                            operationParameterModel.setTaskNum("4");
                                            operationParameterModel.setSendOrder(GroupSendBatchActivity.this.sendOrder);
                                            operationParameterModel.setStartIndex(GroupSendBatchActivity.this.startIndex);
                                            operationParameterModel.setMessageSendType(GroupSendBatchActivity.this.msgType);
                                            operationParameterModel.setSayContent(GroupSendBatchActivity.this.sayContent);
                                            operationParameterModel.setTagListPeoples(GroupSendBatchActivity.this.jumpPersons);
                                            operationParameterModel.setLocalImgUrl("xx.png");
                                            MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                            GroupSendBatchActivity.this.startWX(operationParameterModel);
                                        }

                                        public void convertProgress(int i) {
                                            GroupSendBatchActivity groupSendBatchActivity = GroupSendBatchActivity.this;
                                            groupSendBatchActivity.updateLoadingDialog("视频处理 " + i + "%");
                                        }

                                        public void convertFail() {
                                            GroupSendBatchActivity.this.dismissLoadingDialog();
                                        }
                                    });
                                } else if (GroupSendBatchActivity.this.selectImgs != null && GroupSendBatchActivity.this.selectImgs.size() > 0 && GroupSendBatchActivity.this.sayContent != null && !"".equals(GroupSendBatchActivity.this.sayContent)) {
                                    int unused = GroupSendBatchActivity.this.msgType = 2;
                                    SPUtils.put(MyApplication.getConText(), "batch_img_path", GroupSendBatchActivity.this.selectImgs.get(0));
                                    GroupSendBatchActivity.this.compressSaveImg((List<String>) GroupSendBatchActivity.this.selectImgs, (BaseActivity.OnSaveImgListener) new BaseActivity.OnSaveImgListener() {
                                        public void saveSuccess() {
                                            GroupSendBatchActivity.this.executeStartWx();
                                        }

                                        public void saveFail() {
                                            GroupSendBatchActivity.this.dismissLoadingDialog();
                                            ToastUtils.showToast(MyApplication.getConText(), "图片下载失败");
                                        }
                                    });
                                } else if (GroupSendBatchActivity.this.selectImgs == null || GroupSendBatchActivity.this.selectImgs.size() <= 0) {
                                    SPUtils.put(MyApplication.getConText(), "batch_img_path", "");
                                    int unused2 = GroupSendBatchActivity.this.msgType = 0;
                                    GroupSendBatchActivity.this.executeStartWx();
                                } else {
                                    int unused3 = GroupSendBatchActivity.this.msgType = 1;
                                    SPUtils.put(MyApplication.getConText(), "batch_img_path", GroupSendBatchActivity.this.selectImgs.get(0));
                                    GroupSendBatchActivity.this.compressSaveImg((List<String>) GroupSendBatchActivity.this.selectImgs, (BaseActivity.OnSaveImgListener) new BaseActivity.OnSaveImgListener() {
                                        public void saveSuccess() {
                                            GroupSendBatchActivity.this.executeStartWx();
                                        }

                                        public void saveFail() {
                                            GroupSendBatchActivity.this.dismissLoadingDialog();
                                            ToastUtils.showToast(MyApplication.getConText(), "图片下载失败");
                                        }
                                    });
                                }
                            }
                        });
                        return;
                    }
                } else {
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "分批群发视频教程", QBangApis.VIDEO_GROUP_SEND_BATCH);
                return;
            default:
                return;
        }
    }

    @Subscribe
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendBatchActivity.this.editLeavingMessage.setText(materialStr);
                    if (materialStr != null && !"".equals(materialStr)) {
                        SPUtils.put(MyApplication.getConText(), "batch_say_content", materialStr);
                        if (GroupSendBatchActivity.this.selectImgs != null && GroupSendBatchActivity.this.selectImgs.size() > 0) {
                            GroupSendBatchActivity.this.sendOrderLayout.setVisibility(0);
                        }
                    }
                }
            }
        });
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        this.tags = new LinkedHashSet<>();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.noSendLabText.setText("点击可设置标签(若不设置，默认发送全部好友)");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedTagList.size(); i++) {
                sb.append(selectedTagList.get(i) + ";");
                this.tags.add(selectedTagList.get(i));
            }
            this.noSendStr = sb.toString();
            this.noSendLabText.setText(this.noSendStr);
        }
        if (wxTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet<>();
            this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
            return;
        }
        this.jumpPersons = new LinkedHashSet<>();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void executeStartWx() {
        OperationParameterModel operationParameterModel = new OperationParameterModel();
        operationParameterModel.setTaskNum("4");
        operationParameterModel.setSendOrder(this.sendOrder);
        operationParameterModel.setStartIndex(this.startIndex);
        operationParameterModel.setMessageSendType(this.msgType);
        operationParameterModel.setSayContent(this.sayContent);
        operationParameterModel.setTagListPeoples(this.jumpPersons);
        operationParameterModel.setLocalImgUrl("xx.png");
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWX(operationParameterModel);
    }
}
