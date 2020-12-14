package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.ui.setting.SaveState;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrangChiTiet extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Newspaper>{
    RecyclerView recyclerView;
    ListCommentAdapter mAdapter;
    List<BinhLuan> listBinhLuan= new ArrayList<>();
    SaveState saveState;
    TextView txt_noidung,txt_motangan,txt_tieude,txt_tacgia,txt_tieudeHA;
    DetailNewspaperLoader dt;
    String id;
    ImageView img;
    LoaderManager loaderManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chi_tiet);
        img = findViewById(R.id.imgDetail);
        txt_motangan = findViewById(R.id.txt_motangan);
        txt_tieude = findViewById(R.id.txt_title_detail);
        txt_noidung = findViewById(R.id.txt_noidung);
        txt_tieudeHA = findViewById(R.id.txt_tieudeHA);
        txt_tacgia=findViewById(R.id.txt_tacgia);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        for(int i =0;i<10;i++){
//            BinhLuan binhLuan = new BinhLuan("Tên "+i,"Nội dung "+i);
//            listBinhLuan.add(binhLuan);
//        }
        recyclerView = findViewById(R.id.recyclerview_list_cmt);
        mAdapter = new ListCommentAdapter(this,listBinhLuan);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(1000);
        if (loader == null) {
            loaderManager.initLoader(1000, null, this);
        } else {
            loaderManager.restartLoader(1000, null,this);
        }

    }

    @NonNull
    @Override
    public Loader<Newspaper> onCreateLoader(int id, @Nullable Bundle args) {
        return new DetailNewspaperLoader(this,Integer.valueOf(this.id));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Newspaper> loader, Newspaper data) {
        this.txt_noidung.setText(data.getNoiDung());
        this.txt_motangan.setText(data.getMoTa());
        this.txt_tieude.setText(data.getTieuDe());
        this.txt_tacgia.setText(data.getTacGia());
        this.txt_tieudeHA.setText(data.getTieuDeHinhAnh());
        Picasso.get()
                .load(data.getHinhAnh())
                .into(img);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Newspaper> loader) {

    }
}
