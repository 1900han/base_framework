package com.example.han.framwork.core;

import android.app.Application;

import com.example.han.framwork.constant.ConstantValues;

/**
 * Created by han on 2016/7/5.
 */
public class AppStatusTracker {
    private static AppStatusTracker tracker;
    private  int mAppStatus = ConstantValues.STATUS_ONLINE;
    public static AppStatusTracker getInstance(){
        return tracker;
    }

    public void  setAppStatus(int status){
        this.mAppStatus = status;

    }

    public int getAppStatus(){
        return mAppStatus;
    }
}
