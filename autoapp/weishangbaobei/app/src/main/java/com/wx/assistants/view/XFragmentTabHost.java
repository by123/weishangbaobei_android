package com.wx.assistants.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class XFragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost.OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList<TabInfo> mTabs = new ArrayList<>();

    static final class TabInfo {
        @Nullable
        final Bundle args;
        @NonNull
        final Fragment clss;
        Fragment fragment;
        @NonNull
        final String tag;

        TabInfo(@NonNull String str, @NonNull Fragment fragment2, @Nullable Bundle bundle) {
            this.tag = str;
            this.clss = fragment2;
            this.args = bundle;
        }
    }

    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            this.mContext = context;
        }

        public View createTabContent(String str) {
            View view = new View(this.mContext);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String curTab;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.curTab = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.curTab);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
        }
    }

    public XFragmentTabHost(Context context) {
        super(context, (AttributeSet) null);
        initFragmentTabHost(context, (AttributeSet) null);
    }

    public XFragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initFragmentTabHost(context, attributeSet);
    }

    private void initFragmentTabHost(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.mContainerId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    private void ensureHierarchy(Context context) {
        if (findViewById(16908307) == null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, CropImageView.DEFAULT_ASPECT_RATIO));
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(16908305);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(0, 0, CropImageView.DEFAULT_ASPECT_RATIO));
            FrameLayout frameLayout2 = new FrameLayout(context);
            this.mRealTabContent = frameLayout2;
            this.mRealTabContent.setId(this.mContainerId);
            linearLayout.addView(frameLayout2, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, FragmentManager fragmentManager) {
        ensureHierarchy(context);
        super.setup();
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        ensureContent();
    }

    public void setup(Context context, FragmentManager fragmentManager, int i) {
        ensureHierarchy(context);
        super.setup();
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mContainerId = i;
        ensureContent();
        this.mRealTabContent.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void ensureContent() {
        if (this.mRealTabContent == null) {
            this.mRealTabContent = (FrameLayout) findViewById(this.mContainerId);
            if (this.mRealTabContent == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.mContainerId);
            }
        }
    }

    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.mOnTabChangeListener = onTabChangeListener;
    }

    public void addTab(@NonNull TabHost.TabSpec tabSpec, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        tabSpec.setContent(new DummyTabFactory(this.mContext));
        this.mTabs.add(new TabInfo(tabSpec.getTag(), fragment, bundle));
        addTab(tabSpec);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.curTab = getCurrentTabTag();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.curTab);
    }

    public void onTabChanged(String str) {
        FragmentTransaction doTabChanged;
        if (this.mAttached && (doTabChanged = doTabChanged(str, (FragmentTransaction) null)) != null) {
            doTabChanged.commit();
        }
        if (this.mOnTabChangeListener != null) {
            this.mOnTabChangeListener.onTabChanged(str);
        }
    }

    @Nullable
    private FragmentTransaction doTabChanged(@Nullable String str, @Nullable FragmentTransaction fragmentTransaction) {
        TabInfo tabInfo = null;
        for (int i = 0; i < this.mTabs.size(); i++) {
            TabInfo tabInfo2 = this.mTabs.get(i);
            if (tabInfo2.tag.equals(str)) {
                tabInfo = tabInfo2;
            }
        }
        if (tabInfo != null) {
            if (this.mLastTab != tabInfo) {
                if (fragmentTransaction == null) {
                    fragmentTransaction = this.mFragmentManager.beginTransaction();
                }
                if (!(this.mLastTab == null || this.mLastTab.fragment == null)) {
                    fragmentTransaction.hide(this.mLastTab.fragment);
                }
                if (tabInfo != null) {
                    if (this.mFragmentManager.getFragments() == null || !this.mFragmentManager.getFragments().contains(tabInfo.fragment)) {
                        fragmentTransaction.add(this.mContainerId, tabInfo.fragment, tabInfo.tag);
                    } else {
                        fragmentTransaction.show(tabInfo.fragment);
                    }
                }
                this.mLastTab = tabInfo;
            }
            return fragmentTransaction;
        }
        throw new IllegalStateException("No tab known for tag " + str);
    }

    @Nullable
    private TabInfo getTabInfoForTag(String str) {
        int size = this.mTabs.size();
        for (int i = 0; i < size; i++) {
            TabInfo tabInfo = this.mTabs.get(i);
            if (tabInfo.tag.equals(str)) {
                return tabInfo;
            }
        }
        return null;
    }
}
