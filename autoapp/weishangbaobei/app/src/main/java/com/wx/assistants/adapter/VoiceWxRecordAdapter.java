package com.wx.assistants.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.FileUtils;
import com.wx.assistants.Enity.RecordBean;
import com.wx.assistants.activity.GroupSendVoiceToFriendsActivity;
import com.wx.assistants.activity.GroupSendVoiceToGroupActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.VoiceEvent;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.manager.MediaWxManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class VoiceWxRecordAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public boolean isHasPlay = false;
    private List<AnimationDrawable> mAnimationDrawables;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public List<RecordBean> mRecords;
    /* access modifiers changed from: private */
    public String path = "";

    public long getItemId(int i) {
        return (long) i;
    }

    public VoiceWxRecordAdapter(Context context, List<RecordBean> list) {
        this.mContext = context;
        this.mRecords = list;
        this.mAnimationDrawables = new ArrayList();
    }

    public void setData(List<RecordBean> list) {
        this.mRecords = list;
    }

    public int getCount() {
        return this.mRecords.size();
    }

    public Object getItem(int i) {
        return this.mRecords.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        String str;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(2131427565, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.playLayout = (LinearLayout) view.findViewById(2131297146);
            viewHolder.ieaLlSinger = (LinearLayout) view.findViewById(2131296761);
            viewHolder.ieaTvVoicetime1 = (TextView) view.findViewById(2131296762);
            viewHolder.voiceCollection = (TextView) view.findViewById(2131297655);
            viewHolder.voiceToGroup = (TextView) view.findViewById(2131297660);
            viewHolder.voiceForward = (TextView) view.findViewById(2131297657);
            viewHolder.voiceDelete = (TextView) view.findViewById(2131297656);
            viewHolder.voiceTime = (TextView) view.findViewById(2131297659);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final RecordBean recordBean = this.mRecords.get(i);
        if (i == 0) {
            viewHolder.voiceTime.setVisibility(0);
            viewHolder.voiceTime.setText(DateUtils.convertDate2String(new Date(recordBean.getCreateTimeLong()), "yyyy年MM月dd日"));
        } else {
            try {
                long createTimeLong = this.mRecords.get(i - 1).getCreateTimeLong();
                long createTimeLong2 = this.mRecords.get(i).getCreateTimeLong();
                String convertDate2String = DateUtils.convertDate2String(new Date(createTimeLong), "yyyy年MM月dd日");
                String convertDate2String2 = DateUtils.convertDate2String(new Date(createTimeLong2), "yyyy年MM月dd日");
                if (convertDate2String.equals(convertDate2String2)) {
                    viewHolder.voiceTime.setVisibility(8);
                } else {
                    viewHolder.voiceTime.setVisibility(0);
                    viewHolder.voiceTime.setText(convertDate2String2);
                }
            } catch (Exception unused) {
                viewHolder.voiceTime.setVisibility(8);
            }
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) viewHolder.ieaLlSinger.getBackground();
        if (recordBean.isPlaying) {
            animationDrawable.start();
        } else {
            animationDrawable.selectDrawable(0);
            animationDrawable.stop();
        }
        if (recordBean.getIsCollection()) {
            viewHolder.voiceCollection.setText("取消收藏");
        } else {
            viewHolder.voiceCollection.setText("收藏");
        }
        TextView textView = viewHolder.ieaTvVoicetime1;
        if (recordBean.getSecond() <= 0) {
            str = "0''";
        } else {
            str = recordBean.getSecond() + "''";
        }
        textView.setText(str);
        final LinearLayout linearLayout = viewHolder.ieaLlSinger;
        viewHolder.playLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!VoiceWxRecordAdapter.this.isHasPlay) {
                    boolean unused = VoiceWxRecordAdapter.this.isHasPlay = true;
                    VoiceWxRecordAdapter.this.resetData(i);
                    VoiceWxRecordAdapter.this.notifyDataSetChanged();
                    final AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
                    VoiceWxRecordAdapter.this.resetAnim(animationDrawable);
                    animationDrawable.start();
                    recordBean.setIsPlaying(true);
                    SQLiteUtils.getInstance().updateRecord(recordBean);
                    MediaWxManager.release();
                    String unused2 = VoiceWxRecordAdapter.this.path = recordBean.getPath();
                    String mp3LocPath = recordBean.getMp3LocPath();
                    if (mp3LocPath == null || "".equals(mp3LocPath)) {
                        LogUtils.log("WS_BABY_DDDD_1");
                        FileUtil.amrConvertMp3(VoiceWxRecordAdapter.this.mContext, VoiceWxRecordAdapter.this.path, new FileUtil.OnConvertMp3Listener() {
                            public void convertFail() {
                            }

                            public void convertSuccess(String str) {
                                String unused = VoiceWxRecordAdapter.this.path = str;
                                recordBean.setMp3LocPath(VoiceWxRecordAdapter.this.path);
                                SQLiteUtils.getInstance().updateRecord(recordBean);
                                MediaWxManager.playSound(VoiceWxRecordAdapter.this.path, new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        boolean unused = VoiceWxRecordAdapter.this.isHasPlay = false;
                                        VoiceWxRecordAdapter.this.resetAnim(animationDrawable);
                                        animationDrawable.selectDrawable(0);
                                        animationDrawable.stop();
                                        MediaWxManager.release();
                                        recordBean.setIsPlaying(false);
                                        recordBean.setIsPlayed(true);
                                        SQLiteUtils.getInstance().updateRecord(recordBean);
                                        VoiceWxRecordAdapter.this.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                        return;
                    }
                    LogUtils.log("WS_BABY_DDDD_0");
                    String unused3 = VoiceWxRecordAdapter.this.path = mp3LocPath;
                    MediaWxManager.playSound(VoiceWxRecordAdapter.this.path, new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            boolean unused = VoiceWxRecordAdapter.this.isHasPlay = false;
                            VoiceWxRecordAdapter.this.resetAnim(animationDrawable);
                            animationDrawable.selectDrawable(0);
                            animationDrawable.stop();
                            MediaWxManager.release();
                            recordBean.setIsPlaying(false);
                            recordBean.setIsPlayed(true);
                            SQLiteUtils.getInstance().updateRecord(recordBean);
                            VoiceWxRecordAdapter.this.notifyDataSetChanged();
                        }
                    });
                    return;
                }
                boolean unused4 = VoiceWxRecordAdapter.this.isHasPlay = false;
                AnimationDrawable animationDrawable2 = (AnimationDrawable) linearLayout.getBackground();
                VoiceWxRecordAdapter.this.resetAnim(animationDrawable2);
                recordBean.setIsPlaying(false);
                recordBean.setIsPlayed(true);
                SQLiteUtils.getInstance().updateRecord(recordBean);
                MediaWxManager.release();
                animationDrawable2.stop();
                animationDrawable2.selectDrawable(0);
                VoiceWxRecordAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.voiceDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogUIUtils.dialogDefault(AppManager.getAppManager().currentActivity(), "", "是否删除本条语音", "取消", "确定", (View.OnClickListener) null, new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            FileUtils.delete(recordBean.getPath());
                            FileUtils.delete(recordBean.getMp3LocPath());
                        } catch (Exception unused) {
                        }
                        SQLiteUtils.getInstance().deleteRecord(recordBean);
                        ToastUtils.showToast(MyApplication.getConText(), "已删除");
                        VoiceWxRecordAdapter.this.mRecords.remove(recordBean);
                        VoiceWxRecordAdapter.this.notifyDataSetChanged();
                        if (recordBean.getIsCollection()) {
                            EventBus.getDefault().post(new VoiceEvent(2));
                        }
                    }
                });
            }
        });
        viewHolder.voiceCollection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ("收藏".equals(viewHolder.voiceCollection.getText().toString())) {
                    recordBean.setIsCollection(true);
                    recordBean.setVoiceTag(1);
                    recordBean.setIsFromWx(true);
                    SQLiteUtils.getInstance().updateRecord(recordBean);
                    EventBus.getDefault().post(new VoiceEvent(2));
                    VoiceWxRecordAdapter.this.notifyDataSetChanged();
                    return;
                }
                recordBean.setIsCollection(false);
                recordBean.setVoiceTag(1);
                recordBean.setIsFromWx(true);
                SQLiteUtils.getInstance().updateRecord(recordBean);
                EventBus.getDefault().post(new VoiceEvent(2));
                VoiceWxRecordAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.voiceToGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getConText(), GroupSendVoiceToGroupActivity.class);
                String mp3LocPath = recordBean.getMp3LocPath();
                if (mp3LocPath == null || "".equals(mp3LocPath)) {
                    intent.putExtra("voice_path", recordBean.getPath());
                } else {
                    intent.putExtra("voice_path", recordBean.getMp3LocPath());
                }
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        viewHolder.voiceForward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getConText(), GroupSendVoiceToFriendsActivity.class);
                String mp3LocPath = recordBean.getMp3LocPath();
                if (mp3LocPath == null || "".equals(mp3LocPath)) {
                    intent.putExtra("voice_path", recordBean.getPath());
                } else {
                    intent.putExtra("voice_path", recordBean.getMp3LocPath());
                }
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        return view;
    }

    /* access modifiers changed from: private */
    public void resetData(int i) {
        for (RecordBean isPlaying : this.mRecords) {
            isPlaying.setIsPlaying(false);
        }
    }

    /* access modifiers changed from: private */
    public void resetAnim(AnimationDrawable animationDrawable) {
        if (!this.mAnimationDrawables.contains(animationDrawable)) {
            this.mAnimationDrawables.add(animationDrawable);
        }
        for (AnimationDrawable next : this.mAnimationDrawables) {
            next.selectDrawable(0);
            next.stop();
        }
    }

    class ViewHolder {
        LinearLayout ieaLlSinger;
        TextView ieaTvVoicetime1;
        LinearLayout playLayout;
        TextView voiceCollection;
        TextView voiceDelete;
        TextView voiceForward;
        TextView voiceTime;
        TextView voiceToGroup;

        ViewHolder() {
        }
    }

    public void onDestroy() {
        try {
            if (this.mRecords != null && this.mRecords.size() > 0 && this.isHasPlay) {
                for (int i = 0; i < this.mRecords.size(); i++) {
                    RecordBean recordBean = this.mRecords.get(i);
                    if (recordBean != null && recordBean.isPlaying) {
                        MediaWxManager.release();
                        recordBean.setIsPlaying(false);
                        recordBean.setIsPlayed(true);
                        SQLiteUtils.getInstance().updateRecord(recordBean);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }
}
