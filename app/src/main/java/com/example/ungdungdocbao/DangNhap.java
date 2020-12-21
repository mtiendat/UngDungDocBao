package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangNhap extends AppCompatActivity {
    //TextView txtEmail, txtPass;
    private EditText txtEmail, txtPass;
    private Button btnDangNhap;
    private static String URL_DANGNHAP = "http://10.0.2.2:8000/api/dang-nhap";
    private TextView user_name;
    private TextView user_email;
    SaveState saveState;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dang_nhap, container, false);
        return root;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        txtEmail = findViewById(R.id.editTextTextPersonName);
        txtPass = findViewById(R.id.editTextTextPassword);
        btnDangNhap = findViewById(R.id.btn_dangnhap2);
        //NavigationView navigationView;
        //navigationView = (NavigationView) findViewById(R.id.nav_view);
        //View hView = navigationView.getHeaderView(0);
        //user_name=(TextView)hView.findViewById(R.id.user_name);
        //user_email=(TextView)hView.findViewById(R.id.user_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dangnhap);
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

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = txtEmail.getText().toString().trim();
                String Password = txtPass.getText().toString().trim();
                if (!Email.isEmpty() || !Password.isEmpty()) {
                    Login(Email,Password);
                } else {
                    txtEmail.setError("Please inser Email");
                    txtPass.setError("Please insert Pass");
                }
            }
        });

        saveState = new SaveState(this);
        if (saveState.getState() == true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void Login(final String email, final String password) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DANGNHAP,
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
                                String hoten = jsonObject.getString("hoten");
                                Toast.makeText(DangNhap.this,message+"\nChào "+hoten+"<3",Toast.LENGTH_LONG).show();
                                Thread thread = new Thread(){
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(3500); // Set time LENGTH_LONG Toast
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                thread.start();
                            }else Toast.makeText(DangNhap.this,message+"",Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DangNhap.this,"app error"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangNhap.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("email",email);
                parms.put("password", password);
                return parms;
            }

        };
        queue.add(stringRequest);
    }
}
