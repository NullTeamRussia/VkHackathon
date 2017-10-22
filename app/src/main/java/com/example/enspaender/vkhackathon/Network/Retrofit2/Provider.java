package com.example.enspaender.vkhackathon.Network.Retrofit2;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by enspa on 09.02.2017.
 */

public class Provider {
    private static DataRepository sDataRepository;

    private Provider(){}

    @NonNull
    public static DataRepository provideDataRepository(){
        if(sDataRepository == null){
            sDataRepository = new OnlineDataRepository();
        }
        return sDataRepository;
    }

    public static void setDataRepository(@NonNull OnlineDataRepository speakPhoneRepository) {
        sDataRepository = speakPhoneRepository;
    }

    @MainThread
    public static void init(){
        sDataRepository = new OnlineDataRepository();
    }

    public static DataRepository getsDataRepository() {
        return Provider.sDataRepository;
    }
}
