package com.licenseplate.android.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.licenseplate.android.Application;
import com.licenseplate.android.R;
import com.licenseplate.android.objects.Plate;
import com.licenseplate.android.ui.BaseFragment;
import com.licenseplate.android.ui.TypefacedTextView;
import com.licenseplate.android.util.PlateUtil;

public class CalculatePlateFragment extends BaseFragment implements View.OnClickListener {

    private TypefacedTextView mProvinceText, mLetterText, mResultText;

    private String[] mProvinceArray, mLetterArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calculate_plate, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProvinceText = (TypefacedTextView) view.findViewById(R.id.province);
        mLetterText = (TypefacedTextView) view.findViewById(R.id.letter);
        mResultText = (TypefacedTextView) view.findViewById(R.id.result);

        mProvinceArray = PlateUtil.convertProvinceSimple();
        mLetterArray = PlateUtil.convertLetter();

        mProvinceText.setOnClickListener(this);
        mLetterText.setOnClickListener(this);
    }

    private void showDialog(final String[] array, final TypefacedTextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(array, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(array[which]);
                String result = calculateResult();
                mResultText.setText(TextUtils.isEmpty(result) ? getResources().getString(R.string.empty) : result);
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.province:
                showDialog(mProvinceArray, mProvinceText);
                break;
            case R.id.letter:
                showDialog(mLetterArray, mLetterText);
                break;
            default:
                break;
        }
    }

    private String calculateResult() {
        String province = mProvinceText.getText().toString();
        String letter = mLetterText.getText().toString();

        if (province.equals(getResources().getString(R.string.select_province))) {
            return "";
        }

        if (letter.equals(getResources().getString(R.string.select_letter))) {
            return "";
        }

        String brand = province + letter;

        for (Plate plate : Application.getInstance().getPlateList()) {
            if (plate.getBrand().equals(brand)) {
                return plate.getProvince() + plate.getLocation();
            }
        }
        return "";
    }
}
