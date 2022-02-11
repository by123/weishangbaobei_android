package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.util.MQEmotionUtil;
import com.meiqia.meiqiasdk.util.MQUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MQEmotionKeyboardLayout extends MQBaseCustomCompositeView {
    private static final int EMOTION_COLUMN = 7;
    private static final int EMOTION_PAGE_SIZE = 27;
    private static final int EMOTION_ROW = 4;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private ViewPager mContentVp;
    /* access modifiers changed from: private */
    public ArrayList<GridView> mGridViewList;
    /* access modifiers changed from: private */
    public ArrayList<ImageView> mIndicatorIvList;
    private LinearLayout mIndicatorLl;

    public interface Callback {
        void onDelete();

        void onInsert(String str);
    }

    class EmotionAdapter extends BaseAdapter {
        private List<String> mDatas;

        public EmotionAdapter(List<String> list) {
            this.mDatas = list;
        }

        public int getCount() {
            if (this.mDatas == null) {
                return 0;
            }
            return this.mDatas.size();
        }

        public String getItem(int i) {
            return this.mDatas.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = view == null ? View.inflate(MQEmotionKeyboardLayout.this.getContext(), R.layout.mq_item_emotion_keyboard, (ViewGroup) null) : view;
            ImageView imageView = (ImageView) inflate;
            if (i == getCount() - 1) {
                imageView.setImageResource(R.drawable.mq_emoji_delete);
                imageView.setVisibility(0);
            } else {
                String str = this.mDatas.get(i);
                if (TextUtils.isEmpty(str)) {
                    imageView.setVisibility(4);
                } else {
                    imageView.setImageResource(MQEmotionUtil.getImgByName(str));
                    imageView.setVisibility(0);
                }
            }
            return inflate;
        }
    }

    class EmotionPagerAdapter extends PagerAdapter {
        EmotionPagerAdapter() {
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) MQEmotionKeyboardLayout.this.mGridViewList.get(i));
        }

        public int getCount() {
            return MQEmotionKeyboardLayout.this.mGridViewList.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            viewGroup.addView((View) MQEmotionKeyboardLayout.this.mGridViewList.get(i));
            return MQEmotionKeyboardLayout.this.mGridViewList.get(i);
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public MQEmotionKeyboardLayout(Context context) {
        super(context);
    }

    public MQEmotionKeyboardLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MQEmotionKeyboardLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private GridView getGridView(int i) {
        int dip2px = MQUtils.dip2px(getContext(), 5.0f);
        GridView gridView = new GridView(getContext());
        gridView.setPadding(dip2px, dip2px, dip2px, dip2px);
        gridView.setNumColumns(7);
        gridView.setVerticalSpacing(dip2px);
        gridView.setHorizontalSpacing(dip2px);
        gridView.setOverScrollMode(2);
        gridView.setVerticalScrollBarEnabled(false);
        gridView.setVerticalFadingEdgeEnabled(false);
        gridView.setHorizontalScrollBarEnabled(false);
        gridView.setHorizontalFadingEdgeEnabled(false);
        gridView.setSelector(17170445);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                EmotionAdapter emotionAdapter = (EmotionAdapter) adapterView.getAdapter();
                if (i == emotionAdapter.getCount() - 1) {
                    if (MQEmotionKeyboardLayout.this.mCallback != null) {
                        MQEmotionKeyboardLayout.this.mCallback.onDelete();
                    }
                } else if (MQEmotionKeyboardLayout.this.mCallback != null) {
                    MQEmotionKeyboardLayout.this.mCallback.onInsert(emotionAdapter.getItem(i));
                }
            }
        });
        int i2 = i * 27;
        List asList = Arrays.asList(Arrays.copyOfRange(MQEmotionUtil.sEmotionKeyArr, i2, i2 + 27));
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(asList);
        arrayList.add("");
        gridView.setAdapter(new EmotionAdapter(arrayList));
        return gridView;
    }

    /* access modifiers changed from: protected */
    public int[] getAttrs() {
        return new int[0];
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_layout_emotion_keyboard;
    }

    /* access modifiers changed from: protected */
    public void initAttr(int i, TypedArray typedArray) {
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mContentVp = getViewById(R.id.vp_emotion_keyboard_content);
        this.mIndicatorLl = (LinearLayout) getViewById(R.id.ll_emotion_keyboard_indicator);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        this.mIndicatorIvList = new ArrayList<>();
        this.mGridViewList = new ArrayList<>();
        int length = (MQEmotionUtil.sEmotionKeyArr.length - 1) / 27;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int dip2px = MQUtils.dip2px(getContext(), 5.0f);
        layoutParams.setMargins(dip2px, dip2px, dip2px, dip2px);
        for (int i = 0; i < length + 1; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.mq_selector_emotion_indicator);
            imageView.setEnabled(false);
            this.mIndicatorIvList.add(imageView);
            this.mIndicatorLl.addView(imageView);
            this.mGridViewList.add(getGridView(i));
        }
        this.mIndicatorIvList.get(0).setEnabled(true);
        this.mContentVp.setAdapter(new EmotionPagerAdapter());
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mContentVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                for (int i2 = 0; i2 < MQEmotionKeyboardLayout.this.mIndicatorIvList.size(); i2++) {
                    ((ImageView) MQEmotionKeyboardLayout.this.mIndicatorIvList.get(i2)).setEnabled(false);
                }
                ((ImageView) MQEmotionKeyboardLayout.this.mIndicatorIvList.get(i)).setEnabled(true);
            }
        });
    }
}
