package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.wx.assistants.Enity.TagPeoplesBean;
import com.wx.assistants.adapter.TagMemberListAdapter;
import java.util.ArrayList;
import java.util.List;

public class TagMembersDialog {
    private TagMemberListAdapter adapter;
    private Button btn_pos;
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    private List<String> list = new ArrayList();
    private ListView listView;
    public OnEditTextListener listener;
    private TextView txt_title;

    public interface OnEditTextListener {
        void result(String str);
    }

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public TagMembersDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public TagMembersDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427517, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_title.setVisibility(0);
        this.listView = (ListView) inflate.findViewById(2131297457);
        this.btn_pos = (Button) inflate.findViewById(2131296445);
        this.btn_pos.setVisibility(0);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.9d), -2));
        return this;
    }

    public TagMembersDialog setTitle(String str) {
        if (str != null) {
            this.txt_title.setText(str);
        }
        return this;
    }

    public TagMembersDialog setMemberData(List<TagPeoplesBean> list2) {
        if (list2 != null && list2.size() > 0) {
            TextView textView = this.txt_title;
            textView.setText("标签[" + list2.get(0).getWxTagName() + "]成员");
            this.adapter = new TagMemberListAdapter(list2, this.context);
            this.listView.setAdapter(this.adapter);
        }
        return this;
    }

    public TagMembersDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public TagMembersDialog setPositiveButton(DialogInterface.OnClickListener onClickListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TagMembersDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public void show() {
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
