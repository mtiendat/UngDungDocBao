package com.example.ungdungdocbao.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.Loader.TimKiemLoader;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.R;

import java.util.ArrayList;
import java.util.List;

public class TimKiem extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Newspaper>> {
    EditText mTieuDe;
    LoaderManager loaderManager;
    RecyclerView recyclerView;
    NewspaperAdapter mAdapter;
    private List<Newspaper> dsTimKiem = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timkiem);
        mTieuDe=findViewById(R.id.edit_nhaptimkiem);

    }

    public void timKiem(View view) {
        recyclerView = findViewById(R.id.recyclerview_timkiem);
        mAdapter = new NewspaperAdapter(this,dsTimKiem);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(10);
        if (loader == null) {
            loaderManager.initLoader(10, null, this);
        } else {
            loaderManager.restartLoader(10, null,this);
        }

    }

    @NonNull
    @Override
    public Loader<List<Newspaper>> onCreateLoader(int id, @Nullable Bundle args) {
        return new TimKiemLoader(this,mTieuDe.getText().toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Newspaper>> loader, List<Newspaper> data) {
        dsTimKiem.clear();
        if(data!=null) {
            dsTimKiem.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Newspaper>> loader) {

    }
}