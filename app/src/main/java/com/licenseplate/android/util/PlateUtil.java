package com.licenseplate.android.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.licenseplate.android.MyApplication;
import com.licenseplate.android.objects.Plate;
import com.licenseplate.android.objects.Province;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class PlateUtil {

    public static List<Plate> convertPlate() {
        String licence = readAssert("licence.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Plate>>() {
        }.getType();
        return gson.fromJson(licence, type);
    }

    public static List<Province> convertProvince() {
        String city = readAssert("city.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Province>>() {
        }.getType();
        return gson.fromJson(city, type);
    }

    private static String readAssert(String fileName) {
        String text = "";
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = MyApplication.getInstance().getAssets().open(fileName);
            outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            text = outputStream.toString();
        } catch (Exception e) {

        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }
        return text;
    }

}
