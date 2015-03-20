package com.licenseplate.android;

import com.licenseplate.android.objects.Plate;
import com.licenseplate.android.util.PlateUtil;

import java.util.List;

public class Application extends android.app.Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    public Application() {
        Application.instance = this;
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
