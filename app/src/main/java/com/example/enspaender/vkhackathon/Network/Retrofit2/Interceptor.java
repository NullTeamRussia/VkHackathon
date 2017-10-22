package com.example.enspaender.vkhackathon.Network.Retrofit2;

import android.support.annotation.NonNull;
import com.example.enspaender.vkhackathon.BuildConfig;
import java.io.IOException;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by enspa on 22.05.2017.
 */

public class Interceptor implements okhttp3.Interceptor {
    private final okhttp3.Interceptor mLoggingInterceptor;
    private int code;

    private Interceptor() {
        mLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        code = 0;
    }

    @NonNull
    public static okhttp3.Interceptor create() {
        return new Interceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        return mLoggingInterceptor.intercept(chain);
    }

    public int getCode() {
        return code;
    }
}
