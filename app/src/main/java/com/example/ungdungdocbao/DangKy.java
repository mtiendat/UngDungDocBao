package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ungdungdocbao.ui.setting.SaveState;
import com.example.ungdungdocbao.ui.setting.SettingFragment;
import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
public class DangKy extends AppCompatActivity {
    SaveState saveState;

    private TextView txtTen,txtEmail,txtSdt,txtNgaySinh,txtDiaChi,txtMatK,txtNhapLai;
    private Button btnDangKy;
    private ProgressBar Loadding;
    private  static  String URL_DangKy="http://192.168.97.2/AdminAndroid/register.php";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dang_ky, container, false);
        return root;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar_dangky);
        txtTen = findViewById(R.id.txtHoTenDK);
        txtEmail=findViewById(R.id.txtEmailDK);
        txtSdt=findViewById(R.id.txtSDTDangKy);
        txtDiaChi=findViewById(R.id.txtDiaChiDK);
        txtMatK=findViewById(R.id.txtMatKhauDK);

        setSupportActionBar(toolbar);; //sudung toolbar nhu actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //set nut back cho toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingFragment.class));
            }
        });


        btnDangKy=findViewById(R.id.btnDangKyFormDK);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

      private void Register(){
        btnDangKy.setVisibility(View.GONE);
        final String Name = this.txtTen.getText().toString().trim();
        final String Email = this.txtEmail.getText().toString().trim();
        final String Sdt=this.txtSdt.getText().toString().trim();
        final String Dchi=this.txtDiaChi.getText().toString().trim();
        final String Password = this.txtMatK.getText().toString().trim();

          StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_DangKy, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  try {
                      JSONObject jsonObject=new JSONObject(response);
                      String success=jsonObject.getString("success");
                      if(success.equals("1")){
                          Toast.makeText(DangKy.this,"Register Success",Toast.LENGTH_SHORT).show();
                      }
                  } catch (JSONException e) {
                      e.printStackTrace();
                      btnDangKy.setVisibility(View.VISIBLE);
                      Toast.makeText(DangKy.this,"Register Error"+e.toString(),Toast.LENGTH_SHORT).show();
                  }
              }
          },
                  new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          btnDangKy.setVisibility(View.VISIBLE);
                          Toast.makeText(DangKy.this,"Register Error"+error.toString(),Toast.LENGTH_SHORT).show();
                      }
                  })
          {
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String,String>parms=new HashMap<>();
                  parms.put("username",Name);
                  parms.put("password",Password);
                  parms.put("diachi",Dchi);
                  parms.put("sdt",Sdt);
                  parms.put("email",Email);
                  return parms;
              }
          };
      }
}
