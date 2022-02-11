package com.umeng.socialize;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.net.analytics.SocialAnalytics;
import com.umeng.socialize.shareboard.ShareBoard;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShareAction {
    private Activity activity;
    private ShareBoardlistener boardlistener = null;
    /* access modifiers changed from: private */
    public List<ShareContent> contentlist = new ArrayList();
    private ShareBoardlistener defaultmulshareboardlistener = new ShareBoardlistener() {
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            ShareContent shareContent;
            int indexOf = ShareAction.this.displaylist.indexOf(share_media);
            int size = ShareAction.this.contentlist.size();
            if (size != 0) {
                if (indexOf < size) {
                    shareContent = (ShareContent) ShareAction.this.contentlist.get(indexOf);
                } else {
                    shareContent = (ShareContent) ShareAction.this.contentlist.get(size - 1);
                }
                ShareContent unused = ShareAction.this.mShareContent = shareContent;
            }
            int size2 = ShareAction.this.listenerlist.size();
            if (size2 != 0) {
                if (indexOf < size2) {
                    UMShareListener unused2 = ShareAction.this.mListener = (UMShareListener) ShareAction.this.listenerlist.get(indexOf);
                } else {
                    UMShareListener unused3 = ShareAction.this.mListener = (UMShareListener) ShareAction.this.listenerlist.get(size2 - 1);
                }
            }
            ShareAction.this.setPlatform(share_media);
            ShareAction.this.share();
        }
    };
    private ShareBoardlistener defaultshareboardlistener = new ShareBoardlistener() {
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            ShareAction.this.setPlatform(share_media);
            ShareAction.this.share();
        }
    };
    /* access modifiers changed from: private */
    public List<SHARE_MEDIA> displaylist = null;
    private int gravity = 80;
    /* access modifiers changed from: private */
    public List<UMShareListener> listenerlist = new ArrayList();
    /* access modifiers changed from: private */
    public UMShareListener mListener = null;
    private SHARE_MEDIA mPlatform = null;
    private ShareBoard mShareBoard;
    /* access modifiers changed from: private */
    public ShareContent mShareContent = new ShareContent();
    private List<SnsPlatform> platformlist = new ArrayList();
    private View showatView = null;

    public ShareAction(Activity activity2) {
        if (activity2 != null) {
            this.activity = (Activity) new WeakReference(activity2).get();
        }
    }

    public ShareContent getShareContent() {
        return this.mShareContent;
    }

    public boolean getUrlValid() {
        return this.mShareContent == null || this.mShareContent.mMedia == null || !(this.mShareContent.mMedia instanceof UMWeb) || this.mShareContent.mMedia.toUrl().startsWith("http");
    }

    public SHARE_MEDIA getPlatform() {
        return this.mPlatform;
    }

    public ShareAction setPlatform(SHARE_MEDIA share_media) {
        this.mPlatform = share_media;
        return this;
    }

    public ShareAction setCallback(UMShareListener uMShareListener) {
        this.mListener = uMShareListener;
        return this;
    }

    public ShareAction setShareboardclickCallback(ShareBoardlistener shareBoardlistener) {
        this.boardlistener = shareBoardlistener;
        return this;
    }

    public ShareAction setShareContent(ShareContent shareContent) {
        this.mShareContent = shareContent;
        return this;
    }

    public ShareAction setDisplayList(SHARE_MEDIA... share_mediaArr) {
        this.displaylist = Arrays.asList(share_mediaArr);
        this.platformlist.clear();
        for (SHARE_MEDIA snsPlatform : this.displaylist) {
            this.platformlist.add(snsPlatform.toSnsPlatform());
        }
        return this;
    }

    @Deprecated
    public ShareAction setListenerList(UMShareListener... uMShareListenerArr) {
        this.listenerlist = Arrays.asList(uMShareListenerArr);
        return this;
    }

    @Deprecated
    public ShareAction setContentList(ShareContent... shareContentArr) {
        if (shareContentArr == null || Arrays.asList(shareContentArr).size() == 0) {
            ShareContent shareContent = new ShareContent();
            shareContent.mText = "empty";
            this.contentlist.add(shareContent);
        } else {
            this.contentlist = Arrays.asList(shareContentArr);
        }
        return this;
    }

    public ShareAction addButton(String str, String str2, String str3, String str4) {
        this.platformlist.add(SHARE_MEDIA.createSnsPlatform(str, str2, str3, str4, 0));
        return this;
    }

    public ShareAction withText(String str) {
        this.mShareContent.mText = str;
        return this;
    }

    public ShareAction withSubject(String str) {
        this.mShareContent.subject = str;
        return this;
    }

    public ShareAction withFile(File file) {
        this.mShareContent.file = file;
        return this;
    }

    public ShareAction withApp(File file) {
        this.mShareContent.app = file;
        return this;
    }

    public ShareAction withMedia(UMImage uMImage) {
        this.mShareContent.mMedia = uMImage;
        return this;
    }

    public ShareAction withMedia(UMMin uMMin) {
        this.mShareContent.mMedia = uMMin;
        return this;
    }

    public ShareAction withMedia(UMEmoji uMEmoji) {
        this.mShareContent.mMedia = uMEmoji;
        return this;
    }

    public ShareAction withMedia(UMWeb uMWeb) {
        this.mShareContent.mMedia = uMWeb;
        return this;
    }

    public ShareAction withFollow(String str) {
        this.mShareContent.mFollow = str;
        return this;
    }

    public ShareAction withExtra(UMImage uMImage) {
        this.mShareContent.mExtra = uMImage;
        return this;
    }

    public ShareAction withMedia(UMusic uMusic) {
        this.mShareContent.mMedia = uMusic;
        return this;
    }

    public ShareAction withMedia(UMVideo uMVideo) {
        this.mShareContent.mMedia = uMVideo;
        return this;
    }

    public ShareAction withShareBoardDirection(View view, int i) {
        this.gravity = i;
        this.showatView = view;
        return this;
    }

    public void share() {
        UMShareAPI.get(this.activity).doShare(this.activity, this, this.mListener);
    }

    public void open(ShareBoardConfig shareBoardConfig) {
        if (this.platformlist.size() != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("listener", this.mListener);
            hashMap.put("content", this.mShareContent);
            try {
                this.mShareBoard = new ShareBoard(this.activity, this.platformlist, shareBoardConfig);
                if (this.boardlistener == null) {
                    this.mShareBoard.setShareBoardlistener(this.defaultmulshareboardlistener);
                } else {
                    this.mShareBoard.setShareBoardlistener(this.boardlistener);
                }
                this.mShareBoard.setFocusable(true);
                this.mShareBoard.setBackgroundDrawable(new BitmapDrawable());
                if (this.showatView == null) {
                    this.showatView = this.activity.getWindow().getDecorView();
                }
                this.mShareBoard.showAtLocation(this.showatView, this.gravity, 0, 0);
            } catch (Exception unused) {
                Log.e("");
            }
        } else {
            this.platformlist.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
            this.platformlist.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
            this.platformlist.add(SHARE_MEDIA.SINA.toSnsPlatform());
            this.platformlist.add(SHARE_MEDIA.QQ.toSnsPlatform());
            HashMap hashMap2 = new HashMap();
            hashMap2.put("listener", this.mListener);
            hashMap2.put("content", this.mShareContent);
            this.mShareBoard = new ShareBoard(this.activity, this.platformlist, shareBoardConfig);
            if (this.boardlistener == null) {
                this.mShareBoard.setShareBoardlistener(this.defaultshareboardlistener);
            } else {
                this.mShareBoard.setShareBoardlistener(this.boardlistener);
            }
            this.mShareBoard.setFocusable(true);
            this.mShareBoard.setBackgroundDrawable(new BitmapDrawable());
            if (this.showatView == null) {
                this.showatView = this.activity.getWindow().getDecorView();
            }
            this.mShareBoard.showAtLocation(this.showatView, 80, 0, 0);
        }
        SocialAnalytics.shareBoardStats(this.activity);
    }

    public void open() {
        open((ShareBoardConfig) null);
    }

    public void close() {
        if (this.mShareBoard != null) {
            this.mShareBoard.dismiss();
            this.mShareBoard = null;
        }
    }

    public static Rect locateView(View view) {
        int[] iArr = new int[2];
        if (view == null) {
            return null;
        }
        try {
            view.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            rect.left = iArr[0];
            rect.top = iArr[1];
            rect.right = rect.left + view.getWidth();
            rect.bottom = rect.top + view.getHeight();
            return rect;
        } catch (NullPointerException unused) {
            return null;
        }
    }
}
