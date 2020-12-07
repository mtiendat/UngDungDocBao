package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.ui.setting.SaveState;

import java.util.ArrayList;
import java.util.List;

public class TrangChiTiet extends AppCompatActivity {
    RecyclerView recyclerView;
    ListCommentAdapter mAdapter;
    List<BinhLuan> listBinhLuan= new ArrayList<>();
    SaveState saveState;
    TextView txt_tieude;
    TextView txt_noidung,txt_motangan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chi_tiet);

        txt_motangan = findViewById(R.id.txt_motangan);
        txt_tieude = findViewById(R.id.txt_title_detail);
        txt_noidung = findViewById(R.id.txt_noidung);
        Intent intent = getIntent();
        String tieude = intent.getStringExtra("Title");
        String mota = intent.getStringExtra("Mota");
        String noidung = intent.getStringExtra("ID");
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
        this.txt_tieude.setText(tieude);
        this.txt_motangan.setText(mota);
        this.txt_noidung.setText(noidung);
    }
}
