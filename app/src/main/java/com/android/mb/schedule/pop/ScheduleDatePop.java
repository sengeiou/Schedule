package com.android.mb.schedule.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.mb.schedule.R;
import com.android.mb.schedule.widget.DatePicker;
import com.android.mb.schedule.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by chenqm
 */
public class ScheduleDatePop extends PopupWindow implements View.OnClickListener {
    private View mContentView;
    private Activity mContext;
    private SelectListener mSelectListener;
    private Calendar mCalendar;
    private String mSelectDate;
    //选择时间与当前时间，用于判断用户选择的是否是以前的时间
    private DatePicker mDpDate;
    private TextView mBtnConfirm;
    private TextView mBtnCancel;

    public void setSelectListener(SelectListener selectListener) {
        this.mSelectListener = selectListener;
    }

    public interface SelectListener {
        void onSelected(String selectDate);
    }

    public ScheduleDatePop(Activity context, SelectListener selectListener) {
        mContext = context;
        this.mSelectListener = selectListener;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = inflater.inflate(R.layout.pop_select_date, null);
        this.setContentView(mContentView);
        setFocusable(false);
        setOutsideTouchable(false);
        setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        setHeight(ViewPager.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mDpDate = mContentView.findViewById(R.id.dp_date);
        mBtnConfirm = mContentView.findViewById(R.id.tv_confirm);
        mBtnCancel = mContentView.findViewById(R.id.tv_cancel);
    }


    private void initData(){
        if (mCalendar==null){
            mCalendar = Calendar.getInstance();
        }
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH) + 1;
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        String monthStr = month < 10?("0"+month):month+"";
        String dayStr = day < 10?("0"+day):day+"";

        mSelectDate =  year+ "年" + monthStr + "月" +  dayStr + "日";
    }

    private void initListener() {
        mDpDate.setOnChangeListener(mOnDateChangeLister);
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }


    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_confirm){
            mSelectListener.onSelected(mSelectDate);
            dismiss();
        }else if (id == R.id.tv_cancel){
            dismiss();
        }
    }

    //listeners
    private DatePicker.OnChangeListener mOnDateChangeLister = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week) {
            String monthStr = month < 10?("0"+month):month+"";
            String dayStr = day < 10?("0"+day):day+"";
            mSelectDate = year + "年" + monthStr + "月" + dayStr + "日";
        }
    };

    public void setTime(Calendar calendar){
        if(mDpDate!=null &&  calendar!=null){
            mCalendar = calendar;
            initData();
            mDpDate.setCurrentTime(calendar);
        }
    }

}