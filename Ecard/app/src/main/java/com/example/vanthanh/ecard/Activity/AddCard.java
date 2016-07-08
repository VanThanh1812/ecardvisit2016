package com.example.vanthanh.ecard.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.vanthanh.ecard.Controls.HienthiView;
import com.example.vanthanh.ecard.Fragment.fragment_card;
import com.example.vanthanh.ecard.Objects.InfoCard;
import com.example.vanthanh.ecard.R;
import com.firebase.client.Firebase;

public class AddCard extends FragmentActivity {
    final String[] string = {"zero"};InfoCard infoCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        android.app.ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //
        //addFragment();
        Firebase.setAndroidContext(this);
        infoCard=HienthiView.getCardFirebase("idFace");

        try{
            Log.i("vt", infoCard.getEc_address());
            Toast.makeText(AddCard.this, infoCard.getEc_address(), Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Log.e("sdcard-err2:",ex.getMessage());
            Toast.makeText(AddCard.this, "Wait...", Toast.LENGTH_SHORT).show();
        }
    }
    public void Cancel(View view) {
        Intent intent=new Intent(AddCard.this,MainActivity.class);
        startActivity(intent);


    }
    public void addFragment() {
        FragmentManager manager=getFragmentManager();
        fragment_card fc=new fragment_card();
        FragmentTransaction transaction=manager.beginTransaction();
        if ((!fc.isInLayout())&& (fc!=null)){
            transaction.add(R.id.li_card, fc, HienthiView.TAG_CARD);
        }
        transaction.commit();

    }
//    @Override
//    protected void onStop() {
//        FragmentManager manager = getFragmentManager();
//        fragment_card fc=(fragment_card)manager.findFragmentByTag("CARD");
//        FragmentTransaction transaction = manager.beginTransaction();
//        if (fc != null) {
//            transaction.remove(fc);
//            transaction.commit();
//        }
//        super.onStop();
//        Toast.makeText(AddCard.this, "Destroy", Toast.LENGTH_SHORT).show();
//    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
