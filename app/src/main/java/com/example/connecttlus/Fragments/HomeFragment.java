package com.example.connecttlus.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.connecttlus.Adapter.hAdapter;
import com.example.connecttlus.Adapter.iAdapter;
import com.example.connecttlus.AtdActivity;
import com.example.connecttlus.ExamScheActivity;
import com.example.connecttlus.MarkActivity;
import com.example.connecttlus.Object.Activity;
import com.example.connecttlus.Object.getActivity;
import com.example.connecttlus.R;
import com.example.connecttlus.ScheActivity;
import com.example.connecttlus.Utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView, rimg;
    List<String> titles;
    List<Integer> images;
    ArrayList<Activity> listActiviy;
    hAdapter hadapter;
    iAdapter iAdapter;
    private final String event = "getDSactivity";
    private int page = 0;
    private final int record = 15;
    private int pastVisibleItems, visibleItemsCount, totalItemsCount, previous_total = 0;
    private boolean isLoading = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.homer);
        rimg = view.findViewById(R.id.homer1);
        ArrayList<String> a = new ArrayList<>();
        a.add("Chức năng 1");
        a.add("Chức năng 2");
        ArrayList<Integer> b = new ArrayList<>();
        b.add(R.drawable.ic_temp1);
        b.add(R.drawable.ic_menu);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        hadapter = new hAdapter(getContext(),a,b);
        recyclerView.setAdapter(hadapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rimg.setLayoutManager(linearLayoutManager);
        getActi();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    visibleItemsCount = linearLayoutManager.getChildCount();
                    totalItemsCount = linearLayoutManager.getItemCount();
                    pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if (isLoading)
                        if (totalItemsCount > previous_total) {
                            isLoading = false;
                            previous_total = totalItemsCount;
                        }
                    if (!isLoading && (totalItemsCount - visibleItemsCount) <= (pastVisibleItems + record)) {
                        loadMoreActi();
                        isLoading = true;
                    }
                }
            }
        });
        return view;
    }

    private void getActi() {
        ApiClient.getApiService().getDSactivity(event,page,record).enqueue(new Callback<getActivity>() {
            @Override
            public void onResponse(Call<getActivity> call, Response<getActivity> response) {
                listActiviy = response.body().getItems();
                iAdapter = new iAdapter(getContext(),listActiviy);
                rimg.setAdapter(iAdapter);
                rimg.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<getActivity> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadMoreActi(){
        page++;
        ApiClient.getApiService().getDSactivity(event,page,record).enqueue(new Callback<getActivity>() {
            @Override
            public void onResponse(Call<getActivity> call, Response<getActivity> response) {
                listActiviy = response.body().getItems();
                iAdapter.addActivity(listActiviy);
            }

            @Override
            public void onFailure(Call<getActivity> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}