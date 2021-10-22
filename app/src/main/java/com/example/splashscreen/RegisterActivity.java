package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends AppCompatActivity {
    EditText regUser,regMail,regPass;
    Button regBtn;
    TextView registerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        try {
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){

        }
        regUser = (EditText) findViewById(R.id.username_register);
        regMail = (EditText) findViewById(R.id.email_register);
        regPass = (EditText) findViewById(R.id.password_register);
        regBtn = (Button) findViewById(R.id.btn_register);
        registerView = (TextView) findViewById(R.id.login_text);

        regBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startLoginPage();
            }
        });



        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });



    }
    public void startLoginPage(){
        Intent regIntent = new Intent(RegisterActivity.this,LogInActivity.class);
        regIntent.putExtra("Reg_Username",String.valueOf(regUser));
        regIntent.putExtra("Reg_mail",String.valueOf(regMail));
        regIntent.putExtra("Reg_Password",String.valueOf(regPass));
        startActivity(regIntent);
    }
    public void startLogin(){
        Intent regIntent = new Intent(RegisterActivity.this,LogInActivity.class);
        startActivity(regIntent);
    }
}