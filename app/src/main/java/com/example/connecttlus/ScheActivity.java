package com.example.connecttlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.connecttlus.Adapter.sAdapter;
import com.example.connecttlus.Object.Sche;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    sAdapter sAdapter;
    ArrayList<Sche> listSche;
    Spinner sphk,spdh;
    String url="https://api.github.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sche);
        recyclerView = findViewById(R.id.listsche);
        sphk = (Spinner) findViewById(R.id.sphk);
        spdh = (Spinner) findViewById(R.id.spdh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listSche = new ArrayList<>();
        listSche.add(new Sche("Môn 1","T2: 4, 5, 6"));
        listSche.add(new Sche("Môn 2","T3: 7, 8, 9"));
        listSche.add(new Sche("Môn 1","T2: 4, 5, 6"));
        listSche.add(new Sche("Môn 2","T3: 7, 8, 9"));
        listSche.add(new Sche("Môn 1","T2: 4, 5, 6"));
        listSche.add(new Sche("Môn 2","T3: 7, 8, 9"));
        listSche.add(new Sche("Môn 1","T2: 4, 5, 6"));
        listSche.add(new Sche("Môn 2","T3: 7, 8, 9"));
        listSche.add(new Sche("Môn 1","T2: 4, 5, 6"));
        listSche.add(new Sche("Môn 2","T3: 7, 8, 9"));
        sAdapter = new sAdapter(getApplicationContext(),listSche);
        recyclerView.setAdapter(sAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}