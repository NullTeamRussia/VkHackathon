package com.example.enspaender.vkhackathon.Utils;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import com.example.enspaender.vkhackathon.R;
import java.util.List;

/**
 * Created by enspaender on 21.10.17.
 */

public class NotificationUtils {
  private static final String TAG = NotificationUtils.class.getSimpleName();

  private Context context;

  public NotificationUtils(Context context){
    this.context = context;
  }

  /** show base news notification */
  public void showNotificationMessage(String title, String description,Intent intent){
    showNotification(title, description, intent);
  }


  private void showNotification(String title, String description,Intent intent){

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
    final PendingIntent resultPendingIntent =
        PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        );

    Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
        .setTicker(title)
        .setWhen(0)
        .setAutoCancel(true)
        .setContentTitle(title)
        .setContentIntent(resultPendingIntent)
        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
        .setContentText(description)
        .build();

    NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(100,notification);
  }



  /** show notification for news with inner routing */
  public void showNewsNotificationWithRoute(String title,String description,String url,Intent intent){
    showNotificationWithRoute(title, description,url, intent);
  }

  private void showNotificationWithRoute(String title, String description,
      String url,Intent intent){

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
    final PendingIntent resultPendingIntent =
        PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        );

    Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
        .setTicker(title)
        .setWhen(0)
        .setAutoCancel(true)
        .setContentTitle(title)
        .setContentIntent(resultPendingIntent)
        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
        .setContentText(description)
        .addAction(R.drawable.route,"show route",resultPendingIntent)
        .build();

    NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(100,notification);
  }


  public static boolean isAppInBackground(Context context){
    boolean isInBackground = true;
    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
      List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
      for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
        if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
          for (String activeProcess : processInfo.pkgList) {
            if (activeProcess.equals(context.getPackageName())) {
              isInBackground = false;
            }
          }
        }
      }
    } else {
      List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
      ComponentName componentInfo = taskInfo.get(0).topActivity;
      if (componentInfo.getPackageName().equals(context.getPackageName())) {
        isInBackground = false;
      }
    }

    return isInBackground;
  }
}
