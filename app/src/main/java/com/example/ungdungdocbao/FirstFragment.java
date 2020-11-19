package com.example.ungdungdocbao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FirstFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager firstViewPager;
    public FirstFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        firstViewPager = (ViewPager) rootView.findViewById(R.id.viewpage_content);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(firstViewPager);

        setupViewPager(firstViewPager);
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
