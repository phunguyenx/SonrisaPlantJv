package com.example.sonrisaplantjv.data.remote.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "http://192.168.30.1:8080/api/SonrisaPlant";
    private static Retrofit retrofit = null;
    public static UserApi getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL+"/user/")
                    .addConverterFactory(GsonConverterFactory.create())
                    //checking network.
                    //reference: https://stablekernel.com/article/seamless-network-state-monitoring-with-retrofit-okhttp/
                    .build();
        }
        return retrofit.create(UserApi.class);
    }
}
