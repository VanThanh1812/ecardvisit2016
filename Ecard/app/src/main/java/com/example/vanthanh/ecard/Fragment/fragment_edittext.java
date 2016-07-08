package com.example.vanthanh.ecard.Fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanthanh.ecard.Controls.HienthiView;
import com.example.vanthanh.ecard.Objects.InfoCard;
import com.example.vanthanh.ecard.R;
import com.firebase.client.Firebase;
import com.google.firebase.storage.FirebaseStorage;

import static com.example.vanthanh.ecard.R.layout.fragmentedittext;

/**
 * Created by Van Thanh on 6/21/2016.
 */
public class fragment_edittext extends android.app.Fragment {
    EditText edtID,edtName,edtChucvu,edtSodienthoai,edtDiachi,edtEmail,edtCongty;
    ImageView imgCongty,imgBackground;
    TextView txtLinkAva,txtLinkBackground;
    Button preview,btnCreate;
    FirebaseStorage storage;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(fragmentedittext,null,false);
        storage = FirebaseStorage.getInstance();
        return v;
    }
    public void AnhxaView(Activity v){
        preview=(Button)v.findViewById(R.id.btnPreview);
        imgCongty=(ImageView)v.findViewById(R.id.imgAvaCongty);
        imgBackground=(ImageView)v.findViewById(R.id.imgBackground);
        //
        edtID= (EditText) v.findViewById(R.id.edtID);
        edtName=(EditText)v.findViewById(R.id.edtName);
        edtChucvu=(EditText)v.findViewById(R.id.edtChucvu);
        edtCongty= (EditText) v.findViewById(R.id.edtCongty);
        edtDiachi= (EditText) v.findViewById(R.id.edtDiachi);
        edtEmail=(EditText)v.findViewById(R.id.edtEmail);
        edtSodienthoai=(EditText)v.findViewById(R.id.edtSdt);
        txtLinkAva=(TextView)v.findViewById(R.id.txtLinkAva);
        txtLinkAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(HienthiView.IMAGE_CONGTY_FILECHOOSE);
            }
        });
        txtLinkBackground=(TextView)v.findViewById(R.id.txtLinkCongty);
        txtLinkBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(HienthiView.IMAGE_BACKGROUND_FILECHOOSE);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity v=getActivity();
        AnhxaView(v);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview.setTextColor(Color.RED);
                Context v1 = getActivity().getApplicationContext();
                Preview(v1);
                preview.setTextColor(Color.WHITE);
            }
        });
    }


    public void Preview(Context a){
        Bundle bundle= new Bundle();
        final fragment_card fc = new fragment_card();
        //


        //
        InfoCard infoCard = new InfoCard();
        infoCard.setEc_id(edtID.getText().toString());
        infoCard.setEc_fullname(edtName.getText().toString());
        infoCard.setEc_phone(edtSodienthoai.getText().toString());
        infoCard.setEc_level(edtChucvu.getText().toString());
        infoCard.setEc_company(edtCongty.getText().toString());
        infoCard.setEc_address(edtDiachi.getText().toString());
        infoCard.setEc_email(edtEmail.getText().toString());
        infoCard.setEc_fbhomework("Chưa có đâu");//truyền từ bên activity khi login Facebook
        infoCard.setEc_imgBackground("Link chưa có");
        infoCard.setEc_imgCongty("Link chưa có");
        //
        HienthiView.SendDataFromFragmentCard(infoCard,fc,txtLinkAva,txtLinkBackground);

        //
        final LinearLayout linearLayout= (LinearLayout) getActivity().findViewById(R.id.actyCreateCard);
        //
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if ((!fc.isInLayout()) && (fc != null)) {

            fragmentTransaction.replace(R.id.actyCreateCard, fc, HienthiView.TAG_CARD).addToBackStack("null");
        } else Log.i("vthanh", "here");
        fragmentTransaction.commit();

        btnCreate=new Button(this.getActivity());
        btnCreate.setPadding(1,1,1,1);
        btnCreate.setText("Create card");
        btnCreate.setBackgroundColor(Color.parseColor("#2c5dae"));
        btnCreate.setTextColor(Color.parseColor("#ffffff"));

        final Button btnEditcard=new Button(this.getActivity());
        btnEditcard.setText("Edit card");
//        btnEditcard.setBackgroundColor(Color.parseColor("#2c5dae"));
//        btnEditcard.setTextColor(Color.parseColor("#ffffff"));

        linearLayout.addView(btnCreate);
        linearLayout.addView(btnEditcard);
        linearLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);

        btnEditcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                btnEditcard.setVisibility(View.GONE);
                btnCreate.setVisibility(View.GONE);
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoCard infoCard = new InfoCard();
                infoCard.setEc_id(fc.txtId.getText().toString());
                infoCard.setEc_fullname(fc.txtName.getText().toString());
                infoCard.setEc_phone(fc.txtSodienthoai.getText().toString());
                infoCard.setEc_level(fc.txtChucvu.getText().toString());
                infoCard.setEc_company(fc.txtCongty.getText().toString());
                infoCard.setEc_address(fc.txtDiachi.getText().toString());
                infoCard.setEc_email(fc.txtEmail.getText().toString());
                infoCard.setEc_fbhomework("Chưa có đâu");//truyền từ bên activity khi login Facebook
                infoCard.setEc_imgBackground(HienthiView.LINK_STORAGE + "idFacebook/" + "background");
                infoCard.setEc_imgCongty(HienthiView.LINK_STORAGE + "idFacebook/" + "logo");
                //TODO: set to firebase
                HienthiView.UploadImageStorage(getActivity().getApplicationContext(), storage, "background", HienthiView.uri_background,infoCard);
                HienthiView.UploadImageStorage(getActivity().getApplicationContext(), storage, "logo", HienthiView.uri_congty, infoCard);

                Firebase root=new Firebase(HienthiView.LINK_FIBASE+"idFacebook");
                root.setValue(infoCard);
//                while ((infoCard.getEc_imgBackground()==null)&(infoCard.getEc_imgCongty()==null)){
//                    //TODO:again
//                    HienthiView.setCardFirebase(infoCard,"facebook");
//                }

//
//  AlertDialog.Builder aBuilder = new AlertDialog.Builder(btnCreate.getContext());
//                aBuilder.setTitle("Thành công");
//                aBuilder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        btnCreate.setVisibility(View.GONE);
//                        btnEditcard.setVisibility(View.GONE);
//                    }
//                });
//                aBuilder.show();
                btnCreate.setVisibility(View.GONE);
                btnEditcard.setVisibility(View.GONE);
            }
        });
    }
    //TODO: chọn file
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case HienthiView.IMAGE_CONGTY_FILECHOOSE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    HienthiView.uri_congty=uri;
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
                    txtLinkAva.setText(picturePath);
                    if (bitmap!=null) {
                        imgCongty.setImageBitmap(bitmap);
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
            case HienthiView.IMAGE_BACKGROUND_FILECHOOSE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    HienthiView.uri_background=uri;
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
                    txtLinkBackground.setText(picturePath);
                    Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
                    Drawable drawable=new BitmapDrawable(getResources(),bitmap);
                    //
                    imgBackground.setBackground(drawable);
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
