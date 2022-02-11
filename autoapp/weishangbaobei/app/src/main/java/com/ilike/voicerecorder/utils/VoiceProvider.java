package com.ilike.voicerecorder.utils;

public final class VoiceProvider {
    private static final String TAG = "VoiceProvider";
    private static VoiceProvider instance;
    private SettingsProvider settingsProvider;

    public interface SettingsProvider {
        boolean isSpeakerOpened();
    }

    private VoiceProvider() {
    }

    public static synchronized VoiceProvider getInstance() {
        VoiceProvider voiceProvider;
        synchronized (VoiceProvider.class) {
            if (instance == null) {
                instance = new VoiceProvider();
            }
            voiceProvider = instance;
        }
        return voiceProvider;
    }

    public void setSettingsProvider(SettingsProvider settingsProvider2) {
        this.settingsProvider = settingsProvider2;
    }

    public SettingsProvider getSettingsProvider() {
        if (this.settingsProvider == null) {
            this.settingsProvider = new DefaultSettingsProvider();
        }
        return this.settingsProvider;
    }

    protected class DefaultSettingsProvider implements SettingsProvider {
        public boolean isSpeakerOpened() {
            return true;
        }

        protected DefaultSettingsProvider() {
        }
    }
}
