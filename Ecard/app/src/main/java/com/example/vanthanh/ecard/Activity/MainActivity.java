package com.example.vanthanh.ecard.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.vanthanh.ecard.Controls.HienthiView;
import com.example.vanthanh.ecard.R;

public class MainActivity extends ActionBarActivity {
    EditText edtKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtKey= (EditText) findViewById(R.id.edtKey);
        // lấy ActionBar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemThongbao) {
            AlertDialog.Builder aBuilder=new AlertDialog.Builder(this);
            aBuilder.setTitle("Thong bao");
            aBuilder.setMessage("Nguoi nay muon them card cua ban");
            aBuilder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            aBuilder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void CreateCard(View view) {
        Intent intent=new Intent(MainActivity.this,Creat_card.class);
        startActivity(intent);
    }

    public void AddCard(View view) {
        Intent intent=new Intent(MainActivity.this,AddCard.class);
        intent.putExtra(HienthiView.KEY_SEARCH,edtKey.getText().toString());
        startActivity(intent);
    }

    public void ViewListCard(View view) {
        Intent intent=new Intent(MainActivity.this,ViewListCard.class);
        startActivity(intent);
    }
}
