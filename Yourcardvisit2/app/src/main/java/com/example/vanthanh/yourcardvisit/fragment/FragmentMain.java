package com.example.vanthanh.yourcardvisit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.controls.Func_fragment;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class FragmentMain extends android.app.Fragment {
    Button btnAddcard;
    Button btnListcard;
    Button btnCreateCard;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_main,null);
        btnAddcard=(Button)v.findViewById(R.id.btnAddCard);
        btnAddcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(), StaticValues.TAG_FRAGMENTADDCARD);
            }
        });
        btnListcard=(Button)v.findViewById(R.id.btnViewListCard);
        btnListcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(),StaticValues.TAG_FRAGMENT_LISTCARD);
            }
        });
        btnCreateCard=(Button)v.findViewById(R.id.btnCreatCard);
        btnCreateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(),StaticValues.TAG_FRAGMENT_CREATE);
            }
        });
        return v;
    }
}
