package com.example.vanthanh.yourcardvisit.controls;

import android.app.Activity;
import android.app.FragmentManager;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.fragment.Fragment_FormPreview;
import com.example.vanthanh.yourcardvisit.fragment.FragmentMain;
import com.example.vanthanh.yourcardvisit.fragment.Fragment_AddCard;
import com.example.vanthanh.yourcardvisit.fragment.Fragment_ListCard;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Func_fragment {
    public static void setFragment(Activity activity,String keyfragment){
        FragmentManager fragmentManager=activity.getFragmentManager() ;
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (keyfragment){
            case StaticValues.TAG_FRAGMENTMAIN:
                FragmentMain fragmentMain=new FragmentMain();
                fragmentTransaction.replace(R.id.framelayoutmain,fragmentMain, StaticValues.TAG_FRAGMENTMAIN).addToBackStack(null);
                break;
            case StaticValues.TAG_FRAGMENTADDCARD:
                Fragment_AddCard fragment_addCard=new Fragment_AddCard();
                fragmentTransaction.replace(R.id.framelayoutmain,fragment_addCard,StaticValues.TAG_FRAGMENTADDCARD).addToBackStack(null);
                break;
            case StaticValues.TAG_FRAGMENT_LISTCARD:
                Fragment_ListCard fragment_listCard=new Fragment_ListCard();
                fragmentTransaction.replace(R.id.framelayoutmain,fragment_listCard,StaticValues.TAG_FRAGMENT_LISTCARD).addToBackStack(null);
                break;
            case StaticValues.TAG_FRAGMENT_CREATE:
                Fragment_FormPreview fragment_Info=new Fragment_FormPreview();
                fragmentTransaction.replace(R.id.framelayoutmain,fragment_Info,StaticValues.TAG_FRAGMENT_CREATE).addToBackStack(null);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
