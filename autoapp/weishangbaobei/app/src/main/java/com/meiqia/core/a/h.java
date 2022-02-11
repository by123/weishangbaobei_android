package com.meiqia.core.a;

import com.meiqia.core.bean.MQMessage;
import java.util.Comparator;

public class h implements Comparator<MQMessage> {
    /* renamed from: a */
    public int compare(MQMessage mQMessage, MQMessage mQMessage2) {
        return mQMessage.getCreated_on() < mQMessage2.getCreated_on() ? -1 : 1;
    }
}
