package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.utils.Log;

public class SendMessageToWX {

    public static class Req extends BaseReq {
        private static final int FAV_CONTENT_LENGTH_LIMIT = 26214400;
        private static final String TAG = "MicroMsg.SDK.SendMessageToWX.Req";
        public static final int WXSceneFavorite = 2;
        public static final int WXSceneSession = 0;
        public static final int WXSceneSpecifiedContact = 3;
        public static final int WXSceneTimeline = 1;
        public WXMediaMessage message;
        public int scene;
        public String userOpenId;

        public Req() {
        }

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        public boolean checkArgs() {
            String str;
            if (this.message == null) {
                str = "checkArgs fail ,message is null";
            } else {
                if (this.message.mediaObject.type() == 6 && this.scene == 2) {
                    ((WXFileObject) this.message.mediaObject).setContentLengthLimit(FAV_CONTENT_LENGTH_LIMIT);
                }
                if (this.scene == 3 && this.userOpenId == null) {
                    str = "Send specifiedContact userOpenId can not be null.";
                } else if (this.scene != 3 || this.openId != null) {
                    return this.message.checkArgs();
                } else {
                    str = "Send specifiedContact openid can not be null.";
                }
            }
            Log.e(TAG, str);
            return false;
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.message = WXMediaMessage.Builder.fromBundle(bundle);
            this.scene = bundle.getInt("_wxapi_sendmessagetowx_req_scene");
            this.userOpenId = bundle.getString("_wxapi_sendmessagetowx_req_use_open_id");
        }

        public int getType() {
            return 2;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putAll(WXMediaMessage.Builder.toBundle(this.message));
            bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.scene);
            bundle.putInt("_wxapi_sendmessagetowx_req_media_type", this.message.getType());
            bundle.putString("_wxapi_sendmessagetowx_req_use_open_id", this.userOpenId);
        }
    }

    public static class Resp extends BaseResp {
        public Resp() {
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        public boolean checkArgs() {
            return true;
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
        }

        public int getType() {
            return 2;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }

    private SendMessageToWX() {
    }
}
