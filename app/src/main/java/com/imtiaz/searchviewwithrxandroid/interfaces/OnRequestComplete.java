package com.imtiaz.searchviewwithrxandroid.interfaces;

import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.Notices;

public interface OnRequestComplete {
    void onRequestComplete(Notices o);
    void onRequestError(String errorMsg);
}
