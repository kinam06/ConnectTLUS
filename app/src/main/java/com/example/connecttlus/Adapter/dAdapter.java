package com.example.connecttlus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connecttlus.MonHocActivity;
import com.example.connecttlus.Object.Doc;
import com.example.connecttlus.R;
import com.example.connecttlus.Utils.ItemOnClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class dAdapter extends RecyclerView.Adapter<dAdapter.ViewHolder> {
    Context context;
    ArrayList<Doc> listDoc;

    public dAdapter(Context context, ArrayList<Doc> listDoc) {
        this.context = context;
        this.listDoc = listDoc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.itemview2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liêuk
        Doc doc = listDoc.get(position);
        holder.txtdocsub.setText(doc.getTenmon());
        Picasso.get().load("https://viennhagroup.com/file/"+doc.getUrlanh()).into(holder.imageView);
        holder.setItemClick(new ItemOnClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, MonHocActivity.class);
                intent.putExtra("mamon",doc.getMamon());
                intent.putExtra("tenmon",doc.getTenmon());
                intent.putExtra("anhmon",doc.getUrlanh());
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDoc.size(); // trả item tại vị trí postion
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtdocsub, txtdocname;
        ImageView imageView;
        ItemOnClick itemOnClick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            txtdocsub = itemView.findViewById(R.id.txtdocsub);
            txtdocname = itemView.findViewById(R.id.txtdocname);
            imageView = itemView.findViewById(R.id.doc_img);
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
    public void addDoc(ArrayList<Doc> docs){
        for (Doc doc : docs)
            listDoc.add(doc);
        notifyDataSetChanged();
    }
}