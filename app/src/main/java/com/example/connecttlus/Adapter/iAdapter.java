package com.example.connecttlus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connecttlus.Object.Activity;
import com.example.connecttlus.Object.Doc;
import com.example.connecttlus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class iAdapter extends RecyclerView.Adapter<iAdapter.ViewHolder> {
    Context context;
    ArrayList<Activity> listActivity;

    public iAdapter(Context context, ArrayList<Activity> listActivity) {
        this.context = context;
        this.listActivity = listActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemviewimg, parent, false);
        return new iAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Activity activity = listActivity.get(position);
        holder.txt1.setText(activity.getNameactivity());
        holder.txt2.setText("Album: "+activity.getAlbum());
        holder.txt3.setText(activity.getCreateat());
        Picasso.get().load("https://viennhagroup.com/file/"+activity.getUrlfile()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listActivity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.imgname);
            txt2 = itemView.findViewById(R.id.imgalbum);
            txt3 = itemView.findViewById(R.id.imgtime);
            imageView = itemView.findViewById(R.id.imgh);
        }
    }
    public void addActivity (ArrayList<Activity> activities){
        for (Activity activity : activities)
            listActivity.add(activity);
        notifyDataSetChanged();
    }
}
