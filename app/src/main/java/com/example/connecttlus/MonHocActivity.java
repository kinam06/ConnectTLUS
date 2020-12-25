package com.example.connecttlus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connecttlus.Adapter.mAdapter;
import com.example.connecttlus.Object.NoiDung;
import com.example.connecttlus.Object.getND;
import com.example.connecttlus.Utils.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonHocActivity extends AppCompatActivity {

    TextView mon;
    ImageView back;
    RecyclerView recyclerView;
    private final String event="getDSBaiGiangbyMaMon";
    private int page = 0;
    private final int record = 15;
    private String mamon = "";
    public static String urlanh = "";
    ArrayList<NoiDung> listND;
    mAdapter mAdapter;
    private int pastVisibleItems, visibleItemsCount, totalItemsCount, previous_total = 0;
    private boolean isLoading = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);
        mon=findViewById(R.id.mon);
        back=findViewById(R.id.docback);
        recyclerView = findViewById(R.id.recycleView);
        Intent intent = getIntent();
        mamon = intent.getStringExtra("mamon");
        mon.setText(intent.getStringExtra("tenmon"));
        urlanh = "https://viennhagroup.com/file/"+intent.getStringExtra("anhmon");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        getND();
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
                        loadMoreND();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void getND() {
        ApiClient.getApiService().getDSNoiDung(event,page,record,mamon).enqueue(new Callback<getND>() {
            @Override
            public void onResponse(Call<getND> call, Response<getND> response) {
                listND = response.body().getItems();
                mAdapter = new mAdapter(MonHocActivity.this,listND);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                try {
                    String a = listND.get(0).getIdsub();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Chưa cập nhật dữ liệu",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<getND> call, Throwable t) {

            }
        });
    }
    private void loadMoreND(){
        page++;
        ApiClient.getApiService().getDSNoiDung(event,page,record,mamon).enqueue(new Callback<getND>() {
            @Override
            public void onResponse(Call<getND> call, Response<getND> response) {
                listND = response.body().getItems();
                mAdapter.addND(listND);
            }

            @Override
            public void onFailure(Call<getND> call, Throwable t) {

            }
        });
    }
}