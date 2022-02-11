package com.wx.assistants.bean;

import com.contrarywind.interfaces.IPickerViewData;
import java.util.List;

public class ShengBean implements IPickerViewData {
    public List<Shi> city;
    public String name;

    public static class Shi {
        public List<String> area;
        public String name;
    }

    public String getPickerViewText() {
        return this.name;
    }
}
