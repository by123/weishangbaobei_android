package com.meiqia.core.bean;

public class MQEnterpriseConfig {
    public static final String OPEN = "open";
    public static final String SINGLE = "single";
    public String avatar;
    public String ent_welcome_message;
    public Form form = new Form();
    public String public_nickname;
    public QueueingSetting queueingSetting = new QueueingSetting();
    public RobotSettings robotSettings = new RobotSettings();
    public boolean scheduler_after_client_send_msg;
    public ServiceEvaluationConfig serviceEvaluationConfig = new ServiceEvaluationConfig();
    public Survey survey = new Survey();
    public TicketConfig ticketConfig = new TicketConfig();

    public class Form {
        private String form_def;

        public Form() {
        }

        public String getForm_def() {
            return this.form_def;
        }

        public void setForm_def(String str) {
            this.form_def = str;
        }
    }

    public class QueueingSetting {
        private String intro;

        public QueueingSetting() {
        }

        public String getIntro() {
            return this.intro;
        }

        public void setIntro(String str) {
            this.intro = str;
        }
    }

    public class RobotSettings {
        private boolean show_switch;

        public RobotSettings() {
        }

        public boolean isShow_switch() {
            return this.show_switch;
        }

        public void setShow_switch(boolean z) {
            this.show_switch = z;
        }
    }

    public class ServiceEvaluationConfig {
        private String prompt_text;

        public ServiceEvaluationConfig() {
        }

        public String getPrompt_text() {
            return this.prompt_text;
        }

        public void setPrompt_text(String str) {
            this.prompt_text = str;
        }
    }

    public class Survey {
        private boolean has_submitted_form;
        private String status;

        public Survey() {
        }

        public String getStatus() {
            return this.status;
        }

        public boolean isHas_submitted_form() {
            return this.has_submitted_form;
        }

        public void setHas_submitted_form(boolean z) {
            this.has_submitted_form = z;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }

    public class TicketConfig {
        private String contactRule;
        private String defaultTemplate;
        private String defaultTemplateContent;
        private String email;
        private String intro;
        private String name;
        private String qq;
        private String tel;
        private String wechat;

        public TicketConfig() {
        }

        public String getContactRule() {
            return this.contactRule;
        }

        public String getDefaultTemplate() {
            return this.defaultTemplate;
        }

        public String getDefaultTemplateContent() {
            return this.defaultTemplateContent;
        }

        public String getEmail() {
            return this.email;
        }

        public String getIntro() {
            return this.intro;
        }

        public String getName() {
            return this.name;
        }

        public String getQq() {
            return this.qq;
        }

        public String getTel() {
            return this.tel;
        }

        public String getWechat() {
            return this.wechat;
        }

        public void setContactRule(String str) {
            this.contactRule = str;
        }

        public void setDefaultTemplate(String str) {
            this.defaultTemplate = str;
        }

        public void setDefaultTemplateContent(String str) {
            this.defaultTemplateContent = str;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public void setIntro(String str) {
            this.intro = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setQq(String str) {
            this.qq = str;
        }

        public void setTel(String str) {
            this.tel = str;
        }

        public void setWechat(String str) {
            this.wechat = str;
        }
    }
}
