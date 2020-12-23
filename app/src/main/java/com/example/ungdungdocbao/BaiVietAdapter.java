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

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.WordViewHolder> {
    private  final LinkedList<BaiViet> mWordList;
    private LayoutInflater mInflater;
    private Context context;

    public BaiVietAdapter(Context context,LinkedList<BaiViet> mWordList) {
        this.mWordList = mWordList;
        mInflater=LayoutInflater.from(context);
        this.context= context;

    }
    public BaiVietAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView=mInflater.inflate(R.layout.baiviet_item,parent,false);
        return new WordViewHolder(mItemView,this);
    }
    public class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTim;


        final BaiVietAdapter mAdapter;
        public WordViewHolder(View itemView,BaiVietAdapter adapterWordList){
            super(itemView);
            txtTim=itemView.findViewById(R.id.txtTim);
            this.mAdapter=adapterWordList;
        }
    }
    @NonNull
    @Override

    public void onBindViewHolder(@NonNull BaiVietAdapter.WordViewHolder holder, int position) {
        BaiViet mCurrent=mWordList.get(position);
        holder.txtTim.setText(mCurrent.getTieude());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
