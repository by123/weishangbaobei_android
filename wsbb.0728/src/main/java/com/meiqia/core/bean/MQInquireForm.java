package com.meiqia.core.bean;

import org.json.JSONObject;

public class MQInquireForm {
    public static final String KEY_CAPTCHA = "captcha";
    public static final String KEY_INPUTS = "inputs";
    public static final String KEY_INPUTS_FIELDS = "fields";
    public static final String KEY_INPUTS_FIELDS_CHOICES = "choices";
    public static final String KEY_INPUTS_FIELDS_DISPLAY_NAME = "display_name";
    public static final String KEY_INPUTS_FIELDS_FIELD_NAME = "field_name";
    public static final String KEY_INPUTS_FIELDS_IGNORE_RETURNED_CUSTOMER = "ignore_returned_customer";
    public static final String KEY_INPUTS_FIELDS_OPTIONAL = "optional";
    public static final String KEY_INPUTS_FIELDS_TYPE = "type";
    public static final String KEY_INPUTS_TITLE = "title";
    public static final String KEY_MENUS = "menus";
    public static final String KEY_MENUS_ASSIGNMENTS = "assignments";
    public static final String KEY_MENUS_ASSIGNMENTS_DESCRIPTION = "description";
    public static final String KEY_MENUS_ASSIGNMENTS_TARGET = "target";
    public static final String KEY_MENUS_ASSIGNMENTS_TARGET_KIND = "target_kind";
    public static final String KEY_MENUS_TITLE = "title";
    public static final String KEY_STATUS = "status";
    public static final String KEY_VERSION = "version";
    public static final String STATUS_OPEN = "open";
    private boolean captcha;
    private JSONObject inputs = new JSONObject();
    private boolean isSubmitForm;
    private JSONObject menus = new JSONObject();
    private long version;

    public JSONObject getInputs() {
        return this.inputs;
    }

    public JSONObject getMenus() {
        return this.menus;
    }

    public long getVersion() {
        return this.version;
    }

    public boolean isCaptcha() {
        return this.captcha;
    }

    public boolean isInputsOpen() {
        return "open".equals(this.inputs.optString(KEY_STATUS));
    }

    public boolean isMenusOpen() {
        return "open".equals(this.menus.optString(KEY_STATUS));
    }

    public boolean isSubmitForm() {
        return this.isSubmitForm;
    }

    public void setCaptcha(boolean z) {
        this.captcha = z;
    }

    public void setInputs(JSONObject jSONObject) {
        this.inputs = jSONObject;
    }

    public void setMenus(JSONObject jSONObject) {
        this.menus = jSONObject;
    }

    public void setSubmitForm(boolean z) {
        this.isSubmitForm = z;
    }

    public void setVersion(long j) {
        this.version = j;
    }
}
