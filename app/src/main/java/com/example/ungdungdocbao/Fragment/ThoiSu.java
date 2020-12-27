package com.example.ungdungdocbao.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.Loader.NewsPaperLoader;
import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.R;
import com.example.ungdungdocbao.Bottom_Nav.setting.SaveState;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThoiSu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThoiSu extends Fragment implements LoaderManager.LoaderCallbacks<List<Newspaper>>{
    private RecyclerView recyclerView;
    private NewspaperAdapter mAdapter;
    private List<Newspaper> listNews = new ArrayList<>();
    LoaderManager loaderManager;
    SaveState saveState;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThoiSu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThoiSu.
     */
    // TODO: Rename and change types and number of parameters
    public static ThoiSu newInstance(String param1, String param2) {
        ThoiSu fragment = new ThoiSu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerview_thoisu);
        mAdapter=new NewspaperAdapter(getContext(),listNews);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(1000);
        if (loader == null) {
            loaderManager.initLoader(1000, null, this);
        } else {
            loaderManager.restartLoader(1000, null,this);
        }
    }
    @NonNull
    @Override
    public Loader<List<Newspaper>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsPaperLoader(getContext(),"1");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Newspaper>> loader, List<Newspaper> data) {
        listNews.clear();
        if(data!=null) {
            listNews.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Newspaper>> loader) {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thoi_su, container, false);
    }
}