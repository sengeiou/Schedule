package com.android.mb.schedule.entitys;

import java.io.Serializable;
import java.util.List;

public class ScheduleData implements Serializable {
    private String date;

    private List<ScheduleBean> list;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ScheduleBean> getList() {
        return list;
    }

    public void setList(List<ScheduleBean> list) {
        this.list = list;
    }
}
