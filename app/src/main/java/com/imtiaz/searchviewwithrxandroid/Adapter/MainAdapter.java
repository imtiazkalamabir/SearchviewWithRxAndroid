package com.imtiaz.searchviewwithrxandroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imtiaz.searchviewwithrxandroid.R;
import com.imtiaz.searchviewwithrxandroid.Retrofit.POJO.NoticeItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by sakib on 10/8/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {

    private final LayoutInflater inflater;
    private Context context ;
    List<NoticeItem> list = Collections.emptyList() ;

    public MainAdapter(Context context, List<NoticeItem> list){
        this.context = context ;
        this.list = list ;
        inflater = LayoutInflater.from(context) ;
    }

    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_recyler_item, parent, false);
        MainAdapterViewHolder mainAdapterViewHolder = new MainAdapterViewHolder(view) ;
        return mainAdapterViewHolder;
    }

    public void setData(List<NoticeItem> list){
        this.list.clear();
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder holder, int position) {
        NoticeItem NoticeItem = list.get(position) ;
        holder.name.setText(NoticeItem.getTitle());
        holder.age.setText(NoticeItem.getDescript());
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView name, age ;
        public MainAdapterViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age) ;
        }
    }
}
