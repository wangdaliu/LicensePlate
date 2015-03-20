package com.licenseplate.android.ui;

import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.licenseplate.android.R;

public class BaseActivity extends ActionBarActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
