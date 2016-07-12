package com.example.vanthanh.yourcardvisit.customcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.model.Data_Info;

import java.util.ArrayList;

/**
 * Created by Van Thanh on 7/11/2016.
 */
public class Custom_InfoCard extends ArrayAdapter<Data_Info> {
    Data_Info info_card=null;
    Data_Info images_card=null;
    public Custom_InfoCard(Context context, int resource, ArrayList<Data_Info> info_card,ArrayList<Data_Info> images_card) {
        super(context, 0, info_card);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.yourcard,parent,false);
        }
        Data_Info data_info=getItem(position);
        TextView txtHoten,txtCongty,txtDiachi,txtEmail,txtSodienthoai,txtChucvu;
        ImageView imgLogo=(ImageView)convertView.findViewById(R.id.imgLogo);
        txtChucvu=(TextView)convertView.findViewById(R.id.txtChucvu);
        txtCongty=(TextView)convertView.findViewById(R.id.txtCongty);
        txtDiachi=(TextView)convertView.findViewById(R.id.txtDiachi);
        txtEmail=(TextView)convertView.findViewById(R.id.txtEmail);
        txtSodienthoai=(TextView)convertView.findViewById(R.id.txtSodienthoai);
        txtHoten=(TextView)convertView.findViewById(R.id.txtHoTen);

        txtChucvu.setText(data_info.getCard_chucvu());
        txtCongty.setText(data_info.getCard_congty());
        txtDiachi.setText(data_info.getCard_diachi());
        txtEmail.setText(data_info.getCard_email());
        txtSodienthoai.setText(data_info.getCard_sodienthoai());
        txtHoten.setText(data_info.getCard_name());


        return convertView;
    };

}
