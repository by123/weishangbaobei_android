package com.meiqia.meiqiasdk.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import java.util.List;
import java.util.Map;

public class MQListDialog extends Dialog {
    private ListView mListview;
    private TextView mTitleTv;

    public MQListDialog(Activity activity, @StringRes int i, List<Map<String, String>> list, AdapterView.OnItemClickListener onItemClickListener) {
        this(activity, activity.getString(i), list, onItemClickListener);
    }

    public MQListDialog(Activity activity, String str, List<Map<String, String>> list, final AdapterView.OnItemClickListener onItemClickListener) {
        super(activity, R.style.MQDialog);
        getWindow().setLayout(-1, -2);
        setContentView(R.layout.mq_dialog_ticket_categry);
        this.mTitleTv = (TextView) findViewById(R.id.tv_comfirm_title);
        this.mListview = (ListView) findViewById(R.id.list_lv);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        this.mTitleTv.setText(str);
        Activity activity2 = activity;
        List<Map<String, String>> list2 = list;
        this.mListview.setAdapter(new SimpleAdapter(activity2, list2, R.layout.mq_item_text_list, new String[]{"name"}, new int[]{16908308}));
        this.mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(adapterView, view, i, j);
                }
                MQListDialog.this.dismiss();
            }
        });
    }
}
