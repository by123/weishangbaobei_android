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
import com.wx.assistants.activity.GroupSendVoiceToGroupActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.SelectVoiceEvent;
import com.wx.assistants.bean.VoiceEvent;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.manager.MediaWxManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class SelectVoiceWxRecordAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public boolean isHasPlay = false;
    private List<AnimationDrawable> mAnimationDrawables;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public List<RecordBean> mRecords;
    /* access modifiers changed from: private */
    public String path = "";

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

    public SelectVoiceWxRecordAdapter(Context context, List<RecordBean> list) {
        this.mContext = context;
        this.mRecords = list;
        this.mAnimationDrawables = new ArrayList();
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

    /* access modifiers changed from: private */
    public void resetData(int i) {
        for (RecordBean isPlaying : this.mRecords) {
            isPlaying.setIsPlaying(false);
        }
    }

    public int getCount() {
        return this.mRecords.size();
    }

    public Object getItem(int i) {
        return this.mRecords.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        String str;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(2131427564, (ViewGroup) null);
            ViewHolder viewHolder2 = new ViewHolder();
            viewHolder2.playLayout = (LinearLayout) view.findViewById(2131297146);
            viewHolder2.ieaLlSinger = (LinearLayout) view.findViewById(2131296761);
            viewHolder2.ieaTvVoicetime1 = (TextView) view.findViewById(2131296762);
            viewHolder2.voiceCollection = (TextView) view.findViewById(2131297655);
            viewHolder2.voiceToGroup = (TextView) view.findViewById(2131297660);
            viewHolder2.voiceForward = (TextView) view.findViewById(2131297657);
            viewHolder2.voiceDelete = (TextView) view.findViewById(2131297656);
            viewHolder2.voiceTime = (TextView) view.findViewById(2131297659);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
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
            } catch (Exception e) {
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
                if (!SelectVoiceWxRecordAdapter.this.isHasPlay) {
                    boolean unused = SelectVoiceWxRecordAdapter.this.isHasPlay = true;
                    SelectVoiceWxRecordAdapter.this.resetData(i);
                    SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
                    final AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
                    SelectVoiceWxRecordAdapter.this.resetAnim(animationDrawable);
                    animationDrawable.start();
                    recordBean.setIsPlaying(true);
                    SQLiteUtils.getInstance().updateRecord(recordBean);
                    MediaWxManager.release();
                    String unused2 = SelectVoiceWxRecordAdapter.this.path = recordBean.getPath();
                    String mp3LocPath = recordBean.getMp3LocPath();
                    if (mp3LocPath == null || "".equals(mp3LocPath)) {
                        LogUtils.log("WS_BABY_DDDD_1");
                        FileUtil.amrConvertMp3(SelectVoiceWxRecordAdapter.this.mContext, SelectVoiceWxRecordAdapter.this.path, new FileUtil.OnConvertMp3Listener() {
                            public void convertFail() {
                            }

                            public void convertSuccess(String str) {
                                String unused = SelectVoiceWxRecordAdapter.this.path = str;
                                recordBean.setMp3LocPath(SelectVoiceWxRecordAdapter.this.path);
                                SQLiteUtils.getInstance().updateRecord(recordBean);
                                MediaWxManager.playSound(SelectVoiceWxRecordAdapter.this.path, new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        boolean unused = SelectVoiceWxRecordAdapter.this.isHasPlay = false;
                                        SelectVoiceWxRecordAdapter.this.resetAnim(animationDrawable);
                                        animationDrawable.selectDrawable(0);
                                        animationDrawable.stop();
                                        MediaWxManager.release();
                                        recordBean.setIsPlaying(false);
                                        recordBean.setIsPlayed(true);
                                        SQLiteUtils.getInstance().updateRecord(recordBean);
                                        SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                        return;
                    }
                    LogUtils.log("WS_BABY_DDDD_0");
                    String unused3 = SelectVoiceWxRecordAdapter.this.path = mp3LocPath;
                    MediaWxManager.playSound(SelectVoiceWxRecordAdapter.this.path, new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            boolean unused = SelectVoiceWxRecordAdapter.this.isHasPlay = false;
                            SelectVoiceWxRecordAdapter.this.resetAnim(animationDrawable);
                            animationDrawable.selectDrawable(0);
                            animationDrawable.stop();
                            MediaWxManager.release();
                            recordBean.setIsPlaying(false);
                            recordBean.setIsPlayed(true);
                            SQLiteUtils.getInstance().updateRecord(recordBean);
                            SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
                        }
                    });
                    return;
                }
                boolean unused4 = SelectVoiceWxRecordAdapter.this.isHasPlay = false;
                AnimationDrawable animationDrawable2 = (AnimationDrawable) linearLayout.getBackground();
                SelectVoiceWxRecordAdapter.this.resetAnim(animationDrawable2);
                recordBean.setIsPlaying(false);
                recordBean.setIsPlayed(true);
                SQLiteUtils.getInstance().updateRecord(recordBean);
                MediaWxManager.release();
                animationDrawable2.stop();
                animationDrawable2.selectDrawable(0);
                SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.voiceDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogUIUtils.dialogDefault(AppManager.getAppManager().currentActivity(), "", "是否删除本条语音", "取消", "确定", (View.OnClickListener) null, new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            FileUtils.delete(recordBean.getPath());
                            FileUtils.delete(recordBean.getMp3LocPath());
                        } catch (Exception e) {
                        }
                        SQLiteUtils.getInstance().deleteRecord(recordBean);
                        ToastUtils.showToast(MyApplication.getConText(), "已删除");
                        SelectVoiceWxRecordAdapter.this.mRecords.remove(recordBean);
                        SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
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
                    SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
                    return;
                }
                recordBean.setIsCollection(false);
                recordBean.setVoiceTag(1);
                recordBean.setIsFromWx(true);
                SQLiteUtils.getInstance().updateRecord(recordBean);
                EventBus.getDefault().post(new VoiceEvent(2));
                SelectVoiceWxRecordAdapter.this.notifyDataSetChanged();
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
                String mp3LocPath = recordBean.getMp3LocPath();
                if (mp3LocPath == null || "".equals(mp3LocPath)) {
                    mp3LocPath = recordBean.getPath();
                }
                SPUtils.put(MyApplication.getConText(), "voice_origin_path", mp3LocPath);
                EventBus.getDefault().post(new SelectVoiceEvent(mp3LocPath));
                MyApplication.instance.getBaseActivity().finish();
            }
        });
        return view;
    }

    public void onDestroy() {
        int i = 0;
        try {
            if (this.mRecords != null && this.mRecords.size() > 0 && this.isHasPlay) {
                while (true) {
                    int i2 = i;
                    if (i2 < this.mRecords.size()) {
                        RecordBean recordBean = this.mRecords.get(i2);
                        if (recordBean != null && recordBean.isPlaying) {
                            MediaWxManager.release();
                            recordBean.setIsPlaying(false);
                            recordBean.setIsPlayed(true);
                            SQLiteUtils.getInstance().updateRecord(recordBean);
                        }
                        i = i2 + 1;
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void setData(List<RecordBean> list) {
        this.mRecords = list;
    }
}
