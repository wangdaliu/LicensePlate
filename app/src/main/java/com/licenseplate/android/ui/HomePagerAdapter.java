/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.licenseplate.android.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.google.common.collect.ImmutableList;
import com.licenseplate.android.R;

import java.util.List;

/**
 * Pager adapter for a user's different views
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;
    private List<Fragment> mFragments;

    public HomePagerAdapter(Activity activity) {
        super(activity.getFragmentManager());
        resources = activity.getResources();
        mFragments = ImmutableList.of(
                new CalculatePlateFragment(),
                new ProvinceListFragment()
        );
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
                return resources.getString(R.string.plate);
            case 1:
                return resources.getString(R.string.shows);
            default:
                return null;
        }
    }
}
