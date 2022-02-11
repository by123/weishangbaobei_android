package com.wx.assistants.service;

import java.io.OutputStream;

public class MyProcess {
    private final Process mProcess;

    public MyProcess(String str) throws Exception {
        try {
            this.mProcess = Runtime.getRuntime().exec(str);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void exec(String str) throws Exception {
        OutputStream outputStream = this.mProcess.getOutputStream();
        try {
            outputStream.write((str + "\n").getBytes());
            outputStream.flush();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
