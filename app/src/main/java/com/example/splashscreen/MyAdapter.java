package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Model> mlist;
    Context context;
    DatabaseReference d;
    public MyAdapter(Context context,ArrayList<Model> mlist){
        this.mlist = mlist;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mlist.get(position);

//        Log.d("taskname", ""+model.getTaskDate());
//        Log.d("taskdesc", ""+model.getDescription());
//        Log.d("taskdate", ""+model.getTime());

        holder.taskname.setText(model.getTaskName());
        holder.tasdesc.setText(model.getDescription());
        holder.time.setText(model.getTime());
        holder.date.setText(model.getTaskDate());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView taskname,tasdesc,time,date;
        public MyViewHolder(@NonNull android.view.View itemView){
            super(itemView);
            tasdesc = itemView.findViewById(R.id.task_description);
            taskname = itemView.findViewById(R.id.task_name);
            time = itemView.findViewById(R.id.ttime);
            date = itemView.findViewById(R.id.tdate);
        }


    }
}
