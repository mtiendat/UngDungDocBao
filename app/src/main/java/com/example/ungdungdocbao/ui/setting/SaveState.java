package com.example.ungdungdocbao.ui.setting;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class SaveState {
    Context context;
    SharedPreferences sharedPreferences;

    public SaveState(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("preferences",Context.MODE_PRIVATE);
    }
    public void setState(boolean bvalue){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("bkey",bvalue);
        editor.apply();
    }
    public  boolean getState(){
        return sharedPreferences.getBoolean("bkey",false);
    }

}


