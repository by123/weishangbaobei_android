package com.meiqia.meiqiasdk.pw;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.model.ImageFolderModel;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQImageView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class MQPhotoFolderPw extends MQBasePopupWindow implements AdapterView.OnItemClickListener {
    public static final int ANIM_DURATION = 300;
    private Callback mCallback;
    private ListView mContentLv;
    private int mCurrentPosition;
    private FolderAdapter mFolderAdapter;
    private LinearLayout mRootLl;

    public interface Callback {
        void executeDismissAnim();

        void onSelectedFolder(int i);
    }

    public MQPhotoFolderPw(Activity activity, View view, Callback callback) {
        super(activity, R.layout.mq_pw_photo_folder, view, -1, -1);
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mRootLl = (LinearLayout) getViewById(R.id.root_ll);
        this.mContentLv = (ListView) getViewById(R.id.content_lv);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mRootLl.setOnClickListener(this);
        this.mContentLv.setOnItemClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        setAnimationStyle(16973824);
        setBackgroundDrawable(new ColorDrawable(-1879048192));
        this.mFolderAdapter = new FolderAdapter();
        this.mContentLv.setAdapter(this.mFolderAdapter);
    }

    public void setDatas(ArrayList<ImageFolderModel> arrayList) {
        this.mFolderAdapter.setDatas(arrayList);
    }

    public void show() {
        showAsDropDown(this.mAnchorView);
        ViewCompat.animate(this.mContentLv).translationY((float) (-this.mWindowRootView.getHeight())).setDuration(0).start();
        ViewCompat.animate(this.mContentLv).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(300).start();
        ViewCompat.animate(this.mRootLl).alpha(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(0).start();
        ViewCompat.animate(this.mRootLl).alpha(1.0f).setDuration(300).start();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!(this.mCallback == null || this.mCurrentPosition == i)) {
            this.mCallback.onSelectedFolder(i);
        }
        this.mCurrentPosition = i;
        dismiss();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.root_ll) {
            dismiss();
        }
    }

    public void dismiss() {
        ViewCompat.animate(this.mContentLv).translationY((float) (-this.mWindowRootView.getHeight())).setDuration(300).start();
        ViewCompat.animate(this.mRootLl).alpha(1.0f).setDuration(0).start();
        ViewCompat.animate(this.mRootLl).alpha(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(300).start();
        if (this.mCallback != null) {
            this.mCallback.executeDismissAnim();
        }
        this.mContentLv.postDelayed(new Runnable() {
            public void run() {
                MQPhotoFolderPw.super.dismiss();
            }
        }, 300);
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }

    private class FolderAdapter extends BaseAdapter {
        private List<ImageFolderModel> mDatas = new ArrayList();
        private int mImageHeight;
        private int mImageWidth;

        public long getItemId(int i) {
            return 0;
        }

        public FolderAdapter() {
            this.mImageWidth = MQUtils.getScreenWidth(MQPhotoFolderPw.this.mActivity) / 10;
            this.mImageHeight = this.mImageWidth;
        }

        public int getCount() {
            return this.mDatas.size();
        }

        public ImageFolderModel getItem(int i) {
            return this.mDatas.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            FolderViewHolder folderViewHolder;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mq_item_photo_folder, viewGroup, false);
                folderViewHolder = new FolderViewHolder();
                folderViewHolder.photoIv = (MQImageView) view.findViewById(R.id.photo_iv);
                folderViewHolder.nameTv = (TextView) view.findViewById(R.id.name_tv);
                folderViewHolder.countTv = (TextView) view.findViewById(R.id.count_tv);
                view.setTag(folderViewHolder);
            } else {
                folderViewHolder = (FolderViewHolder) view.getTag();
            }
            ImageFolderModel item = getItem(i);
            folderViewHolder.nameTv.setText(item.name);
            folderViewHolder.countTv.setText(String.valueOf(item.getCount()));
            MQImage.displayImage(MQPhotoFolderPw.this.mActivity, folderViewHolder.photoIv, item.coverPath, R.drawable.mq_ic_holder_light, R.drawable.mq_ic_holder_light, this.mImageWidth, this.mImageHeight, (MQImageLoader.MQDisplayImageListener) null);
            return view;
        }

        public void setDatas(ArrayList<ImageFolderModel> arrayList) {
            if (arrayList != null) {
                this.mDatas = arrayList;
            } else {
                this.mDatas.clear();
            }
            notifyDataSetChanged();
        }
    }

    private class FolderViewHolder {
        public TextView countTv;
        public TextView nameTv;
        public MQImageView photoIv;

        private FolderViewHolder() {
        }
    }
}
