package com.example.robocore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadaptersponser2 extends RecyclerView.Adapter<myadaptersponser2.ViewHolder> {
    ArrayList<listitemsponser2> listitemsponser2s;
    Context context;

    public myadaptersponser2(ArrayList<listitemsponser2> listitemsponser2s, Context context) {
        this.listitemsponser2s = listitemsponser2s;
        this.context = context;
    }

    @Override
    public myadaptersponser2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_designsponsor2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myadaptersponser2.ViewHolder holder, int position) {
holder.name.setText(listitemsponser2s.get(position).getApp_name());
        holder.logo.setImageResource(listitemsponser2s.get(position).getApp_logo());



    }

    @Override
    public int getItemCount() {
        return listitemsponser2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView logo;
        private TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logo =itemView.findViewById(R.id.app_logo);
            name=itemView.findViewById(R.id.app_name);

        }
    }
}
