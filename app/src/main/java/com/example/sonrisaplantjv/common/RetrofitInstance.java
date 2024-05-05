package com.example.sonrisaplantjv.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sonrisaplantjv.data.CurrentContext;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "http://192.168.30.1:8080/api/SonrisaPlant/";
    private static Retrofit retrofit = null;

    public RetrofitInstance() {
    }

    public static Retrofit getRetrofitInstance() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", CurrentContext.AccessToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
