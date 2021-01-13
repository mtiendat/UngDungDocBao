package com.example.ungdungdocbao.Bottom_Nav.favorite;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungdocbao.Activity.MainActivity;
import com.example.ungdungdocbao.Adapter.NewspaperAdapter;
import com.example.ungdungdocbao.Adapter.TinYeuThichAdapter;
import com.example.ungdungdocbao.Loader.DanhSachTinDaXemLoader;
import com.example.ungdungdocbao.Models.Newspaper;
import com.example.ungdungdocbao.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewspaperAdapter mAdapter;
    private List<Newspaper> dsTinYeuThich = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("TAG_ONVIEW","oncrea");

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG_ONVIEW","onviewcrea");
        recyclerView =view.findViewById(R.id.recyclerview_tinyeuthich);
        mAdapter = new NewspaperAdapter(getContext(),dsTinYeuThich);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        Log.d("TAG_ONVIEW","oncreateview");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void getList(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:8000/api/tin-da-xem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("reponsestringrequest",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray newspaper = jsonObject.getJSONArray("data");
                            for (int i=0;i<newspaper.length();i++){
                                JSONObject item = newspaper.getJSONObject(i);
                                Integer id= item.getInt("id");
                                String tieuDe = item.getString("TieuDe");
                                String danhMuc= item.getString("DanhMuc");
                                String noiDung = item.getString("NoiDung");
                                String moTa =item.getString("MoTa");
                                String ngayDang =item.getString("NgayDang");
                                String hinhAnh = item.getString("HinhAnh");
                                String tieuDeHinhAnh = item.getString("TieuDeHinhAnh");
                                String tacGia = item.getString("TacGia");
                                int luotXem = item.getInt("LuotXem");
                                Newspaper aNewspaper = new Newspaper(id, tieuDe, danhMuc, moTa, noiDung, ngayDang, hinhAnh, tieuDeHinhAnh, tacGia,luotXem);
                                dsTinYeuThich.add(aNewspaper);

                            }
                            mAdapter.updateDataSet(dsTinYeuThich);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                for(int i=0;i<MainActivity.dsTinYeuThich.size();i++) {
                    parms.put("id"+i, MainActivity.dsTinYeuThich.get(i).toString());
                }
                parms.put("size", String.valueOf(MainActivity.dsTinYeuThich.size()));
                return parms;
            }

        };

        queue.add(stringRequest);

    }


}