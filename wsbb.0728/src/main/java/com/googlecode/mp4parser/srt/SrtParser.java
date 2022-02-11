package com.googlecode.mp4parser.srt;

import com.googlecode.mp4parser.authoring.tracks.TextTrackImpl;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class SrtParser {
    private static long parse(String str) {
        return (Long.parseLong(str.split(":")[0].trim()) * 60 * 60 * 1000) + (Long.parseLong(str.split(":")[1].trim()) * 60 * 1000) + (Long.parseLong(str.split(":")[2].split(ListUtils.DEFAULT_JOIN_SEPARATOR)[0].trim()) * 1000) + Long.parseLong(str.split(":")[2].split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1].trim());
    }

    public static TextTrackImpl parse(InputStream inputStream) throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream, "UTF-8"));
        TextTrackImpl textTrackImpl = new TextTrackImpl();
        while (lineNumberReader.readLine() != null) {
            String readLine = lineNumberReader.readLine();
            String str = "";
            while (true) {
                String readLine2 = lineNumberReader.readLine();
                if (readLine2 == null || readLine2.trim().equals("")) {
                    textTrackImpl.getSubs().add(new TextTrackImpl.Line(parse(readLine.split("-->")[0]), parse(readLine.split("-->")[1]), str));
                } else {
                    str = String.valueOf(str) + readLine2 + "\n";
                }
            }
            textTrackImpl.getSubs().add(new TextTrackImpl.Line(parse(readLine.split("-->")[0]), parse(readLine.split("-->")[1]), str));
        }
        return textTrackImpl;
    }
}
