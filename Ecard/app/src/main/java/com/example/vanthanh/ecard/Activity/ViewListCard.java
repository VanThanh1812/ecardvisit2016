package com.example.vanthanh.ecard.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.vanthanh.ecard.Controls.GetCardFromServer;
import com.example.vanthanh.ecard.Fragment.fragment_card;
import com.example.vanthanh.ecard.R;
import com.firebase.client.Firebase;

public class ViewListCard extends FragmentActivity {
    ListView lstCard;
    LinearLayout linearMyCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_card);
        Firebase.setAndroidContext(this);
        android.app.ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //
        lstCard=(ListView)findViewById(R.id.listFragmentcard);
        linearMyCard=(LinearLayout)findViewById(R.id.linearMycard);
    }

    public void ListCard(View view) {
        linearMyCard.setVisibility(View.GONE);
        lstCard.setVisibility(View.VISIBLE);
    }

    public void MyCard(View view) {
        lstCard.setVisibility(View.GONE);
        linearMyCard.setVisibility(View.VISIBLE);
        fragment_card fc = new fragment_card();
        GetCardFromServer.getCard("idFacebook", fc, ViewListCard.this);


    }

}
