package com.example.ungdungdocbao.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.Loader.NewsPaperLoader;
import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TheGioi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheGioi extends Fragment implements LoaderManager.LoaderCallbacks<List<Newspaper>> {
    private RecyclerView recyclerView;
    private NewspaperAdapter mAdapter;
    LoaderManager loaderManager;
    private ProgressDialog progressDialog;
    public List<Newspaper>  listNews = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TheGioi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TheGioi.
     */
    // TODO: Rename and change types and number of parameters
    public static TheGioi newInstance(String param1, String param2) {
        TheGioi fragment = new TheGioi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_the_gioi, container, false);
        recyclerView=view.findViewById(R.id.recyclerview_thegioi);
        mAdapter=new NewspaperAdapter(getContext(),listNews);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loaderManager = LoaderManager.getInstance(this);
        Loader loader = loaderManager.getLoader(1000);
        StartLoader();
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.refreshTheGioi);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                StartLoader(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @NonNull
    @Override
    public Loader<List<Newspaper>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsPaperLoader(getContext(),"2");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Newspaper>> loader, List<Newspaper> data) {
        listNews.clear();
        mAdapter.updateDataSet(data);
        progressDialog.dismiss();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Newspaper>> loader) {

    }
    public void StartLoader(){
        Loader loader = loaderManager.getLoader(1000);
        if (loader == null) {
            loaderManager.initLoader(1000, null, this);
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Đang tải chờ tí nhé...");
            progressDialog.setProgressStyle(R.color.colorThemeVNExpress);
            progressDialog.show();
        } else {
            loaderManager.restartLoader(1000, null,this);
        }
    }
}