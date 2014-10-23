package com.bluemor.viewcontainer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DemoFraActivity extends FragmentActivity {
    private static final String TAG = "SampleFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            getSupportFragmentManager().beginTransaction().add(android.R.id.content,
                    DemoFragment.newInstance(), TAG).commit();
        }
    }
}
