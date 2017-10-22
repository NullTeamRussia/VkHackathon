package com.example.enspaender.vkhackathon.Services;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by enspaender on 21.10.17.
 */

public class GetInstanceId extends FirebaseInstanceIdService {
  private static final String TAG = GetInstanceId.class.getSimpleName();

  @Override public void onTokenRefresh() {
    String refreshToken = FirebaseInstanceId.getInstance().getToken();
    Log.e(TAG,"new fb token: " + refreshToken);

  }
}
