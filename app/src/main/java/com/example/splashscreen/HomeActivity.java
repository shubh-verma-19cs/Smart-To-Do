package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

//    private DrawerLayout drawer ;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Model> list;
    UUID userId;
    String currentDate;

    BottomSheet b = new BottomSheet();
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,date);
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        b.date = currentDate;
    }
    DatabaseReference mDatabase;
    FirebaseDatabase db;
    FirebaseAuth fa;
    FirebaseUser curr_user;
    ImageView listSettings;
    ImageView calendar_date;
    String mail;

//    Button Cld_btn;
    FloatingActionButton bottomsheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fa = FirebaseAuth.getInstance();
        curr_user = fa.getCurrentUser();
        mail = curr_user.getUid();

        userId = (UUID) UUID.randomUUID();
//        recyclerView = findViewById(R.id.tasks);
        bottomsheet = findViewById(R.id.list_settings);
        recyclerView = findViewById(R.id.recycler_tasks);
        mDatabase = FirebaseDatabase.getInstance().getReference("users/"+mail);
        calendar_date = findViewById(R.id.task_date);

        bottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialog();
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"Tag");
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Model>();
        myAdapter = new MyAdapter(HomeActivity.this,list);
        recyclerView.setAdapter(myAdapter);
        EventChangeListner();

//        listSettings = findViewById(R.id.list_settings);

//        Toolbar toolbar = findViewById(R.id.toolbar);

//        listSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startListSettings();
//            }
//        });

//        setSupportActionBar(toolbar);
//        drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
    }

    private void EventChangeListner() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Log.d("Nnn", ""+dataSnapshot.getValue());

                    Model model=  dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for (int i=0;i<list.size();i++){
            Log.d("FFF",list.get(i).TaskName);
        }
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    private void showDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
    public void writeNewUser(UUID userId, String name, String taskdesc) {
//        user newuser = new user();
        mDatabase.child("users").child(mail).child(userId.toString()).child("taskname").setValue(name);

    }

//    public void startListSettings() {
//        Intent listIntent = new Intent(HomeActivity.this, ListSettings.class);
//        startActivity(listIntent);
//    }

//    @Override
//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
}
