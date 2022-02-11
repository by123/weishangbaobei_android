package com.umeng.commonsdk.internal.utils;

import android.os.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class g {
    private static final String a = "\n";
    private static final byte[] b = "\nexit\n".getBytes();
    private static byte[] c = new byte[32];

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0072, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0078, code lost:
        c(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007d, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0083, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0086, code lost:
        r3 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0071 A[ExcHandler: all (r1v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:13:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:88:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    public static String a(String... strArr) {
        Process process;
        InputStreamReader inputStreamReader;
        InputStream inputStream;
        InputStream inputStream2;
        OutputStream outputStream;
        BufferedReader bufferedReader;
        StringBuilder sb;
        StringBuilder sb2;
        try {
            process = new ProcessBuilder(new String[0]).command(strArr).start();
            try {
                outputStream = process.getOutputStream();
                try {
                    inputStream2 = process.getInputStream();
                    try {
                        inputStream = process.getErrorStream();
                        try {
                            outputStream.write(b);
                            outputStream.flush();
                            process.waitFor();
                            inputStreamReader = new InputStreamReader(inputStream2);
                        } catch (IOException e) {
                            inputStreamReader = null;
                            bufferedReader = null;
                            sb = null;
                            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                            if (process != null) {
                            }
                            if (sb == null) {
                            }
                        } catch (Exception e2) {
                            inputStreamReader = null;
                            bufferedReader = null;
                            sb = null;
                            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                            if (process != null) {
                            }
                            if (sb == null) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            inputStreamReader = null;
                            bufferedReader = null;
                            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                            if (process != null) {
                            }
                            throw th;
                        }
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (IOException e3) {
                            bufferedReader = null;
                            sb = null;
                            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                            if (process != null) {
                                sb2 = sb;
                                c(process);
                                sb = sb2;
                            }
                            if (sb == null) {
                            }
                        } catch (Exception e4) {
                            bufferedReader = null;
                            sb = null;
                            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                            if (process != null) {
                            }
                            if (sb == null) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = null;
                            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                            if (process != null) {
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                    } catch (Exception e6) {
                        inputStream = null;
                        inputStreamReader = null;
                        bufferedReader = null;
                        sb = null;
                        a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                        if (process != null) {
                        }
                        if (sb == null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = null;
                        inputStreamReader = null;
                        bufferedReader = null;
                        a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                        if (process != null) {
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                } catch (Exception e8) {
                    inputStream2 = null;
                    inputStream = null;
                    inputStreamReader = null;
                    bufferedReader = null;
                    sb = null;
                    a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                    if (process != null) {
                    }
                    if (sb == null) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream2 = null;
                    inputStream = null;
                    inputStreamReader = null;
                    bufferedReader = null;
                    a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                    if (process != null) {
                    }
                    throw th;
                }
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb = new StringBuilder();
                        sb.append(readLine);
                        sb.append(a);
                        while (true) {
                            String readLine2 = bufferedReader.readLine();
                            if (readLine2 != null) {
                                sb.append(readLine2);
                                sb.append(a);
                            }
                        }
                        do {
                        } while (inputStream.read(c) > 0);
                        a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                        if (process != null) {
                            sb2 = sb;
                            c(process);
                            sb = sb2;
                        }
                        if (sb == null) {
                            return null;
                        }
                        return sb.toString();
                    }
                    sb = null;
                    do {
                    } while (inputStream.read(c) > 0);
                    a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                    if (process != null) {
                    }
                } catch (IOException e9) {
                } catch (Exception e10) {
                    a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                    if (process != null) {
                    }
                    if (sb == null) {
                    }
                } catch (Throwable th5) {
                }
            } catch (IOException e11) {
            } catch (Exception e12) {
                outputStream = null;
                inputStream2 = null;
                inputStream = null;
                inputStreamReader = null;
                bufferedReader = null;
                sb = null;
                a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                if (process != null) {
                }
                if (sb == null) {
                }
            } catch (Throwable th6) {
                th = th6;
                outputStream = null;
                inputStream2 = null;
                inputStream = null;
                inputStreamReader = null;
                bufferedReader = null;
                a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
                if (process != null) {
                }
                throw th;
            }
        } catch (IOException e13) {
            process = null;
        } catch (Exception e14) {
            process = null;
            outputStream = null;
            inputStream2 = null;
            inputStream = null;
            inputStreamReader = null;
            bufferedReader = null;
            sb = null;
            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
            if (process != null) {
            }
            if (sb == null) {
            }
        } catch (Throwable th7) {
            th = th7;
            outputStream = null;
            inputStream2 = null;
            inputStream = null;
            inputStreamReader = null;
            bufferedReader = null;
            process = null;
            a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
            if (process != null) {
            }
            throw th;
        }
        if (sb == null) {
        }
        outputStream = null;
        inputStream2 = null;
        inputStream = null;
        inputStreamReader = null;
        bufferedReader = null;
        sb = null;
        a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
        if (process != null) {
        }
        if (sb == null) {
        }
        inputStream2 = null;
        inputStream = null;
        inputStreamReader = null;
        bufferedReader = null;
        sb = null;
        a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
        if (process != null) {
        }
        if (sb == null) {
        }
        inputStream = null;
        inputStreamReader = null;
        bufferedReader = null;
        sb = null;
        a(outputStream, inputStream, inputStream2, inputStreamReader, bufferedReader);
        if (process != null) {
        }
        if (sb == null) {
        }
    }

    private static void a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e3) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e4) {
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e5) {
            }
        }
    }

    private static void a(Process process) {
        int b2 = b(process);
        if (b2 != 0) {
            try {
                Process.killProcess(b2);
            } catch (Exception e) {
                try {
                    process.destroy();
                } catch (Exception e2) {
                }
            }
        }
    }

    private static int b(Process process) {
        String obj = process.toString();
        try {
            return Integer.parseInt(obj.substring(obj.indexOf("=") + 1, obj.indexOf("]")));
        } catch (Exception e) {
            return 0;
        }
    }

    private static void c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    a(process);
                }
            } catch (IllegalThreadStateException e) {
                a(process);
            }
        }
    }
}
