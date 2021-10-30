package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private static final int SPLASH_SCREEN_TIME_OUT=3000;


    TextView textNotifItemCount;
    int mNotifItemCount = 10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            this.getSupportActionBar().hide();
//        }
//        catch(NullPointerException e){
//
//        }
        ConstraintLayout constraintLayout = findViewById(R.id.root_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(100);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this,
                        LogInActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);


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