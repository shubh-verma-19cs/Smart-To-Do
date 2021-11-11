package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawer ;


    TextView textNotifItemCount;
    MenuItem settingsButton;
    int mNotifItemCount = 10;

    ImageView listSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listSettings = findViewById(R.id.list_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);

        settingsButton = findViewById(R.id.settings);
        try {
            settingsButton.setOnMenuItemClickListener(menuItem -> {
                startSettingsActivity();
                return true;
            });
        } catch (NullPointerException e){

        }

        listSettings.setOnClickListener(new View.OnClickListener() {
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