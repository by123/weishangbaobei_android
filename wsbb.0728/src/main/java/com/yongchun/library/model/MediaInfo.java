package com.yongchun.library.model;

import java.util.Hashtable;

public class MediaInfo {
    public long duration;
    public int height;
    public int width;

    public static MediaInfo parse(String str) {
        MediaInfo mediaInfo = new MediaInfo();
        try {
            String[] split = str.split(";");
            Hashtable hashtable = new Hashtable();
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3 != null && split3.length > 1) {
                    hashtable.put(split3[0], split3[1]);
                }
            }
            if (hashtable.containsKey("duration")) {
                try {
                    String str2 = (String) hashtable.get("duration");
                    if (str2.contains("ms")) {
                        str2 = str2.replace("ms", "");
                    }
                    if (str2.contains(".")) {
                        str2 = str2.split("\\.")[0];
                    }
                    mediaInfo.duration = Long.parseLong(str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (hashtable.containsKey("videostream_codecpar_width")) {
                try {
                    mediaInfo.width = Integer.parseInt((String) hashtable.get("videostream_codecpar_width"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (hashtable.containsKey("videostream_codecpar_height")) {
                try {
                    mediaInfo.height = Integer.parseInt((String) hashtable.get("videostream_codecpar_height"));
                    return mediaInfo;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return mediaInfo;
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return mediaInfo;
    }
}
