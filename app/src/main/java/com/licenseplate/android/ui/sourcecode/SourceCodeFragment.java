package com.licenseplate.android.ui.sourcecode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.licenseplate.android.R;
import com.licenseplate.android.ui.BaseFragment;

public class SourceCodeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.source_code, null);
    }
}
