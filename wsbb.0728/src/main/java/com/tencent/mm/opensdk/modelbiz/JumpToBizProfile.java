package com.tencent.mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.utils.Log;

public class JumpToBizProfile {
    public static final int JUMP_TO_HARD_WARE_BIZ_PROFILE = 1;
    public static final int JUMP_TO_NORMAL_BIZ_PROFILE = 0;

    public static class Req extends BaseReq {
        private static final int EXT_MSG_LENGTH = 1024;
        private static final String TAG = "MicroMsg.SDK.JumpToBizProfile.Req";
        public String extMsg;
        public int profileType = 0;
        public String toUserName;

        public boolean checkArgs() {
            String str;
            if (this.toUserName == null || this.toUserName.length() == 0) {
                str = "checkArgs fail, toUserName is invalid";
            } else if (this.extMsg != null && this.extMsg.length() > 1024) {
                str = "ext msg is not null, while the length exceed 1024 bytes";
            } else if (this.profileType != 1) {
                return true;
            } else {
                if (this.extMsg != null && this.extMsg.length() != 0) {
                    return true;
                }
                str = "scene is jump to hardware profile, while extmsg is null";
            }
            Log.e(TAG, str);
            return false;
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.toUserName = bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name");
            this.extMsg = bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg");
        }

        public int getType() {
            return 7;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_profile_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_profile_req_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_scene", 0);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_profile_type", this.profileType);
        }
    }
}
