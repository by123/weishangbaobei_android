package com.wx.assistants.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.wx.assistants.application.MyApplication;
import com.yongchun.library.view.photoview.PhotoView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import java.util.List;

public class PublicViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> imgList;

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public PublicViewPagerAdapter(Context context2, List<String> list) {
        this.context = context2;
        this.imgList = list;
    }

    public int getCount() {
        return this.imgList.size();
    }

    private View getItemView(int i) {
        return LayoutInflater.from(this.context).inflate(i, (ViewGroup) null, false);
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [com.yongchun.library.view.photoview.PhotoView, android.widget.ImageView] */
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View itemView = getItemView(2131427766);
        ? r1 = (PhotoView) itemView.findViewById(2131296801);
        JCVideoPlayer findViewById = itemView.findViewById(2131296867);
        findViewById.setBack(8);
        String str = this.imgList.get(i);
        if (str.endsWith(PictureFileUtils.POST_VIDEO)) {
            r1.setVisibility(8);
            findViewById.setVisibility(0);
            findViewById.setUp(str, str, "");
        } else {
            r1.setVisibility(0);
            findViewById.setVisibility(8);
            Glide.with(MyApplication.getConText()).load(str).into(r1);
        }
        viewGroup.addView(itemView);
        return itemView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
