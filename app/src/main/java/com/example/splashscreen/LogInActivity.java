package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity {

    EditText userEdit, passEdit;
    TextView registerView, forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        try {
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){

        }

        userEdit = (EditText) findViewById(R.id.username);
        passEdit = (EditText) findViewById(R.id.password);
        registerView = (TextView) findViewById(R.id.register_text);
        Button loginButton = (Button) findViewById(R.id.loginbtn);
        forgetPass = (TextView) findViewById(R.id.forgotpass);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userEdit.getText().toString().equals("admin") &&
                   passEdit.getText().toString().equals("Admin")){
                        startHomePage();
                }
            }

            
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForgetPage();
            }
        });

        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegisterPage();
            }
        });
        
        
        
    }

    private void startForgetPage() {
        Intent forgetIntent = new Intent(LogInActivity.this, ForgotPassword.class);
        startActivity(forgetIntent);
    }

    public void startRegisterPage() {
        Intent regIntent = new Intent(LogInActivity.this, RegisterActivity.class);
        startActivity(regIntent);
    }

    public void startHomePage() {

        Intent logIntent = new Intent(LogInActivity.this, HomeActivity.class);
        logIntent.putExtra("username", String.valueOf(userEdit));
        logIntent.putExtra("password", String.valueOf(passEdit));
        startActivity(logIntent);

    }
}