package com.example.vanthanh.ecard.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.vanthanh.ecard.Fragment.fragment_edittext;
import com.example.vanthanh.ecard.R;
import com.firebase.client.Firebase;

public class Creat_card extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_card);
        android.app.ActionBar actionBar = getActionBar();
        Firebase.setAndroidContext(this);
        // hiển thị nút Up ở Home icon
        //actionBar.setDisplayHomeAsUpEnabled(true);
        fragment_edittext fe=new fragment_edittext();
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        if ((!fe.isInLayout())&& (fe!=null)){

            fragmentTransaction.replace(R.id.actyCreateCard,fe,"abc");
        }else Log.i("vthanh", "here");
        //fragmentTransaction.commit();
        fragmentTransaction.commit();

    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


//    @Override
//    public void onBackPressed() {
//
//        super.onBackPressed();
//    }
//
}
