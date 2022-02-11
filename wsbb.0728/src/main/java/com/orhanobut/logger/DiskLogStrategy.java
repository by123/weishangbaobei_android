package com.orhanobut.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DiskLogStrategy implements LogStrategy {
    @NonNull
    private final Handler handler;

    static class WriteHandler extends Handler {
        @NonNull
        private final String folder;
        private final int maxFileSize;

        WriteHandler(@NonNull Looper looper, @NonNull String str, int i) {
            super((Looper) Utils.checkNotNull(looper));
            this.folder = (String) Utils.checkNotNull(str);
            this.maxFileSize = i;
        }

        private File getLogFile(@NonNull String str, @NonNull String str2) {
            Utils.checkNotNull(str);
            Utils.checkNotNull(str2);
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, String.format("%s_%s.csv", new Object[]{str2, 0}));
            File file3 = null;
            int i = 0;
            while (file2.exists()) {
                i++;
                file3 = file2;
                file2 = new File(file, String.format("%s_%s.csv", new Object[]{str2, Integer.valueOf(i)}));
            }
            return (file3 == null || file3.length() >= ((long) this.maxFileSize)) ? file2 : file3;
        }

        private void writeLog(@NonNull FileWriter fileWriter, @NonNull String str) throws IOException {
            Utils.checkNotNull(fileWriter);
            Utils.checkNotNull(str);
            fileWriter.append(str);
        }

        public void handleMessage(@NonNull Message message) {
            FileWriter fileWriter;
            String str = (String) message.obj;
            try {
                FileWriter fileWriter2 = new FileWriter(getLogFile(this.folder, "logs"), true);
                try {
                    writeLog(fileWriter2, str);
                    fileWriter2.flush();
                    fileWriter2.close();
                } catch (IOException e) {
                    fileWriter = fileWriter2;
                }
            } catch (IOException e2) {
                fileWriter = null;
                if (fileWriter != null) {
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException e3) {
                    }
                }
            }
        }
    }

    public DiskLogStrategy(@NonNull Handler handler2) {
        this.handler = (Handler) Utils.checkNotNull(handler2);
    }

    public void log(int i, @Nullable String str, @NonNull String str2) {
        Utils.checkNotNull(str2);
        this.handler.sendMessage(this.handler.obtainMessage(i, str2));
    }
}
