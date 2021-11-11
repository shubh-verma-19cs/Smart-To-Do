package com.example.splashscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList optionImg, optionName, optionDesc;
    Context context;

    public Adapter(Context context, ArrayList optionImg, ArrayList optionName, ArrayList optionDesc){
        this.context = context;
        this.optionImg = optionImg;
        this.optionName = optionName;
        this.optionDesc = optionDesc;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preferences_list_item, parent, false);
        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int res = (int) optionImg.get(position);
        holder.images.setImageResource(res);
        holder.text.setText((String) optionName.get(position));
        holder.desc.setText((String) optionDesc.get(position));
    }

    @Override
    public int getItemCount() {
        return optionImg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView text, desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.optionImg);
            text = (TextView) itemView.findViewById(R.id.optionName);
            desc = (TextView) itemView.findViewById(R.id.optionDesc);

        }
    }
}
