package com.example.vanthanh.yourcardvisit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.vanthanh.yourcardvisit.R;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Fragment_ListCard extends Fragment {
    GridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_listcard,null);
        gridView=(GridView)v.findViewById(R.id.gridView);
        return v;
    }
}
