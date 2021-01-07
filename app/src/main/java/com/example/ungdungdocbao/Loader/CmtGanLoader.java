package com.example.ungdungdocbao.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.android.volley.toolbox.StringRequest;
import com.example.ungdungdocbao.Models.BinhLuan;
import com.example.ungdungdocbao.NetWorkUltis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CmtGanLoader extends AsyncTaskLoader<List<BinhLuan>> {
    public String id;

    public CmtGanLoader(@NonNull Context context,String id) {
        super(context);
        this.id = id;
    }

    @Nullable
    @Override
    public List<BinhLuan> loadInBackground() {
        List<BinhLuan> listbinhLuan = new ArrayList<>();
        try {
            String results = NetWorkUltis.getBinhLuanByID(id);
            JSONObject jsonObject = new JSONObject(results);
            JSONArray binhluan = jsonObject.getJSONArray("data");
            for (int i = 0; i < binhluan.length(); i++) {
                JSONObject item = binhluan.getJSONObject(i);
                Integer id = item.getInt("id");
                String nameUser = item.getString("name");
                String content = item.getString("noidung");
                BinhLuan binhLuan = new BinhLuan(nameUser,content);
                listbinhLuan.add(binhLuan);
            }
            return listbinhLuan;
        } catch (JSONException e) {
            e.printStackTrace();
    }return null;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
