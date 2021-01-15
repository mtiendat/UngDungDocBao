package com.example.ungdungdocbao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.Models.BinhLuan;
import com.example.ungdungdocbao.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BinhLuanGanDayAdapter extends RecyclerView.Adapter<BinhLuanGanDayAdapter.BLViewHolder> {
    private final List<BinhLuan> listBinhLuan;
    public LayoutInflater mInflater;

    public BinhLuanGanDayAdapter(Context context, List<BinhLuan> listBinhLuan) {
        mInflater = LayoutInflater.from(context);
        this.listBinhLuan = listBinhLuan;
    }

    @NonNull
    @Override
    public BLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_cmt,parent,false);
        return new BLViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull BLViewHolder holder, int position) {
        final BinhLuan binhLuan = listBinhLuan.get(position);
        holder.mName.setText(binhLuan.getNameUser());
        holder.mContent.setText(binhLuan.getContent());
        Picasso.get().load(binhLuan.getAvatar()).into(holder.mAvatar);
    }

    @Override
    public int getItemCount() {
        return listBinhLuan.size();
    }

    public class BLViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mAvatar;
        public final TextView mName;
        public final TextView mContent;
        public final BinhLuanGanDayAdapter adapter;
        public BLViewHolder(@NonNull View itemView, BinhLuanGanDayAdapter adapter) {
            super(itemView);
            this.mName = itemView.findViewById(R.id.txt_cmt_name);;
            this.mContent = itemView.findViewById(R.id.txt_comment_content);
            this.mAvatar = itemView.findViewById(R.id.img_avatar);
            this.adapter = adapter;
        }
    }
}
