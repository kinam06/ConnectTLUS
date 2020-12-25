package com.example.connecttlus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connecttlus.Object.NoiDung;
import com.example.connecttlus.R;
import com.example.connecttlus.Utils.ItemOnClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.connecttlus.MonHocActivity.urlanh;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    Context context;
    ArrayList<NoiDung> listND;
    public mAdapter(Context context, ArrayList<NoiDung> listND) {
        this.context = context;
        this.listND = listND;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview4, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoiDung noiDung = listND.get(position);
        holder.t1.setText(noiDung.getItem());
        if (!noiDung.getLink().equals("")) {
            holder.t2.setText(noiDung.getLink());
        }
        else
            holder.t2.setText("Không có file đính kèm.");
        holder.t3.setText(noiDung.getDetailname());
        Picasso.get().load(urlanh).into(holder.circleImageView);
        holder.setItemClick(new ItemOnClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!noiDung.getLink().equals("")) {
                    Intent intent = new Intent("android.intent.action.VIEW",
                            Uri.parse("https://viennhagroup.com/upload/" + noiDung.getLink()));
                    context.startActivities(new Intent[]{intent});
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listND.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView t1,t2,t3;
        CircleImageView circleImageView;
        ItemOnClick itemOnClick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.txt1);
            t2 = itemView.findViewById(R.id.txt2);
            t3 = itemView.findViewById(R.id.txt3);
            circleImageView = itemView.findViewById(R.id.mon_img);
            itemView.setOnClickListener(this);

        }
        public void setItemClick(ItemOnClick itemOnClick) {
            this.itemOnClick = itemOnClick;
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(v,getAdapterPosition(),false);
        }
    }
    public void addND(ArrayList<NoiDung> noiDungs){
        for (NoiDung noiDung : noiDungs)
            listND.add(noiDung);
        notifyDataSetChanged();
    }
}
