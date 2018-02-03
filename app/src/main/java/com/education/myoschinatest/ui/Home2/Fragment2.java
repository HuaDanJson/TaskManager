package com.education.myoschinatest.ui.Home2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences pref = getActivity().getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment2;
    }

    public static Fragment2 instanceFragment() {
        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Override
    public void initView() {

    }

}
