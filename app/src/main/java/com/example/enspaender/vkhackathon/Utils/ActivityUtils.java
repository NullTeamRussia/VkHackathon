package com.example.enspaender.vkhackathon.Utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by enspaender on 21.10.17.
 */

public class ActivityUtils {

  public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull
      Fragment fragment,int frameID){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameID,fragment);

    transaction.commit();
  }

  public static void replaceFragment(FragmentManager fragmentManager,int containerID,Fragment fragment){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(containerID,fragment);

    transaction.commit();
  }
}
