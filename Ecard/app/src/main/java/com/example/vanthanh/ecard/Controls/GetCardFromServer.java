package com.example.vanthanh.ecard.Controls;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.vanthanh.ecard.Fragment.fragment_card;
import com.example.vanthanh.ecard.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by Van Thanh on 7/7/2016.
 */
public class GetCardFromServer {
    public static final String TAG="vanthanhabc";
    public static final String LINKFIREBASE="https://ecardvisit-b1a54.firebaseio.com/";
    public static void getCard(String idFacebook,fragment_card fc, final Activity activity){
        final Bundle bundle=new Bundle();
        String link=LINKFIREBASE+idFacebook;
        Firebase card=new Firebase(link);
        card.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, dataSnapshot.getValue().toString());

                Map<String,Object> map=dataSnapshot.getValue(Map.class);
                bundle.putString("ecname",map.get("ec_fullname").toString());
                bundle.putString("ecdiachi",map.get("ec_address").toString());
                bundle.putString("eccongty",map.get("ec_company").toString());
                bundle.putString("ecemail",map.get("ec_email").toString());
                bundle.putString("ecid",map.get("ec_id").toString());
                bundle.putString("linkbackground",map.get("ec_imgBackground").toString());
                bundle.putString("linklogo",map.get("ec_imgCongty").toString());
                bundle.putString("ecchucvu",map.get("ec_level").toString());
                bundle.putString("ecsodienthoai",map.get("ec_phone").toString());
                fragment_card fc = new fragment_card();
                fc.setArguments(bundle);
                FragmentTransaction fragmentTransaction =activity.getFragmentManager().beginTransaction();
                if ((!fc.isInLayout()) && (fc != null)) {

                    fragmentTransaction.replace(R.id.linearMycard, fc, HienthiView.TAG_CARD);
                } else Log.i("vthanh", "here");
                fragmentTransaction.commit();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
