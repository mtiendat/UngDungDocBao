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

public class BaiVietLoader extends AsyncTaskLoader<String> {
    private String tukhoa;


    public BaiVietLoader(@NonNull Context context, String tukhoa) {
        super(context);
        this.tukhoa = tukhoa;

    }

    @Nullable
    @Override
    public String loadInBackground() {

        return  NetWorkUltis.getTieuDe(this.tukhoa);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}