package com.example.ungdungdocbao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.List;

public class DetailNewspaperLoader extends AsyncTaskLoader<Newspaper> {
    public int id;
    public DetailNewspaperLoader(@NonNull Context context, int id) {
        super(context);
        this.id = id;
    }
    @Nullable
    @Override
    public Newspaper loadInBackground() {
        Newspaper newspaper;
        try {
            String results = NetWorkUltis.getDetailNewspaper(id);
            JSONObject jsonObject = new JSONObject(results);
            JSONArray detail = jsonObject.getJSONArray("data");
            for (int i = 0; i < detail.length(); i++) {
                JSONObject item = detail.getJSONObject(i);
                Integer id = item.getInt("id");
                String tieuDe = item.getString("TieuDe");
                String danhMuc = item.getString("DanhMuc");
                String noiDung = item.getString("NoiDung");
                String moTa = item.getString("MoTa");
                String ngayDang = item.getString("NgayDang");
                String hinhAnh = item.getString("HinhAnh");
                String tieuDeHinhAnh = item.getString("TieuDeHinhAnh");
                String tacGia = item.getString("TacGia");
                Newspaper aNewspaper = new Newspaper(id, tieuDe, danhMuc, moTa, noiDung, ngayDang, hinhAnh, tieuDeHinhAnh, tacGia);
                return aNewspaper;
            }

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    }
