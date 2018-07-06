package com.imtiaz.searchviewwithrxandroid.Presenter;

import com.imtiaz.searchviewwithrxandroid.Model.SearchApi;
import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.imtiaz.searchviewwithrxandroid.interfaces.MainActivityView;
import com.imtiaz.searchviewwithrxandroid.interfaces.OnRequestComplete;

/**
 * Created by ASUS on 11-Oct-17.
 */

public class Presenter {
    MainActivityView view;
    public Presenter(MainActivityView mainActivity){
        this.view=mainActivity;
    }

    public void getSearchResult(String query){
        new SearchApi(view.getAppContext(), query, new OnRequestComplete() {
            @Override
            public void onRequestComplete(Notices o) {
                view.stopLoading();
                view.showSearchData(o);
            }

            @Override
            public void onRequestError(String errorMsg) {
                view.stopLoading();
                view.showMessage(errorMsg);
            }
        });
    }

}
