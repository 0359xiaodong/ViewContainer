package com.bluemor.viewcontainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Random;

public class DemoActActivity extends Activity {

    private ViewContainer vc;

    private final String KEY_CUSTOMER1 = "customer1";
    private final String KEY_CUSTOMER2 = "customer2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        vc = new ViewContainer(this, R.layout.activity_act);

        View inflate1 = View.inflate(this, R.layout.container_customer1, null);
        inflate1.findViewById(R.id.bt).setOnClickListener(listener);

        View inflate2 = View.inflate(this, R.layout.container_customer2, null);
        inflate2.findViewById(R.id.bt).setOnClickListener(listener);

        vc.setDefaultClickListener(listener)
                .addView(KEY_CUSTOMER1, inflate1)
                .addView(KEY_CUSTOMER2, inflate2)
                .build(this);
    }

    private RefreshClickListener listener = new RefreshClickListener();

    private class RefreshClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            loadData();
        }
    }

    public void load(View v) {
        loadData();
    }

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
                        showRandomView(new Random().nextInt(6));
                    }
                });
            };
        }.start();
    }

    private void showRandomView(int nextInt) {
        switch (nextInt) {
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
            case 5:
                vc.showView(KEY_CUSTOMER2);
                break;
        }
    }

}
