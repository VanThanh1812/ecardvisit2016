package com.example.vanthanh.ecard.Fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vanthanh.ecard.Controls.HienthiView;
import com.example.vanthanh.ecard.R;

/**
 * Created by Van Thanh on 6/21/2016.
 */
public class fragment_card extends Fragment {
    TextView txtId,txtName,txtCongty,txtEmail,txtSodienthoai,txtDiachi,txtChucvu;
    View v;
    ImageView imgCongty;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragmentcard, null, false);
        AnhXa(v);
        getInfoCard();
        registerForContextMenu(v);
        return v;
    }
    public void AnhXa(View v){
        txtId=(TextView)v.findViewById(R.id.txtId);
        txtChucvu=(TextView)v.findViewById(R.id.txtChucvu);
        txtCongty=(TextView)v.findViewById(R.id.txtCongty);
        txtEmail=(TextView)v.findViewById(R.id.txtEmail);
        txtDiachi=(TextView)v.findViewById(R.id.txtDiachi);
        txtSodienthoai=(TextView)v.findViewById(R.id.txtSdt);
        txtName=(TextView)v.findViewById(R.id.txtName);
        imgCongty=(ImageView)v.findViewById(R.id.imgCongty);
    }
    public void getInfoCard(){
        Bundle bundle=getArguments();
        if (bundle!=null){

            txtName.setText(bundle.getString("ecname"));
            txtSodienthoai.setText(bundle.getString("ecsodienthoai"));
            txtEmail.setText(bundle.getString("ecemail"));
            txtCongty.setText(bundle.getString("eccongty"));
            txtChucvu.setText(bundle.getString("ecchucvu"));
            txtId.setText(bundle.getString("ecid"));
            txtDiachi.setText(bundle.getString("ecdiachi"));
        //
            String linklogo=bundle.getString("linklogo");
            Bitmap bitmap= BitmapFactory.decodeFile(linklogo);
            imgCongty.setImageBitmap(bitmap);
            HienthiView.getImagetoCard(bundle.getString("logo"),bundle.getString("linkbackground"),imgCongty,v);
            String linkbackground=bundle.getString("linkbackground");
            Bitmap bitmap2= BitmapFactory.decodeFile(linkbackground);
            Drawable drawable=new BitmapDrawable(getResources(),bitmap2);
            RelativeLayout relativeLayout=(RelativeLayout)v.findViewById(R.id.viewCard);
            relativeLayout.setBackground(drawable);
        }else return;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.editcard,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editcard:
                return true;
            default:
                return true;
        }
    }
}
