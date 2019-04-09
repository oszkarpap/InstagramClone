package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.net.PasswordAuthentication;
import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtpassword;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtUsername = findViewById(R.id.edtEnterUsername);
        edtpassword = findViewById(R.id.edtEnterPasword);
        edtpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }

                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogin);

        setTitle("Sign Up");
        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {
            //ParseUser.getCurrentUser().logOut();
            transitiontoSocialMediaActivity();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:

                if (edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") || edtpassword.getText().toString().equals("")) {
                    Toast.makeText(this, "Email, Username, Password is required!", Toast.LENGTH_SHORT).show();

                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtpassword.getText().toString());


                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUp.this, appUser.get("username") + " is signed up successfully", Toast.LENGTH_SHORT).show();
                                    transitiontoSocialMediaActivity();
                            } else {
                                Toast.makeText(SignUp.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
        }


    }


    public void rootLayoutTapped(View view) {

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void transitiontoSocialMediaActivity(){
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }

}

