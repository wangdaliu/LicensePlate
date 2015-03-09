package com.licenseplate.android;

import android.app.Application;

import com.licenseplate.android.objects.Plate;
import com.licenseplate.android.util.PlateUtil;

import java.util.List;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public MyApplication() {
        MyApplication.instance = this;
    }

    private List<Plate> mPlateList;

    public List<Plate> getPlateList() {
        return mPlateList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlateList = PlateUtil.convertPlate();
    }
}
