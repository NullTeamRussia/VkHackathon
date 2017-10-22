package com.example.enspaender.vkhackathon;

import android.app.Application;
import android.content.Intent;
import com.example.enspaender.vkhackathon.Login.LoginActivity;
import com.example.enspaender.vkhackathon.Services.GetInstanceId;
import com.google.firebase.iid.FirebaseInstanceId;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

/**
 * Created by enspaender on 21.10.17.
 */

public class MyApplication extends Application {

  VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
    @Override
    public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
      if (newToken == null) {

        // VkAccessToken is invalid
        Intent intent = new Intent(MyApplication.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

      }
    }
  };

  private String fcmToken;

  @Override public void onCreate() {
    super.onCreate();

    vkAccessTokenTracker.startTracking();
    VKSdk.initialize(this);
  }
}
