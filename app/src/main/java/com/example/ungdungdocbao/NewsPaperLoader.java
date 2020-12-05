package com.example.ungdungdocbao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class NewsPaperLoader extends AsyncTaskLoader<List<Newspaper>> {

    public String danhmuc;
    public NewsPaperLoader(@NonNull Context context, String danhmuc) {
        super(context);
        this.danhmuc=danhmuc;
    }

    @Nullable
    @Override
    public List<Newspaper> loadInBackground() {
        List<Newspaper> listNewspaper= new ArrayList<>();
        try {
            String results= NetWorkUltis.getNewspaper(danhmuc);
            JSONObject jsonObject = new JSONObject(results);
            JSONArray newspaper = jsonObject.getJSONArray("data");
            for (int i=0;i<newspaper.length();i++){
                JSONObject item = newspaper.getJSONObject(i);
                String tieuDe = item.getString("TieuDe");
                String danhMuc= item.getString("DanhMuc");
                String noiDung = item.getString("NoiDung");
                String moTa =item.getString("MoTa");
                String ngayDang =item.getString("NgayDang");
                String hinhAnh = item.getString("HinhAnh");
                String tieuDeHinhAnh = item.getString("TieuDeHinhAnh");
                String tacGia = item.getString("TacGia");
                Newspaper aNewspaper = new Newspaper(tieuDe,danhMuc,moTa,noiDung,ngayDang,hinhAnh,tieuDeHinhAnh, tacGia);
                listNewspaper.add(aNewspaper);
            }
            return listNewspaper;

        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
