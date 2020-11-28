package com.example.ungdungdocbao.ui.setting;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ungdungdocbao.DangKy;
import com.example.ungdungdocbao.DangNhap;
import com.example.ungdungdocbao.MainActivity;
import com.example.ungdungdocbao.R;

public class SettingFragment extends AppCompatActivity {

    private ImageButton imgBtnDangNhap;
    private ImageButton imgBtnDangKy;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        return root;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public void nextActivityDangNhap(View view) {
        imgBtnDangNhap=findViewById(R.id.imgBtn_dangnhap);
        imgBtnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingFragment.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

    public void nextActivityDangKy(View view) {
        imgBtnDangKy=findViewById(R.id.imgBtn_dangky);
        imgBtnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingFragment.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
}
