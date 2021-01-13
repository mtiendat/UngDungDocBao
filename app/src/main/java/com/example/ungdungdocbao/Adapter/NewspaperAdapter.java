package com.example.ungdungdocbao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.Activity.MainActivity;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.Activity.TrangChiTiet;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewspaperAdapter extends RecyclerView.Adapter<NewspaperAdapter.NewspaperViewHolder> {

    private List<Newspaper> listNews;
    private LayoutInflater mInflater;
    private Context context;


    public NewspaperAdapter(Context context, List<Newspaper> newsList) {
        mInflater=LayoutInflater.from(context);
        this.listNews = newsList;
        this.context=context;
    }
    public void updateDataSet(List<Newspaper> newResult){
        if(newResult!=null){
            listNews = newResult;
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NewspaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.newspaper_list_item,parent,false);
        return new NewspaperViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewspaperViewHolder holder, int position) {

        final Newspaper newspaper = listNews.get(position);
        final String id = String.valueOf(newspaper.getID());
        final String name = String.valueOf(FirstFragment.USER_NAME);
        final String id_user = String.valueOf(FirstFragment.USER_ID);
        final String avatar = String.valueOf(FirstFragment.USER_AVATAR);
        Picasso.get()
                .load(newspaper.getHinhAnh())
                .into(holder.mHinhAnh);
        holder.mTieuDe.setText(newspaper.getTieuDe().toString());
        holder.mTieuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Trang Chi Tiết
                Intent intent = new Intent(v.getContext(), TrangChiTiet.class);
                intent.putExtra("ID",id);
                intent.putExtra("id_user",id_user);
                intent.putExtra("user_name",name);
                intent.putExtra("user_avatar",avatar);
                MainActivity.dsTinDaXem.add(newspaper.getID());
                v.getContext().startActivity(intent);
                //Tin Đã Xem
                int dem=0;
                //Kiểm Tra Xem ID có trùng không?
                for (int item:MainActivity.dsTinDaXem) {
                    if(newspaper.getID()!=item){
                        dem++;
                    }
                }
                if(dem==MainActivity.dsTinDaXem.size()) {
                    MainActivity.dsTinDaXem.add(newspaper.getID()); //add id vào danh sách
                }

            }
        });
        holder.mHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangChiTiet.class);
                intent.putExtra("ID",id);
                intent.putExtra("id_user",id_user);
                intent.putExtra("user_name",name);
                intent.putExtra("user_avatar",avatar);
                MainActivity.dsTinDaXem.add(newspaper.getID());
                v.getContext().startActivity(intent);
                //Tin Đã Xem
                int dem=0;
                //Kiểm Tra Xem ID có trùng không?
                for (int item:MainActivity.dsTinDaXem) {
                    if(newspaper.getID()!=item){
                        dem++;
                    }
                }
                if(dem==MainActivity.dsTinDaXem.size()) {
                    MainActivity.dsTinDaXem.add(newspaper.getID()); //add id vào danh sách
                }
            }
        });
        holder.mMoTa.setText(newspaper.getMoTa().toString());
        holder.mFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    holder.mFlag.setBackgroundResource(R.drawable.ic_love);
                    MainActivity.dsTinYeuThich.add(newspaper.getID());

            }
        });

    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class NewspaperViewHolder extends RecyclerView.ViewHolder{

        final ImageView mHinhAnh;
        final TextView mTieuDe;
        final TextView mMoTa;
        final TextView mID;
        final ImageView mFlag;
        final NewspaperAdapter mAdapter;
        public NewspaperViewHolder(@NonNull View itemView, NewspaperAdapter mAdapter) {
            super(itemView);
            mHinhAnh=itemView.findViewById(R.id.img_main);
            mTieuDe=itemView.findViewById(R.id.txt_title);
            mMoTa=itemView.findViewById(R.id.txt_desciption);
            mID=itemView.findViewById(R.id.txt_ID);
            mFlag = itemView.findViewById(R.id.img_flag);
            this.mAdapter = mAdapter;
        }
    }


}
