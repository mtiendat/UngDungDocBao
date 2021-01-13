package com.example.ungdungdocbao.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.NetWorkUltis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            JSONObject item = jsonObject.getJSONObject("data");
                Integer id = item.getInt("id");
                String tieuDe = item.getString("TieuDe");
                String danhMuc = item.getString("DanhMuc");
                String noiDung = item.getString("NoiDung");
                String moTa = item.getString("MoTa");
                String ngayDang = item.getString("NgayDang");
                String hinhAnh = item.getString("HinhAnh");
                String tieuDeHinhAnh = item.getString("TieuDeHinhAnh");
                String tacGia = item.getString("TacGia");
                int luotXem = item.getInt("LuotXem");
                Newspaper aNewspaper = new Newspaper(id, tieuDe, danhMuc, moTa, noiDung, ngayDang, hinhAnh, tieuDeHinhAnh, tacGia,luotXem);
                return aNewspaper;


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
