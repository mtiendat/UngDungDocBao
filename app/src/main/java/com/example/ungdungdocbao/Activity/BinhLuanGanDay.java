package com.example.ungdungdocbao.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.example.ungdungdocbao.Adapter.BinhLuanGanDayAdapter;
import com.example.ungdungdocbao.Adapter.ListCommentAdapter;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.Loader.CmtGanLoader;
import com.example.ungdungdocbao.Loader.CommentLoader;
import com.example.ungdungdocbao.Models.BinhLuan;
import com.example.ungdungdocbao.R;

import java.util.ArrayList;
import java.util.List;

public class BinhLuanGanDay extends AppCompatActivity {
    RecyclerView recyclerView;
    BinhLuanGanDayAdapter mAdapter;
    Toolbar toolbar;
    List<BinhLuan> listBL = new ArrayList<>();
    String id;
    SaveState saveState;
    CmtGanLoader mLoader;
    LoaderManager loaderManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binhluanganday);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar_cmt_gan);
        setSupportActionBar(toolbar);; //sudung toolbar nhu actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //set nut back cho toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingFragment.class));
            }
        });
        saveState = new SaveState(this);
        if(saveState.getState()==true) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(1111);
        if (loader == null) {
            loaderManager.initLoader(1111, null, new CallBack());
        } else {
            loaderManager.restartLoader(1111, null, new CallBack());
        }
        recyclerView = findViewById(R.id.recy_cmt_near);
        mAdapter = new BinhLuanGanDayAdapter(this,listBL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);
    }
    private class CallBack implements LoaderManager.LoaderCallbacks<List<BinhLuan>>{

        @NonNull
        @Override
        public Loader<List<BinhLuan>> onCreateLoader(int idd, @Nullable Bundle args) {
            return new CmtGanLoader(getApplicationContext(),id);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<BinhLuan>> loader, List<BinhLuan> data) {
            listBL.clear();
            if(data!=null) {
                listBL.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<BinhLuan>> loader) {

        }
    }
}
