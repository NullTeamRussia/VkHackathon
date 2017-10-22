package com.example.enspaender.vkhackathon.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.enspaender.vkhackathon.BuildingMaps.BuildingNavigation;
import com.example.enspaender.vkhackathon.BuildingMaps.MainActivity;
import com.example.enspaender.vkhackathon.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;
import java.util.Arrays;

/**
 * Created by enspaender on 21.10.17.
 */

public class LoginActivity extends AppCompatActivity
{

  private final String[] scope = new String[]{ VKScope.WALL, VKScope.MESSAGES};
  Button loginVk;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
    loginVk = (Button)findViewById(R.id.vk_auth);
    loginVk.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        vkLogin();
      }
    });

    Log.e("VK", Arrays.asList(fingerprints).toString());
  }

  private void vkLogin() {
    VKSdk.login(this,scope);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
      @Override
      public void onResult(VKAccessToken res) {
        // User passed Authorization
        startActivity(new Intent(LoginActivity.this,BuildingNavigation.class));
      }
      @Override
      public void onError(VKError error) {
        // User didn't pass Authorization
        Toast.makeText(getApplicationContext(), "NE OK", Toast.LENGTH_SHORT).show();
      }
    })) {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
