package com.imtiaz.searchviewwithrxandroid.interfaces;

import android.content.Context;

import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.Notices;

public interface MainActivityView {
    void showSearchData(Notices hashMap);

    void startLoading();

    void stopLoading();

    void showMessage(String msg);

    Context getAppContext();
}
