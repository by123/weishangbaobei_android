package com.ilike.voicerecorder.utils;

public final class VoiceProvider {
    private static final String TAG = "VoiceProvider";
    private static VoiceProvider instance;
    private SettingsProvider settingsProvider;

    protected class DefaultSettingsProvider implements SettingsProvider {
        protected DefaultSettingsProvider() {
        }

        public boolean isSpeakerOpened() {
            return true;
        }
    }

    public interface SettingsProvider {
        boolean isSpeakerOpened();
    }

    private VoiceProvider() {
    }

    public static VoiceProvider getInstance() {
        VoiceProvider voiceProvider;
        synchronized (VoiceProvider.class) {
            try {
                if (instance == null) {
                    instance = new VoiceProvider();
                }
                voiceProvider = instance;
            } catch (Throwable th) {
                Class<VoiceProvider> cls = VoiceProvider.class;
                throw th;
            }
        }
        return voiceProvider;
    }

    public SettingsProvider getSettingsProvider() {
        if (this.settingsProvider == null) {
            this.settingsProvider = new DefaultSettingsProvider();
        }
        return this.settingsProvider;
    }

    public void setSettingsProvider(SettingsProvider settingsProvider2) {
        this.settingsProvider = settingsProvider2;
    }
}
