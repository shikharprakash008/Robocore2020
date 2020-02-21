package com.example.robocore;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> mNames, ArrayList<Integer> mImageUrls) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onCreateViewHolder:called.");
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);

        holder.textView.setText(mNames.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i;

                switch (holder.textView.getText().toString()) {
                    case "Situation 2.0":
                        i = new Intent(mContext, four_member_registration.class);
                        i.putExtra("event", "Situation");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "Cross Roads":
                        i = new Intent(mContext, four_member_registration.class);
                        i.putExtra("event", "CrossRoads");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "Rugged Rage":
                        i = new Intent(mContext, four_member_registration.class);
                        i.putExtra("event", "RuggedRage");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "Paper Presentation":
                        i = new Intent(mContext, two_member_registration.class);
                        i.putExtra("event", "PaperPresentation");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "Robo Soccer":
                        i = new Intent(mContext, four_member_registration.class);
                        i.putExtra("event", "RoboSoccer");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "D-Code":
                        i = new Intent(mContext, two_member_registration.class);
                        i.putExtra("event", "DCode");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "Arduino Clash":
                        i = new Intent(mContext, three_member_registration.class);
                        i.putExtra("event", "ArduinoClash");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    case "Project Symposium":
                        i = new Intent(mContext, four_member_registration.class);
                        i.putExtra("event", "ProjectSymposium");
                        i.putExtra("fee", "1");
                        mContext.startActivity(i);
                        break;
                    default:
                        Toast.makeText(mContext, "Sonething went wrong.", Toast.LENGTH_SHORT).show();
                }
//                Log.d(TAG, "onClick:clicked on an image:" + mNames.get(position));
//                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.main_one);
            textView = itemView.findViewById(R.id.text);
        }
    }


}

