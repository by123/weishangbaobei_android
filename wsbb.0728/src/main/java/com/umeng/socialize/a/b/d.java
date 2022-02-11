package com.umeng.socialize.a.b;

import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class d {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final String[] m = {"jpeg", "gif", "png", "bmp", "pcx", "iff", "ras", "pbm", "pgm", "ppm", "psd", "swf"};

    /* JADX WARNING: Removed duplicated region for block: B:120:0x0243 A[SYNTHETIC, Splitter:B:120:0x0243] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x026b A[SYNTHETIC, Splitter:B:129:0x026b] */
    public static String a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                int read = byteArrayInputStream.read();
                int read2 = byteArrayInputStream.read();
                if (read == 71 && read2 == 73) {
                    String str = m[1];
                    try {
                        byteArrayInputStream.close();
                        return str;
                    } catch (IOException e2) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e2.getMessage());
                        return str;
                    }
                } else if (read == 137 && read2 == 80) {
                    String str2 = m[2];
                    try {
                        byteArrayInputStream.close();
                        return str2;
                    } catch (IOException e3) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e3.getMessage());
                        return str2;
                    }
                } else if (read == 255 && read2 == 216) {
                    String str3 = m[0];
                    try {
                        byteArrayInputStream.close();
                        return str3;
                    } catch (IOException e4) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e4.getMessage());
                        return str3;
                    }
                } else if (read == 66 && read2 == 77) {
                    String str4 = m[3];
                    try {
                        byteArrayInputStream.close();
                        return str4;
                    } catch (IOException e5) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e5.getMessage());
                        return str4;
                    }
                } else if (read == 10 && read2 < 6) {
                    String str5 = m[4];
                    try {
                        byteArrayInputStream.close();
                        return str5;
                    } catch (IOException e6) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e6.getMessage());
                        return str5;
                    }
                } else if (read == 70 && read2 == 79) {
                    String str6 = m[5];
                    try {
                        byteArrayInputStream.close();
                        return str6;
                    } catch (IOException e7) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e7.getMessage());
                        return str6;
                    }
                } else if (read == 89 && read2 == 166) {
                    String str7 = m[6];
                    try {
                        byteArrayInputStream.close();
                        return str7;
                    } catch (IOException e8) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e8.getMessage());
                        return str7;
                    }
                } else if (read == 80 && read2 >= 49 && read2 <= 54) {
                    int i2 = read2 - 48;
                    if (i2 < 1 || i2 > 6) {
                        try {
                            byteArrayInputStream.close();
                            return "";
                        } catch (IOException e9) {
                            Log.um(UmengText.CHECK_FORMAT_ERROR + e9.getMessage());
                            return "";
                        }
                    } else {
                        String str8 = m[new int[]{7, 8, 9}[(i2 - 1) % 3]];
                        try {
                            byteArrayInputStream.close();
                            return str8;
                        } catch (IOException e10) {
                            Log.um(UmengText.CHECK_FORMAT_ERROR + e10.getMessage());
                            return str8;
                        }
                    }
                } else if (read == 56 && read2 == 66) {
                    String str9 = m[10];
                    try {
                        byteArrayInputStream.close();
                        return str9;
                    } catch (IOException e11) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e11.getMessage());
                        return str9;
                    }
                } else if (read == 70 && read2 == 87) {
                    String str10 = m[11];
                    try {
                        byteArrayInputStream.close();
                        return str10;
                    } catch (IOException e12) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e12.getMessage());
                        return str10;
                    }
                } else {
                    try {
                        byteArrayInputStream.close();
                        return "";
                    } catch (IOException e13) {
                        Log.um(UmengText.CHECK_FORMAT_ERROR + e13.getMessage());
                        return "";
                    }
                }
            } catch (Exception e14) {
                e = e14;
                try {
                    Log.um(UmengText.CHECK_FORMAT_ERROR + e.getMessage());
                    if (byteArrayInputStream != null) {
                    }
                    return "";
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e15) {
                            Log.um(UmengText.CHECK_FORMAT_ERROR + e15.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayInputStream != null) {
                }
                throw th;
            }
        } catch (Exception e16) {
            e = e16;
            byteArrayInputStream = null;
            Log.um(UmengText.CHECK_FORMAT_ERROR + e.getMessage());
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                    return "";
                } catch (IOException e17) {
                    Log.um(UmengText.CHECK_FORMAT_ERROR + e17.getMessage());
                    return "";
                }
            }
            return "";
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = null;
            if (byteArrayInputStream != null) {
            }
            throw th;
        }
    }
}
