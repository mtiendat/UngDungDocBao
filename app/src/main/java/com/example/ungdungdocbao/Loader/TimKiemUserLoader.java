package com.example.ungdungdocbao.Loader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.example.ungdungdocbao.Activity.TimKiemUser;
import com.example.ungdungdocbao.Models.User;
import com.example.ungdungdocbao.NetWorkUltis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimKiemUserLoader extends AsyncTaskLoader<User> {

    private String email;
    public TimKiemUserLoader(@NonNull Context context, String email) {
        super(context);
        this.email = email;
    }

    @Nullable
    @Override
    public User loadInBackground() {
        User result;
        try {
            String getUser = NetWorkUltis.getUser(this.email);
            JSONObject jsonObject = new JSONObject(getUser);
            JSONArray user = jsonObject.getJSONArray("data");
            JSONObject item = user.getJSONObject(0);
            String name= item.getString("username");
            String email = item.getString("email");
            String avatar = item.getString("anhdaidien");
            result = new User(name,email,avatar);
            return result;
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
