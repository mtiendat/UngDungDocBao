package com.example.ungdungdocbao;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrangChiTiet extends AppCompatActivity {
    RecyclerView recyclerView;
    ListCommentAdapter mAdapter;
    List<BinhLuan> listBinhLuan= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chi_tiet);
        for(int i =0;i<10;i++){
            BinhLuan binhLuan = new BinhLuan("Tên "+i,"Nội dung "+i);
            listBinhLuan.add(binhLuan);
        }
        recyclerView = findViewById(R.id.recyclerview_list_cmt);
        mAdapter = new ListCommentAdapter(this,listBinhLuan);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
