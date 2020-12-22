package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.ui.setting.SaveState;
import com.example.ungdungdocbao.ui.setting.SettingFragment;
import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {
    SaveState saveState;

    private TextView txtUsername,txtEmail,txtSdt,txtHoTen,txtDiaChi,txtMatKhau;
    private Button btnDangKy;
    private ProgressBar Loadding;
    String boundary = "apiclient-" + System.currentTimeMillis();
    private  static  String URL_DangKy="http://10.0.2.2:8000/api/dang-ky";
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
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail=findViewById(R.id.txtEmailDK);
        txtSdt=findViewById(R.id.txtSDTDangKy);
        txtDiaChi=findViewById(R.id.txtDiaChi);
        txtMatKhau=findViewById(R.id.txtMatKhauDK);
        txtHoTen=findViewById(R.id.txtHoTen);

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
    }

      private void Register(){
       final String username = this.txtUsername.getText().toString().trim();
       final String hoten = this.txtHoTen.getText().toString().trim();
       final String email = this.txtEmail.getText().toString().trim();
       final String sdt=this.txtSdt.getText().toString().trim();
       final String diachi=this.txtDiaChi.getText().toString().trim();
       final String password = this.txtMatKhau.getText().toString().trim();
          RequestQueue queue = Volley.newRequestQueue(this);
          StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DangKy,
                  new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                          // Display the first 500 characters of the response string.
                          try {
                              Log.i("tagconvertstr", "["+response+"]");
                              JSONObject jsonObject=new JSONObject(response);
                              String status=jsonObject.getString("status");
                              String message=jsonObject.getString("message");
                              if(status.equals("success")){
                                   Toast.makeText(DangKy.this,message+" Vui lòng đăng nhập!",Toast.LENGTH_LONG).show();
                                  Thread thread = new Thread(){
                                      @Override
                                      public void run() {
                                          try {
                                              Thread.sleep(3500); // Set time LENGTH_LONG Toast
                                              startActivity(new Intent(getApplicationContext(),DangNhap.class));
                                          } catch (Exception e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  };
                                  thread.start();
                              }else Toast.makeText(DangKy.this,message+"",Toast.LENGTH_SHORT).show();

                             } catch (JSONException e) {
                             e.printStackTrace();
                            Toast.makeText(DangKy.this,"Register Error"+e.toString(),Toast.LENGTH_SHORT).show();
                             }
                      }
                  },
                  new Response.ErrorListener() {
                    @Override
                   public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKy.this,"Email hoặc password đã tồn tại",Toast.LENGTH_SHORT).show();
                }
             })
          {
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String, String> parms = new HashMap<>();
                  parms.put("username",username);
                  parms.put("hoten", hoten);
                  parms.put("email",email);
                  parms.put("sdt",sdt);
                  parms.put("password", password);
                  parms.put("diachi",diachi);
                  return parms;
              }

          };
          queue.add(stringRequest);
      }
}
