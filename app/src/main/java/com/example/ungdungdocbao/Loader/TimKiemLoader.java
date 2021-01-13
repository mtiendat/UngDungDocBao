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

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class TimKiemLoader  extends AsyncTaskLoader<List<Newspaper>> {
    private String tuKhoa;
    public TimKiemLoader(@NonNull Context context, String tuKhoa) {
        super(context);
        this.tuKhoa=tuKhoa;
    }

    @Nullable
    @Override
    public List<Newspaper> loadInBackground() {
        List<Newspaper> listNewspaper= new ArrayList<>();
        try {
            String results= NetWorkUltis.getTieuDe(tuKhoa);
            JSONObject jsonObject = new JSONObject(results);
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
                listNewspaper.add(aNewspaper);
            }
            return listNewspaper;

        } catch (JSONException e) {

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
