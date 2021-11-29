package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Model> mlist;
    Context context;
    HomeActivity hm;
    DatabaseReference d;
    int pos;
    static String empt = " ";
    public MyAdapter(Context context,ArrayList<Model> mlist){
        this.mlist = mlist;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        hm = new HomeActivity();
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

           final Model model = mlist.get(position);
//        Log.d("taskname", ""+model.getTaskDate());
//        Log.d("taskdesc", ""+model.getDescription());
//        Log.d("taskdate", ""+model.getTime());

            int size = hm.Task_Id.size();
            holder.taskname.setText(model.getTaskName());
            holder.tasdesc.setText(model.getDescription());
            holder.time.setText(model.getTime());
            holder.date.setText(model.getTaskDate());
            holder.checkBox.setChecked(model.isCheckboxStatus());

//            holder.Textid = hm.Task_Id.get(size-position-1);
//            Log.d("id", hm.Task_Id.get(size-position-1));
            holder.taskname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent settingsIntent = new Intent(context, TaskSettings.class);
                    settingsIntent.putExtra("name",model.getTaskName());
                    settingsIntent.putExtra("Desc",model.getDescription());
                    settingsIntent.putExtra("Time",model.getTime());
                    settingsIntent.putExtra("Date",model.getTaskDate());
                    settingsIntent.putExtra("Status",model.isCheckboxStatus());
                    settingsIntent.putExtra("Link",model.getLtask());
                    settingsIntent.putExtra("Priority",model.getPriority());
//                    Intent updateIntent = new Intent(context, TaskSettingsAdapter.class);

                    pos = holder.getAdapterPosition();
                    settingsIntent.putExtra("tid",hm.Task_Id.get(size-pos-1));
//                    updateIntent.putExtra("tid",hm.Task_Id.get(size-pos-1));


                    settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(settingsIntent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView taskname,tasdesc,time,date;
        CheckBox checkBox;
        String Textid;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tasdesc = itemView.findViewById(R.id.task_description);
            taskname = itemView.findViewById(R.id.task_name);
            time = itemView.findViewById(R.id.ttime);
            date = itemView.findViewById(R.id.tdate);
            checkBox = itemView.findViewById(R.id.todo_checkbox);
        }


    }
}
