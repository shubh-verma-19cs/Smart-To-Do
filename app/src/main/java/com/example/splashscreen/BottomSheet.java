package com.example.splashscreen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class BottomSheet extends BottomSheetDialogFragment  {
    public BottomSheet(){

    }



    UUID userId;
    static String date;
    private DatabaseReference mDatabase;
    static String Time;
    ImageView time;
    int thour,tmin;
    String Task_Time;
    static String link_task;
    static String link_meet;
    FirebaseAuth fb;
    String mail;
    FirebaseUser curr_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view1 = inflater.inflate(R.layout.bottomsheetlayout,container,false);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
        view1.startAnimation(hyperspaceJumpAnimation);
        Button Savebtn = view1.findViewById(R.id.task_save);
        ImageView calendar_date = view1.findViewById(R.id.task_date);
        ImageView Time = view1.findViewById(R.id.task_time);
        fb = FirebaseAuth.getInstance();
        curr_user = fb.getCurrentUser();
        mail = curr_user.getUid();
        Log.d("uid", ""+mail);
        userId = (UUID) UUID.randomUUID();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        calendar_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getActivity().getSupportFragmentManager(), "Date Picker");
            }
        });
        ImageView time = view1.findViewById(R.id.task_time);
        ImageView meet_link = view1.findViewById(R.id.task_link);
        meet_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        thour = hour;
                        tmin = min;
                        String time = thour+":"+tmin;
                        SimpleDateFormat f24hour =  new SimpleDateFormat("HH:mm");
                        try{
                            Date date = f24hour.parse(time);
                            SimpleDateFormat f12hour = new SimpleDateFormat("HH:mm aa");
                            Task_Time = f12hour.format(date);
                        }
                        catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(thour,tmin);
                timePickerDialog.show();
            }
        });

        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText taskname = view1.findViewById(R.id.Task_Name);
                EditText taskdesc = view1.findViewById(R.id.Task_Desc);
//                HomeActivity hm = new HomeActivity();
//                EditText taskname = view.findViewById(R.id.task_name);
                String tname,tdesc;
                if(!TextUtils.isEmpty(taskname.getText().toString().trim())){
                    tname = taskname.getText().toString().trim();
                    if(!TextUtils.isEmpty(taskdesc.getText().toString().trim())){
                        tdesc = taskdesc.getText().toString().trim();
                        HomeActivity h = new HomeActivity();

                        if(!TextUtils.isEmpty(link_meet)){
                            if(!TextUtils.isEmpty(link_task)){
                                writeNewUser(userId,tname,tdesc,""+date,""+Task_Time,""+link_meet,""+link_task);
                                Log.d("NNN", ""+link_meet);
                                Log.d("NNN", ""+link_task);
                            }
                            else{
                                writeNewUser(userId,tname,tdesc,""+date,""+Task_Time,""+link_meet);
                            }
                        }
                        else{
                            writeNewUser(userId,tname,tdesc,""+date,""+Task_Time);
                        }
                        Toast.makeText(getContext(),"Task Added Successfully",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getContext(),"Enter Task Details",Toast.LENGTH_LONG).show();
                }
                Animation hyperspaceJumpAnimation1 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom);
                view1.startAnimation(hyperspaceJumpAnimation1);
//                startHomePage();
            }
        });
        return view1;
    }

    private void startDialog() {
        Dialog_Link dialog_link = new Dialog_Link();
        dialog_link.show(getActivity().getSupportFragmentManager(), "Edit Task Details");
    }

    public void writeNewUser(UUID userId, String name, String taskdesc,String taskdate,String Time) {
//        HashMap<String ,String > usermap = new HashMap<>();
//        usermap.put("TaskName",name);
//        usermap.put("Description",taskdesc);
//        usermap.put("Date",taskdate);
//        usermap.put("Time",Time);
        mDatabase.child("users").child(mail).child(userId.toString()).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(userId.toString()).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(userId.toString()).child("Time").setValue(Time);


//        mDatabase.child("users").child(userId.toString()).setValue(usermap);

        Log.d("NEWS", Time);
        Log.d("NEWS", taskdesc);
        Log.d("NEWS", taskdate);


    }
    public void writeNewUser(UUID userId, String name, String taskdesc,String taskdate,String Time,String meet){
//        HashMap<String ,String > usermap = new HashMap<>();
//        usermap.put("TaskName",name);
//        usermap.put("Description",taskdesc);
//        usermap.put("Date",taskdate);
//        usermap.put("Time",Time);
//        usermap.put("meetlink",meet);
//        mDatabase.child("users").child(userId.toString()).setValue(usermap);
        mDatabase.child("users").child(mail).child(userId.toString()).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(userId.toString()).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(userId.toString()).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(userId.toString()).child("meet").setValue(meet);


    }
    public void startHomePage() {

        Intent logIntent = new Intent(getContext(), HomeActivity.class);
//        logIntent.putExtra("username", String.valueOf(userEdit));
//        logIntent.putExtra("password", String.valueOf(passEdit));
        startActivity(logIntent);

    }
    public void writeNewUser(UUID userId, String name, String taskdesc,String taskdate,String Time,String meet,String ltask){
//        HashMap<String ,String > usermap = new HashMap<>();
//        usermap.put("TaskName",name);
//        usermap.put("Description",taskdesc);
//        usermap.put("Date",taskdate);
//        usermap.put("Time",Time);
//        usermap.put("meetlink",meet);
//        usermap.put("tasklink",ltask);
//        mDatabase.child("users").child(userId.toString()).setValue(usermap);
        mDatabase.child("users").child(mail).child(userId.toString()).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(userId.toString()).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(userId.toString()).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(userId.toString()).child("meet").setValue(meet);
        mDatabase.child("users").child(mail).child(userId.toString()).child("ltask").setValue(ltask);

    }

}
