//package com.example.splashscreen;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatDelegate;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//public class TaskSettingsAdapter extends RecyclerView.Adapter<TaskSettingsAdapter.ViewHolder> {
//
//    ArrayList taskSettingImg, taskSettingName, taskSettingDesc;
//    Context context;
//    DatabaseReference mDatabase;
//    String userid;
//    FirebaseUser curr_user;
//    FirebaseAuth fb;
//    EditText taskname;
//    static String updated_name;
//    static String UserId;
//    static String tid;
//    Context mcontext;
//    TaskSettings ts = new TaskSettings();
////    public void openDialog(android.view.View view){
////        Update_Dialog dialog = new Update_Dialog();
////        ts.UsersAdapter(mcontext);
////        (AppCompatActivity)mcontext.
////        dialog.show((AppCompatActivity) mcontext, "Testing");
////    }
//
//    public TaskSettingsAdapter(Context context, ArrayList taskSettingImg, ArrayList taskSettingName, ArrayList taskSettingDesc){
//        this.context = context;
//        this.taskSettingImg = taskSettingImg;
//        this.taskSettingName = taskSettingName;
//        this.taskSettingDesc = taskSettingDesc;
//    }
//
//    @NonNull
//    @Override
//    public TaskSettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_settings_items, parent, false);
//        TaskSettingsAdapter.ViewHolder viewHolder = new TaskSettingsAdapter.ViewHolder(view, context);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        taskname = view.findViewById(R.id.dialog_update);
//
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TaskSettingsAdapter.ViewHolder holder, int position) {
//        int res = (int) taskSettingImg.get(position);
//        holder.images.setImageResource(res);
//        holder.text.setText((String) taskSettingName.get(position));
//        holder.text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                openDialog(view);
////                fb = FirebaseAuth.getInstance();
////                mDatabase = FirebaseDatabase.getInstance().getReference();
////                curr_user = fb.getCurrentUser();
////                userid = curr_user.getUid();
////                Toast.makeText(context, "lol", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        holder.desc.setText((String) taskSettingDesc.get(position));
//
//
//    }
////    public void openDialog(android.view.View view){};
//
//    @Override
//    public int getItemCount() {
//        return taskSettingImg.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ImageView images;
//        TextView text, desc;
//
//        public ViewHolder(@NonNull View itemView, Context context) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            images = itemView.findViewById(R.id.taskSettingImg);
//            text = itemView.findViewById(R.id.taskSettingName);
//            desc = itemView.findViewById(R.id.taskSettingDesc);
//            text.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
////            openDialog(view);
//
////            int position = this.getAdapterPosition();
////            fb = FirebaseAuth.getInstance();
////            mDatabase = FirebaseDatabase.getInstance().getReference();
////            curr_user = fb.getCurrentUser();
////            userid = curr_user.getUid();
////            AlertDialog.Builder builder = new AlertDialog.Builder(context);
////            switch (position) {
////                case 0:
//////                    showSelection();
////                    Toast.makeText(context, "Due Date", Toast.LENGTH_SHORT).show();
//////                    builder.show(view.getContext().getSu);
//////                    dialog_link.show(getActivity().getSupportFragmentManager(), "Edit Task Details");
////                    break;
////                case 1:
////                    Toast.makeText(context, "Due Date", Toast.LENGTH_SHORT).show();
//////                    showNameSelection();
////                    break;
////                case 2:
////                    Toast.makeText(context, "Time", Toast.LENGTH_SHORT).show();
////                    break;
////                case 3:
//////                    showThemeSelection();
////                    Toast.makeText(context, "Repeat", Toast.LENGTH_SHORT).show();
////                    break;
////                case 4:
////                    Toast.makeText(context, "Priority", Toast.LENGTH_SHORT).show();
////                    break;
////                case 5:
////                    Toast.makeText(context, "Add meeting Link", Toast.LENGTH_SHORT).show();
////                    break;
////                case 6:
////                    Toast.makeText(context, "Add Location", Toast.LENGTH_SHORT).show();
////                    break;
////            }
//        }
//
//
//
//
//    }
//
////    public void showSelection() {
////        AlertDialog.Builder builder = new AlertDialog.Builder(context);
////        builder.setTitle("Name");
////        builder.setView(R.layout.layout_dialog).setTitle("Dialog Box").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
////            }
////        }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                taskname = view.findViewById(R.id.dialog_update);
////                String updated_name = taskname.getText().toString().trim();
////                Intent intent = new Intent(context, TaskSettings.class);
////                intent.putExtra("updated_name",updated_name);
////                Log.d("Fuck", ts.tid);
////                mDatabase.child("users").child(UserId).child(ts.tid).child("TaskName").setValue(updated_name);
////            }
////        });
////        AlertDialog alert = builder.create();
////        alert.setCanceledOnTouchOutside(true);
////        alert.show();
////    }
//}
