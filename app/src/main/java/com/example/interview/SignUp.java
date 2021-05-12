package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {
    TextInputEditText username;
    TextInputEditText mobile;
    ImageButton back_login;
    FloatingActionButton verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.un);
        mobile = findViewById(R.id.mbp);
        back_login = findViewById(R.id.back_login);
        verify = findViewById(R.id.si_verify);
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signuser(view);
            }
        });
    }
    public boolean validNumber(){
        String mn = mobile.getText().toString().trim();
        //String checkspaces = "Aw{1,20}z";
        if (mn.isEmpty()) {
            mobile.setError("Enter valid phone number");
            return false;
        } else if (mn.length()!=10) {
            mobile.setError("Enter valid phone number");
            return false;
        } else {
            mobile.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String val = username.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No White spaces are allowed!");
            return false;
        } else {
            username.setError(null);
            //username.setErrorEnabled(false);
            return true;
        }
    }
    public void signuser(View view){
        if(!validNumber() && !validateUsername())
            return;

        String ph = mobile.getText().toString();

        Intent intent = new Intent(SignUp.this,MainActivity.class);
        intent.putExtra("ph",ph);
        startActivity(intent);
    }
}