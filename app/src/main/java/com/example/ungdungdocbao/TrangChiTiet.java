package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
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

public class TrangChiTiet extends AppCompatActivity{

    RecyclerView recyclerView;
    ListCommentAdapter mAdapter;
    List<BinhLuan> listBinhLuan= new ArrayList<>();
    SaveState saveState;
    TextView txt_noidung,txt_motangan,txt_tieude,txt_tacgia,txt_tieudeHA;
    DetailNewspaperLoader dt;
    static String id;
    ImageView img;
    LoaderManager loaderManager;
    EditText txtBinhLuan;

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
        txtBinhLuan = findViewById(R.id.edittext_binh_luan);
        txt_tieude.setFocusable(true);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView = findViewById(R.id.recyclerview_list_cmt);
        mAdapter = new ListCommentAdapter(this,listBinhLuan);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(1111);
        if (loader == null) {
            loaderManager.initLoader(1111, null, new CallBack1());
        } else {
            loaderManager.restartLoader(1111, null, new CallBack1());
        }

        Loader loader2 = loaderManager.getLoader(2222);
        if (loader2 == null) {
            loaderManager.initLoader(2222, null, new CallBack2());
        } else {
            loaderManager.restartLoader(2222, null, new CallBack2());
        }
        recyclerView = findViewById(R.id.recyclerview_list_cmt);
        mAdapter = new ListCommentAdapter(getApplicationContext(),listBinhLuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);

    }

    private class CallBack1 implements LoaderManager.LoaderCallbacks<Newspaper>{

        @NonNull
        @Override
        public Loader<Newspaper> onCreateLoader(int idd, @Nullable Bundle args) {
            return new DetailNewspaperLoader(getApplicationContext(),Integer.valueOf(id));
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Newspaper> loader, Newspaper data) {
            txt_noidung.setText(data.getNoiDung());
            txt_motangan.setText(data.getMoTa());
            txt_tieude.setText(data.getTieuDe());
            txt_tacgia.setText(data.getTacGia());
            txt_tieudeHA.setText(data.getTieuDeHinhAnh());
            Picasso.get()
                    .load(data.getHinhAnh())
                    .into(img);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Newspaper> loader) {

        }
    }
    private class CallBack2 implements LoaderManager.LoaderCallbacks<List<BinhLuan>>{

        @NonNull
        @Override
        public Loader<List<BinhLuan>> onCreateLoader(int idd, @Nullable Bundle args) {
            return new CommentLoader(getApplicationContext(),Integer.valueOf(id));
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<BinhLuan>> loader, List<BinhLuan> data) {
            listBinhLuan.clear();
            if(data!=null) {
                listBinhLuan.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<BinhLuan>> loader) {

        }
    }
}
