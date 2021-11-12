package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    EditText regUser,regMail,regPass;
    Button regBtn;
    TextView registerView;
    CheckBox checkBox;

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
        checkBox= (CheckBox) findViewById(R.id.chck1);
        FirebaseAuth mAuth;
        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
//        if(mAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }
        regBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email = regMail.getText().toString().trim();
                String username = regUser.getText().toString().trim();
                String password = regPass.getText().toString().trim();
                //trim -> removes extra spaces
                if(TextUtils.isEmpty(email)){
                    regMail.setError("Email is required");
                }
                if(TextUtils.isEmpty(username)){
                    regUser.setError("Username is required");
                }
                if(TextUtils.isEmpty(password)){
                    regPass.setError("Password is required");
                }
                if(password.length()<6){
                    regPass.setError("Password length should be greater than 6 characters !!");
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Error !!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startLoginPage();
            }
        });



        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    regPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    regPass.setTransformationMethod((PasswordTransformationMethod.getInstance()));
                }
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