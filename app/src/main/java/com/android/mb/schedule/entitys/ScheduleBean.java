package com.android.mb.schedule.entitys;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class ScheduleBean implements MultiItemEntity,Serializable{

    private long id;
    private String title;
    private String description;
    private String date;
    private long time_s;
    private long time_e;
    private String address;
    private String startTime;
    private String endTime;
    private int allDay;
    private int repeattype;
    private int isFinish;
    private int remind;
    private int important;
    private String summary;
    private String not_remind_related;
    private int timeType;//0:全天  1:上午 2:下午


    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTime_s() {
        return time_s;
    }

    public void setTime_s(long time_s) {
        this.time_s = time_s;
    }

    public long getTime_e() {
        return time_e;
    }

    public void setTime_e(long time_e) {
        this.time_e = time_e;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getAllDay() {
        return allDay;
    }

    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    public int getRepeattype() {
        return repeattype;
    }

    public void setRepeattype(int repeattype) {
        this.repeattype = repeattype;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public int getRemind() {
        return remind;
    }

    public void setRemind(int remind) {
        this.remind = remind;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public String getSummary() {
        return summary == null ? "" : summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNot_remind_related() {
        return not_remind_related == null ? "" : not_remind_related;
    }

    public void setNot_remind_related(String not_remind_related) {
        this.not_remind_related = not_remind_related;
    }

    @Override
    public int getItemType() {
        return allDay;
    }
}
