package com.wx.assistants.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.socialize.sina.params.ShareRequestParam;
import com.wx.assistants.activity.MemberCenterPassiveCardActivity;
import com.wx.assistants.activity.PassiveAddCardActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.PassiveCardBean;
import com.wx.assistants.bean.PassiveCardEvent;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.DialogUIUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class PassCardAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private List<PassiveCardBean> list;
    private OnClick onClick;
    private int thisPosition;

    public interface OnClick {
        void click(int i);
    }

    public int getThisPosition() {
        return this.thisPosition;
    }

    public void setThisPosition(int i) {
        this.thisPosition = i;
    }

    public PassCardAdapter(List<PassiveCardBean> list2, Context context2) {
        this.list = list2;
        this.context = context2;
    }

    public void updateData(List<PassiveCardBean> list2) {
        this.list.clear();
        if (list2 == null) {
            this.list = new ArrayList();
        } else {
            this.list = list2;
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427665, (ViewGroup) null));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final PassiveCardBean passiveCardBean = this.list.get(i);
        viewHolder.codeTv.setText(passiveCardBean.getWechatNo());
        if (passiveCardBean.getFanceStartTime() == null) {
            viewHolder.openPassive.setEnabled(true);
            viewHolder.openPassive.setText("开启被加");
        } else {
            viewHolder.openPassive.setEnabled(false);
            viewHolder.openPassive.setText("正被加中");
        }
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PassCardAdapter.this.context, PassiveAddCardActivity.class);
                intent.putExtra("id", passiveCardBean.getId());
                intent.putExtra(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE, passiveCardBean.getWechatNo());
                PassCardAdapter.this.context.startActivity(intent);
            }
        });
        viewHolder.openPassive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PassCardAdapter.this.context, MemberCenterPassiveCardActivity.class);
                intent.putExtra("id", passiveCardBean.getId());
                intent.putExtra(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE, passiveCardBean.getWechatNo());
                PassCardAdapter.this.context.startActivity(intent);
            }
        });
        viewHolder.deleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PassCardAdapter.this.deleteCard(passiveCardBean.getId(), passiveCardBean.getWechatNo());
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codeTv;
        LinearLayout deleteCard;
        LinearLayout itemLayout;
        Button openPassive;

        public ViewHolder(View view) {
            super(view);
            this.codeTv = (TextView) view.findViewById(2131296518);
            this.itemLayout = (LinearLayout) view.findViewById(2131296833);
            this.openPassive = (Button) view.findViewById(2131297089);
            this.deleteCard = (LinearLayout) view.findViewById(2131296578);
        }
    }

    public void setOnClick(OnClick onClick2) {
        this.onClick = onClick2;
    }

    public void deleteCard(final int i, String str) {
        Context context2 = this.context;
        DialogUIUtils.dialogDefault(context2, "删除被加号", "您是否确定要删除\n【" + str + "】", "取消", "确定", (View.OnClickListener) null, new View.OnClickListener() {
            public void onClick(View view) {
                ApiWrapper.getInstance().delCardInfo(Integer.valueOf(i), new ApiWrapper.CallbackListener<ConmdBean>() {
                    public void onFailure(FailureModel failureModel) {
                    }

                    public void onFinish(ConmdBean conmdBean) {
                        EventBus.getDefault().post(new PassiveCardEvent());
                    }
                });
            }
        });
    }
}
