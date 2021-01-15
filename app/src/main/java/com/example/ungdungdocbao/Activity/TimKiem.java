package com.example.ungdungdocbao.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Loader.TimKiemLoader;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.R;

import java.util.ArrayList;
import java.util.List;

public class TimKiem extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Newspaper>> {
    EditText mTieuDe;
    TextView mSoLuong;
    LoaderManager loaderManager;
    RecyclerView recyclerView;
    private ImageButton imgBack;
    NewspaperAdapter mAdapter;
    private List<Newspaper> dsTimKiem = new ArrayList<>();
    SaveState saveState;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timkiem);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar_timkiem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        mTieuDe=findViewById(R.id.edit_nhaptimkiem);
        mSoLuong=findViewById(R.id.txt_soluongkq);
        saveState = new SaveState(this);
        if(saveState.getState()==true) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public void timKiem(View view) {
        recyclerView = findViewById(R.id.recyclerview_timkiem);
        mAdapter = new NewspaperAdapter(this,dsTimKiem);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaderManager = LoaderManager.getInstance(this);
        if(mTieuDe.getText().length()==0){
            mTieuDe.setError("Chưa nhập tiêu đề...");
        }else {
            Loader loader = loaderManager.getLoader(10);
            if (loader == null) {
                loaderManager.initLoader(10, null, this);
            } else {
                loaderManager.restartLoader(10, null, this);
            }
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
            mSoLuong.setVisibility(View.VISIBLE);
            mSoLuong.setText("Tìm thấy "+ data.size()+" kết quả");
            dsTimKiem.addAll(data);
            mAdapter.notifyDataSetChanged();
        }else {
            mSoLuong.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Không tìm thấy bài báo nào...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Newspaper>> loader) {

    }
}
