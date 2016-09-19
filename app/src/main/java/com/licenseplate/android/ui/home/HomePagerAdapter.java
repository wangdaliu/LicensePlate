package com.licenseplate.android.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.licenseplate.android.Application;
import com.licenseplate.android.R;
import com.licenseplate.android.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Pager adapter for a user's different views
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;

    public HomePagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new CalculatePlateFragment());
        fragments.add(new ProvinceListFragment());
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Application.getInstance().getResources().getString(R.string.plate);
            case 1:
                return Application.getInstance().getResources().getString(R.string.shows);
            default:
                return null;
        }
    }
}
