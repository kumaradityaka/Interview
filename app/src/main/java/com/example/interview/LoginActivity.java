package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    EditText mobile_no;
    TextView newUser;
    TextView SignUp;
    ImageView loginImg;
    private final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FloatingActionButton verify;


        loginImg = findViewById(R.id.login_img);
        verify = findViewById(R.id.login_b);
        mobile_no = findViewById(R.id.mobile_no);
        newUser = findViewById(R.id.new_user);
        SignUp = findViewById(R.id.signup);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser(view);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });


        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails result) {
                Log.i(TAG, result.getUserState().toString());
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, e.toString());
            }
        });

    }

    private void showSignIn() {
        try {
            AWSMobileClient.getInstance().showSignIn(this,
                    SignInUIOptions.builder().nextActivity(OtpActivity.class).build());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    public boolean validNumber(){
        String mn = mobile_no.getText().toString().trim();
        //String checkspaces = "Aw{1,20}z";
        if (mn.isEmpty()) {
            mobile_no.setError("Enter valid phone number");
            return false;
        } else if (mn.length()!=10) {
            mobile_no.setError("Enter valid phone number");
            return false;
        } else {
            mobile_no.setError(null);
            return true;
        }
    }
    public void loginuser(View view){
        if(!validNumber())
            return;

        String ph = mobile_no.getText().toString();

        Intent intent = new Intent(LoginActivity.this,OtpActivity.class);
        intent.putExtra("ph",ph);
        startActivity(intent);
    }
}