package com.example.splashscreen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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


    BottomSheetDialog dialog;
    HomeActivity homeActivity;
    String currentDateTime;
    SimpleDateFormat sdf;
    TaskSettings taskSettings;
//    String currentDateandTime;



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


    TextView dateTV, timeTV;
    boolean checkBox;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view1 = inflater.inflate(R.layout.bottomsheetlayout,container,false);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
        view1.startAnimation(hyperspaceJumpAnimation);
        Button Savebtn = view1.findViewById(R.id.task_save);
        ImageView calendar_date = view1.findViewById(R.id.task_date);
        ImageView Time = view1.findViewById(R.id.task_time);

        dateTV = view1.findViewById(R.id.dateTV);
        timeTV = view1.findViewById(R.id.timeTV);

        dateTV.setVisibility(View.GONE);
        timeTV.setVisibility(View.GONE);
        checkBox = false;

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        currentDateTime = sdf.format(new Date());

        Log.d("SHUBH ",currentDateTime);

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
                                        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                                                thour = selectedHour;
                                                tmin = selectedMin;
                                                String time = thour + ":" + tmin;
                                                SimpleDateFormat f24hour = new SimpleDateFormat("HH:mm");
                                                try {
                                                    Date date = f24hour.parse(time);
                                                    SimpleDateFormat f12hour = new SimpleDateFormat("HH:mm aa");
                                                    Task_Time = f12hour.format(date);


                                                } catch (ParseException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),onTimeSetListener,thour,tmin,false);
                                        timePickerDialog.show();
                                    }
                                });

        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSettings = new TaskSettings();
                EditText taskname = view1.findViewById(R.id.Task_Name);
                EditText taskdesc = view1.findViewById(R.id.Task_Desc);
//                HomeActivity hm = new HomeActivity();
//                EditText taskname = view.findViewById(R.id.task_name);
                String tname,tdesc;

                if(date==null){
                    date = " ";
                }
                if(Task_Time==null){
                    Task_Time = " ";
                }
//                Log.d("TASKTIME",Task_Time);
                if(!TextUtils.isEmpty(taskname.getText().toString().trim())){
                    tname = taskname.getText().toString().trim();
                    if(!TextUtils.isEmpty(taskdesc.getText().toString().trim())){
                        tdesc = taskdesc.getText().toString().trim();
                        HomeActivity h = new HomeActivity();

                        if(!TextUtils.isEmpty(link_meet)){
                            if(!TextUtils.isEmpty(link_task)){
                                taskSettings.tlink = link_task;
                                writeNewUser(currentDateTime,tname,tdesc,""+date,""+Task_Time,""+link_meet,""+link_task,checkBox);
                            }
                            else{
                                writeNewUser(currentDateTime,tname,tdesc,""+date,""+Task_Time,""+link_meet,checkBox);
                            }
                        }
                        else{
                            if(!TextUtils.isEmpty(link_task)){
                                taskSettings.tlink = link_task;
                                mwriteNewUser(currentDateTime,tname,tdesc,""+date,""+Task_Time,""+link_task,checkBox);
                            }
                            else{
                                mtwriteNewUser(currentDateTime,tname,tdesc,""+date,""+Task_Time,checkBox);
                            }
                        }
                        Toast.makeText(getContext(),"Task Added Successfully",Toast.LENGTH_LONG).show();
                    }
                    else{
                        tdesc = taskdesc.getText().toString().trim();
                        if(!TextUtils.isEmpty(link_meet)){
                            if(!TextUtils.isEmpty(link_task)){
                                dwriteNewUser(currentDateTime,tname,""+date,""+Task_Time,""+link_meet,""+link_task,checkBox);
                            }
                            else{
                                dwriteNewUser(currentDateTime,tname,""+date,""+Task_Time,""+link_meet,checkBox);
                            }
                        }
                        else{
                            if(!TextUtils.isEmpty(link_task)){
                                mdwriteNewUser(currentDateTime,tname,""+date,""+Task_Time,""+link_task,checkBox);
                            }
                            else{
                                mtdwriteNewUser(currentDateTime,tname,""+date,""+Task_Time,checkBox);
                            }
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

//        dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialog);
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialog) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        BottomSheetDialog d = (BottomSheetDialog) dialog;
//                        FrameLayout bottomSheet = d.findViewById(R.id.design_bottom_sheet);
//                        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    }
//                },0);
//            }
//        });


        return view1;




    }

    private void startDialog() {
        Dialog_Link dialog_link = new Dialog_Link();
        dialog_link.show(getActivity().getSupportFragmentManager(), "Edit Task Details");
    }

    public void writeNewUser(String currentDateTime, String name, String taskdesc,String taskdate,String Time,boolean checkBox) {
//        HashMap<String ,String > usermap = new HashMap<>();
//        usermap.put("TaskName",name);
//        usermap.put("Description",taskdesc);
//        usermap.put("Date",taskdate);
//        usermap.put("Time",Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
    }
    public void writeNewUser(String currentDateTime, String name, String taskdesc,String taskdate,String Time,String meet,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("meet").setValue(meet);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);
    }
    public void startHomePage() {

        Intent logIntent = new Intent(getContext(), HomeActivity.class);
//        logIntent.putExtra("username", String.valueOf(userEdit));
//        logIntent.putExtra("password", String.valueOf(passEdit));
        startActivity(logIntent);

    }
    public void writeNewUser(String currentDateTime, String name, String taskdesc,String taskdate,String Time,String meet,String ltask,boolean checkBox){
//        HashMap<String ,String > usermap = new HashMap<>();
//        usermap.put("TaskName",name);
//        usermap.put("Description",taskdesc);
//        usermap.put("Date",taskdate);
//        usermap.put("Time",Time);
//        usermap.put("meetlink",meet);
//        usermap.put("tasklink",ltask);
//        mDatabase.child("users").child(userId.toString()).setValue(usermap);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("meet").setValue(meet);
        mDatabase.child("users").child(mail).child(currentDateTime).child("ltask").setValue(ltask);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);

    }
    public void mtwriteNewUser(String currentDateTime, String name, String taskdesc,String taskdate,String Time,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);
    }
    public void mwriteNewUser(String currentDateTime, String name, String taskdesc,String taskdate,String Time,String ltask,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("ltask").setValue(ltask);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);
    }
    public void mtdwriteNewUser(String currentDateTime, String name,String taskdate,String Time,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
//        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);
    }
    public void mdwriteNewUser(String currentDateTime, String name,String taskdate,String Time,String ltask,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
//        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("ltask").setValue(ltask);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);
    }
    public void dwriteNewUser(String currentDateTime, String name,String taskdate,String Time,String meet,String ltask,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
//        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("meet").setValue(meet);
        mDatabase.child("users").child(mail).child(currentDateTime).child("ltask").setValue(ltask);
        mDatabase.child("users").child(mail).child(currentDateTime).child("checkboxStatus").setValue(checkBox);
    }
    public void dwriteNewUser(String currentDateTime, String name,String taskdate,String Time,String meet,boolean checkBox){
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskName").setValue(name);
//        mDatabase.child("users").child(mail).child(userId.toString()).child("Description").setValue(taskdesc);
        mDatabase.child("users").child(mail).child(currentDateTime).child("TaskDate").setValue(taskdate);
        mDatabase.child("users").child(mail).child(currentDateTime).child("Time").setValue(Time);
        mDatabase.child("users").child(mail).child(currentDateTime).child("meet").setValue(meet);
    }



}