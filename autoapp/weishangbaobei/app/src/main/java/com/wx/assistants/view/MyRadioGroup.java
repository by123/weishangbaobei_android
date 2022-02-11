package com.wx.assistants.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressLint({"NewApi"})
public class MyRadioGroup extends LinearLayout {
    /* access modifiers changed from: private */
    public static final String TAG = "MyRadioGroup";
    /* access modifiers changed from: private */
    public int mCheckedId = -1;
    /* access modifiers changed from: private */
    public CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    /* access modifiers changed from: private */
    public boolean mProtectFromCheckedChange = false;
    /* access modifiers changed from: private */
    public HashMap<Integer, RadioButton> mRadioButtons = new HashMap<>();

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MyRadioGroup myRadioGroup, int i);
    }

    public MyRadioGroup(Context context) {
        super(context);
        setOrientation(1);
        init();
    }

    public MyRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(getOrientation());
        init();
    }

    private void init() {
        this.mChildOnCheckedChangeListener = new CheckedStateTracker();
        this.mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(this.mPassThroughListener);
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        ViewGroup.OnHierarchyChangeListener unused = this.mPassThroughListener.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.mCheckedId != -1) {
            this.mProtectFromCheckedChange = true;
            setCheckedStateForView(this.mCheckedId, true);
            this.mProtectFromCheckedChange = false;
            setCheckedId(this.mCheckedId);
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) view;
            if (radioButton.isChecked()) {
                this.mProtectFromCheckedChange = true;
                if (this.mCheckedId != -1) {
                    setCheckedStateForView(this.mCheckedId, false);
                }
                this.mProtectFromCheckedChange = false;
                setCheckedId(radioButton.getId());
            }
        }
        super.addView(view, i, layoutParams);
    }

    public void check(int i) {
        if (i == -1 || i != this.mCheckedId) {
            if (this.mCheckedId != -1) {
                setCheckedStateForView(this.mCheckedId, false);
            }
            if (i != -1) {
                setCheckedStateForView(i, true);
            }
            setCheckedId(i);
        }
    }

    /* access modifiers changed from: private */
    public void setCheckedId(int i) {
        this.mCheckedId = i;
        if (this.mOnCheckedChangeListener != null) {
            this.mOnCheckedChangeListener.onCheckedChanged(this, this.mCheckedId);
        }
    }

    /* access modifiers changed from: private */
    public void setCheckedStateForView(int i, boolean z) {
        View findViewById = findViewById(i);
        if (findViewById != null && (findViewById instanceof RadioButton)) {
            ((RadioButton) findViewById).setChecked(z);
        }
    }

    public int getCheckedRadioButtonId() {
        return this.mCheckedId;
    }

    public void clearCheck() {
        check(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MyRadioGroup.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MyRadioGroup.class.getName());
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2, f);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        /* access modifiers changed from: protected */
        public void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            if (typedArray.hasValue(i)) {
                this.width = typedArray.getLayoutDimension(i, "layout_width");
            } else {
                this.width = -2;
            }
            if (typedArray.hasValue(i2)) {
                this.height = typedArray.getLayoutDimension(i2, "layout_height");
            } else {
                this.height = -2;
            }
        }
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!MyRadioGroup.this.mProtectFromCheckedChange) {
                boolean unused = MyRadioGroup.this.mProtectFromCheckedChange = true;
                if (MyRadioGroup.this.mCheckedId != -1) {
                    MyRadioGroup.this.setCheckedStateForView(MyRadioGroup.this.mCheckedId, false);
                }
                boolean unused2 = MyRadioGroup.this.mProtectFromCheckedChange = false;
                MyRadioGroup.this.setCheckedId(compoundButton.getId());
            }
        }
    }

    public RadioButton getRadioButtonByCheckedId(int i) {
        return this.mRadioButtons.get(Integer.valueOf(i));
    }

    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private int checkedCount;
        /* access modifiers changed from: private */
        public ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
            this.checkedCount = 0;
        }

        public ArrayList<RadioButton> findRadioButtons(ViewGroup viewGroup) {
            ArrayList<RadioButton> arrayList = new ArrayList<>();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof RadioButton) {
                    arrayList.add((RadioButton) childAt);
                } else if (childAt instanceof ViewGroup) {
                    arrayList.addAll(findRadioButtons((ViewGroup) childAt));
                }
            }
            return arrayList;
        }

        public void setIdAndOnCheckedChangeListener(RadioButton radioButton) {
            int id = radioButton.getId();
            if (id == -1) {
                id = View.generateViewId();
                radioButton.setId(id);
            }
            radioButton.setOnCheckedChangeListener(MyRadioGroup.this.mChildOnCheckedChangeListener);
            if (radioButton.isChecked()) {
                int i = this.checkedCount;
                this.checkedCount = i + 1;
                if (i > 1) {
                    Log.e(MyRadioGroup.TAG, "You can only select one RadioButton");
                }
                int unused = MyRadioGroup.this.mCheckedId = radioButton.getId();
            }
            if (this.checkedCount > 1) {
                MyRadioGroup.this.removeAllViews();
            }
            MyRadioGroup.this.mRadioButtons.put(Integer.valueOf(id), radioButton);
        }

        public void onChildViewAdded(View view, View view2) {
            if (view == MyRadioGroup.this && (view2 instanceof RadioButton)) {
                setIdAndOnCheckedChangeListener((RadioButton) view2);
            } else if (view == MyRadioGroup.this && (view2 instanceof ViewGroup)) {
                Iterator<RadioButton> it = findRadioButtons((ViewGroup) view2).iterator();
                while (it.hasNext()) {
                    setIdAndOnCheckedChangeListener(it.next());
                }
            }
            if (this.mOnHierarchyChangeListener != null) {
                this.mOnHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            if (view == MyRadioGroup.this && (view2 instanceof RadioButton)) {
                ((RadioButton) view2).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            }
            if (this.mOnHierarchyChangeListener != null) {
                this.mOnHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }
}
