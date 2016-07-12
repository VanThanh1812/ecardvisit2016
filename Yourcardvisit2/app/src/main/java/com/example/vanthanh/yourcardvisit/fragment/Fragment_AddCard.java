package com.example.vanthanh.yourcardvisit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vanthanh.yourcardvisit.R;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Fragment_AddCard extends Fragment {
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_addcard,null);
        return v;
    }
}
