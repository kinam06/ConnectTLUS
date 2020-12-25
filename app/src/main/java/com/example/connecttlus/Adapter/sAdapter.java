package com.example.connecttlus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connecttlus.Object.Sche;
import com.example.connecttlus.R;

import java.util.ArrayList;

public class sAdapter extends RecyclerView.Adapter<sAdapter.ViewHolder> {
    private final ArrayList<Sche> listSche;
    private final Context context;

    public sAdapter(Context context, ArrayList<Sche> listSche) {
        this.listSche = listSche;
        this.context = context;
    }

    @NonNull
    @Override
    public sAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview3, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sAdapter.ViewHolder holder, int position) {
        Sche sche = listSche.get(position);
        holder.txtschesub.setText(sche.getSub_name());
        holder.txtschetime.setText(sche.getSchedule());
    }

    @Override
    public int getItemCount() {
        return listSche.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtschesub, txtschetime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtschesub = itemView.findViewById(R.id.txtschesub);
            txtschetime = itemView.findViewById(R.id.txtschetime);
        }
    }
}
