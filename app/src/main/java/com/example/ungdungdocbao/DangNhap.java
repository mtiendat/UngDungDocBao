package com.example.ungdungdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangNhap extends AppCompatActivity {
    //TextView txtEmail, txtPass;
    private EditText txtEmail, txtPass;
    private Button btnDangNhap;
    private static String URL_DANGNHAP = "http://192.168.97.2/AdminAndroid/login.php";
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

    public void Login(final String Email, final String Password) {
        btnDangNhap.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DANGNHAP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String email = object.getString("email").trim();
                            String passwrord = object.getString("password").trim();
                            Toast.makeText(DangNhap.this, "Success Login.\nYour Name: "  + "\n Your Email: " + email, Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    btnDangNhap.setVisibility(View.VISIBLE);
                    Toast.makeText(DangNhap.this, "Login Error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btnDangNhap.setVisibility(View.VISIBLE);
                        Toast.makeText(DangNhap.this, "Login Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("email",Email);
                parms.put("password", Password);
                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
