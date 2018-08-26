package com.android.mb.schedule.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.schedule.R;
import com.android.mb.schedule.adapter.ScheduleAdapter;
import com.android.mb.schedule.base.BaseActivity;
import com.android.mb.schedule.pop.ScheduleRepeatPop;
import com.android.mb.schedule.utils.Helper;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018\8\20 0020.
 */

public class RelateScheduleActivity extends BaseActivity implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mAdapter;
    private int mCurrentPage = 0;

    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_relate_schedule;
    }

    @Override
    protected void initTitle() {
        setTitleText("与我相关的日程");
    }

    @Override
    protected void bindViews() {
        mRecyclerView =  findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ScheduleAdapter(R.layout.item_schedule,getData());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentPage >= 10) {
                            //数据全部加载完毕
                            mAdapter.loadMoreEnd();
                        } else {
                            mAdapter.addData(getData());
                            mAdapter.loadMoreComplete();
                            mCurrentPage++;
                        }
                    }

                }, 1000);

            }
        },mRecyclerView);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
    }

    public List getData() {
        List<String> dataList = new ArrayList<>();
        for (int i=0;i<10;i++){
            dataList.add(Helper.date2String(new Date()));
        }
        return dataList;
    }
}
