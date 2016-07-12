package com.example.vanthanh.yourcardvisit.controls;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by Van Thanh on 7/11/2016.
 */
public class FirebaseData {
    public static void create_Info_Card(Activity activity,String idFacebook,Data_Info data_info){
        //TODO:lưu thông tin
        Firebase.setAndroidContext(activity);
        Firebase root=new Firebase(StaticValues.LINKROOT+"/"+StaticValues.CHILD_DATA+"/"+idFacebook);
        Log.d("abc", idFacebook);
        root.setValue(data_info);
        Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();

        //TODO:lưu link ảnh

    }
    public static void create_Images_Card(final String type_image, final String idFacebook,Uri uri){
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference reference=storage.getReferenceFromUrl(StaticValues.LINKSTORAGE + idFacebook + "/");
        StorageReference ref_logo=reference.child(type_image+"/"+uri.getLastPathSegment());
        UploadTask uploadTask_logo=ref_logo.putFile(uri);
        uploadTask_logo.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Firebase root=new Firebase(StaticValues.LINKROOT+"/"+StaticValues.CHILD_IMAGE+"/"+idFacebook);
                root.child(type_image).setValue(taskSnapshot.getDownloadUrl().toString());
            }
        });
    }
    public static void get_Card_From_Firebase(){

    }

}
