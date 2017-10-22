package com.example.enspaender.vkhackathon.Services;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.example.enspaender.vkhackathon.BuildingMaps.BuildingNavigation;
import com.example.enspaender.vkhackathon.BuildingMaps.MainActivity;
import com.example.enspaender.vkhackathon.NewsScreen.NewsActivity;
import com.example.enspaender.vkhackathon.Utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.description;

/**
 * Created by enspaender on 21.10.17.
 */

public class FirebaseCloudMessagingService extends FirebaseMessagingService {
  private static final String TAG = FirebaseCloudMessagingService.class.getSimpleName();

  private String title,description,url;
  private int type;


  @Override public void onMessageReceived(RemoteMessage remoteMessage) {

    Log.e(TAG,"remote message from: " + remoteMessage.getFrom());
    Log.e(TAG, "MESSAGE NOTIFICATION BODY" + remoteMessage.getNotification());
    Log.e(TAG, "MESSAGE DATA" + remoteMessage.getData());

    if (remoteMessage == null) {
      Log.e(TAG, "MESSAGE NULL");
      return;
    }

    if (remoteMessage.getNotification() != null) {
      Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
    }

    if (remoteMessage.getData().size() > 0) {
      Log.e(TAG, "Message data payload: " + remoteMessage.getData());
      JSONObject jsonObject = new JSONObject(remoteMessage.getData());
      showNotification(jsonObject);

    }

  }

  private void showNotification(JSONObject jsonObject){

    Log.e(TAG, "JSON" + jsonObject.toString());

    try {
      //            JSONObject data = jsonObject.getJSONObject("data");
      title = jsonObject.getString("title");
      description = jsonObject.getString("description");
      type = jsonObject.getInt("type");


      Log.e(TAG, "title:" + title);
      Log.e(TAG, "description:" + description);
      //Log.e(TAG, "url:" + url);
      Log.e(TAG, "type" + String.valueOf(type));
      if (type == 1) {
        url = jsonObject.getString("url");
      }


        /** if simple news notification */
        if (type == 0) {

          NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
          Intent simpleNews = new Intent(getApplicationContext(), NewsActivity.class);

          notificationUtils.showNotificationMessage(title, description, simpleNews);
        }

        /** if notification with routing */
        if (type == 1) {
           Intent withRouting = new Intent(getApplicationContext(), BuildingNavigation.class);
            withRouting.putExtra("url", url);

            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());

            notificationUtils.showNewsNotificationWithRoute(title, description, url, withRouting);

        }



    } catch (JSONException e) {
      Log.e(TAG,"Jsone Exception:" + e.getMessage());
    }catch (Exception e){
      Log.e(TAG, "Exception: " + e.getMessage());
    }



  }
}
