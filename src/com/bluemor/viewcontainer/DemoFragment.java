package com.bluemor.viewcontainer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class DemoFragment extends Fragment {

    private Activity activity;

    private Button bt;

    private ViewContainer vc;

    private final String KEY_CUSTOMER1 = "customer1";
    private final String KEY_CUSTOMER2 = "customer2";

    public static DemoFragment newInstance() {
        DemoFragment fragment = new DemoFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        vc = new ViewContainer(getActivity(), R.layout.fragment);

        View inflate1 = View.inflate(getActivity(), R.layout.container_customer1, null);
        inflate1.findViewById(R.id.bt).setOnClickListener(listener);

        View inflate2 = View.inflate(getActivity(), R.layout.container_customer2, null);
        inflate2.findViewById(R.id.bt).setOnClickListener(listener);

        View view = vc.setDefaultClickListener(listener)
                .addView(KEY_CUSTOMER1, inflate1)
                .addView(KEY_CUSTOMER2, inflate2)
                .build(this);

        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        return view;
    }

    private RefreshClickListener listener = new RefreshClickListener();

    private class RefreshClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            loadData();
        }
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
                activity.runOnUiThread(new Runnable() {
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
