package com.education.myoschinatest.ui.Home1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;

public class Fragment1 extends BaseFragment {


    public static Fragment1 instanceFragment() {
        Fragment1 fragment1 = new Fragment1();
        return fragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment1;
    }

}
