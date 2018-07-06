package com.imtiaz.searchviewwithrxandroid.Retrofit;

import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.imtiaz.searchviewwithrxandroid.Utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 10-Oct-17.
 */

public interface ApiInterface{

    @POST(Constants.search_result_api)
    Observable<Notices> getData(@Query("key") String key);

}
