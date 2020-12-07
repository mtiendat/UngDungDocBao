package com.example.ungdungdocbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewspaperAdapter extends RecyclerView.Adapter<NewspaperAdapter.NewspaperViewHolder> {

    private final List<Newspaper> listNews;
    private LayoutInflater mInflater;
    private Context context;
    public NewspaperAdapter(Context context, List<Newspaper> newsList) {
        mInflater=LayoutInflater.from(context);
        this.listNews = newsList;
        this.context=context;
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
        Picasso.get()
                .load(newspaper.getHinhAnh())
                .into(holder.mHinhAnh);
        holder.mTieuDe.setText(newspaper.getTieuDe().toString());
        holder.mMoTa.setText(newspaper.getMoTa().toString());

    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class NewspaperViewHolder extends RecyclerView.ViewHolder{

        final ImageView mHinhAnh;
        final TextView mTieuDe;
        final TextView mMoTa;

        final NewspaperAdapter mAdapter;
        public NewspaperViewHolder(@NonNull View itemView, NewspaperAdapter mAdapter) {
            super(itemView);
            mHinhAnh=itemView.findViewById(R.id.img_main);
            mTieuDe=itemView.findViewById(R.id.txt_title);
            mMoTa=itemView.findViewById(R.id.txt_desciption);
            this.mAdapter = mAdapter;
        }
    }
}
