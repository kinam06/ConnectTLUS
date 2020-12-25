package com.example.connecttlus.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.connecttlus.Adapter.nAdapter;
import com.example.connecttlus.Object.Notify;
import com.example.connecttlus.Object.getNotify;
import com.example.connecttlus.R;
import com.example.connecttlus.Utils.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    nAdapter nAdapter;
    ArrayList<Notify> listNoti;
    ProgressBar progressBarN, progressBarN2;
    private final String event = "getDSNotify";
    private int page_number = 0;
    private final int record = 15;
    private int pastVisibleItems, visibleItemsCount, totalItemsCount, previous_total = 0;
    private boolean isLoading = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotiFragment newInstance(String param1, String param2) {
        NotiFragment fragment = new NotiFragment();
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
        View view = inflater.inflate(R.layout.fragment_noti, container, false);
        recyclerView = view.findViewById(R.id.listnoti);
        progressBarN = view.findViewById(R.id.progressBarN);
        progressBarN2 = view.findViewById(R.id.progressBarN2);
        progressBarN.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        getNoti();
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
                        loadMoreNotify();
                        isLoading = true;
                    }
                }
            }
        });
        return view;
    }

    private void getNoti() {
        ApiClient.getApiService().getDSNotify(event,page_number,record).enqueue(new Callback<getNotify>() {
            @Override
            public void onResponse(Call<getNotify> call, Response<getNotify> response) {
                listNoti=response.body().getItems();
                nAdapter = new nAdapter(getContext(),listNoti);
                recyclerView.setAdapter(nAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                progressBarN.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<getNotify> call, Throwable t) {
                Log.e("Loi: ",t.getMessage());
                Toast.makeText(getContext(),"Kiểm tra kết nối!",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadMoreNotify(){
        page_number++;
        progressBarN2.setVisibility(View.VISIBLE);
        ApiClient.getApiService().getDSNotify(event,page_number,record).enqueue(new Callback<getNotify>() {
            @Override
            public void onResponse(Call<getNotify> call, Response<getNotify> response) {
                listNoti=response.body().getItems();
                nAdapter.addNotify(listNoti);
                progressBarN2.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<getNotify> call, Throwable t) {
                Toast.makeText(getActivity(),"Lỗi", Toast.LENGTH_LONG).show();
                progressBarN2.setVisibility(View.GONE);
                Log.e("Loi: ",t.getMessage());
            }
        });
    }
}