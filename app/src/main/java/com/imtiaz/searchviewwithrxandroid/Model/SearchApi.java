package com.imtiaz.searchviewwithrxandroid.Model;

import android.content.Context;

import com.imtiaz.searchviewwithrxandroid.Retrofit.ApiInterface;
import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.imtiaz.searchviewwithrxandroid.Retrofit.RetrofiClient;
import com.imtiaz.searchviewwithrxandroid.interfaces.OnRequestComplete;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by ASUS on 11-Oct-17.
 */

public class SearchApi {
    OnRequestComplete requestComplete;
    PublishSubject publishSubject;
    public SearchApi(final Context context, final String query, final OnRequestComplete onRequestComplete) {
        this.requestComplete = onRequestComplete;
        ApiInterface apiInterface= RetrofiClient.getApiInterface();
        if (publishSubject == null) {
            publishSubject = PublishSubject.create();
            publishSubject
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .switchMap(searchValue -> apiInterface.getData(query)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()))
                    .subscribeWith(new DisposableObserver<Notices>() {
                        @Override
                        public void onNext(Notices response) {
                            //Update View here
                            requestComplete.onRequestComplete(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //On error
                            requestComplete.onRequestError("Something went wrong..");
                        }

                        @Override
                        public void onComplete() {
                            //On complete
                        }
                    });
            publishSubject.onNext(query);
        }


    }
}
