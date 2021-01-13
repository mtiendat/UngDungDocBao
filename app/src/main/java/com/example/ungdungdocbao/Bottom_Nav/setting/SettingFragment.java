package com.example.ungdungdocbao.Bottom_Nav.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdocbao.Activity.BinhLuanGanDay;
import com.example.ungdungdocbao.Activity.DangKy;
import com.example.ungdungdocbao.Activity.DangNhap;
import com.example.ungdungdocbao.Activity.MainActivity;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.Activity.TrangTinDaXem;

public class SettingFragment extends AppCompatActivity {

    private ImageButton imgBtnDangNhap;
    private ImageButton imgBtnDangKy;
    private Switch switch_btn;
    private ImageButton imgBtnTinDaXem,imgBtnBinhLuanGanDay;
    SaveState saveState ;
    Switch aSwitch;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        return root;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        imgBtnTinDaXem = findViewById(R.id.img_button_tindaxem);
        imgBtnBinhLuanGanDay = findViewById(R.id.img_bl_near);
        imgBtnBinhLuanGanDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingFragment.this, BinhLuanGanDay.class);
                intent.putExtra("ID", FirstFragment.USER_ID);
                startActivity(intent);
            }
        });
        imgBtnTinDaXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingFragment.this, TrangTinDaXem.class));

            }
        });
        DarkMode();
        switch_btn= findViewById(R.id.switch_nen_toi);
        if(saveState.getState()==true)
            switch_btn.setChecked(true);
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    saveState.setState(true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    saveState.setState(false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
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
    public void DarkMode(){
        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    public void nextActivityDangNhap(View view) {
                Intent intent = new Intent(SettingFragment.this, DangNhap.class);
                startActivity(intent);

    }

    public void nextActivityDangKy(View view) {
                Intent intent = new Intent(SettingFragment.this, DangKy.class);
                startActivity(intent);
    }
}
