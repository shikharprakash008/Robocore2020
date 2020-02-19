package com.example.robocore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Imageviewholder> {
    private int[] images;

    public RecyclerAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public Imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        Imageviewholder imageviewholder = new Imageviewholder(view);
        return imageviewholder;
    }

    @Override
    public void onBindViewHolder(Imageviewholder holder, int position) {
        int images_id = images[position];
        holder.item.setImageResource(images_id);
        holder.itemtext.setText("Image:" + position);


    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class Imageviewholder extends RecyclerView.ViewHolder {
        ImageView item;
        TextView itemtext;

        public Imageviewholder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.imagerecycle);
            itemtext = itemView.findViewById(R.id.textrecycle);

        }
    }

}
