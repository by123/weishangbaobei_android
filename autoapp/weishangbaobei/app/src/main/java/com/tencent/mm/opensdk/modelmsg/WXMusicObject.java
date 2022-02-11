package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.utils.Log;

public class WXMusicObject implements WXMediaMessage.IMediaObject {
    private static final int LENGTH_LIMIT = 10240;
    private static final int LYRIC_LENGTH_LIMIT = 32768;
    private static final String TAG = "MicroMsg.SDK.WXMusicObject";
    public String musicDataUrl;
    public String musicLowBandDataUrl;
    public String musicLowBandUrl;
    public String musicUrl;
    public String songAlbumUrl;
    public String songLyric;

    public boolean checkArgs() {
        String str;
        String str2;
        if ((this.musicUrl == null || this.musicUrl.length() == 0) && (this.musicLowBandUrl == null || this.musicLowBandUrl.length() == 0)) {
            str2 = TAG;
            str = "both arguments are null";
        } else if (this.musicUrl != null && this.musicUrl.length() > LENGTH_LIMIT) {
            str2 = TAG;
            str = "checkArgs fail, musicUrl is too long";
        } else if (this.musicLowBandUrl != null && this.musicLowBandUrl.length() > LENGTH_LIMIT) {
            str2 = TAG;
            str = "checkArgs fail, musicLowBandUrl is too long";
        } else if (this.songAlbumUrl != null && this.songAlbumUrl.length() > LENGTH_LIMIT) {
            str2 = TAG;
            str = "checkArgs fail, songAlbumUrl is too long";
        } else if (this.songLyric == null || this.songLyric.length() <= LYRIC_LENGTH_LIMIT) {
            return true;
        } else {
            str2 = TAG;
            str = "checkArgs fail, songLyric is too long";
        }
        Log.e(str2, str);
        return false;
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxmusicobject_musicUrl", this.musicUrl);
        bundle.putString("_wxmusicobject_musicLowBandUrl", this.musicLowBandUrl);
        bundle.putString("_wxmusicobject_musicDataUrl", this.musicDataUrl);
        bundle.putString("_wxmusicobject_musicLowBandDataUrl", this.musicLowBandDataUrl);
        bundle.putString("_wxmusicobject_musicAlbumUrl", this.songAlbumUrl);
        bundle.putString("_wxmusicobject_musicLyric", this.songLyric);
    }

    public int type() {
        return 3;
    }

    public void unserialize(Bundle bundle) {
        this.musicUrl = bundle.getString("_wxmusicobject_musicUrl");
        this.musicLowBandUrl = bundle.getString("_wxmusicobject_musicLowBandUrl");
        this.musicDataUrl = bundle.getString("_wxmusicobject_musicDataUrl");
        this.musicLowBandDataUrl = bundle.getString("_wxmusicobject_musicLowBandDataUrl");
        this.songAlbumUrl = bundle.getString("_wxmusicobject_musicAlbumUrl");
        this.songLyric = bundle.getString("_wxmusicobject_musicLyric");
    }
}
