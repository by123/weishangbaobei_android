package com.wx.assistants.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;
import com.fb.jjyyzjy.watermark.module.video.VideoWatermarkActivity;
import com.fb.jjyyzjy.watermark.module.watermark.WatermarkActivity;
import com.google.gson.internal.LinkedTreeMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;
import com.wx.assistants.activity.AutoAddCardActivity;
import com.wx.assistants.activity.AutoAddContactCameraActivity;
import com.wx.assistants.activity.AutoAddContacts1Activity;
import com.wx.assistants.activity.AutoAddContacts2Activity;
import com.wx.assistants.activity.AutoAddGroupFansActivity;
import com.wx.assistants.activity.AutoAddGroupFansCompanyActivity;
import com.wx.assistants.activity.AutoAddMyCustomerActivity;
import com.wx.assistants.activity.AutoAddNearbyPeoplesActivity;
import com.wx.assistants.activity.AutoAddSearchActivity;
import com.wx.assistants.activity.AutoPassNilValidationActivity;
import com.wx.assistants.activity.AutoPassValidationActivity;
import com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity;
import com.wx.assistants.activity.AutoPullFriendsToGroupActivity;
import com.wx.assistants.activity.CleanCircleActivity;
import com.wx.assistants.activity.CleanFriendsActivity;
import com.wx.assistants.activity.CleanGroupsActivity;
import com.wx.assistants.activity.CleanHomeMsgActivity;
import com.wx.assistants.activity.CleanUnReadActivity;
import com.wx.assistants.activity.CleanVideoWaterMarkctivity;
import com.wx.assistants.activity.CleanZombieActivity;
import com.wx.assistants.activity.CleanZombieDNDActivity;
import com.wx.assistants.activity.ClearFriendRecoverActivity;
import com.wx.assistants.activity.CloneCircleActivity;
import com.wx.assistants.activity.ContactActivity;
import com.wx.assistants.activity.GroupSendBatchToFriendActivity;
import com.wx.assistants.activity.GroupSendCardToGroupActivity;
import com.wx.assistants.activity.GroupSendCollectionToFriendsActivity;
import com.wx.assistants.activity.GroupSendCollectionToGroupActivity;
import com.wx.assistants.activity.GroupSendMiniProgramsToFriendsActivity;
import com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity;
import com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity;
import com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity;
import com.wx.assistants.activity.GroupSendTextImageToFriendActivity;
import com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity;
import com.wx.assistants.activity.GroupSendTextImageToGroupActivity;
import com.wx.assistants.activity.InviteAwardActivity;
import com.wx.assistants.activity.InviteFriendActivity;
import com.wx.assistants.activity.LabelRemarkActivity;
import com.wx.assistants.activity.MemberCenterListActivity;
import com.wx.assistants.activity.OneKeyClickCommentActivity;
import com.wx.assistants.activity.OneKeyCopyWXHActivity;
import com.wx.assistants.activity.OneKeyForwardBigVideoActivity;
import com.wx.assistants.activity.OneKeyForwardCircleAlbumActivity;
import com.wx.assistants.activity.OneKeyForwardMaterialActivity;
import com.wx.assistants.activity.OneKeyForwardVoiceActivity;
import com.wx.assistants.activity.OneKeyForwardWindowActivity;
import com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity;
import com.wx.assistants.activity.PassiveActivity;
import com.wx.assistants.activity.WechatImagesSlicerActivity;
import com.wx.assistants.activity.WechatSportsActivity;
import com.wx.assistants.adapter.HomeItemAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.HomeItemBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.service_utils.GroupSendCardToFriendsUtils;
import com.wx.assistants.utils.ApkDownUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.DragFloatActionButton;
import com.wx.assistants.view.GridItemDecoration;
import com.wx.assistants.view.HomeBottomSendLayout;
import com.wx.assistants.view.RecyclerViewDivider;
import com.wx.assistants.webview.WebViewActivity;
import com.yongchun.library.glide.GlideRoundTransform;
import java.util.ArrayList;
import java.util.List;
import www.linwg.org.lib.LCardView;

