package com.example.enspaender.vkhackathon.Network.Retrofit2;

import android.support.annotation.NonNull;
import com.example.enspaender.vkhackathon.BuildConfig;
import com.example.enspaender.vkhackathon.Network.Api.ExtraService;
import com.example.enspaender.vkhackathon.Network.Api.MapService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by enspa on 09.02.2017.
 */

public class RetrofitManager {

  private static final String BASE_URL = "http://api.hermitage.nullteam.info";
  private static final String HELPER_URL = "http://helper.hermitage.nullteam.info";
  private  static OkHttpClient sClient;
  //private static volatile TasksDetailService tasksDetailService;
  private static volatile MapService mMapService;
  private static volatile ExtraService mExtraService;


  private RetrofitManager(){}

  public static MapService getmMapService(){
    MapService service = mMapService;
    if (service == null){
      synchronized (RetrofitManager.class){
        if (service == null){
          service = mMapService = buildingRetrofit(false).create(MapService.class);
        }
      }
    }
    return service;
  }

  public static ExtraService getmExtraService(){
    ExtraService service = mExtraService;
    if (service == null){
      synchronized (RetrofitManager.class){
        if (service == null){
          service = mExtraService = buildingRetrofit(false).create(ExtraService.class);
        }
      }
    }
    return service;
  }

  public static ExtraService getExtraHelperService(){
    ExtraService service = mExtraService;
    if (service == null){
      synchronized (RetrofitManager.class){
        if (service == null){
          service = mExtraService = buildHelperRetrofit().create(ExtraService.class);
        }
      }
    }
    return service;
  }

    //public static InventarizationEquipService inventarization(){
    //    InventarizationEquipService inv = inventarizationEquipService;
    //    if (inv == null){
    //        synchronized (RetrofitManager.class){
    //            if (inv == null){
    //                inv = inventarizationEquipService = buildingRetrofit().create(InventarizationEquipService.class);
    //            }
    //        }
    //    }
    //    return inv;
    //}

  @NonNull
  private static Retrofit buildingRetrofit(boolean isLogin){
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(isLogin ? buildLoginClient() : buildMainClient())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
  }

  @NonNull
  private static Retrofit buildHelperRetrofit(){
    return new Retrofit.Builder()
        .baseUrl(HELPER_URL)
        .client(buildMainClient())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
  }

  @NonNull
  public static Gson getGson(){
    return new GsonBuilder().create();
  }


  @NonNull private static OkHttpClient buildLoginClient()
  {
    return new OkHttpClient.Builder()
        .addInterceptor(new LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("REQUEST")
            .response("RESPONSE")
            .build())
        .build();
  }

  @NonNull private static OkHttpClient buildMainClient()
  {
    return new OkHttpClient.Builder()
        .addInterceptor(new LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .addHeader("Authorization","test")
            .request("REQUEST")
            .response("RESPONSE")
            .build())
        .build();
  }

}
