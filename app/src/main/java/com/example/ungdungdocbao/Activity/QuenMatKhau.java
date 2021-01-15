package com.example.ungdungdocbao.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QuenMatKhau extends AppCompatActivity {

    private TextView txtQuenPass;
    private Button btnSubmit;
    SaveState saveState;
    private ProgressDialog progressDialog;
    private EditText editTextEmail, editTextPass,editTextConfirmPass;
    private  static  String URL_QuenPass=MainActivity.URL+"quenmatkhau";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.quen_mat_khau, container, false);
        return root;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mat_khau);
        editTextEmail = findViewById(R.id.editText_email);
        editTextPass=findViewById(R.id.editText_password);
        editTextConfirmPass= findViewById(R.id.editText_confirmpassword);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolba_quenpass);
        setSupportActionBar(toolbar);
        ; //sudung toolbar nhu actionbar
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

        Intent intent = getIntent();
        editTextEmail.setText(intent.getStringExtra("EMAIL"));
        btnSubmit=findViewById(R.id.btn_quempass);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPass.getText().length()==0){
                    editTextPass.setError("Chưa nhập mật khẩu");
                }else if(editTextConfirmPass.getText().length()==0){
                    editTextPass.setError("Chưa nhập xác nhận mật khẩu");
                }
                else if(!editTextPass.getText().toString().equals(editTextConfirmPass.getText().toString())){
                    editTextConfirmPass.setError("Xác nhận mật khẩu chưa đúng...");
                }else {
                    progressDialog = new ProgressDialog(QuenMatKhau.this);
                    progressDialog.setMessage("Vui lòng đợi");
                    progressDialog.show();
                    quenMatKhau();
                }
            }
        });
    }


    public void quenMatKhau() {

        final String email = this.editTextEmail.getText().toString().trim();
        final String password = this.editTextPass.getText().toString().trim();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_QuenPass,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", response);
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");
                            if (status.equals("success")) {
                                Toast.makeText(QuenMatKhau.this, message + " Vui lòng đăng nhập!", Toast.LENGTH_LONG).show();
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(3000); // Set time LENGTH_LONG Toast
                                            startActivity(new Intent(getApplicationContext(), DangNhap.class));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                thread.start();
                            } else
                                Toast.makeText(QuenMatKhau.this, message + "", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(QuenMatKhau.this, "Register Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(QuenMatKhau.this, "Email không đúng", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("email", email);
                parms.put("password", password);
                return parms;
            }


        };
        queue.add(stringRequest);
    }
}
