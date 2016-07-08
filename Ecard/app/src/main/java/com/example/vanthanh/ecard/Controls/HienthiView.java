package com.example.vanthanh.ecard.Controls;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanthanh.ecard.Fragment.fragment_card;
import com.example.vanthanh.ecard.Objects.InfoCard;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by Van Thanh on 6/14/2016.
 */
public class HienthiView {
    final public static String LINK_FIBASE="https://ecardvisit-b1a54.firebaseio.com/";
    public static String LINK_STORAGE="gs://ecardvisit-b1a54.appspot.com/Image/";
    final public static String KEY_SEARCH="keysearch";
    //
    public static final String TAG_EDIT="tagedit";
    public static final String TAG_CARD="tagcard";
    public static final int IMAGE_CONGTY_FILECHOOSE=0;
    public static final int IMAGE_BACKGROUND_FILECHOOSE = 1;
    //
    public static Uri uri_background=null;
    public static Uri uri_congty=null;
    public static void SetView(EditText edt, TextView txt){
        txt.setText(edt.getText().toString());
    }
    public static void setCardFirebase(InfoCard infoCard,String idFacebook){ //object này lấy ID facebook làm key
        Firebase root=new Firebase(LINK_FIBASE);
        root.child(idFacebook).setValue(infoCard);
    }
    public static InfoCard getCardFirebase(String idFacebook){
        final InfoCard infoCard=new InfoCard();

        Firebase link=new Firebase(LINK_FIBASE+idFacebook);
        link.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                topic top = new topic();
//                Map<String, String> map = dataSnapshot.getValue(Map.class);
//                top.ten = map.get("ten");
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                infoCard.setEc_id(map.get("ec_id"));
                infoCard.setEc_address(map.get("ec_address"));
                infoCard.setEc_company(map.get("ec_company"));
                infoCard.setEc_email(map.get("ec_email"));
                infoCard.setEc_fbhomework(map.get("ec_fbhomework"));
                infoCard.setEc_fullname(map.get("ec_fullname"));
                infoCard.setEc_level(map.get("ec_level"));
                infoCard.setEc_imgBackground(map.get("ec_imgBackground"));
                infoCard.setEc_imgCongty(map.get("ec_imgCongty"));
                infoCard.setEc_phone(map.get("ec_phone"));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return infoCard;
    }
    public static String Convert_Image_to_String(BitmapDrawable bitmapDrawable){
        Bitmap bmp = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String imgString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return imgString;
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }



    public static void SearchKey(final String key){
        Firebase root=new Firebase(LINK_FIBASE);
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(key==dataSnapshot.getKey().toString()) Log.i("vt100",dataSnapshot.getKey().toString()+" "+key);
                else Log.i("vt100","NULL");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public static StorageReference ConnectStorage(String link,FirebaseStorage storage){
        StorageReference storageReference=storage.getReferenceFromUrl(link);
        return storageReference;
    }
    public static void UploadImageStorage(final Context context,FirebaseStorage storage, final String child, Uri uri, final InfoCard infoCard){
        StorageReference storageReference=ConnectStorage(LINK_STORAGE, storage); //Link_storage="gs://project-402830641404228791.appspot.com/Images/"
        Log.i("abcccc",LINK_STORAGE);
        StorageReference riversRef=storageReference.child("facebook"+"/" + child);
        UploadTask uploadTask=riversRef.putFile(uri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
            }
        });
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void SendDataFromFragmentCard(InfoCard infoCard,fragment_card fc,TextView txtLinklogo,TextView txtLinkBackgruond){
        Bundle bundle=new Bundle();
        bundle.putString("ecid", infoCard.getEc_id().toString());
        bundle.putString("ecname", infoCard.getEc_fullname().toString());
        bundle.putString("eccongty", infoCard.getEc_company().toString());
        bundle.putString("ecchucvu", infoCard.getEc_level().toString());
        bundle.putString("ecemail", infoCard.getEc_email().toString());
        bundle.putString("ecdiachi", infoCard.getEc_address().toString());
        bundle.putString("ecsodienthoai", infoCard.getEc_phone().toString());
        bundle.putString("linklogo",txtLinklogo.getText().toString());
        bundle.putString("linkbackground", txtLinkBackgruond.getText().toString());
        fc.setArguments(bundle);
    }
    public static void getImagetoCard(String logo,String background,ImageView imageView,View v){
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference storageRef=firebaseStorage.getReferenceFromUrl(logo + ".jpg");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
             Log.i("vanthanh",uri.toString());
            }
        });
        StorageReference storageRef2=firebaseStorage.getReferenceFromUrl(background+".jpg");
        //Picasso.with(this).load(String.valueOf(storageRef.getDownloadUrl()));
    }
}
