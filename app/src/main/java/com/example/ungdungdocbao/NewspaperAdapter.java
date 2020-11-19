package com.example.ungdungdocbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class NewspaperAdapter extends RecyclerView.Adapter<NewspaperAdapter.NewspaperViewHolder> {

    private final LinkedList<Newspaper> listNews;
    private LayoutInflater mInflater;
    public NewspaperAdapter(Context context, LinkedList<Newspaper> newsList) {
        mInflater=LayoutInflater.from(context);
        this.listNews = newsList;
    }

    @NonNull
    @Override
    public NewspaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.newspaper_list_item,parent,false);
        return new NewspaperViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull NewspaperViewHolder holder, int position) {
        Newspaper newspaper = listNews.get(position);
        holder.mTitle.setText(newspaper.getTitle().toString());
        holder.mDesciption.setText(newspaper.getDescription().toString());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class NewspaperViewHolder extends RecyclerView.ViewHolder{

        private  final ImageView imageView;
        private final TextView mTitle;
        private final TextView mDesciption;
        final NewspaperAdapter mAdapter;
        public NewspaperViewHolder(@NonNull View itemView, NewspaperAdapter mAdapter) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_main);
            mTitle=itemView.findViewById(R.id.txt_title);
            mDesciption=itemView.findViewById(R.id.txt_desciption);
            this.mAdapter = mAdapter;
        }
    }
}
