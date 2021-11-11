package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList optionImg = new ArrayList<>(Arrays.asList(R.drawable.notif, R.drawable.theme, R.drawable.about, R.drawable.blank));
    ArrayList optionName = new ArrayList<>(Arrays.asList("Notifications", "Theme", "About", "Version"));
    ArrayList optionDesc = new ArrayList<>(Arrays.asList("Set Notification preferences", "Set App theme", "About the app", "0.1"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        Adapter adapter = new Adapter(PreferencesActivity.this, optionImg, optionName, optionDesc);
        recyclerView.setAdapter(adapter);

    }
}