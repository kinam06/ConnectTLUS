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

import com.example.connecttlus.Adapter.dAdapter;
import com.example.connecttlus.Object.Doc;
import com.example.connecttlus.Object.getDoc;
import com.example.connecttlus.R;
import com.example.connecttlus.Utils.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    dAdapter dAdapter;
    ArrayList<Doc> listDoc;
    ProgressBar dProgressBar;
    private final String event="getDSMonHoc";
    private int page_number = 0;
    private final int record = 15;
    private int pastVisibleItems, visibleItemsCount, totalItemsCount, previous_total = 0;
    private boolean isLoading = true;

    public DocFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DocFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DocFragment newInstance(String param1, String param2) {
        DocFragment fragment = new DocFragment();
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
        View view = inflater.inflate(R.layout.fragment_doc, container, false);
        recyclerView = view.findViewById(R.id.listdoc);
        dProgressBar = view.findViewById(R.id.docprogessbar);
        dProgressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        getDoc();
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
                        loadMoreDoc();
                        isLoading = true;
                    }
                }
            }
        });
        return view;
    }

    private void getDoc() {
        ApiClient.getApiService().getDSMonhoc(event,page_number,record).enqueue(new Callback<getDoc>() {
            @Override
            public void onResponse(Call<getDoc> call, Response<getDoc> response) {
                listDoc=response.body().getItems();
                dAdapter = new dAdapter(getContext(),listDoc);
                recyclerView.setAdapter(dAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                dProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<getDoc> call, Throwable t) {
            }
        });
    }
    private void loadMoreDoc(){
        page_number++;
        ApiClient.getApiService().getDSMonhoc(event,page_number,record).enqueue(new Callback<getDoc>() {
            @Override
            public void onResponse(Call<getDoc> call, Response<getDoc> response) {
                listDoc=response.body().getItems();
                dAdapter.addDoc(listDoc);
            }
            @Override
            public void onFailure(Call<getDoc> call, Throwable t) {
                Toast.makeText(getActivity(),"ga qua", Toast.LENGTH_LONG).show();
                Log.e("Loi: ",t.getMessage());
            }
        });
    }
}