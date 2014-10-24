package com.bluemor.viewcontainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class DemoActActivity extends Activity {

    private ViewContainer vc;

    private final String KEY_CUSTOMER1 = "customer1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        vc = new ViewContainer(this, R.layout.activity_act);

        View inflate = View.inflate(this, R.layout.container_custom, null);
        inflate.findViewById(R.id.bt).setOnClickListener(listener);

        vc.setDefaultClickListener(listener)
                .addView(KEY_CUSTOMER1, inflate)
                .build(this);

        findViewById(R.id.bt).setOnClickListener(listener);
    }

    private RefreshClickListener listener = new RefreshClickListener();

    private class RefreshClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            loadData();
        }
    }

    private int i;

    private void loadData() {
        vc.showLoading();
        new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        i++;
                        showDemoView();
                    }
                });
            };
        }.start();
    }

    private void showDemoView() {
        switch (i % 5) {
            case 0:
                vc.showContent();
                break;
            case 1:
                vc.showEmpty();
                break;
            case 2:
                vc.showError();
                break;
            case 3:
                vc.showNonet();
                break;
            case 4:
                vc.showView(KEY_CUSTOMER1);
                break;
        }
    }

}
