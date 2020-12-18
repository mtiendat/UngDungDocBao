package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.ui.setting.SettingFragment;

import java.util.ArrayList;
import java.util.List;

public class TrangTinDaXem  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Newspaper> {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private NewspaperAdapter mAdapter;
    LoaderManager loaderManager;
    int i;
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tindaxem);
        i = MainActivity.tindaxem;
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
        mAdapter=new NewspaperAdapter(this,MainActivity.dsTinDaXem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(100);
             if (loader == null) {
                 loaderManager.initLoader(100, null, this);
             } else {
                 loaderManager.restartLoader(100, null, this);
             }
    }

    @NonNull
    @Override
    public Loader<Newspaper> onCreateLoader(int id, @Nullable Bundle args) {

         return new DetailNewspaperLoader(this,i);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Newspaper> loader, Newspaper data) {
        MainActivity.dsTinDaXem.add(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Newspaper> loader) {

    }
}
