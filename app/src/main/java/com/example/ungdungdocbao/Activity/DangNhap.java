package com.example.ungdungdocbao.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.CallbackManager;

import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.facebook.login.LoginResult;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.android.volley.Request;
import com.example.ungdungdocbao.R;
import com.facebook.FacebookCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class DangNhap extends AppCompatActivity {
    //TextView txtEmail, txtPass;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private EditText txtEmail, txtPass;
    private TextView txtQuenPass;
    private Button btnDangNhap;
    String name,image,email,id;
    private static String URL_DANGNHAP = MainActivity.URL+"dang-nhap";
    private final int RC_SIGN_IN = 999;
    SaveState saveState;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dang_nhap, container, false);
        return root;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.dang_nhap);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dangnhap);
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

        saveState = new SaveState(this);
        txtEmail = findViewById(R.id.editTextTextPersonName);
        txtPass = findViewById(R.id.editTextTextPassword);
        btnDangNhap = findViewById(R.id.btn_dangnhap2);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = txtEmail.getText().toString().trim();
                String Password = txtPass.getText().toString().trim();
                if (Email.equals("")){
                    txtEmail.setError("Vui lòng nhập email");
                } else if(Password.equals("")) {
                    txtPass.setError("Vui lòng nhập password");
                }else  Login(Email,Password);
            }
        });
        txtQuenPass=findViewById(R.id.txt_quenmk);
        txtQuenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, TimKiemUser.class);
                startActivity(intent);
            }
        });

        NenToi(saveState);
        LoginGoogle();
        LoginFacebook();

    }
    public void NenToi(SaveState saveState){
        if(saveState.getState()==true)
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
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");
                            if(status.equals("success")){
                                final String user_name = jsonObject.getString("hoten");
                                final String user_email = jsonObject.getString("email");
                                final String user_id = jsonObject.getString("user_name");
                                final String user_avatar = jsonObject.getString("anhdaidien");
                                Toast.makeText(DangNhap.this, message+". Xin chào "+user_name, Toast.LENGTH_LONG).show();

                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(3000); // Set time LENGTH_LONG Toast
                                            FirstFragment.USER_NAME=user_name;
                                            FirstFragment.USER_EMAIL=user_email;
                                            FirstFragment.USER_ID=user_id;
                                            FirstFragment.USER_AVATAR=user_avatar;

                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
    public void LoginGoogle(){
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById (R.id.sign_in_button );

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult ( signInIntent , RC_SIGN_IN );
            }
        });
    }
    public void LoginFacebook(){
        callbackManager = CallbackManager.Factory.create();
        loginButton=findViewById(R.id.login_button);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        id = null;
                        try {
                            id = object.getString("id");

                            try {
                                String first_name = object.getString("first_name");
                                String last_name = object.getString("last_name");
                                name= last_name+" "+first_name;
                                image= object.getJSONObject("picture").getJSONObject("data").getString("url");
                                updateUI("facebook",id,name,image,null);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();


            }
            @Override
            public void onCancel() {
                Toast.makeText(DangNhap.this,"Thất Bại.", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FacebookException e) {
                Toast.makeText(DangNhap.this,"Thất Bại", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            if(account.getPhotoUrl()!=null) {
                URL pic = new URL(account.getPhotoUrl().toString());
                updateUI("google",account.getId(),account.getDisplayName(),pic.toString(),account.getEmail());
            }else updateUI("google",account.getId(),account.getDisplayName(),null,account.getEmail());



        } catch (ApiException | MalformedURLException e) {

        }
    }
    public void updateUI(String flatform,String UserID, String Username,String Useravatar,  String Useremail){
        FirstFragment.USER_NAME=Username;
        FirstFragment.USER_EMAIL=Useremail;
        FirstFragment.USER_AVATAR=Useravatar;
        FirstFragment.USER_ID=UserID;
        Toast.makeText(DangNhap.this,"Đăng nhập "+flatform +" thành công", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000); // Set time LENGTH_LONG Toast

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void nextActivityDangKyFromDangNhap(View view) {
        startActivity(new Intent(this,DangKy.class));
    }
}
