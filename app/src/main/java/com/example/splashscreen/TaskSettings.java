package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskSettings extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList taskSettingImg = new ArrayList<>(Arrays.asList(
       R.drawable.task, R.drawable.subtask, R.drawable.calendar,
       R.drawable.clock, R.drawable.repeat, R.drawable.priority,
       R.drawable.link, R.drawable.location
    ));
    ArrayList taskSettingName = new ArrayList<>(Arrays.asList("Tasks",
            "Sub-Tasks", "Due Date", "Time", "Repeat", "Priority", "Add meeting link", "Add location"
    ));
    ArrayList taskSettingDesc = new ArrayList<>(Arrays.asList("Tap to add a task", "Tap to add a subtask", "Set due date", "Set time", "Select days to repeat the reminder", "Set task priority", "Set meeting link", "Add your task location"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_settings);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        TaskSettingsAdapter taskSettingsAdapter = new TaskSettingsAdapter(TaskSettings.this, taskSettingImg, taskSettingName, taskSettingDesc);
        recyclerView.setAdapter(taskSettingsAdapter);
    }
}