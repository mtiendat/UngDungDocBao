package com.example.ungdungdocbao.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.ungdungdocbao.ui.home.HomeFragment;
//import com.example.ungdungdocbao.ui.setting.SettingFragment;
import com.example.ungdungdocbao.Adapter.BottomNavPagerAdapter;
import com.example.ungdungdocbao.Fragment.FirstFragment;
import com.example.ungdungdocbao.Bottom_Nav.favorite.FavoriteFragment;
import com.example.ungdungdocbao.Bottom_Nav.home.HomeFragment;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.example.ungdungdocbao.Bottom_Nav.setting.SettingFragment;
import com.example.ungdungdocbao.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  static Boolean mInNightMode=false;
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private TextView txt_tile;
    private ImageButton imgBtnSetting;
    private ImageButton imgBtnSearch;
    SaveState saveState;
    public static Integer tindaxem;
    public static List<Integer> dsTinDaXem = new ArrayList<>();
    public static List<Integer> dsTinYeuThich = new ArrayList<>();
    public static final String URL = "http://192.168.42.14:8000/api/";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_favorite:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        saveState =new SaveState(this);

        nextActivitySetting();//An Button Setting
        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        final BottomNavigationView navigation = findViewById(R.id.bot_nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_favorite);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(saveState.getState()==true) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            navigation.setBackgroundColor(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    private void setupViewPager(ViewPager viewPager) {
        BottomNavPagerAdapter adapter = new BottomNavPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FirstFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new FavoriteFragment());
        viewPager.setAdapter(adapter);

    }



    public void nextActivitySetting() {
        imgBtnSetting=findViewById(R.id.imgBtn_setting);
        imgBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingFragment.class);
                startActivity(intent);
            }
        });

    }
    public void nextTimKiem(View view) {
        imgBtnSearch=findViewById(R.id.imgBtn_search);
        startActivity(new Intent(MainActivity.this,TimKiem.class));

    }
}