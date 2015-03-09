package com.licenseplate.android.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.licenseplate.android.R;


public abstract class TabPagerActivity<V extends PagerAdapter> extends BaseActivity implements TabHost.OnTabChangeListener, TabHost.TabContentFactory, ViewPager.OnPageChangeListener {

    private TextView mAlertBadgeTextView;

    /**
     * View pager
     */
    protected ViewPager pager;

    /**
     * Tab host
     */
    protected TabHost host;

    /**
     * Pager adapter
     */
    protected V adapter;


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (host.getCurrentTab() != position)
            host.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int page = Integer.parseInt(tabId);
        if (pager.getCurrentItem() != page) {
            pager.setCurrentItem(page, false);
        }
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(getApplication());
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        pager = (ViewPager) findViewById(R.id.vp_pages);
        pager.setOffscreenPageLimit(5);
        pager.setOnPageChangeListener(this);
        host = (TabHost) findViewById(R.id.th_tabs);
        host.setup();
        host.setOnTabChangedListener(this);
    }

    /**
     * Get content view to be used when {@link #onCreate(Bundle)} is called
     *
     * @return layout resource id
     */
    protected int getContentView() {
        return R.layout.pager_with_tabs;
    }

    /**
     * Configure tabs and pager
     */
    protected void configureTabPager() {
        if (adapter == null) {
            createPager();
            createTabs();
        }
    }

    /**
     * Create tab using information from current adapter
     * <p/>
     * This can be called when the tabs changed but must be called after an
     * initial call to {@link #configureTabPager()}
     */
    protected void createTabs() {
        if (host.getTabWidget().getTabCount() > 0) {
            // Crash on Gingerbread if tab isn't set to zero since adding a
            // new tab removes selection state on the old tab which will be
            // null unless the current tab index is the same as the first
            // tab index being added
            host.setCurrentTab(0);
            host.clearAllTabs();
        }

        LayoutInflater inflater = getLayoutInflater();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec spec = host.newTabSpec("" + i);
            spec.setContent(this);
            View view = inflater.inflate(R.layout.tab, null);
            ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView title = (TextView) view.findViewById(R.id.tv_tab);
            icon.setImageDrawable(getIcon(i));
            title.setText(getTitle(i));

            if (i == 3) {
                mAlertBadgeTextView = (TextView)view.findViewById(R.id.badge_text_view);
            }

            spec.setIndicator(view);
            host.addTab(spec);
        }
    }

    private void createPager() {
        adapter = createAdapter();
        pager.setAdapter(adapter);
    }

    /**
     * Get title for position
     *
     * @param position
     * @return title
     */
    protected String getTitle(final int position) {
        return adapter.getPageTitle(position).toString();
    }

    /**
     * Get icon for position
     *
     * @param position
     * @return icon
     */
    protected Drawable getIcon(final int position) {
        return null;
    }

    /**
     * Get ContentDescription for position
     *
     * @param position
     * @return icon
     */
    protected String getContentDescription(final int position) {
        return null;
    }

    /**
     * Create pager adapter
     *
     * @return pager adapter
     */
    protected abstract V createAdapter();

    public void setCurrentItem(int item) {
        pager.setCurrentItem(item);
        host.setCurrentTab(item);
    }

    public void updateAlertBadgeNumber(int number) {
        mAlertBadgeTextView.setText(Integer.toString(number));
        mAlertBadgeTextView.setVisibility(number > 0 ? View.VISIBLE : View.INVISIBLE);
    }
}
