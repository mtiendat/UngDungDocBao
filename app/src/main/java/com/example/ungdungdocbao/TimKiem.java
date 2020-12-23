package com.example.ungdungdocbao;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
public class TimKiem extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    static final int WEATHER_LOADER_ID = 1000;
    LoaderManager loaderManager;

    EditText editTextTuKhoa;

    LinkedList<BaiViet> baivietlist;
    RecyclerView recyclerViewBaiVietList;
    BaiVietAdapter baivietAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tim_kiem);
        loaderManager = LoaderManager.getInstance(this);



    }
    public void timKiem(View view) {
        this.editTextTuKhoa = findViewById(R.id.txtTim);
        Bundle info = new Bundle();
        info.putString("tukhoa", this.editTextTuKhoa.getText().toString());
        if (loaderManager.getLoader(WEATHER_LOADER_ID) == null) {

            loaderManager.initLoader(WEATHER_LOADER_ID,info,this);
        } else {
            loaderManager.restartLoader(WEATHER_LOADER_ID, info, this);
        }



    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new BaiVietLoader(this, args.getString("tukhoa"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        Log.d("Test_data",data.toString());
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            Log.d("TEST_LG", itemsArray.toString());

            // Tạo nguồn dữ liệu
            this.baivietlist = new LinkedList<BaiViet>();
            String tieude = null;

            for(int i = 0 ; i < itemsArray.length() ; i++){
                JSONObject jsonObject1 = itemsArray.getJSONObject(i);
                JSONObject volumebaiviet = jsonObject1.getJSONObject("volumebaiviet");
                try{
                    tieude = volumebaiviet.getString("tieude");

                    BaiViet temp = new BaiViet(tieude);
                    this.baivietlist.add(temp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            //Thiết lập Adapter và LayoutManager cho RecyclerView
            recyclerViewBaiVietList= findViewById(R.id.recy_timkiem);
            Log.d("Test_array",this.baivietlist.get(0).getTieude());
            baivietAdapter = new BaiVietAdapter(this, this.baivietlist);
            recyclerViewBaiVietList.setAdapter(baivietAdapter);

            recyclerViewBaiVietList.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}