package com.licenseplate.android.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.licenseplate.android.R;
import com.licenseplate.android.ui.home.HomeFragment;
import com.licenseplate.android.ui.me.AboutFragment;
import com.licenseplate.android.util.KeyboardUtil;
import com.wandoujia.ads.sdk.Ads;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = "HomeActivity";

    private static final String APP_ID = "100043892";
    private static final String SECRET_KEY = "c56d49436e6b4be951593fab235dacb2";
    private static final String BANNER = "da57b39600376dc53c481978d764dbb1";

    private ListView mMenuListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CustomViewPager mCustomViewPager;

    private HomeAdapter mPagerAdapter;

    private String[] mMenuArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(getResources().getString(R.string.home));


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0); // this disables the animation
            }
        };
        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // load menu
        mMenuListView = (ListView) findViewById(R.id.menu_list);

        mMenuArray = getResources().getStringArray(R.array.menu);
        mMenuListView.setAdapter(new MenuAdapter(this, mMenuArray));
        mMenuListView.setOnItemClickListener(this);

        mCustomViewPager = (CustomViewPager) findViewById(R.id.vp_pages);
        mCustomViewPager.setOffscreenPageLimit(3);
        mPagerAdapter = new HomeAdapter(this);
        mCustomViewPager.setAdapter(mPagerAdapter);

        new AsyncTask<Void, Void, Boolean>() {


            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Ads.init(HomeActivity.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                    Log.e(TAG, "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                Log.e(TAG, "result " + success);
                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(HomeActivity.this, BANNER);
                    FrameLayout banner = (FrameLayout) findViewById(R.id.banner);
                    banner.addView(bannerView, new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                }
            }
        }.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeyboardUtil.hideKeyboard(HomeActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawer(mMenuListView);
        mCustomViewPager.setCurrentItem(position, false);

        getSupportActionBar().setTitle(mMenuArray[position]);
    }

    private class HomeAdapter extends FragmentPagerAdapter {

        private List mFragments;

        public HomeAdapter(FragmentActivity activity) {
            super(activity.getSupportFragmentManager());
            List<Fragment> fragments = new ArrayList<>();
            fragments.add(new HomeFragment());
            fragments.add(new AboutFragment());
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) mFragments.get(position);
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
    }
}
