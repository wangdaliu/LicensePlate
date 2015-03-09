package com.licenseplate.android.ui;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;

import com.licenseplate.android.util.KeyboardUtil;


public class HomeActivity extends TabPagerActivity {

    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomePagerAdapter = new HomePagerAdapter(HomeActivity.this);
        if (adapter == null) {
            configureTabPager();
        }
    }

    @Override
    protected PagerAdapter createAdapter() {
        return mHomePagerAdapter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeyboardUtil.hideKeyboard(HomeActivity.this);
    }
}
