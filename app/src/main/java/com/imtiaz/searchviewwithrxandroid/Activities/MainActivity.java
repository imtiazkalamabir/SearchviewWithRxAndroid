package com.imtiaz.searchviewwithrxandroid.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.imtiaz.searchviewwithrxandroid.Adapter.MainAdapter;
import com.imtiaz.searchviewwithrxandroid.Presenter.Presenter;
import com.imtiaz.searchviewwithrxandroid.R;
import com.imtiaz.searchviewwithrxandroid.Retrofit.ApiInterface;
import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.NoticeItem;
import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.Notices;
import com.imtiaz.searchviewwithrxandroid.Retrofit.RetrofiClient;
import com.imtiaz.searchviewwithrxandroid.interfaces.MainActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    ApiInterface apiInterface;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.myrecyler)
    RecyclerView myrecyler;
    Presenter presenter;
    List<NoticeItem> list;

    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiInterface = RetrofiClient.getApiInterface();

        searchview.setIconifiedByDefault(false);
        list=new ArrayList<>();

        adapter=new MainAdapter(getAppContext(),list);
        myrecyler.setLayoutManager(new LinearLayoutManager(this));
        myrecyler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        presenter=new Presenter(this);
        searchview.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //Here we request a search
                        presenter.getSearchResult(newText);
                        return false;
                    }
                });
    }


    @Override
    public void showSearchData(Notices hashMap) {
        list=hashMap.getNotice();
        if(list!=null){
            adapter.setData(list);
        }else{
            adapter.clear();
        }

        if(hashMap!=null){

            if(hashMap.getNotice()!=null){
                for(NoticeItem noticeItem:hashMap.getNotice()){
                    Log.e("notice",noticeItem.getTitle());
                }
            }
        }
    }

    @Override
    public void startLoading() {
        //Toast.makeText(getAppContext(),"Start hunting",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void stopLoading() {

        //Toast.makeText(getAppContext(),"Finished",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {

        Toast.makeText(getAppContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }
}
