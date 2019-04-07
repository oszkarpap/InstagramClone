package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp, edtUserNameLogIn, edtPasswordSignUp, getEdtPasswordLogIn;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login);

        edtUserNameLogIn = findViewById(R.id.edtLoginUserName);
        edtUserNameSignUp = findViewById(R.id.edtUserName);
        getEdtPasswordLogIn = findViewById(R.id.edtLoginPassword);
        edtPasswordSignUp = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLoginUser);
        btnSignUp = findViewById(R.id.btnSignUpUser);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null ){
                            Toast.makeText(SignUpLoginActivity.this, appUser.get("username")+" is signed up successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogIn.getText().toString(), getEdtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e== null){
                            Toast.makeText(SignUpLoginActivity.this, user.get("username")+" is logged in successfully", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        }else
                        {
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
