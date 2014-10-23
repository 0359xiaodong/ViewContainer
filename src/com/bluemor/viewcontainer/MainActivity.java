package com.bluemor.viewcontainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoActivity(View v) {
        startActivity(new Intent(this, DemoActActivity.class));
    }

    public void gotoFragment(View v) {
        startActivity(new Intent(this, DemoFraActivity.class));
    }
}
