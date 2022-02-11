package com.meiqia.core.a;

import android.text.TextUtils;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQClient;
import com.meiqia.core.bean.MQConversation;
import com.meiqia.core.bean.MQEnterpriseConfig;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.bean.MQMessage;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.umeng.qq.tencent.AuthActivity;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static MQMessage a(JSONObject jSONObject) {
        MQMessage mQMessage = new MQMessage("hybrid");
        long optLong = jSONObject.optLong("id");
        String optString = jSONObject.optJSONObject("body").optString("content");
        String optString2 = jSONObject.optString("created_on");
        if (TextUtils.isEmpty(optString2)) {
            optString2 = jSONObject.optString("created_at");
        }
        long a = j.a(optString2);
        String optString3 = jSONObject.optString("track_id");
        String optString4 = jSONObject.optString("agent_nickname");
        String optString5 = jSONObject.optString("avatar");
        String optString6 = jSONObject.optString("token");
        mQMessage.setEnterprise_id(jSONObject.optLong("enterprise_id"));
        mQMessage.setAgent_id(optString6);
        mQMessage.setId(optLong);
        mQMessage.setCreated_on(a);
        mQMessage.setContent(optString);
        mQMessage.setFrom_type("agent");
        mQMessage.setTrack_id(optString3);
        mQMessage.setAgent_nickname(optString4);
        mQMessage.setAvatar(optString5);
        return mQMessage;
    }

    public static String a(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        Iterator<String> it = map.keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return stringBuffer.toString();
            }
            String next = it.next();
            stringBuffer.append(i2 == 0 ? "?" : "&");
            stringBuffer.append(next);
            stringBuffer.append("=");
            stringBuffer.append(map.get(next));
            i = i2 + 1;
        }
    }

    private static String a(JSONObject jSONObject, String str) {
        JSONObject jSONObject2;
        try {
            jSONObject2 = new JSONObject(str);
        } catch (Exception e) {
            jSONObject2 = new JSONObject();
        }
        try {
            String optString = jSONObject.optString("content_robot");
            jSONObject2.put("sub_type", jSONObject.optString("sub_type"));
            jSONObject2.put("question_id", jSONObject.optLong("question_id"));
            if (TextUtils.isEmpty(optString)) {
                try {
                    optString = new JSONObject(str).optString("content_robot");
                } catch (JSONException e2) {
                    f.a("parseRobotPropertyToExtra JSONException");
                }
            }
            jSONObject2.put("content_robot", optString);
        } catch (JSONException e3) {
            jSONObject2 = new JSONObject();
        }
        return jSONObject2.toString();
    }

    public static List<MQMessage> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString(AuthActivity.ACTION_KEY);
                    if ("message".equals(optString) || "ticket_reply".equals(optString)) {
                        MQMessage b = b(optJSONObject);
                        if (!TextUtils.isEmpty(b.getExtra())) {
                            try {
                                if (new JSONObject(b.getExtra()).optBoolean("is_withdrawn", false)) {
                                }
                            } catch (JSONException e) {
                            }
                        }
                        arrayList.add(b);
                    }
                }
            }
        }
        return arrayList;
    }

    public static JSONArray a(Object obj) {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < length; i++) {
                jSONArray.put(b(Array.get(obj, i)));
            }
            return jSONArray;
        }
        throw new JSONException("Not a primitive data: " + obj.getClass());
    }

    public static JSONArray a(Collection collection) {
        JSONArray jSONArray = new JSONArray();
        if (collection != null) {
            for (Object b : collection) {
                jSONArray.put(b(b));
            }
        }
        return jSONArray;
    }

    public static JSONObject a(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (str != null) {
                try {
                    jSONObject.put(str, b(next.getValue()));
                } catch (Exception e) {
                    f.a("mapToJson Exception");
                }
            } else {
                throw new NullPointerException("key == null");
            }
        }
        return jSONObject;
    }

    public static JSONObject a(Response response) {
        try {
            return new JSONObject(b(response));
        } catch (Exception e) {
            Exception exc = e;
            JSONObject jSONObject = new JSONObject();
            f.a("responseToJsonObj : " + exc.toString());
            return jSONObject;
        }
    }

    public static void a(MQEnterpriseConfig mQEnterpriseConfig, JSONObject jSONObject, i iVar, MQClient mQClient) {
        JSONObject optJSONObject = jSONObject.optJSONObject("ticket_config");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("service_evaluation_config");
        JSONObject optJSONObject3 = jSONObject.optJSONObject("robot_settings");
        JSONObject optJSONObject4 = jSONObject.optJSONObject("survey");
        JSONObject optJSONObject5 = jSONObject.optJSONObject("form");
        JSONObject optJSONObject6 = jSONObject.optJSONObject("ent_welcome_message");
        JSONObject optJSONObject7 = jSONObject.optJSONObject("queueing_settings");
        if (optJSONObject6 != null) {
            String optString = optJSONObject6.optString("content");
            if (!TextUtils.equals(optJSONObject6.optString(MQInquireForm.KEY_STATUS, "open"), "open")) {
                optString = "";
            }
            String optString2 = jSONObject.optString("avatar");
            String optString3 = jSONObject.optString("public_nickname");
            boolean optBoolean = jSONObject.optBoolean("scheduler_after_client_send_msg", false);
            mQEnterpriseConfig.avatar = optString2;
            mQEnterpriseConfig.public_nickname = optString3;
            mQEnterpriseConfig.ent_welcome_message = optString;
            mQEnterpriseConfig.scheduler_after_client_send_msg = optBoolean;
        }
        if (optJSONObject != null) {
            mQEnterpriseConfig.ticketConfig.setIntro(optJSONObject.optString("intro"));
            mQEnterpriseConfig.ticketConfig.setContactRule(optJSONObject.optString("contactRule"));
            mQEnterpriseConfig.ticketConfig.setDefaultTemplateContent(optJSONObject.optString("defaultTemplateContent"));
            mQEnterpriseConfig.ticketConfig.setEmail(optJSONObject.optString("email"));
            mQEnterpriseConfig.ticketConfig.setQq(optJSONObject.optString("qq"));
            mQEnterpriseConfig.ticketConfig.setTel(optJSONObject.optString("tel"));
            mQEnterpriseConfig.ticketConfig.setWechat(optJSONObject.optString(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE));
            mQEnterpriseConfig.ticketConfig.setName(optJSONObject.optString("name"));
        }
        if (optJSONObject2 != null) {
            mQEnterpriseConfig.serviceEvaluationConfig.setPrompt_text(optJSONObject2.optString("prompt_text"));
        }
        if (optJSONObject3 != null) {
            mQEnterpriseConfig.robotSettings.setShow_switch(optJSONObject3.optBoolean("show_switch"));
        }
        if (optJSONObject4 != null) {
            mQEnterpriseConfig.survey.setHas_submitted_form(iVar.b(mQClient, "has_submitted_form", false));
            mQEnterpriseConfig.survey.setStatus(optJSONObject4.optString(MQInquireForm.KEY_STATUS));
        }
        if (optJSONObject5 != null) {
            mQEnterpriseConfig.form.setForm_def(optJSONObject5.optString("form_def"));
        }
        if (optJSONObject7 != null) {
            mQEnterpriseConfig.queueingSetting.setIntro(optJSONObject7.optString("intro"));
        }
    }

    public static void a(MQMessage mQMessage) {
        if (mQMessage.isRobot()) {
            try {
                JSONObject jSONObject = new JSONObject(mQMessage.getExtra());
                mQMessage.setContent_robot(jSONObject.optString("content_robot"));
                mQMessage.setSub_type(jSONObject.optString("sub_type"));
                mQMessage.setQuestion_id(jSONObject.optLong("question_id"));
                if (!MQMessage.ALL_SUB_TYPE.contains(mQMessage.getSub_type())) {
                    mQMessage.setSub_type("unknown");
                }
            } catch (JSONException e) {
                f.a("parseExtraToRobotProperty JSONException");
            }
        }
    }

    public static MQMessage b(JSONObject jSONObject) {
        String str;
        String str2;
        MQMessage mQMessage = new MQMessage("text");
        long optLong = jSONObject.optLong("id");
        long optLong2 = jSONObject.optLong("conversation_id");
        String optString = jSONObject.optString("content_type");
        String optString2 = jSONObject.optString("content");
        String optString3 = jSONObject.optString("type");
        String optString4 = jSONObject.optString("created_on");
        if (TextUtils.isEmpty(optString4)) {
            optString4 = jSONObject.optString("created_at");
        }
        long a = j.a(optString4);
        String optString5 = jSONObject.optString("from_type");
        String optString6 = jSONObject.optString("track_id");
        JSONObject optJSONObject = jSONObject.optJSONObject("agent");
        String str3 = "";
        if (optJSONObject != null) {
            str3 = optJSONObject.optString("nickname");
            str = optJSONObject.optString("avatar");
            str2 = optJSONObject.optString("token");
        } else {
            str = "";
            str2 = "";
        }
        String optString7 = jSONObject.optString("media_url");
        long optLong3 = jSONObject.optLong("enterprise_id");
        String optString8 = jSONObject.optString("extra");
        if (TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, optString5)) {
            optString8 = a(jSONObject, optString8);
            try {
                JSONObject jSONObject2 = new JSONObject(optString8);
                try {
                    if ((TextUtils.equals(jSONObject2.optString("sub_type"), "redirect") || TextUtils.equals(jSONObject2.optString("sub_type"), "manual_redirect") || TextUtils.equals(jSONObject2.optString("sub_type"), "queueing") || TextUtils.equals(jSONObject2.optString("sub_type"), "evaluate") || TextUtils.equals(jSONObject2.optString("sub_type"), "reply")) && TextUtils.equals(optString, "hybrid")) {
                        optString = MQMessage.TYPE_FROM_ROBOT;
                    }
                } catch (Exception e) {
                }
            } catch (Exception e2) {
            }
        }
        mQMessage.setExtra(optString8);
        mQMessage.setEnterprise_id(optLong3);
        mQMessage.setAgent_id(str2);
        mQMessage.setId(optLong);
        mQMessage.setConversation_id(optLong2);
        mQMessage.setContent_type(optString);
        mQMessage.setType(optString3);
        mQMessage.setCreated_on(a);
        mQMessage.setContent(optString2);
        mQMessage.setFrom_type(optString5);
        mQMessage.setTrack_id(optString6);
        mQMessage.setAgent_nickname(str3);
        mQMessage.setAvatar(str);
        mQMessage.setMedia_url(optString7);
        a(mQMessage);
        return mQMessage;
    }

    private static Object b(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                return a((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return a(obj);
            }
            if (obj instanceof Map) {
                return a((Map<?, ?>) (Map) obj);
            }
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String b(Response response) {
        try {
            return new String(response.body().bytes(), "UTF-8");
        } catch (Exception e) {
            f.a("responseToJsonObj : " + e.toString());
            return null;
        }
    }

    public static MQAgent c(JSONObject jSONObject) {
        MQAgent mQAgent = new MQAgent();
        long optLong = jSONObject.optLong("enterprise_id");
        String optString = jSONObject.optString("avatar");
        String optString2 = jSONObject.optString("cellphone");
        String optString3 = jSONObject.optString("nickname");
        String optString4 = jSONObject.optString("public_cellphone");
        String optString5 = jSONObject.optString("public_email");
        String optString6 = jSONObject.optString("qq");
        String optString7 = jSONObject.optString("signature");
        String optString8 = jSONObject.optString("telephone");
        String optString9 = jSONObject.optString("weixin");
        String optString10 = jSONObject.optString("token");
        String optString11 = jSONObject.optString(MQInquireForm.KEY_STATUS);
        boolean optBoolean = jSONObject.optBoolean("is_online");
        String optString12 = jSONObject.optString("privilege");
        int optInt = jSONObject.optInt("id");
        mQAgent.setEnterprise_id(optLong);
        mQAgent.setAvatar(optString);
        mQAgent.setCellphone(optString2);
        mQAgent.setNickname(optString3);
        mQAgent.setPublic_cellphone(optString4);
        mQAgent.setPublic_email(optString5);
        mQAgent.setQq(optString6);
        mQAgent.setSignature(optString7);
        mQAgent.setTelephone(optString8);
        mQAgent.setWeixin(optString9);
        mQAgent.setId(optString10);
        mQAgent.setStatus(optString11);
        mQAgent.setIsOnline(optBoolean);
        mQAgent.setPrivilege(optString12);
        mQAgent.setAgentId(optInt);
        return mQAgent;
    }

    public static MQConversation d(JSONObject jSONObject) {
        MQConversation mQConversation = new MQConversation();
        long optLong = jSONObject.optLong("id");
        int optInt = jSONObject.optInt("assignee");
        long optLong2 = jSONObject.optLong("enterprise_id");
        long a = j.a(jSONObject.optString("created_on"));
        mQConversation.setAssignee(optInt);
        mQConversation.setEnterprise_id(optLong2);
        mQConversation.setCreated_on(a);
        mQConversation.setId(optLong);
        return mQConversation;
    }
}
