package com.example.connecttlus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connecttlus.AtdActivity;
import com.example.connecttlus.ExamScheActivity;
import com.example.connecttlus.MarkActivity;
import com.example.connecttlus.R;
import com.example.connecttlus.ScheActivity;

import java.util.List;

public class hAdapter extends RecyclerView.Adapter<hAdapter.ViewHolder> {

    Context context;
    List<String> titles;
    List<Integer> images;

    public hAdapter(Context context, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            gridIcon = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition()==0) {
                        Intent intent=new Intent(context, ScheActivity.class);
                    }
                    if (getAdapterPosition()==1) {
                        Intent intent=new Intent(context, AtdActivity.class);
                    }
                    if (getAdapterPosition()==2) {
                        Intent intent=new Intent(context, ExamScheActivity.class);
                    }
                    if (getAdapterPosition()==3) {
                        Intent intent=new Intent(context, MarkActivity.class);
                    }
                }
            });
        }
    }
}
