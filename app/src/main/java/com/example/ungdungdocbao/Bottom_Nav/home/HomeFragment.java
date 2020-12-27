package com.example.ungdungdocbao.Bottom_Nav.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager firstViewPager;
    SaveState saveState;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*for(int i=0;i<10;i++)
        {
            Newspaper news = new Newspaper("TIÊU ĐỀ SỐ"+i,"MÔ TẢ THỨ "+i);
            listNews.addLast(news);
        }
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        mAdapter=new NewspaperAdapter(getContext(),listNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);*/
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);

    }
}