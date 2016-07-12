package com.example.vanthanh.yourcardvisit.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.controls.FirebaseData;
import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Fragment_FormPreview extends Fragment {
    TextView txtHoten,txtCongty,txtDiachi,txtEmail,txtSodienthoai,txtChucvu;
    EditText edtHoten,edtCongty,edtDiachi,edtEmail,edtSodienthoai,edtChucvu;
    ImageView imgLogo;
    LinearLayout layout;
    Button btnCreate;
    View v;
    String link_logo=null;
    String link_background=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_createcard,null);
        v.setLongClickable(true);
        Connect();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View popup=getActivity().getLayoutInflater().inflate(R.layout.dialog_forminfo,null);
        connectPopup(popup);
        builder.setView(popup);
        builder.setTitle("Điền thông tin tại đây");
        builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtChucvu.setText(edtChucvu.getText().toString());
                txtHoten.setText(edtHoten.getText().toString());
                txtSodienthoai.setText(edtSodienthoai.getText().toString());
                txtCongty.setText(edtCongty.getText().toString());
                txtEmail.setText(edtEmail.getText().toString());
                txtDiachi.setText(edtEmail.getText().toString());
                Toast.makeText(getActivity(),"Chạm và giữ để chỉnh sửa lại",Toast.LENGTH_LONG);
            }
        });
        builder.show();
        return v;
    }
    View.OnClickListener create=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Data_Info data_info=new Data_Info(txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
            FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
        }
    };
    View.OnLongClickListener event=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            final TextView txt=(TextView)view;
            final EditText editText=new EditText(getActivity());
            editText.setText(txt.getText().toString());
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("Chỉnh sửa");
            builder.setView(editText);
            builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    txt.setText(editText.getText().toString());
                }
            });
            builder.show();
            return true;
        }
    };
    //kết nối và đặt các sự kiện
    private void Connect(){
        txtChucvu=(TextView)v.findViewById(R.id.txtChucvu);
        txtCongty=(TextView)v.findViewById(R.id.txtCongty);
        txtDiachi=(TextView)v.findViewById(R.id.txtDiachi);
        txtEmail=(TextView)v.findViewById(R.id.txtEmail);
        txtSodienthoai=(TextView)v.findViewById(R.id.txtSodienthoai);
        txtHoten=(TextView)v.findViewById(R.id.txtHoTen);
        imgLogo=(ImageView)v.findViewById(R.id.imgLogo);
        btnCreate=(Button)v.findViewById(R.id.btnCreate);

        txtChucvu.setOnLongClickListener(event);
        txtHoten.setOnLongClickListener(event);
        txtCongty.setOnLongClickListener(event);
        txtDiachi.setOnLongClickListener(event);
        txtSodienthoai.setOnLongClickListener(event);
        txtEmail.setOnLongClickListener(event);

        //click ảnh
        imgLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showFileChooser(StaticValues.IMAGE_CONGTY_FILECHOOSE);
                return true;
            }
        });

        //click nền
        layout=(LinearLayout)v.findViewById(R.id.yourcard);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showFileChooser(StaticValues.IMAGE_BACKGROUND_FILECHOOSE);
                return true;
            }
        });

        //click button
        btnCreate.setOnClickListener(create);

    }
    //pop up hiện form điền dữ liệu
    private void connectPopup(View popup){
        edtHoten=(EditText)popup.findViewById(R.id.edtName);
        edtCongty=(EditText)popup.findViewById(R.id.edtCongty);
        edtChucvu=(EditText)popup.findViewById(R.id.edtChucvu);
        edtDiachi=(EditText)popup.findViewById(R.id.edtDiachi);
        edtEmail=(EditText)popup.findViewById(R.id.edtEmail);
        edtSodienthoai=(EditText)popup.findViewById(R.id.edtSodienthoai);
    }
    private void showFileChooser(int NUMBER) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),NUMBER);

        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getActivity(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        return super.onContextItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case StaticValues.IMAGE_CONGTY_FILECHOOSE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    FirebaseData.create_Images_Card(StaticValues.TYPE_LOGO,StaticValues.idfacebook,uri);
                    //HienthiView.uri_congty=uri;
                    //url=uri.toString();
                    Log.d("vthanh", "File Uri: " + uri.toString());

                    // Get the path
                    //String path = getPath(this, uri);

                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.i("vt100",picturePath);
                    Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
                    Drawable drawable=new BitmapDrawable(getResources(),bitmap);
                    //txtLinkAva.setText(picturePath);
                    if (bitmap!=null) {
                        imgLogo.setImageBitmap(bitmap);
                    }else Log.i("null","null");
                    //
//                        int index= lastIndexOf(path, '/');
//                        int length=path.length();
//                        String filechoose=substring(path, index, length);
                    //txtLinkAva.setText(filechoose);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
            case StaticValues.IMAGE_BACKGROUND_FILECHOOSE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    FirebaseData.create_Images_Card(StaticValues.TYPE_BACKGROUND,StaticValues.idfacebook,uri);
                    //HienthiView.uri_background=uri;
                    //url=uri.toString();
                    Log.d("vthanh", "File Uri: " + uri.toString());

                    // Get the path
                    //String path = getPath(this, uri);
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    //
                    //txtLinkBackground.setText(picturePath);
                    Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
                    Drawable drawable=new BitmapDrawable(getResources(),bitmap);
                    //
                    layout.setBackground(drawable);
                    //
//                        int index= lastIndexOf(path, '/');
//                        int length=path.length();
//                        String filechoose=substring(path, index, length);
//                        txtLinkAva.setText(filechoose);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
