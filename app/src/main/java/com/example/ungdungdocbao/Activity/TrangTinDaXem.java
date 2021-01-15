package com.example.ungdungdocbao.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Loader.DanhSachTinDaXemLoader;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrangTinDaXem  extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private NewspaperAdapter mAdapter;
    LoaderManager loaderManager;
    private List<Newspaper> dsTinDaXem = new ArrayList<>();
    SaveState saveState;
    private ProgressDialog progressDialog;
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tindaxem);
        saveState = new SaveState(this);
        toolbar=(Toolbar) findViewById(R.id.toolbar_tindaxem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingFragment.class));
            }
        });
         recyclerView =findViewById(R.id.recyclerView_tindaxem);
         mAdapter=new NewspaperAdapter(this,dsTinDaXem);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(mAdapter);
         if(saveState.getState()==true) {
             getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
         }
         else
             getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
         getList();

    }
    public void getList(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.URL+"tin-da-xem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("reponsestringrequest",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray newspaper = jsonObject.getJSONArray("data");
                            for (int i=0;i<newspaper.length();i++){
                                JSONObject item = newspaper.getJSONObject(i);
                                Integer id= item.getInt("id");
                                String tieuDe = item.getString("TieuDe");
                                String danhMuc= item.getString("DanhMuc");
                                String noiDung = item.getString("NoiDung");
                                String moTa =item.getString("MoTa");
                                String ngayDang =item.getString("NgayDang");
                                String hinhAnh = item.getString("HinhAnh");
                                String tieuDeHinhAnh = item.getString("TieuDeHinhAnh");
                                String tacGia = item.getString("TacGia");
                                int luotXem = item.getInt("LuotXem");
                                Newspaper aNewspaper = new Newspaper(id, tieuDe, danhMuc, moTa, noiDung, ngayDang, hinhAnh, tieuDeHinhAnh, tacGia,luotXem);
                                dsTinDaXem.add(aNewspaper);

                            }
                            mAdapter.updateDataSet(dsTinDaXem); //notifychangedata adapter

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                for(int i=0;i<MainActivity.dsTinDaXem.size();i++) {
                    parms.put("id"+i, MainActivity.dsTinDaXem.get(i).toString());//put từng id vào với key id1,id2,...
                }
                parms.put("size", String.valueOf(MainActivity.dsTinDaXem.size()));//Truyền size lên
                return parms;
            }

        };

        queue.add(stringRequest);

    }
}
