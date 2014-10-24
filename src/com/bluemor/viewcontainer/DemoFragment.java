package com.bluemor.viewcontainer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class DemoFragment extends Fragment {

    private Activity activity;

    private ViewContainer vc;

    private final String KEY_CUSTOMER1 = "customer1";

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
        vc = new ViewContainer(getActivity(), R.layout.activity_act);

        View inflate = View.inflate(getActivity(), R.layout.container_custom, null);
        inflate.findViewById(R.id.bt).setOnClickListener(listener);

        View view = vc.setDefaultClickListener(listener)
                .addView(KEY_CUSTOMER1, inflate)
                .build(this);

        view.findViewById(R.id.bt).setOnClickListener(listener);
        return view;
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
                activity.runOnUiThread(new Runnable() {
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
