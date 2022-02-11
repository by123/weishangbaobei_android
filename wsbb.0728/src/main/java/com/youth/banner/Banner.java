package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;
import com.youth.banner.view.BannerViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Banner extends FrameLayout implements ViewPager.OnPageChangeListener {
    private BannerPagerAdapter adapter;
    private int bannerBackgroundImage;
    private ImageView bannerDefaultImage;
    /* access modifiers changed from: private */
    public OnBannerClickListener bannerListener;
    private int bannerStyle;
    private TextView bannerTitle;
    private Context context;
    /* access modifiers changed from: private */
    public int count;
    /* access modifiers changed from: private */
    public int currentItem;
    /* access modifiers changed from: private */
    public int delayTime;
    private DisplayMetrics dm;
    private int gravity;
    /* access modifiers changed from: private */
    public WeakHandler handler;
    private ImageLoaderInterface imageLoader;
    private List imageUrls;
    /* access modifiers changed from: private */
    public List<View> imageViews;
    private LinearLayout indicator;
    private List<ImageView> indicatorImages;
    private LinearLayout indicatorInside;
    private int indicatorSize;
    /* access modifiers changed from: private */
    public boolean isAutoPlay;
    private boolean isScroll;
    private int lastPosition;
    /* access modifiers changed from: private */
    public OnBannerListener listener;
    private int mIndicatorHeight;
    private int mIndicatorMargin;
    private int mIndicatorSelectedResId;
    private int mIndicatorUnselectedResId;
    private int mIndicatorWidth;
    private int mLayoutResId;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private BannerScroller mScroller;
    private TextView numIndicator;
    private TextView numIndicatorInside;
    private int scaleType;
    private int scrollTime;
    public String tag;
    /* access modifiers changed from: private */
    public final Runnable task;
    private int titleBackground;
    private int titleHeight;
    private int titleTextColor;
    private int titleTextSize;
    private LinearLayout titleView;
    private List<String> titles;
    /* access modifiers changed from: private */
    public BannerViewPager viewPager;

    class BannerPagerAdapter extends PagerAdapter {
        BannerPagerAdapter() {
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return Banner.this.imageViews.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            viewGroup.addView((View) Banner.this.imageViews.get(i));
            View view = (View) Banner.this.imageViews.get(i);
            if (Banner.this.bannerListener != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.e(Banner.this.tag, "你正在使用旧版点击事件接口，下标是从1开始，为了体验请更换为setOnBannerListener，下标从0开始计算");
                        Banner.this.bannerListener.OnBannerClick(i);
                    }
                });
            }
            if (Banner.this.listener != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Banner.this.listener.OnBannerClick(Banner.this.toRealPosition(i));
                    }
                });
            }
            return view;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public Banner(Context context2) {
        this(context2, (AttributeSet) null);
    }

    public Banner(Context context2, AttributeSet attributeSet) {
        this(context2, attributeSet, 0);
    }

    public Banner(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.tag = "banner";
        this.mIndicatorMargin = 5;
        this.bannerStyle = 1;
        this.delayTime = BannerConfig.TIME;
        this.scrollTime = BannerConfig.DURATION;
        this.isAutoPlay = true;
        this.isScroll = true;
        this.mIndicatorSelectedResId = R.drawable.gray_radius;
        this.mIndicatorUnselectedResId = R.drawable.white_radius;
        this.mLayoutResId = R.layout.banner;
        this.count = 0;
        this.gravity = -1;
        this.lastPosition = 1;
        this.scaleType = 1;
        this.handler = new WeakHandler();
        this.task = new Runnable() {
            public void run() {
                if (Banner.this.count > 1 && Banner.this.isAutoPlay) {
                    int unused = Banner.this.currentItem = (Banner.this.currentItem % (Banner.this.count + 1)) + 1;
                    if (Banner.this.currentItem == 1) {
                        Banner.this.viewPager.setCurrentItem(Banner.this.currentItem, false);
                        Banner.this.handler.post(Banner.this.task);
                        return;
                    }
                    Banner.this.viewPager.setCurrentItem(Banner.this.currentItem);
                    Banner.this.handler.postDelayed(Banner.this.task, (long) Banner.this.delayTime);
                }
            }
        };
        this.context = context2;
        this.titles = new ArrayList();
        this.imageUrls = new ArrayList();
        this.imageViews = new ArrayList();
        this.indicatorImages = new ArrayList();
        this.dm = context2.getResources().getDisplayMetrics();
        this.indicatorSize = this.dm.widthPixels / 80;
        initView(context2, attributeSet);
    }

    private void createIndicator() {
        this.indicatorImages.clear();
        this.indicator.removeAllViews();
        this.indicatorInside.removeAllViews();
        for (int i = 0; i < this.count; i++) {
            ImageView imageView = new ImageView(this.context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mIndicatorWidth, this.mIndicatorHeight);
            layoutParams.leftMargin = this.mIndicatorMargin;
            layoutParams.rightMargin = this.mIndicatorMargin;
            if (i == 0) {
                imageView.setImageResource(this.mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(this.mIndicatorUnselectedResId);
            }
            this.indicatorImages.add(imageView);
            if (this.bannerStyle == 1 || this.bannerStyle == 4) {
                this.indicator.addView(imageView, layoutParams);
            } else if (this.bannerStyle == 5) {
                this.indicatorInside.addView(imageView, layoutParams);
            }
        }
    }

    private void handleTypedArray(Context context2, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.Banner);
            this.mIndicatorWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_width, this.indicatorSize);
            this.mIndicatorHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_height, this.indicatorSize);
            this.mIndicatorMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_margin, 5);
            this.mIndicatorSelectedResId = obtainStyledAttributes.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
            this.mIndicatorUnselectedResId = obtainStyledAttributes.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);
            this.scaleType = obtainStyledAttributes.getInt(R.styleable.Banner_image_scale_type, this.scaleType);
            this.delayTime = obtainStyledAttributes.getInt(R.styleable.Banner_delay_time, BannerConfig.TIME);
            this.scrollTime = obtainStyledAttributes.getInt(R.styleable.Banner_scroll_time, BannerConfig.DURATION);
            this.isAutoPlay = obtainStyledAttributes.getBoolean(R.styleable.Banner_is_auto_play, true);
            this.titleBackground = obtainStyledAttributes.getColor(R.styleable.Banner_title_background, -1);
            this.titleHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_title_height, -1);
            this.titleTextColor = obtainStyledAttributes.getColor(R.styleable.Banner_title_textcolor, -1);
            this.titleTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_title_textsize, -1);
            this.mLayoutResId = obtainStyledAttributes.getResourceId(R.styleable.Banner_banner_layout, this.mLayoutResId);
            this.bannerBackgroundImage = obtainStyledAttributes.getResourceId(R.styleable.Banner_banner_default_image, R.drawable.no_banner);
            obtainStyledAttributes.recycle();
        }
    }

    private void initImages() {
        this.imageViews.clear();
        if (this.bannerStyle == 1 || this.bannerStyle == 4 || this.bannerStyle == 5) {
            createIndicator();
        } else if (this.bannerStyle == 3) {
            TextView textView = this.numIndicatorInside;
            textView.setText("1/" + this.count);
        } else if (this.bannerStyle == 2) {
            TextView textView2 = this.numIndicator;
            textView2.setText("1/" + this.count);
        }
    }

    private void initView(Context context2, AttributeSet attributeSet) {
        this.imageViews.clear();
        handleTypedArray(context2, attributeSet);
        View inflate = LayoutInflater.from(context2).inflate(this.mLayoutResId, this, true);
        this.bannerDefaultImage = (ImageView) inflate.findViewById(R.id.bannerDefaultImage);
        this.viewPager = (BannerViewPager) inflate.findViewById(R.id.bannerViewPager);
        this.titleView = (LinearLayout) inflate.findViewById(R.id.titleView);
        this.indicator = (LinearLayout) inflate.findViewById(R.id.circleIndicator);
        this.indicatorInside = (LinearLayout) inflate.findViewById(R.id.indicatorInside);
        this.bannerTitle = (TextView) inflate.findViewById(R.id.bannerTitle);
        this.numIndicator = (TextView) inflate.findViewById(R.id.numIndicator);
        this.numIndicatorInside = (TextView) inflate.findViewById(R.id.numIndicatorInside);
        this.bannerDefaultImage.setImageResource(this.bannerBackgroundImage);
        initViewPagerScroll();
    }

    private void initViewPagerScroll() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.mScroller = new BannerScroller(this.viewPager.getContext());
            this.mScroller.setDuration(this.scrollTime);
            declaredField.set(this.viewPager, this.mScroller);
        } catch (Exception e) {
            Log.e(this.tag, e.getMessage());
        }
    }

    private void setBannerStyleUI() {
        int i = this.count > 1 ? 0 : 8;
        switch (this.bannerStyle) {
            case 1:
                this.indicator.setVisibility(i);
                return;
            case 2:
                this.numIndicator.setVisibility(i);
                return;
            case 3:
                this.numIndicatorInside.setVisibility(i);
                setTitleStyleUI();
                return;
            case 4:
                this.indicator.setVisibility(i);
                setTitleStyleUI();
                return;
            case 5:
                this.indicatorInside.setVisibility(i);
                setTitleStyleUI();
                return;
            default:
                return;
        }
    }

    private void setData() {
        this.currentItem = 1;
        if (this.adapter == null) {
            this.adapter = new BannerPagerAdapter();
            this.viewPager.addOnPageChangeListener(this);
        }
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setFocusable(true);
        this.viewPager.setCurrentItem(1);
        if (this.gravity != -1) {
            this.indicator.setGravity(this.gravity);
        }
        if (!this.isScroll || this.count <= 1) {
            this.viewPager.setScrollable(false);
        } else {
            this.viewPager.setScrollable(true);
        }
        if (this.isAutoPlay) {
            startAutoPlay();
        }
    }

    private void setImageList(List<?> list) {
        if (list == null || list.size() <= 0) {
            this.bannerDefaultImage.setVisibility(0);
            Log.e(this.tag, "The image data set is empty.");
            return;
        }
        this.bannerDefaultImage.setVisibility(8);
        initImages();
        int i = 0;
        while (i <= this.count + 1) {
            View view = null;
            if (this.imageLoader != null) {
                view = this.imageLoader.createImageView(this.context);
            }
            if (view == null) {
                view = new ImageView(this.context);
            }
            setScaleType(view);
            Object obj = i == 0 ? list.get(this.count - 1) : i == this.count + 1 ? list.get(0) : list.get(i - 1);
            this.imageViews.add(view);
            if (this.imageLoader != null) {
                this.imageLoader.displayImage(this.context, obj, view);
            } else {
                Log.e(this.tag, "Please set images loader.");
            }
            i++;
        }
    }

    private void setScaleType(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            switch (this.scaleType) {
                case 0:
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    return;
                case 1:
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    return;
                case 2:
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    return;
                case 3:
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    return;
                case 4:
                    imageView.setScaleType(ImageView.ScaleType.FIT_END);
                    return;
                case 5:
                    imageView.setScaleType(ImageView.ScaleType.FIT_START);
                    return;
                case 6:
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    return;
                case 7:
                    imageView.setScaleType(ImageView.ScaleType.MATRIX);
                    return;
                default:
                    return;
            }
        }
    }

    private void setTitleStyleUI() {
        if (this.titles.size() == this.imageUrls.size()) {
            if (this.titleBackground != -1) {
                this.titleView.setBackgroundColor(this.titleBackground);
            }
            if (this.titleHeight != -1) {
                this.titleView.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.titleHeight));
            }
            if (this.titleTextColor != -1) {
                this.bannerTitle.setTextColor(this.titleTextColor);
            }
            if (this.titleTextSize != -1) {
                this.bannerTitle.setTextSize(0, (float) this.titleTextSize);
            }
            if (this.titles != null && this.titles.size() > 0) {
                this.bannerTitle.setText(this.titles.get(0));
                this.bannerTitle.setVisibility(0);
                this.titleView.setVisibility(0);
                return;
            }
            return;
        }
        throw new RuntimeException("[Banner] --> The number of titles and images is different");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isAutoPlay) {
            int action = motionEvent.getAction();
            if (action == 1 || action == 3 || action == 4) {
                startAutoPlay();
            } else if (action == 0) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public Banner isAutoPlay(boolean z) {
        this.isAutoPlay = z;
        return this;
    }

    public void onPageScrollStateChanged(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
        switch (i) {
            case 0:
                if (this.currentItem == 0) {
                    this.viewPager.setCurrentItem(this.count, false);
                    return;
                } else if (this.currentItem == this.count + 1) {
                    this.viewPager.setCurrentItem(1, false);
                    return;
                } else {
                    return;
                }
            case 1:
                if (this.currentItem == this.count + 1) {
                    this.viewPager.setCurrentItem(1, false);
                    return;
                } else if (this.currentItem == 0) {
                    this.viewPager.setCurrentItem(this.count, false);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(toRealPosition(i), f, i2);
        }
    }

    public void onPageSelected(int i) {
        this.currentItem = i;
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(toRealPosition(i));
        }
        if (this.bannerStyle == 1 || this.bannerStyle == 4 || this.bannerStyle == 5) {
            this.indicatorImages.get(((this.lastPosition - 1) + this.count) % this.count).setImageResource(this.mIndicatorUnselectedResId);
            this.indicatorImages.get(((i - 1) + this.count) % this.count).setImageResource(this.mIndicatorSelectedResId);
            this.lastPosition = i;
        }
        if (i == 0) {
            i = this.count;
        }
        if (i > this.count) {
            i = 1;
        }
        switch (this.bannerStyle) {
            case 2:
                this.numIndicator.setText(i + "/" + this.count);
                return;
            case 3:
                this.numIndicatorInside.setText(i + "/" + this.count);
                this.bannerTitle.setText(this.titles.get(i + -1));
                return;
            case 4:
                this.bannerTitle.setText(this.titles.get(i - 1));
                return;
            case 5:
                this.bannerTitle.setText(this.titles.get(i - 1));
                return;
            default:
                return;
        }
    }

    public void releaseBanner() {
        this.handler.removeCallbacksAndMessages((Object) null);
    }

    public Banner setBannerAnimation(Class<? extends ViewPager.PageTransformer> cls) {
        try {
            setPageTransformer(true, cls.newInstance());
        } catch (Exception e) {
            Log.e(this.tag, "Please set the PageTransformer class");
        }
        return this;
    }

    public Banner setBannerStyle(int i) {
        this.bannerStyle = i;
        return this;
    }

    public Banner setBannerTitles(List<String> list) {
        this.titles = list;
        return this;
    }

    public Banner setDelayTime(int i) {
        this.delayTime = i;
        return this;
    }

    public Banner setImageLoader(ImageLoaderInterface imageLoaderInterface) {
        this.imageLoader = imageLoaderInterface;
        return this;
    }

    public Banner setImages(List<?> list) {
        this.imageUrls = list;
        this.count = list.size();
        return this;
    }

    public Banner setIndicatorGravity(int i) {
        switch (i) {
            case 5:
                this.gravity = 19;
                break;
            case 6:
                this.gravity = 17;
                break;
            case 7:
                this.gravity = 21;
                break;
        }
        return this;
    }

    public Banner setOffscreenPageLimit(int i) {
        if (this.viewPager != null) {
            this.viewPager.setOffscreenPageLimit(i);
        }
        return this;
    }

    @Deprecated
    public Banner setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.bannerListener = onBannerClickListener;
        return this;
    }

    public Banner setOnBannerListener(OnBannerListener onBannerListener) {
        this.listener = onBannerListener;
        return this;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public Banner setPageTransformer(boolean z, ViewPager.PageTransformer pageTransformer) {
        this.viewPager.setPageTransformer(z, pageTransformer);
        return this;
    }

    public Banner setViewPagerIsScroll(boolean z) {
        this.isScroll = z;
        return this;
    }

    public Banner start() {
        setBannerStyleUI();
        setImageList(this.imageUrls);
        setData();
        return this;
    }

    public void startAutoPlay() {
        this.handler.removeCallbacks(this.task);
        this.handler.postDelayed(this.task, (long) this.delayTime);
    }

    public void stopAutoPlay() {
        this.handler.removeCallbacks(this.task);
    }

    public int toRealPosition(int i) {
        int i2 = (i - 1) % this.count;
        return i2 < 0 ? i2 + this.count : i2;
    }

    public void update(List<?> list) {
        this.imageUrls.clear();
        this.imageViews.clear();
        this.indicatorImages.clear();
        this.imageUrls.addAll(list);
        this.count = this.imageUrls.size();
        start();
    }

    public void update(List<?> list, List<String> list2) {
        this.titles.clear();
        this.titles.addAll(list2);
        update(list);
    }

    public void updateBannerStyle(int i) {
        this.indicator.setVisibility(8);
        this.numIndicator.setVisibility(8);
        this.numIndicatorInside.setVisibility(8);
        this.indicatorInside.setVisibility(8);
        this.bannerTitle.setVisibility(8);
        this.titleView.setVisibility(8);
        this.bannerStyle = i;
        start();
    }
}
