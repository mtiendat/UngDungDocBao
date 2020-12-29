package com.example.ungdungdocbao.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.android.volley.Request;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {
    SaveState saveState;

    private TextView txtUsername,txtEmail,txtSdt,txtHoTen,txtDiaChi,txtMatKhau;
    private Button btnDangKy;
    private ProgressBar Loadding;
    private ImageView imageUpload;
    private Button uploadButton, selectImageButton;
    private Bitmap bitmap;
    private ProgressDialog progressDialog;
    public String imageString, filePath;
    private static final int  REQUEST_PERMISSIONS = 10;
    private static final int PICK_IMAGE_REQUEST=1;
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
        imageUpload = findViewById(R.id.uploadImage);
        uploadButton = findViewById(R.id.uploadButton);
        selectImageButton = findViewById(R.id.selectImageButton);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(DangKy.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(DangKy.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(DangKy.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                } else {
                    selectImageFromGallery();
                }

            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        setSupportActionBar(toolbar);; //sudung toolbar nhu actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //set nut back cho toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        btnDangKy=findViewById(R.id.btnDangKyFormDK);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DangKy.this);
                progressDialog.setMessage("Uploading, please wait...");
                progressDialog.show();
                Register();
            }
        });

        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

      public void Register(){
       final String username = this.txtUsername.getText().toString().trim();
       final String hoten = this.txtHoTen.getText().toString().trim();
       final String email = this.txtEmail.getText().toString().trim();
       final String sdt=this.txtSdt.getText().toString().trim();
       final String diachi=this.txtDiaChi.getText().toString().trim();
       final String password = this.txtMatKhau.getText().toString().trim();
          RequestQueue queue = Volley.newRequestQueue(this);
          VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, URL_DangKy,
                  new Response.Listener<NetworkResponse>() {
                      @Override
                      public void onResponse(NetworkResponse response) {
                          progressDialog.dismiss();
                          try {
                              Log.i("tagconvertstr", "["+new String(response.data)+"]");
                              String test = new String(response.data);
                              JSONObject jsonObject=new JSONObject(new String(response.data));
                              String status=jsonObject.getString("status");
                              String message=jsonObject.getString("message");
                              if(status.equals("success")){
                                   Toast.makeText(DangKy.this,message+" Vui lòng đăng nhập!",Toast.LENGTH_LONG).show();
                                  Thread thread = new Thread(){
                                      @Override
                                      public void run() {
                                          try {
                                              Thread.sleep(3000); // Set time LENGTH_LONG Toast
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
              @Override
              protected Map<String, DataPart> getByteData() {
                  Map<String, DataPart> params = new HashMap<>();
                  long imagename = System.currentTimeMillis();
                  params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                  return params;
              }

          };
          queue.add(stringRequest);
      }



    public void selectImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST  && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri picUri = data.getData();
            filePath = getPath(picUri);
            if (filePath != null) {
                try {
                    //getting image from gallery
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri );

                    //Setting image to ImageView
                    imageUpload.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(
                        DangKy.this, "Bạn chưa chọn hình...",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
