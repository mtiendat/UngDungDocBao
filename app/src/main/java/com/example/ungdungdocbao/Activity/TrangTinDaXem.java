package com.example.ungdungdocbao.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.Loader.DanhSachTinDaXemLoader;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.R;

import java.util.ArrayList;
import java.util.List;

public class TrangTinDaXem  extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private NewspaperAdapter mAdapter;
    LoaderManager loaderManager;
    private List<Newspaper> dsTinDaXem = new ArrayList<>();
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tindaxem);
        toolbar=(Toolbar) findViewById(R.id.toolbar_tindaxem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingFragment.class));
            }
        });
         recyclerView =findViewById(R.id.recyclerView_tindaxem);
         mAdapter=new NewspaperAdapter(this,dsTinDaXem);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(mAdapter);


    }
}
