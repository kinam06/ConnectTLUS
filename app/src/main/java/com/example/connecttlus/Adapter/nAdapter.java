package com.example.connecttlus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connecttlus.Object.Notify;
import com.example.connecttlus.R;
import com.example.connecttlus.Utils.ItemOnClick;

import java.util.ArrayList;


public class nAdapter extends RecyclerView.Adapter<nAdapter.ViewHolder> {
    Context context;
    private final ArrayList<Notify> listThongBao;

    public nAdapter(Context context, ArrayList<Notify> listThongBao) {
        this.context = context;
        this.listThongBao = listThongBao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liêuk
        Notify notify = listThongBao.get(position);
        holder.txtNoti.setText("" + notify.getContent());
        holder.txtNotius.setText("Người gửi: " + notify.getDetailname());
        if (notify.getAttachfile().length()>0) {
            holder.txtNotides.setText(notify.getAttachfile());
        }
        else
            holder.txtNotides.setText("Không có file đính kèm.");
        holder.txtNotiTime.setText(notify.getCreateat());
        holder.setItemClick(new ItemOnClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (notify.getAttachfile().length()>0) {
                    Intent intent = new Intent("android.intent.action.VIEW",
                            Uri.parse("https://viennhagroup.com/upload/" + notify.getAttachfile()));
                    context.startActivities(new Intent[]{intent});
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listThongBao.size(); // trả item tại vị trí postion
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtNoti, txtNotius, txtNotides, txtNotiTime;
        ItemOnClick itemOnClick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            txtNoti = itemView.findViewById(R.id.txtnoti);
            txtNotius = itemView.findViewById(R.id.txtnotius);
            txtNotides = itemView.findViewById(R.id.txtnotides);
            txtNotiTime = itemView.findViewById(R.id.notitime);
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
    public void addNotify(ArrayList<Notify> itemNotifies) {
        for (Notify notify : itemNotifies){
            listThongBao.add(notify);}
        notifyDataSetChanged();
    }
}