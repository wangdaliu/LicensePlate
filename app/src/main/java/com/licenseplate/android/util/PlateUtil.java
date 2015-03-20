package com.licenseplate.android.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.licenseplate.android.Application;
import com.licenseplate.android.R;
import com.licenseplate.android.objects.Plate;

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

    public static String[] convertProvince() {
        return Application.getInstance().getResources().getStringArray(R.array.province);
    }

    public static String[] convertProvinceSimple() {
        return Application.getInstance().getResources().getStringArray(R.array.province_simple);
    }

    public static String[] convertLetter() {
        return Application.getInstance().getResources().getStringArray(R.array.letter);
    }

    private static String readAssert(String fileName) {
        String text = "";
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = Application.getInstance().getAssets().open(fileName);
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
