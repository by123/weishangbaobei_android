package com.wx.assistants.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import com.wx.assistants.Enity.ContactBean;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PhoneUtil {
    public static final String NICK_NAME = "display_name";
    public static final String NUM = "data1";
    private Context context;
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public PhoneUtil(Context context2) {
        this.context = context2;
    }

    public static boolean isPhoneNumber(String str) {
        if (str.length() != 11) {
            return false;
        }
        for (int i = 0; i < 11; i++) {
            if (!PhoneNumberUtils.isISODigit(str.charAt(i))) {
                return false;
            }
        }
        return Pattern.compile("^((13[^4,\\D])|(134[^9,\\D])|(14[5,7])|(15[^4,\\D])|(17[3,6-8])|(18[0-9]))\\d{8}$").matcher(str).matches();
    }

    public List<ContactBean> getPhone() {
        String string;
        ArrayList arrayList = new ArrayList();
        Cursor query = this.context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                String string2 = query.getString(query.getColumnIndex(NUM));
                if (string2 != null && !"".equals(string2)) {
                    if (string2.startsWith("+86")) {
                        string2 = string2.substring(string2.indexOf("+86") + 3);
                    }
                    if (string2.startsWith("86")) {
                        string2 = string2.substring(string2.indexOf("86") + 2);
                    }
                    if (string2.startsWith("+")) {
                        string2 = string2.substring(string2.indexOf("+") + 1);
                    }
                    String replace = string2.replace(" ", "").replace("-", "");
                    if (replace != null && !"".equals(replace) && replace.length() == 11 && !replace.startsWith("0")) {
                        try {
                            string = query.getString(query.getColumnIndex("display_name"));
                            if (string == null || "".equals(string)) {
                                string = query.getString(query.getColumnIndex("display_name"));
                            }
                        } catch (Exception e) {
                            string = query.getString(query.getColumnIndex("display_name"));
                        }
                        List<ContactBean> contact = SQLiteUtils.getInstance().getContact(replace);
                        if (contact == null || contact.size() <= 0) {
                            ContactBean contactBean = new ContactBean(string, replace, false);
                            SQLiteUtils.getInstance().addContact(contactBean);
                            arrayList.add(contactBean);
                        } else {
                            arrayList.add(contact.get(0));
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
