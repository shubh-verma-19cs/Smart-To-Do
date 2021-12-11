package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Model> list;
    static ArrayList<String> Task_Id;
    UUID userId;
    String currentDate;

    SimpleDateFormat sdf;
    String currentDateandTime;
    BottomSheet b = new BottomSheet();
    private DrawerLayout drawer ;
    private NavigationView navigationView;

    TextView textNotifItemCount;
    int mNotifItemCount = 10;
    FloatingActionButton floatadd;
    FloatingActionButton bottomsheet;




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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        currentDateandTime = sdf.format(new Date());


        floatadd = findViewById(R.id.floatingadd);

        Toolbar toolbar = findViewById(R.id.toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_profile:
                        Toast.makeText(HomeActivity.this, "Working", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(HomeActivity.this, PreferencesActivity.class);
                        startActivity(i);
                        break;
//                    case R.id.team1:
//
//                        break;
//                    case R.id.team2:
//                        break;
//                    case R.id.nav_new_team:
//                        break;
                    case R.id.settings:
                        Intent i1 = new Intent(HomeActivity.this, PreferencesActivity.class);
                        startActivity(i1);
                        break;

                    case R.id.logout:

                        FirebaseAuth.getInstance().signOut();
                        Intent logIntent = new Intent(HomeActivity.this, LogInActivity.class);
                        startActivity(logIntent);
                        Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                        break;



                }

                return false;
            }



        });

        fa = FirebaseAuth.getInstance();
        curr_user = fa.getCurrentUser();
        mail = curr_user.getUid();
        Task_Id = new ArrayList<>();

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
//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // do whatever
////                        startListSettings();
//                        startTaskActivity();
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        // do whatever
////                        Delete_task();
//                    }
//                })
//        );

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Model>();
        myAdapter = new MyAdapter(HomeActivity.this,list);
        recyclerView.setAdapter(myAdapter);
        EventChangeListner();


        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startListSettings();
            }
        });

        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();





    }

    private void EventChangeListner() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                Task_Id.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Log.d("Nnn", ""+dataSnapshot.getKey());
                    Task_Id.add(dataSnapshot.getKey());
                    Model model=  dataSnapshot.getValue(Model.class);
                    list.add(0,model);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        for (int i=0;i<list.size();i++){
//            Log.d("FFF",list.get(i).TaskName);
//        }
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
//    public void writeNewUser(String currentDateandTime, String name, String taskdesc) {
////        user newuser = new user();
//        mDatabase.child("users").child(mail).child(currentDateandTime).child("taskname").setValue(name);
//
//    }

    public void startTaskActivity() {
        Intent settingsIntent = new Intent(HomeActivity.this, TaskSettings.class);
        startActivity(settingsIntent);
    }

    public void startSettingsActivity() {
        Intent settingsIntent = new Intent(HomeActivity.this, PreferencesActivity.class);
        startActivity(settingsIntent);
    }

    public void startListSettings() {
        Intent listIntent = new Intent(HomeActivity.this, ListSettings.class);
        startActivity(listIntent);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
              this.finishAffinity();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_notif);

        View actionView = menuItem.getActionView();
        textNotifItemCount = (TextView) actionView.findViewById(R.id.notif_icon);

        setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_notif: {

//                Notification activity;
                Intent notifAct = new Intent(HomeActivity.this, Notifications.class);
                startActivity(notifAct);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {
        if(textNotifItemCount != null){
            if (mNotifItemCount == 0){
                if (textNotifItemCount.getVisibility() != View.GONE){
                    textNotifItemCount.setVisibility(View.GONE);
                }
            } else{
                textNotifItemCount.setText(String.valueOf(Math.min(mNotifItemCount, 99)));
                if (textNotifItemCount.getVisibility() != View.VISIBLE){
                    textNotifItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}