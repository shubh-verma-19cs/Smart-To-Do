package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TaskSettings extends AppCompatActivity {

//    RecyclerView recyclerView;
//    ArrayList taskSettingImg = new ArrayList<>(Arrays.asList(
//       R.drawable.task,R.drawable.task, R.drawable.calendar,
//       R.drawable.clock,R.drawable.priority,
//       R.drawable.link, R.drawable.location,R.drawable.task
//    ));
//    Context context;
//    public void UsersAdapter(Context context) {
//        this.context = context;
//    }
    String currentDate;
    int hour,min,year,month,date;
    String Task_Time;
    String priority;
    String tname,ttime,tdesc,tdate,taskid,updated_name;
    String tid;
    String is_completed;
    static String tlink;
    TextView Taskname,TaskDesc,TaskDate,TaskTime,TaskPriority,TaskLink,TaskLocation,TaskStatus;
    DatabaseReference mDatabase;
    String userid;
    FirebaseUser curr_user;


//    ArrayList taskSettingName = new ArrayList<>();
//    ArrayList taskSettingDesc = new ArrayList<>(Arrays.asList("Task Name", "Task Description", "Task date", "Task time", "Task priority", "Task link", "Task location","Task Status"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_settings);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        curr_user = FirebaseAuth.getInstance().getCurrentUser();
        userid = curr_user.getUid();


        Taskname = findViewById(R.id.Taskname);
        TaskDesc = findViewById(R.id.taskDescription);
        TaskDate = findViewById(R.id.taskDate);
        TaskTime = findViewById(R.id.taskTime);
        TaskPriority = findViewById(R.id.taskPriority);
        TaskLink = findViewById(R.id.taskLink);
        TaskLocation = findViewById(R.id.taskLocation);
        TaskStatus = findViewById(R.id.taskStatus);

        tname = getIntent().getStringExtra("name");
        tdesc = getIntent().getStringExtra("Desc");
        ttime = getIntent().getStringExtra("Time");
        tdate = getIntent().getStringExtra("Date");
        tid = getIntent().getStringExtra("tid");
        tlink = getIntent().getStringExtra("Link");
        priority = getIntent().getStringExtra("Priority");
        is_completed = getIntent().getStringExtra("Status");

        Taskname.setText(tname);
        TaskDesc.setText(tdesc);
        TaskTime.setText(ttime);
        TaskDate.setText(tdate);
        TaskPriority.setText(priority);
        TaskLink.setText(tlink);
//        TaskLocation.setText()


//        taskSettingName.add(tname);
//        taskSettingName.add(tdesc);
//        taskSettingName.add(tdate);
//        taskSettingName.add(ttime);
//        taskSettingName.add(tid);
//        taskSettingName.add(tlink);
//        taskSettingName.add("Add location");
//        taskSettingName.add(is_completed);
//        try{
//            Log.d("lol", updated_name);
//        }
//        catch (NullPointerException n){
//
//        }
//        if(!updated_name.isEmpty()){
//            taskSettingName.set(0,updated_name);
//        }
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewtask);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        TaskSettingsAdapter taskSettingsAdapter = new TaskSettingsAdapter(TaskSettings.this, taskSettingImg, taskSettingName, taskSettingDesc);
//        recyclerView.setAdapter(taskSettingsAdapter);
    }
    private void startDialog(int task_number,String tid) {
        Update_Dialog update_dialog = new Update_Dialog(task_number,tid);
        if(task_number==0){
            update_dialog.show(this.getSupportFragmentManager(), "Edit Task Name");
        }
        else if(task_number==1){
            update_dialog.show(this.getSupportFragmentManager(), "Edit Task Description");
        }
        else if (task_number==2){
            update_dialog.show(this.getSupportFragmentManager(), "Edit Task Priority");
        }
        else if(task_number==3){
            update_dialog.show(this.getSupportFragmentManager(), "Edit Task Link");
        }

//        dialog_link.task = tname;
//        Taskname.setText(tname);
    }
    private void startDialog(int task_number,String tid,boolean dialog_need){

    }
    public void startTaskPage() {

        Intent logIntent = new Intent(TaskSettings.this, TaskSettings.class);
//        logIntent.putExtra("username", String.valueOf(userEdit));
//        logIntent.putExtra("password", String.valueOf(passEdit));
        startActivity(logIntent);

    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                hour = selectedHour;
                min = selectedMin;
                String time = hour+":"+min;
                SimpleDateFormat f24hour =  new SimpleDateFormat("HH:mm");
                try{
                    Date date = f24hour.parse(time);
                    SimpleDateFormat f12hour = new SimpleDateFormat("HH:mm aa");
                    Task_Time = f12hour.format(date);


                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                mDatabase.child("users").child(userid).child(tid).child("Time").setValue(Task_Time);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,min,false);
        timePickerDialog.show();
    }

    public void popDatePicker(View view) {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.DAY_OF_MONTH,date);
                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                mDatabase.child("users").child(userid).child(tid).child("TaskDate").setValue(currentDate);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,onDateSetListener,2021,month,date);
        datePickerDialog.show();
    }
    public void popTaskName(View view){
        startDialog(0,tid);
    }
    public void popTaskDesc(View view){
        startDialog(1,tid);
    }
    public void popTaskPriority(View view){
        startDialog(2,tid);
    }
    public void popTaskLink(View view){
        startDialog(3,tid);
    }

}