public class TabHomeFragment2 extends Fragment {
    @BindView(2131296373)
    ViewPager banner;
    /* access modifiers changed from: private */
    public List<HomeBannerBean> bannerBeans;
    @BindView(2131296415)
    TextView broad;
    @BindView(2131296462)
    LCardView cardView;
    @BindView(2131296475)
    LinearLayout changeGroupLayout;
    @BindView(2131296509)
    LinearLayout cleanLayout;
    @BindView(2131296510)
    RecyclerView cleanRV;
    @BindView(2131296523)
    RecyclerView commonRV;
    @BindView(2131296524)
    LinearLayout companyLayout;
    @BindView(2131296525)
    RecyclerView companyRV;
    @BindView(2131296679)
    DragFloatActionButton floatBtn;
    @BindView(2131296735)
    LinearLayout groupSendLayout;
    @BindView(2131296737)
    RecyclerView groupSendRV;
    /* access modifiers changed from: private */
    public Handler handler = new Handler() {
        public void handleMessage(Message message) {
            try {
                TabHomeFragment2.this.banner.setCurrentItem(TabHomeFragment2.this.banner.getCurrentItem() + 1);
                if (TabHomeFragment2.this.isRunning) {
                    TabHomeFragment2.this.handler.sendEmptyMessageDelayed(0, 6000);
                }
            } catch (Exception unused) {
                LogUtils.log("WS_BABY_Catch_49");
            }
        }
    };
    @BindView(2131296745)
    HomeBottomSendLayout homeBottomSendLayout;
    private HomeItemAdapter homeCleanItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeCleanItemBeans;
    private HomeItemAdapter homeCompanyItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeCompanyItemBeans;t
    private HomeItemAdapter homeGroupSendItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeGroupSendItemBeans;
    private HomeItemAdapter homeItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeItemBeans;
    private HomeItemAdapter homeOtherItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeOtherItemBeans;
    private HomeItemAdapter homeRMItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeRenMaiItemBeans;
    private HomeItemAdapter homeToolItemAdapter;
    /* access modifiers changed from: private */
    public ArrayList<HomeItemBean> homeToolItemBeans;
    /* access modifiers changed from: private */
    public boolean isRunning = true;
    /* access modifiers changed from: private */
    public List<String> list_imgs = new ArrayList();
    private View mCacheView;
    @BindView(2131297031)
    LinearLayout moveFriendLayout;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    Button navTitle;
    @BindView(2131297098)
    LinearLayout otherLayout;
    @BindView(2131297099)
    RecyclerView otherRV;
    @BindView(2131297100)
    LinearLayout outCommonUse;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297239)
    LinearLayout renmaiLayout;
    @BindView(2131297240)
    RecyclerView renmaiRV;
    HomeBannerBean singImgBean;
    @BindView(2131297388)
    ImageView singleVDImage;
    @BindView(2131297525)
    LinearLayout toolLayout;
    @BindView(2131297526)
    RecyclerView toolRV;
    private Unbinder unbinder;

    public void initInviteData() {
        LogUtils.log("WS_BABY_InComeFragment.initInviteData");
        ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        MyApplication.inviteUrlStr = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                    } catch (Exception unused) {
                        LogUtils.log("WS_BABY_Catch_34");
                    }
                }
            }

            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    ToastUtils.showToast(TabHomeFragment2.this.getActivity(), failureModel.getErrorMessage());
                }
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mCacheView == null) {
            this.mCacheView = layoutInflater.inflate(2131427541, viewGroup, false);
            ViewGroup viewGroup2 = (ViewGroup) this.mCacheView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.mCacheView);
            }
            ButterKnife.bind(this, this.mCacheView);
            if (((Boolean) SPUtils.get(MyApplication.getConText(), "version_open", true)).booleanValue()) {
                ApkDownUtils.getInstance(getActivity()).checkVersion(false);
            }
            initView();
            initInviteData();
            initViewData();
            initData();
            initViewListener();
        }
        this.unbinder = ButterKnife.bind(this, this.mCacheView);
        return this.mCacheView;
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [com.wx.assistants.view.DragFloatActionButton, android.widget.ImageView] */
    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                TabHomeFragment2.this.initData();
            }
        });
        Glide.with(MyApplication.getConText()).load(2131558575).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this.floatBtn);
        this.floatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebViewActivity.startActivity(TabHomeFragment2.this.getActivity(), "收益推广视频", QBangApis.INCOME_PROMOTE_URL, false, true);
            }
        });
    }

    public void initViewData() {
        this.homeItemBeans = new ArrayList<>();
        this.homeItemBeans.add(new HomeItemBean(CleanZombieActivity.class, 2131230991, 2131558531, "检测僵尸粉", "", true));
        this.homeItemBeans.add(new HomeItemBean(GroupSendTextImageToFriendActivity.class, 2131230993, 2131558562, "图文给好友", "", true));
        this.homeItemBeans.add(new HomeItemBean(AutoAddGroupFansActivity.class, 2131230986, 2131558549, "群内加人", "", false));
        this.homeItemBeans.add(new HomeItemBean(OneKeyForwardCircleAlbumActivity.class, 2131230988, 2131558539, "转发朋友圈", "", false));
        if (this.homeItemBeans != null && this.homeItemBeans.size() > 0) {
            this.homeItemAdapter = new HomeItemAdapter(this.homeItemBeans, getContext());
            this.commonRV.setAdapter(this.homeItemAdapter);
            this.commonRV.addItemDecoration(new RecyclerViewDivider(getContext(), 1, 0, ContextCompat.getColor(getContext(), 2131099799)));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
            this.commonRV.addItemDecoration(new GridItemDecoration.Builder(getContext()).setHorizontalSpan(2131165432).setVerticalSpan(2131165317).setColorResource(2131100063).setShowLastLine(false).build());
            this.commonRV.setLayoutManager(gridLayoutManager);
            this.homeItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                public void click(int i) {
                    Class className = ((HomeItemBean) TabHomeFragment2.this.homeItemBeans.get(i)).getClassName();
                    if (TabHomeFragment2.this.isVideoWatermark(className)) {
                        TabHomeFragment2.this.getActivity().checkUserRule(className);
                    } else if (TabHomeFragment2.this.isGroupSend(className)) {
                        TabHomeFragment2.this.homeBottomSendLayout.setStartClass(TabHomeFragment2.this.getActivity(), className, true);
                    } else {
                        TabHomeFragment2.this.isBatchWatermark(className);
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), className));
                    }
                }
            });
            this.commonRV.setNestedScrollingEnabled(false);
        }
        this.homeRenMaiItemBeans = new ArrayList<>();
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoAddContactCameraActivity.class, 2131230989, 2131558507, "拍照加人", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoAddContacts1Activity.class, 2131230994, 2131558520, "通讯录加人", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoAddNearbyPeoplesActivity.class, 2131230987, 2131558554, "附近加人", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoAddMyCustomerActivity.class, 2131230992, 2131558518, "精准客源", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoPassValidationActivity.class, 2131230990, 2131558521, "接受好友请求", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoPullFriendsToGroupActivity.class, 2131230987, 2131558558, "拉人进群", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(LabelRemarkActivity.class, 2131230994, 2131558574, "智能分组", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoAddCardActivity.class, 2131230992, 2131558545, "批量加名片", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoPassNilValidationActivity.class, 2131230990, 2131558544, "通过免验证", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(AutoPullFriendsToAllGroupActivity.class, 2131230992, 2131558527, "换群专用", "", false));
        this.homeRenMaiItemBeans.add(new HomeItemBean(OneKeyCopyWXHActivity.class, 2131230989, 2131558543, "好友迁移", "", false));
        if (this.homeRenMaiItemBeans != null && this.homeRenMaiItemBeans.size() > 0) {
            this.homeRMItemAdapter = new HomeItemAdapter(this.homeRenMaiItemBeans, getContext());
            this.renmaiRV.setAdapter(this.homeRMItemAdapter);
            this.renmaiRV.addItemDecoration(new RecyclerViewDivider(getContext(), 1, 0, ContextCompat.getColor(getContext(), 2131099799)));
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 4);
            this.renmaiRV.addItemDecoration(new GridItemDecoration.Builder(getContext()).setHorizontalSpan(2131165321).setVerticalSpan(2131165320).setColorResource(2131100063).setShowLastLine(false).build());
            this.renmaiRV.setLayoutManager(gridLayoutManager2);
            this.homeRMItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                public void click(int i) {
                    Class className = ((HomeItemBean) TabHomeFragment2.this.homeRenMaiItemBeans.get(i)).getClassName();
                    if (TabHomeFragment2.this.isVideoWatermark(className)) {
                        TabHomeFragment2.this.getActivity().checkUserRule(className);
                    } else if (TabHomeFragment2.this.isGroupSend(className)) {
                        TabHomeFragment2.this.homeBottomSendLayout.setStartClass(TabHomeFragment2.this.getActivity(), className, true);
                    } else {
                        TabHomeFragment2.this.isBatchWatermark(className);
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), className));
                    }
                }
            });
            this.renmaiRV.setNestedScrollingEnabled(false);
        }
        this.homeGroupSendItemBeans = new ArrayList<>();
        ArrayList<HomeItemBean> arrayList = this.homeGroupSendItemBeans;
        HomeItemBean homeItemBean = r9;
        HomeItemBean homeItemBean2 = new HomeItemBean(GroupSendTextImageToGroupActivity.class, 2131230994, 2131558548, "图文群发", "", false);
        arrayList.add(homeItemBean);
        this.homeGroupSendItemBeans.add(new HomeItemBean(GroupSendBatchToFriendActivity.class, 2131230992, 2131558524, "批量群发", "", false));
        this.homeGroupSendItemBeans.add(new HomeItemBean(GroupSendCardToGroupActivity.class, 2131230987, 2131558526, "名片群发", "", false));
        this.homeGroupSendItemBeans.add(new HomeItemBean(GroupSendCollectionToGroupActivity.class, 2131230994, 2131558536, "收藏群发", "", false));
        this.homeGroupSendItemBeans.add(new HomeItemBean(GroupSendPublicNumberToGroupActivity.class, 2131230989, 2131558557, "公众号群发", "", false));
        this.homeGroupSendItemBeans.add(new HomeItemBean(GroupSendMiniProgramsToGroupActivity.class, 2131230990, 2131558556, "小程序群发", "", false));
        this.homeGroupSendItemBeans.add(new HomeItemBean(OneKeyForwardBigVideoActivity.class, 2131230992, 2131558523, "发布大视频", "", false));
        if (this.homeGroupSendItemBeans != null && this.homeGroupSendItemBeans.size() > 0) {
            this.homeGroupSendItemAdapter = new HomeItemAdapter(this.homeGroupSendItemBeans, getContext());
            this.groupSendRV.setAdapter(this.homeGroupSendItemAdapter);
            this.groupSendRV.addItemDecoration(new RecyclerViewDivider(getContext(), 1, 0, ContextCompat.getColor(getContext(), 2131099799)));
            GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getContext(), 4);
            this.groupSendRV.addItemDecoration(new GridItemDecoration.Builder(getContext()).setHorizontalSpan(2131165321).setVerticalSpan(2131165320).setColorResource(2131100063).setShowLastLine(false).build());
            this.groupSendRV.setLayoutManager(gridLayoutManager3);
            this.homeGroupSendItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                public void click(int i) {
                    Class className = ((HomeItemBean) TabHomeFragment2.this.homeGroupSendItemBeans.get(i)).getClassName();
                    if (TabHomeFragment2.this.isVideoWatermark(className)) {
                        TabHomeFragment2.this.getActivity().checkUserRule(className);
                    } else if (TabHomeFragment2.this.isGroupSend(className)) {
                        TabHomeFragment2.this.homeBottomSendLayout.setStartClass(TabHomeFragment2.this.getActivity(), className, true);
                    } else {
                        TabHomeFragment2.this.isBatchWatermark(className);
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), className));
                    }
                }
            });
            this.groupSendRV.setNestedScrollingEnabled(false);
        }
        this.homeOtherItemBeans = new ArrayList<>();
        this.homeOtherItemBeans.add(new HomeItemBean(OneKeyForwardCircleAlbumActivity.class, 2131230992, 2131558541, "转发朋友圈", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(CloneCircleActivity.class, 2131230994, 2131558535, "克隆朋友圈", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(OneKeyForwardVoiceActivity.class, 2131230990, 2131558576, "语音转发", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(OneKeyForwardWindowActivity.class, 2131230987, 2131558540, "聊天窗口转发", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(WechatImagesSlicerActivity.class, 2131230987, 2131558519, "九宫格切图", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(OneKeyClickCommentActivity.class, 2131230989, 2131558537, "朋友圈点评", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(OneKeyLikeCommentToFriendsActivity.class, 2131230992, 2131558542, "好友点评", "", false));
        this.homeOtherItemBeans.add(new HomeItemBean(WechatSportsActivity.class, 2131230990, 2131558534, "运动点赞", "", false));
        if (this.homeOtherItemBeans != null && this.homeOtherItemBeans.size() > 0) {
            this.homeOtherItemAdapter = new HomeItemAdapter(this.homeOtherItemBeans, getContext());
            this.otherRV.setAdapter(this.homeOtherItemAdapter);
            this.otherRV.addItemDecoration(new RecyclerViewDivider(getContext(), 1, 0, ContextCompat.getColor(getContext(), 2131099799)));
            GridLayoutManager gridLayoutManager4 = new GridLayoutManager(getContext(), 4);
            this.otherRV.addItemDecoration(new GridItemDecoration.Builder(getContext()).setHorizontalSpan(2131165321).setVerticalSpan(2131165320).setColorResource(2131100063).setShowLastLine(false).build());
            this.otherRV.setLayoutManager(gridLayoutManager4);
            this.homeOtherItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                public void click(int i) {
                    Class className = ((HomeItemBean) TabHomeFragment2.this.homeOtherItemBeans.get(i)).getClassName();
                    if (TabHomeFragment2.this.isVideoWatermark(className)) {
                        TabHomeFragment2.this.getActivity().checkUserRule(className);
                    } else if (TabHomeFragment2.this.isGroupSend(className)) {
                        TabHomeFragment2.this.homeBottomSendLayout.setStartClass(TabHomeFragment2.this.getActivity(), className, true);
                    } else {
                        TabHomeFragment2.this.isBatchWatermark(className);
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), className));
                    }
                }
            });
            this.otherRV.setNestedScrollingEnabled(false);
        }
        this.homeToolItemBeans = new ArrayList<>();
        this.homeToolItemBeans.add(new HomeItemBean(WatermarkActivity.class, 2131230992, 2131558555, "图片加水印", "", false));
        this.homeToolItemBeans.add(new HomeItemBean(VideoWatermarkActivity.class, 2131230989, 2131558566, "视频加水印", "", false));
        this.homeToolItemBeans.add(new HomeItemBean(CleanVideoWaterMarkctivity.class, 2131230987, 2131558565, "视频去水印", "", false));
        this.homeToolItemBeans.add(new HomeItemBean(WatermarkActivity.class, 2131230994, 2131558564, "视频拼接", "", false));
        if (this.homeToolItemBeans != null && this.homeToolItemBeans.size() > 0) {
            this.homeToolItemAdapter = new HomeItemAdapter(this.homeToolItemBeans, getContext());
            this.toolRV.setAdapter(this.homeToolItemAdapter);
            this.toolRV.addItemDecoration(new RecyclerViewDivider(getContext(), 1, 0, ContextCompat.getColor(getContext(), 2131099799)));
            GridLayoutManager gridLayoutManager5 = new GridLayoutManager(getContext(), 4);
            this.toolRV.addItemDecoration(new GridItemDecoration.Builder(getContext()).setHorizontalSpan(2131165321).setVerticalSpan(2131165320).setColorResource(2131100063).setShowLastLine(false).build());
            this.toolRV.setLayoutManager(gridLayoutManager5);
            this.homeToolItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                public void click(int i) {
                    if (i == 3) {
                        MobclickAgent.onEvent(TabHomeFragment2.this.getActivity(), "NO_42_VIDEO_STITCHING");
                        MobclickAgent.onEvent((Context) TabHomeFragment2.this.getActivity(), "NO_42_VIDEO_STITCHING", "视频拼接");
                        TabHomeFragment2.this.getActivity().checkUserVideoEditRule();
                        return;
                    }
                    Class className = ((HomeItemBean) TabHomeFragment2.this.homeToolItemBeans.get(i)).getClassName();
                    if (TabHomeFragment2.this.isVideoWatermark(className)) {
                        TabHomeFragment2.this.getActivity().checkUserRule(className);
                    } else if (TabHomeFragment2.this.isGroupSend(className)) {
                        TabHomeFragment2.this.homeBottomSendLayout.setStartClass(TabHomeFragment2.this.getActivity(), className, true);
                    } else {
                        TabHomeFragment2.this.isBatchWatermark(className);
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), className));
                    }
                }
            });
            this.toolRV.setNestedScrollingEnabled(false);
        }
        this.homeCleanItemBeans = new ArrayList<>();
        this.homeCleanItemBeans.add(new HomeItemBean(CleanZombieDNDActivity.class, 2131230990, 2131558538, "无打扰检测", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(CleanFriendsActivity.class, 2131230994, 2131558509, "删除好友", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(ClearFriendRecoverActivity.class, 2131230987, 2131558559, "恢复删除", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(CleanGroupsActivity.class, 2131230989, 2131558546, "群清理", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(CleanUnReadActivity.class, 2131230987, 2131558563, "消息标为已读", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(CleanHomeMsgActivity.class, 2131230992, 2131558533, "消息清理", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(CleanCircleActivity.class, 2131230990, 2131558532, "清理朋友圈", "", false));
        this.homeCleanItemBeans.add(new HomeItemBean(InviteFriendActivity.class, 2131230989, 2131558611, "推广赚钱", "", false));
        if (this.homeCleanItemBeans != null && this.homeCleanItemBeans.size() > 0) {
            this.homeCleanItemAdapter = new HomeItemAdapter(this.homeCleanItemBeans, getContext());
            this.cleanRV.setAdapter(this.homeCleanItemAdapter);
            GridLayoutManager gridLayoutManager6 = new GridLayoutManager(getContext(), 4);
            this.cleanRV.addItemDecoration(new GridItemDecoration.Builder(getContext()).setHorizontalSpan(2131165321).setVerticalSpan(2131165320).setColorResource(2131100063).setShowLastLine(false).build());
            this.cleanRV.setLayoutManager(gridLayoutManager6);
            this.homeCleanItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                public void click(int i) {
                    if (i == 7) {
                        WebViewActivity.startActivity(TabHomeFragment2.this.getActivity(), "收益推广视频", QBangApis.INCOME_PROMOTE_URL, false, true);
                        return;
                    }
                    Class className = ((HomeItemBean) TabHomeFragment2.this.homeCleanItemBeans.get(i)).getClassName();
                    if (TabHomeFragment2.this.isVideoWatermark(className)) {
                        TabHomeFragment2.this.getActivity().checkUserRule(className);
                    } else if (TabHomeFragment2.this.isGroupSend(className)) {
                        TabHomeFragment2.this.homeBottomSendLayout.setStartClass(TabHomeFragment2.this.getActivity(), className, true);
                    } else {
                        TabHomeFragment2.this.isBatchWatermark(className);
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), className));
                    }
                }
            });
            this.cleanRV.setNestedScrollingEnabled(false);
            this.homeCompanyItemBeans = new ArrayList<>();
            this.homeCompanyItemBeans.add(new HomeItemBean(AutoAddGroupFansCompanyActivity.class, 0, 2131558547, "外部群加粉", "", false));
            this.homeCompanyItemBeans.add(new HomeItemBean(GroupSendTextImageToFriendCompanyActivity.class, 0, 2131558548, "群发客户", "", false));
            if (this.homeCompanyItemBeans != null && this.homeCompanyItemBeans.size() > 0) {
                this.homeCompanyItemAdapter = new HomeItemAdapter(this.homeCompanyItemBeans, getContext());
                this.companyRV.setAdapter(this.homeCompanyItemAdapter);
                this.companyRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
                this.homeCompanyItemAdapter.setOnClick(new HomeItemAdapter.OnClick() {
                    public void click(int i) {
                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), ((HomeItemBean) TabHomeFragment2.this.homeCompanyItemBeans.get(i)).getClassName()));
                    }
                });
                this.companyRV.setNestedScrollingEnabled(false);
            }
        }
        if (!((Boolean) SPUtils.get(MyApplication.getConText(), "version_open", true)).booleanValue()) {
            int marketVersionChannel = getMarketVersionChannel();
            if ("9".equals(Integer.valueOf(marketVersionChannel)) || "2".equals(Integer.valueOf(marketVersionChannel)) || "6".equals(Integer.valueOf(marketVersionChannel))) {
                this.homeItemBeans.remove(1);
                this.homeItemAdapter.notifyDataSetChanged();
                this.renmaiLayout.setVisibility(8);
                this.otherLayout.setVisibility(8);
                this.cleanLayout.setVisibility(8);
                this.companyLayout.setVisibility(8);
                return;
            }
            this.homeItemBeans.remove(1);
            this.homeItemAdapter.notifyDataSetChanged();
            this.renmaiLayout.setVisibility(8);
            this.otherLayout.setVisibility(8);
            this.cleanLayout.setVisibility(8);
            this.companyLayout.setVisibility(8);
        }
    }

    public int getMarketVersionChannel() {
        try {
            String[] split = PackageUtils.getVersionName(getActivity()).split("\\.");
            if (split.length == 4) {
                return Integer.parseInt(split[3]);
            }
            return 10;
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_52");
            e.printStackTrace();
            return 10;
        }
    }

    private void initView() {
        this.handler.sendEmptyMessageDelayed(0, 3000);
    }

    /* access modifiers changed from: private */
    public void initData() {
        ApiWrapper.getInstance().getScroll(MacAddressUtils.getMacAddress(MyApplication.mContext), (String) null, new ApiWrapper.CallbackListener<ConmdBean<List<HomeBannerBean>>>() {
            public void onFinish(ConmdBean conmdBean) {
                if (TabHomeFragment2.this.refreshLayout != null) {
                    TabHomeFragment2.this.refreshLayout.finishRefresh();
                }
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            List list = (List) conmdBean.getData();
                            List unused = TabHomeFragment2.this.bannerBeans = new ArrayList();
                            TabHomeFragment2.this.singImgBean = new HomeBannerBean();
                            HomeBannerBean homeBannerBean = new HomeBannerBean();
                            for (int i = 0; i < list.size(); i++) {
                                HomeBannerBean homeBannerBean2 = (HomeBannerBean) list.get(i);
                                String type = homeBannerBean2.getType();
                                if ("ADVER".equals(type)) {
                                    TabHomeFragment2.this.bannerBeans.add(homeBannerBean2);
                                } else if ("SCROLL".equals(type)) {
                                    homeBannerBean = homeBannerBean2;
                                } else if ("BANNER".equals(type)) {
                                    TabHomeFragment2.this.singImgBean = homeBannerBean2;
                                } else if ("ADVER_PAY".equals(type)) {
                                    MyApplication.bannerPayBean = homeBannerBean2;
                                }
                            }
                            TabHomeFragment2.this.broad.setText(homeBannerBean.getScrollBar());
                            TabHomeFragment2.this.broad.setSelected(true);
                            if (TabHomeFragment2.this.bannerBeans.size() > 0) {
                                TabHomeFragment2.this.list_imgs.clear();
                                for (int i2 = 0; i2 < TabHomeFragment2.this.bannerBeans.size(); i2++) {
                                    HomeBannerBean homeBannerBean3 = (HomeBannerBean) TabHomeFragment2.this.bannerBeans.get(i2);
                                    String picUrl = homeBannerBean3.getPicUrl();
                                    if (picUrl != null && !picUrl.isEmpty()) {
                                        TabHomeFragment2.this.list_imgs.add(QBangApis.IMG_PREFIX_URL + picUrl);
                                        LogUtils.log("WS_BABY_IMGS=http://image.abcvabc.com/" + homeBannerBean3.getPicUrl());
                                    }
                                }
                                if (TabHomeFragment2.this.list_imgs.size() > 0) {
                                    TabHomeFragment2.this.banner.setAdapter(new ImgAdapter());
                                    if (TabHomeFragment2.this.list_imgs.size() > 1) {
                                        TabHomeFragment2.this.banner.setCurrentItem(1);
                                    }
                                }
                            }
                            String picUrl2 = TabHomeFragment2.this.singImgBean.getPicUrl();
                            if (picUrl2 == null || picUrl2.isEmpty()) {
                                TabHomeFragment2.this.singleVDImage.setVisibility(8);
                                return;
                            }
                            TabHomeFragment2.this.singleVDImage.setVisibility(0);
                            String str = QBangApis.IMG_PREFIX_URL + picUrl2;
                            LogUtils.log("WS_BABY_PIC=" + str);
                            Glide.with(MyApplication.getConText()).load(str).transform(new BitmapTransformation[]{new GlideRoundTransform(TabHomeFragment2.this.getActivity(), 8)}).into(TabHomeFragment2.this.singleVDImage);
                            TabHomeFragment2.this.singleVDImage.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    String url = TabHomeFragment2.this.singImgBean.getUrl();
                                    if ("video_water_mark_dislodge".equals(url)) {
                                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), CleanVideoWaterMarkctivity.class));
                                    } else if ("invitation_friend".equals(url)) {
                                        TabHomeFragment2.this.getActivity().startActivity(InviteFriendActivity.class, true, true);
                                    } else if ("invitation_reward".equals(url)) {
                                        TabHomeFragment2.this.getActivity().startActivity(InviteAwardActivity.class, true, true);
                                    } else if ("member_center".equals(url)) {
                                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), MemberCenterListActivity.class));
                                    } else if ("material_forwarding".equals(url)) {
                                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), OneKeyForwardMaterialActivity.class));
                                    } else if ("search_add".equals(url)) {
                                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), AutoAddSearchActivity.class));
                                    } else if ("passive_add".equals(url)) {
                                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), PassiveActivity.class));
                                    } else if ("clean_zombie_free".equals(url)) {
                                        TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), CleanZombieActivity.class));
                                    } else if (!"forward_big_video".equals(url)) {
                                        WebViewActivity.startActivity(TabHomeFragment2.this.getActivity(), "", url, false, true);
                                    }
                                }
                            });
                            return;
                        }
                        ToastUtils.showToast(TabHomeFragment2.this.getActivity(), conmdBean.getMessage());
                    } catch (Exception e) {
                        LogUtils.log("WS_BABY_Catch_53");
                        e.printStackTrace();
                    }
                }
            }

            public void onFailure(FailureModel failureModel) {
                TabHomeFragment2.this.refreshLayout.finishRefresh(false);
                ToastUtils.showToast(TabHomeFragment2.this.getActivity(), failureModel.getErrorMessage());
            }
        });
    }

    @OnClick({2131296373, 2131296415, 2131297052, 2131297031, 2131296475})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296475:
                startActivity(new Intent(getActivity(), AutoPullFriendsToAllGroupActivity.class));
                return;
            case 2131297031:
                startActivity(new Intent(getActivity(), OneKeyCopyWXHActivity.class));
                return;
            case 2131297052:
                WebViewActivity.startActivity(getActivity(), "常见问题·疑难解答", QBangApis.FAQ_URL, false);
                return;
            default:
                return;
        }
    }

    public boolean isVideoWatermark(Class cls) {
        if (cls != VideoWatermarkActivity.class) {
            return false;
        }
        MobclickAgent.onEvent(getActivity(), "NO_40_VIDEO_WATERMARK");
        MobclickAgent.onEvent((Context) getActivity(), "NO_40_VIDEO_WATERMARK", "视频水印");
        return true;
    }

    public boolean isGroupSend(Class cls) {
        Class cls2 = cls;
        if (cls2 != GroupSendMiniProgramsToFriendsActivity.class && cls2 != GroupSendMiniProgramsToGroupActivity.class && cls2 != GroupSendPublicNumberToFriendsActivity.class && cls2 != GroupSendPublicNumberToGroupActivity.class && cls2 != GroupSendCardToFriendsUtils.class && cls2 != GroupSendCardToGroupActivity.class && cls2 != GroupSendCollectionToFriendsActivity.class && cls2 != GroupSendCollectionToGroupActivity.class && cls2 != GroupSendTextImageToGroupActivity.class && cls2 != AutoAddContacts1Activity.class && cls2 != ContactActivity.class) {
            return false;
        }
        if (cls2 == AutoAddContacts1Activity.class || cls2 == AutoAddContacts2Activity.class) {
            this.homeBottomSendLayout.setInfo("通讯录加人", 2131558522, 2131558553, "自动通讯录", "自选通讯录", true);
            return true;
        }
        this.homeBottomSendLayout.setInfo("选择群发对象", 2131558572, 2131558573, "群发给好友", "发送到群聊", false);
        return true;
    }

    public void isBatchWatermark(Class cls) {
        if (cls != null && cls == WatermarkActivity.class) {
            MobclickAgent.onEvent(getActivity(), "NO_43_BATCH_VIDEO_WATERMARK");
            MobclickAgent.onEvent((Context) getActivity(), "NO_43_BATCH_VIDEO_WATERMARK", "批量水印");
        }
    }

    public void onResume() {
        TabHomeFragment2.super.onResume();
        initInviteData();
    }

    public void onDestroyView() {
        TabHomeFragment2.super.onDestroyView();
        this.unbinder.unbind();
    }

    private class ImgAdapter extends PagerAdapter {
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private ImgAdapter() {
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            final int size = i % TabHomeFragment2.this.list_imgs.size();
            ImageView imageView = (ImageView) LayoutInflater.from(TabHomeFragment2.this.getActivity()).inflate(2131427554, (ViewGroup) null).findViewById(2131296376);
            String str = (String) TabHomeFragment2.this.list_imgs.get(size);
            if (TabHomeFragment2.this.getActivity() != null && Util.isOnMainThread() && !TabHomeFragment2.this.getActivity().isFinishing()) {
                Glide.with(MyApplication.getConText()).load(str).transform(new BitmapTransformation[]{new GlideRoundTransform(TabHomeFragment2.this.getActivity(), 8)}).into(imageView);
            }
            imageView.setTag(Integer.valueOf(size));
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (TabHomeFragment2.this.bannerBeans != null && TabHomeFragment2.this.bannerBeans.size() > 0) {
                        HomeBannerBean homeBannerBean = (HomeBannerBean) TabHomeFragment2.this.bannerBeans.get(size);
                        if (homeBannerBean.getUrl() != null && !"".equals(homeBannerBean.getUrl())) {
                            String url = homeBannerBean.getUrl();
                            if ("video_water_mark_dislodge".equals(url)) {
                                TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), CleanVideoWaterMarkctivity.class));
                            } else if ("invitation_friend".equals(url)) {
                                TabHomeFragment2.this.getActivity().startActivity(InviteFriendActivity.class, true, true);
                            } else if ("invitation_reward".equals(url)) {
                                TabHomeFragment2.this.getActivity().startActivity(InviteAwardActivity.class, true, true);
                            } else if ("member_center".equals(url)) {
                                TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), MemberCenterListActivity.class));
                            } else if ("material_forwarding".equals(url)) {
                                TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), OneKeyForwardMaterialActivity.class));
                            } else if ("search_add".equals(url)) {
                                TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), AutoAddSearchActivity.class));
                            } else if ("passive_add".equals(url)) {
                                TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), PassiveActivity.class));
                            } else if ("clean_zombie_free".equals(url)) {
                                TabHomeFragment2.this.startActivity(new Intent(TabHomeFragment2.this.getActivity(), CleanZombieActivity.class));
                            } else if (!"forward_big_video".equals(url)) {
                                WebViewActivity.startActivity(TabHomeFragment2.this.getActivity(), "", url, false, true);
                            }
                        }
                    }
                }
            });
            viewGroup.addView(imageView);
            return imageView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            int size = i % TabHomeFragment2.this.list_imgs.size();
            viewGroup.removeView((View) obj);
        }
    }

    public void onHiddenChanged(boolean z) {
        TabHomeFragment2.super.onHiddenChanged(z);
        if (!z && ((Boolean) SPUtils.get(MyApplication.getConText(), "version_open", true)).booleanValue()) {
            ApkDownUtils.getInstance(getActivity()).checkVersion(false);
        }
    }

    public void onStart() {
        TabHomeFragment2.super.onStart();
    }
}
