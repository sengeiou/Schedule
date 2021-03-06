package com.android.mb.schedule.presenter;

import android.text.TextUtils;

import com.android.mb.schedule.api.ScheduleMethods;
import com.android.mb.schedule.app.MBApplication;
import com.android.mb.schedule.base.BaseMvpPresenter;
import com.android.mb.schedule.entitys.LoginData;
import com.android.mb.schedule.presenter.interfaces.IHomePresenter;
import com.android.mb.schedule.retrofit.http.exception.ApiException;
import com.android.mb.schedule.utils.NetworkHelper;
import com.android.mb.schedule.utils.ProjectHelper;
import com.android.mb.schedule.view.interfaces.IHomeView;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;

public class HomePresenter extends BaseMvpPresenter<IHomeView> implements IHomePresenter {

    private int isLogin = 1;

    @Override
    public void getUserInfo() {
        if (NetworkHelper.isNetworkAvailable(MBApplication.getInstance())){
            Observable observable = ScheduleMethods.getInstance().getUserInfo();
            toSubscribe(observable,  new Subscriber<LoginData>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if(mMvpView!=null){
                        if (e instanceof ApiException && !TextUtils.isEmpty(e.getMessage())){
                            mMvpView.showToastMessage(e.getMessage());
                        }
                    }
                }

                @Override
                public void onNext(LoginData result) {
                    if (mMvpView!=null){
                        mMvpView.getUserInfoSuccess(result);
                    }
                }
            });
        }
    }

    @Override
    public void addLog(Map<String, Object> requestMap) {
        isLogin = (int) requestMap.get("is_login");
        Observable observable = ScheduleMethods.getInstance().addLog(requestMap);
        toSubscribe(observable,  new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(mMvpView!=null){
                    mMvpView.addLog(isLogin);
                }
            }

            @Override
            public void onNext(Object result) {
                if(mMvpView!=null){
                    mMvpView.addLog(isLogin);
                }
            }
        });
    }

}
