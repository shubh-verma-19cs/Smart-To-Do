package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawer ;
    private NavigationView navigationView;

//    FirebaseAuth mAuth ;
//    SharedPreferences sp;

    TextView textNotifItemCount;
//    MenuItem settingsButton;

    int mNotifItemCount = 10;

//    ImageView listSettings;

    FloatingActionButton floatadd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        floatadd = findViewById(R.id.floatingadd);

        Toolbar toolbar = findViewById(R.id.toolbar);

//        mAuth = FirebaseAuth.getInstance();
//        sp = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);

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
                    case R.id.team1:

                        break;
                    case R.id.team2:
                        break;
                    case R.id.nav_new_team:
                        break;
                    case R.id.settings:
                        Intent i1 = new Intent(HomeActivity.this, PreferencesActivity.class);
                        startActivity(i1);
                        break;

                    case R.id.logout:
//
//                        mAuth.signOut();
//
//                        SharedPreferences.Editor editor = sp.edit();
//                        
//                        editor.putInt("key", 0);
//                        editor.apply();
//
//                        Intent logIntent = new Intent(getApplicationContext(), LogInActivity.class);
//                        startActivity(logIntent);

//                        startLogoutActivity();
//                        FirebaseAuth.getInstance().signOut();

                        break;



                }

                return false;
            }
        });


//

//        settingsButton = findViewById(R.id.settings);
//        try {
//            settingsButton.setOnMenuItemClickListener(menuItem -> {
//                startSettingsActivity();
//                return true;
//            });
//        } catch (NullPointerException e){
//
//        }

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

//    public void startLogoutActivity() {
//        new AlertDialog.Builder(this).setIcon(R.drawable.ic_baseline_info_24)
//                .setTitle("Logout").setMessage("Are you sure you wish to logout?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putInt("key", 0);
//                editor.apply();
//
//                Intent logIntent = new Intent(getApplicationContext(), LogInActivity.class);
//                startActivity(logIntent);
//            }
//
//        }).setNegativeButton("No", null).show();
//    }


//    private void setNavigationViewListener() {
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.settings:
//                Toast.makeText(HomeActivity.this, "Is this working", Toast.LENGTH_SHORT).show();
//                Intent settingsIntent = new Intent(HomeActivity.this, PreferencesActivity.class);
//                startActivity(settingsIntent);
//                break;
//        }
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

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
//            super.onBackPressed();
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