package com.wx.assistants.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.activity.AutoAddContacts1Activity;
import com.wx.assistants.activity.AutoAddContacts3Activity;
import com.wx.assistants.activity.ContactActivity;
import com.wx.assistants.activity.GroupSendCardToFriendsActivity;
import com.wx.assistants.activity.GroupSendCardToGroupActivity;
import com.wx.assistants.activity.GroupSendCollectionToFriendsActivity;
import com.wx.assistants.activity.GroupSendCollectionToGroupActivity;
import com.wx.assistants.activity.GroupSendMiniProgramsToFriendsActivity;
import com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity;
import com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity;
import com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity;
import com.wx.assistants.activity.GroupSendTextImageToFriendActivity;
import com.wx.assistants.activity.GroupSendTextImageToGroupActivity;
import com.wx.assistants.service_utils.GroupSendCardToFriendsUtils;

public class HomeBottomSendLayout extends LinearLayout implements View.OnClickListener {
    private LinearLayout bottomLayout;
    private TextView bottomText;
    private LinearLayout bottomWhiteLayout;
    private LinearLayout bottomWhiteTopLayout;
    private Class className;
    private FrameLayout groupSendFriend;
    private FrameLayout groupSendGroup;
    private ImageView leftImg;
    private TextView leftText;
    private Activity mActivity;
    private FrameLayout newFriendLayout;
    private ImageView rightImg;
    private TextView rightText;

    public HomeBottomSendLayout(Context context) {
        super(context);
    }

    public HomeBottomSendLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initCirculateNumView(context);
    }

    public HomeBottomSendLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void setGone() {
        setVisibility(8);
    }

    private void setVisibility() {
        setVisibility(0);
    }

    public void clickItem(int i) {
        if (this.className == GroupSendTextImageToGroupActivity.class) {
            setGone();
            if (i == 0) {
                this.mActivity.startActivity(new Intent(this.mActivity, GroupSendTextImageToFriendActivity.class));
                return;
            }
            this.mActivity.startActivity(new Intent(this.mActivity, GroupSendTextImageToGroupActivity.class));
        } else if (this.className == GroupSendCollectionToFriendsActivity.class || this.className == GroupSendCollectionToGroupActivity.class) {
            setGone();
            if (i == 0) {
                this.mActivity.startActivity(new Intent(this.mActivity, GroupSendCollectionToFriendsActivity.class));
                return;
            }
            this.mActivity.startActivity(new Intent(this.mActivity, GroupSendCollectionToGroupActivity.class));
        } else if (this.className == GroupSendCardToFriendsUtils.class || this.className == GroupSendCardToGroupActivity.class) {
            setGone();
            if (i == 0) {
                this.mActivity.startActivity(new Intent(this.mActivity, GroupSendCardToFriendsActivity.class));
                return;
            }
            this.mActivity.startActivity(new Intent(this.mActivity, GroupSendCardToGroupActivity.class));
        } else if (this.className == GroupSendPublicNumberToFriendsActivity.class || this.className == GroupSendPublicNumberToGroupActivity.class) {
            setGone();
            if (i == 0) {
                this.mActivity.startActivity(new Intent(this.mActivity, GroupSendPublicNumberToFriendsActivity.class));
                return;
            }
            this.mActivity.startActivity(new Intent(this.mActivity, GroupSendPublicNumberToGroupActivity.class));
        } else if (this.className == GroupSendMiniProgramsToFriendsActivity.class || this.className == GroupSendMiniProgramsToGroupActivity.class) {
            setGone();
            if (i == 0) {
                this.mActivity.startActivity(new Intent(this.mActivity, GroupSendMiniProgramsToFriendsActivity.class));
                return;
            }
            this.mActivity.startActivity(new Intent(this.mActivity, GroupSendMiniProgramsToGroupActivity.class));
        } else if (this.className == AutoAddContacts1Activity.class || this.className == ContactActivity.class || this.className == AutoAddContacts3Activity.class) {
            setGone();
            if (i == 0) {
                this.mActivity.startActivity(new Intent(this.mActivity, AutoAddContacts1Activity.class));
            } else if (i == 1) {
                this.mActivity.startActivity(new Intent(this.mActivity, ContactActivity.class));
            } else {
                this.mActivity.startActivity(new Intent(this.mActivity, AutoAddContacts3Activity.class));
            }
        }
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427555, this, true);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131296399:
            case 2131296408:
                setGone();
                return;
            case 2131296733:
                if (this.className != null) {
                    clickItem(0);
                    return;
                }
                return;
            case 2131296734:
                if (this.className != null) {
                    clickItem(1);
                    return;
                }
                return;
            case 2131297061:
                if (this.className != null) {
                    clickItem(2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.bottomLayout = (LinearLayout) findViewById(2131296399);
        this.bottomWhiteLayout = (LinearLayout) findViewById(2131296407);
        this.bottomWhiteTopLayout = (LinearLayout) findViewById(2131296408);
        this.bottomText = (TextView) findViewById(2131296406);
        this.groupSendFriend = (FrameLayout) findViewById(2131296733);
        this.groupSendGroup = (FrameLayout) findViewById(2131296734);
        this.newFriendLayout = (FrameLayout) findViewById(2131297061);
        this.bottomLayout.setOnClickListener(this);
        this.bottomWhiteTopLayout.setOnClickListener(this);
        this.groupSendFriend.setOnClickListener(this);
        this.groupSendGroup.setOnClickListener(this);
        this.newFriendLayout.setOnClickListener(this);
        this.leftText = (TextView) findViewById(2131296401);
        this.rightText = (TextView) findViewById(2131296405);
        this.rightImg = (ImageView) findViewById(2131296404);
        this.leftImg = (ImageView) findViewById(2131296400);
    }

    public void setInfo(String str, int i, int i2, String str2, String str3, boolean z) {
        this.bottomText.setText(str);
        this.leftText.setText(str2);
        this.rightText.setText(str3);
        this.leftImg.setBackgroundResource(i);
        this.rightImg.setBackgroundResource(i2);
        if (z) {
            this.newFriendLayout.setVisibility(0);
        } else {
            this.newFriendLayout.setVisibility(8);
        }
    }

    public void setStartClass(Activity activity, Class cls, boolean z) {
        this.mActivity = activity;
        this.className = cls;
        if (z) {
            setVisibility();
        } else {
            setGone();
        }
    }
}
