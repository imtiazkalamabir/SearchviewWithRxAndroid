package com.imtiaz.searchviewwithrxandroid.Retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.imtiaz.searchviewwithrxandroid.Utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 10-Oct-17.
 */

public class RetrofiClient {
    public static Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiInterface getApiInterface(){
        return getRetrofitClient().create(ApiInterface.class);
    }
}
