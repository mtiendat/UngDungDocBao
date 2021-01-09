package com.example.ungdungdocbao.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ungdungdocbao.Activity.DangNhap;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.Adapter.TabViewPagerAdapter;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class FirstFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mFirstViewPager;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private TextView user_name;
    private TextView user_email;
    private TextView user_id;
    private TextView dangXuat;
    private ImageView user_avatar;
    public static String USER_NAME="";
    public static String USER_ID="";
    public static String USER_EMAIL="";
    public static String USER_AVATAR="";
    SaveState saveState;
    public FirstFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveState = new SaveState(getContext());
        if(saveState.getState()==true)
            ((AppCompatActivity)(getActivity())).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            ((AppCompatActivity)(getActivity())).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);


    }

    public void someMethodThatUsesActivity(Activity myActivityReference){
        myActivityReference.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        someMethodThatUsesActivity(getActivity());//ẨN THANH STATUS TRẠNG THÁI
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        mFirstViewPager = (ViewPager) rootView.findViewById(R.id.viewpage_content);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        //Drawer Toolbar
        mDrawerLayout = rootView.findViewById(R.id.drawer_layout);
        mNavigationView = rootView.findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // set item as selected to persist highlight
                switch (item.getItemId()){
                    case R.id.thoisu:
                        mFirstViewPager.setCurrentItem(0);
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.thegioi:
                        mFirstViewPager.setCurrentItem(1);
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.thethao:
                        mFirstViewPager.setCurrentItem(2);
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.congnghe:
                        mFirstViewPager.setCurrentItem(3);
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.giaitri:
                        mFirstViewPager.setCurrentItem(4);
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.khoahoc:
                        mFirstViewPager.setCurrentItem(5);
                        mDrawerLayout.closeDrawers();
                        return true;
                }
                // close drawer when item is tapped
                return true;
                //Nếu Item được chạm , đoạn code này sẽ thiết lập lựa chon item và Drawer cũng được đóng bởi hàm closeDrawers().
            }
        });
        View hview = mNavigationView.getHeaderView(0); //lấy view header của drawer
        user_name=hview.findViewById(R.id.user_name);
        user_email=hview.findViewById(R.id.email);
        user_id=hview.findViewById(R.id.user_id);
        user_avatar=hview.findViewById(R.id.img_avatar);
        dangXuat=hview.findViewById(R.id.dangxuat);
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USER_NAME="";
                USER_EMAIL="";
                USER_ID="";
                USER_AVATAR="";
                dangXuat.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),"Đăng xuất thành công",Toast.LENGTH_LONG).show();
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000); // Set time LENGTH_LONG Toast
                            startActivity(new Intent(getContext(), DangNhap.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
        //hiển thị tên,email,.. lên header drawer
        if(!USER_NAME.equals("")) {
            user_name.setText(USER_NAME);
            user_email.setText(USER_EMAIL);
            user_id.setText(USER_ID);
            dangXuat.setText("Đăng xuất");
            dangXuat.setVisibility(View.VISIBLE); //(mặc định ẩn nút)
            Picasso.get().load(USER_AVATAR).into(user_avatar);
        }
        mToolbar = rootView.findViewById(R.id.toolbar_drawer);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);//set toolbar như là 1 actionbar
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);//Thêm Icon ---
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START); //MỞ DRAWER KHI NG DÙNG CHỌN BUTTON
            }
        });

        mTabLayout.setupWithViewPager(mFirstViewPager);//set viewpager

        setupViewPager(mFirstViewPager);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ThoiSu(), "Thời Sự");
        adapter.addFragment(new TheGioi(), "Thế Giới");
        adapter.addFragment(new TheThao(),"Thể Thao");
        adapter.addFragment(new CongNghe(),"Công Nghệ");
        adapter.addFragment(new GiaiTri(),"Giải Trí");
        adapter.addFragment(new KhoaHoc(),"Khoa Học");
        viewPager.setAdapter(adapter);
    }
}
