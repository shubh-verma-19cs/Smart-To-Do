//package com.example.splashscreen;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.core.view.View;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    android.content.Context context;
//
//    public MyAdapter(android.content.Context context, ArrayList<user> list) {
//        this.context = context;
//
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        android.view.View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        user User = list.get(position);
//        holder.task_name.setText(User.getTask_name());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        android.view.View vmview;
//
//        public MyViewHolder(@NonNull android.view.View itemView){
//         super(itemView);
//         vmview = itemView;
//     }
//     public void setTask(String task){
//         EditText task_set_name = vmview.findViewById(R.id.Task_Name);
//         task_set_name.setText(task);
//
//     }
//     public void setDesc(String task_desc){
//            EditText task_set_desc = vmview.findViewById(R.id.Task_Desc);
//            task_set_desc.setText(task_desc);
//
//        }
// }
//}
