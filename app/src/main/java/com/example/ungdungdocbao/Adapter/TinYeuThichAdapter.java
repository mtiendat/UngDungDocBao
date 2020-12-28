package com.example.ungdungdocbao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.Activity.MainActivity;
import com.example.ungdungdocbao.Activity.TrangChiTiet;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TinYeuThichAdapter extends RecyclerView.Adapter<TinYeuThichAdapter.TinYeuThichViewHolder>{

    private final List<Newspaper> listNews;
    private LayoutInflater mInflater;
    private Context context;


    public TinYeuThichAdapter(Context context, List<Newspaper> newsList) {
        mInflater=LayoutInflater.from(context);
        this.listNews = newsList;
        this.context=context;
    }
    @NonNull
    @Override
    public TinYeuThichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.newspaper_list_item,parent,false);
        return new TinYeuThichViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull TinYeuThichViewHolder holder, int position) {
        final Newspaper newspaper = listNews.get(position);
        Picasso.get()
                .load(newspaper.getHinhAnh())
                .into(holder.mHinhAnh);
        holder.mTieuDe.setText(newspaper.getTieuDe());
        holder.mTieuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangChiTiet.class);
                String id = String.valueOf(newspaper.getID());
                intent.putExtra("ID",id);
                MainActivity.dsTinDaXem.add(newspaper.getID());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class TinYeuThichViewHolder extends RecyclerView.ViewHolder{

        final ImageView mHinhAnh;
        final TextView mTieuDe;
        final TextView mMoTa;
        final TextView mID;
        final ImageView mFlag;
        final TinYeuThichAdapter mAdapter;
        public TinYeuThichViewHolder(@NonNull View itemView, TinYeuThichAdapter mAdapter) {
            super(itemView);
            mHinhAnh=itemView.findViewById(R.id.img_main);
            mTieuDe=itemView.findViewById(R.id.txt_title);
            mMoTa=itemView.findViewById(R.id.txt_desciption);
            mID=itemView.findViewById(R.id.txt_ID);
            mFlag = itemView.findViewById(R.id.img_flag);
            this.mAdapter = mAdapter;

//            this.mMoTa.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(),TrangChiTiet.class);
//                    intent.putExtra("Title",mTieuDe.getText());
//                    intent.putExtra("Mota",mMoTa.getText());
//
//                }
//            });
//            this.mHinhAnh.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(),TrangChiTiet.class);
//                    intent.putExtra("Title",mTieuDe.getText());
//                    intent.putExtra("Mota",mMoTa.getText());
//
//                }
//            });
        }
    }
}
