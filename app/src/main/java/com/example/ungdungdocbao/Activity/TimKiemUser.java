package com.example.ungdungdocbao.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.Loader.TimKiemUserLoader;
import com.example.ungdungdocbao.Models.User;
import com.example.ungdungdocbao.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TimKiemUser  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {

    Button timKiemUSer;
    TextView mName, mEmail;
    EditText mTuKhoa;
    ImageView mAvatar;
    LoaderManager loaderManager;
    SaveState saveState;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kiem_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_timkiemuser);
        setSupportActionBar(toolbar);
        //su dung toolbar nhu actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //set nut back cho toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingFragment.class));
            }
        });
        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mAvatar = findViewById(R.id.img_tk_user);
        mEmail = findViewById(R.id.txt_email);
        mName = findViewById(R.id.txt_name);

        timKiemUSer = findViewById(R.id.btn_tim_user);
        mTuKhoa = findViewById(R.id.edt_tim_user);


    }
    public void timKiemUser(View view) {
        Log.d("TAG_NAME","Click");
        if(mTuKhoa.getText().length()==0) {
            mTuKhoa.setError("Chưa nhập email...");
        }else {
            loaderManager = LoaderManager.getInstance(this);
            Loader loader = loaderManager.getLoader(1000);
            if (loader == null) {
                loaderManager.initLoader(1000, null, this);
            } else {
                loaderManager.restartLoader(1000, null, this);
            }
        }
    }
    @NonNull
    @Override
    public Loader<User> onCreateLoader(int id, @Nullable Bundle args) {
        return new TimKiemUserLoader(this,mTuKhoa.getText().toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<User> loader, final User data) {
        if(data!=null) {
            mName.setText("Username: "+ data.getName());
            mEmail.setText("Email: " + data.getEmail());
            Picasso.get().load(data.getAvatar()).into(mAvatar);
            mEmail.setVisibility(View.VISIBLE);
            mName.setVisibility(View.VISIBLE);
            mAvatar.setVisibility(View.VISIBLE);
            mEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TimKiemUser.this, QuenMatKhau.class);
                    intent.putExtra("EMAIL", data.getEmail());
                    startActivity(intent);
                }
            });
            mAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TimKiemUser.this, QuenMatKhau.class);
                    intent.putExtra("EMAIL", data.getEmail());
                    startActivity(intent);
                }
            });
            mName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TimKiemUser.this, QuenMatKhau.class);
                    intent.putExtra("EMAIL", data.getEmail());
                    startActivity(intent);
                }
            });
        }else Toast.makeText(this,"Không tìm thấy tài khoản này...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<User> loader) {

    }


}
