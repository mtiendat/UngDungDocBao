package com.example.ungdungdocbao;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class FirstFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mFirstViewPager;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    public FirstFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                item.setChecked(true);
                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();
                return true;
                //Nếu Item được chạm , đoạn code này sẽ thiết lập lựa chon item và Drawer cũng được đóng bởi hàm closeDrawers().
            }
        });
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

        mTabLayout.setupWithViewPager(mFirstViewPager);//set viewpage
        setupViewPager(mFirstViewPager);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ThoiSu(), "Thời Sự");
        adapter.addFragment(new GocNhin(),"Góc Nhìn");
        adapter.addFragment(new TheGioi(), "Thế Giới");
        adapter.addFragment(new TheThao(),"Thể Thao");
        adapter.addFragment(new CongNghe(),"Công Nghệ");
        adapter.addFragment(new GiaiTri(),"Giải Trí");
        adapter.addFragment(new KhoaHoc(),"Khoa Học");
        viewPager.setAdapter(adapter);
    }
}
