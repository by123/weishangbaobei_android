package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.meiqia.meiqiasdk.third.photoview.PhotoView;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.utils.LogUtils;
import java.util.List;

public class PreviewMultiImageActivity extends BaseActivity {
    private static final String TAG = "MultiPreviewActivity";
    private ImagePageAdapter mAdapter;
    /* access modifiers changed from: private */
    public List<String> mPictures;
    private ViewPager mViewPager;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    private int position;

    static {
        StubApp.interface11(9355);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
    }

    @OnClick({2131297049})
    public void onViewClicked() {
        back();
    }

    private class ImagePageAdapter extends PagerAdapter {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private ImagePageAdapter() {
        }

        public int getCount() {
            return PreviewMultiImageActivity.this.mPictures.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            PhotoView photoView = new PhotoView(viewGroup.getContext());
            try {
                Glide.with(MyApplication.getConText()).load((String) PreviewMultiImageActivity.this.mPictures.get(i)).into(photoView);
            } catch (Exception e) {
                LogUtils.log("WS_BABY_Catch_29");
                Log.w(PreviewMultiImageActivity.TAG, e);
            }
            viewGroup.addView(photoView);
            return photoView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }
}
