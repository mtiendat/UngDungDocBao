package com.example.ungdungdocbao.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ungdungdocbao.Models.BinhLuan;
import com.example.ungdungdocbao.NetWorkUltis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentLoader extends AsyncTaskLoader<List<BinhLuan>> {
    public int id;

    public CommentLoader(@NonNull Context context,int id) {
        super(context);
        this.id = id;
    }

    @Nullable
    @Override
    public List<BinhLuan> loadInBackground() {
        List<BinhLuan> listbinhLuan = new ArrayList<>();
        try {
            String results = NetWorkUltis.getBinhLuan(id);
            JSONObject jsonObject = new JSONObject(results);
            JSONArray binhluan = jsonObject.getJSONArray("data");
            for (int i = 0; i < binhluan.length(); i++) {
                JSONObject item = binhluan.getJSONObject(i);
                Integer id = item.getInt("id");
                String nameUser = item.getString("name");
                String content = item.getString("noidung");
                String avatar = item.getString("anhdaidien");
                BinhLuan binhLuan = new BinhLuan(nameUser,content,avatar);
                listbinhLuan.add(binhLuan);
            }
            return listbinhLuan;
        } catch (JSONException e) {
            e.printStackTrace();
    }
        return null;}
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
