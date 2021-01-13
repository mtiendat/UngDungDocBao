package com.example.ungdungdocbao.Loader;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Bottom_Nav.favorite.FavoriteFragment;
import com.example.ungdungdocbao.Models.Newspaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhSachTinDaXemLoader {
    public static List<Newspaper> listResult = new ArrayList<>();
    public DanhSachTinDaXemLoader(Context context, final List<Integer>dsID){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:8000/api/tin-da-xem",
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
                                listResult.add(aNewspaper);
                            }
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
                        for(int i=0;i<dsID.size();i++) {
                            parms.put("id"+i, dsID.get(i).toString());
                        }
                        parms.put("size", String.valueOf(dsID.size()));
                        return parms;
                    }

                };

                queue.add(stringRequest);


    }

}
