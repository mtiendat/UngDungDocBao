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
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Adapter.ListCommentAdapter;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.Loader.CommentLoader;
import com.example.ungdungdocbao.Loader.DetailNewspaperLoader;
import com.example.ungdungdocbao.Models.BinhLuan;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.VolleyMultipartRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrangChiTiet extends AppCompatActivity{

    RecyclerView recyclerView;
    ListCommentAdapter mAdapter;
    List<BinhLuan> listBinhLuan= new ArrayList<>();
    SaveState saveState;
    TextView txt_noidung,txt_motangan,txt_tieude,txt_tacgia,txt_tieudeHA,txtLuotXem,txt_NgayDang;
    DetailNewspaperLoader dt;
    private  static  String URL_DangBL=MainActivity.URL+"dang-binhluan";
    private String id, id_user,name, user_avatar;
    ImageView img,btn_dangbl;
    LoaderManager loaderManager;
    EditText txtBinhLuan;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chi_tiet);

        btn_dangbl = findViewById(R.id.img_dangBL);
        img = findViewById(R.id.imgDetail);
        txt_motangan = findViewById(R.id.txt_motangan);
        txt_tieude = findViewById(R.id.txt_title_detail);
        txt_noidung = findViewById(R.id.txt_noidung);
        txt_tieudeHA = findViewById(R.id.txt_tieudeHA);
        txt_tacgia=findViewById(R.id.txt_tacgia);
        txtBinhLuan = findViewById(R.id.edit_binhluan);
        txtLuotXem=findViewById(R.id.txt_luotxem);
        txt_tieude.setFocusable(true);
        txt_NgayDang = findViewById(R.id.txtNgayDang);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        id_user = intent.getStringExtra("id_user");
        name = intent.getStringExtra("user_name");
        user_avatar = intent.getStringExtra("user_avatar");
        saveState=new SaveState(this);
        if(saveState.getState()==true)
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải...");
        progressDialog.show();

        loaderManager = LoaderManager.getInstance(this);
        loaderTrangChiTiet();
        loaderBinhLuan();

        recyclerView = findViewById(R.id.recyclerview_list_cmt);
        mAdapter = new ListCommentAdapter(this,listBinhLuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);

    }
public void loaderTrangChiTiet(){
    Loader loader = loaderManager.getLoader(1111);
    if (loader == null) {
        loaderManager.initLoader(1111, null, new CallBack1());

    } else {
        loaderManager.restartLoader(1111, null, new CallBack1());
    }

}
public void loaderBinhLuan(){
    Loader loader2 = loaderManager.getLoader(2222);
    if (loader2 == null) {
        loaderManager.initLoader(2222, null, new CallBack2());
    } else {
        loaderManager.restartLoader(2222, null, new CallBack2());
    }
}
    private void postBinhLuan() {
        final String noidung = txtBinhLuan.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DangBL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");
                            if(status.equals("success")){
                                txtBinhLuan.setText("");
                                loaderBinhLuan();
                                Toast.makeText(getApplicationContext(),"Đăng thành công",Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(getApplicationContext(),message+"",Toast.LENGTH_SHORT).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"app error"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("id_user",id_user);
                parms.put("name", name);
                parms.put("id_baiviet", id.toString());
                parms.put("noidung", noidung);
                parms.put("anhdaidien",user_avatar);
                return parms;
            }

        };
        queue.add(stringRequest);
        }

    public void dangBL(View view) {
        if(id_user.equals("")) {
            Intent intent = new Intent(getApplicationContext(), DangNhap.class);
            startActivity(intent);
        } else {
            postBinhLuan();
        }

    }

    private class CallBack1 implements LoaderManager.LoaderCallbacks<Newspaper>{

        @NonNull
        @Override
        public Loader<Newspaper> onCreateLoader(int idd, @Nullable Bundle args) {
            return new DetailNewspaperLoader(getApplicationContext(),Integer.valueOf(id));
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Newspaper> loader, Newspaper data) {
            txt_noidung.setText(data.getNoiDung());
            txt_motangan.setText(data.getMoTa());
            txt_tieude.setText(data.getTieuDe());
            txt_tacgia.setText(data.getTacGia());
            txt_tieudeHA.setText(data.getTieuDeHinhAnh());
            txtLuotXem.setText(data.getLuotXem().toString());
            txt_NgayDang.setText(data.getNgayDang().toString());
            Picasso.get()
                    .load(data.getHinhAnh())
                    .into(img);
            progressDialog.dismiss();
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Newspaper> loader) {

        }
    }
    private class CallBack2 implements LoaderManager.LoaderCallbacks<List<BinhLuan>>{

        @NonNull
        @Override
        public Loader<List<BinhLuan>> onCreateLoader(int idd, @Nullable Bundle args) {
            return new CommentLoader(getApplicationContext(),Integer.valueOf(id));
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<BinhLuan>> loader, List<BinhLuan> data) {
            listBinhLuan.clear();
            if(data!=null) {
                listBinhLuan.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<BinhLuan>> loader) {

        }
    }
}
