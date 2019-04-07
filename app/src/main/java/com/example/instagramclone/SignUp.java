package com.example.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText edtName, edtPS, edtPP, edtKS, edtKP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.button);
        btnSave.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.editName);
        edtPS = findViewById(R.id.editPS);
        edtPP = findViewById(R.id.editPP);
        edtKS = findViewById(R.id.editKS);
        edtKP = findViewById(R.id.editKP);



    }


    @Override
    public void onClick(View v) {

        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPS.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(edtPP.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKS.getText().toString()));
            kickBoxer.put("kickpower", Integer.parseInt(edtKP.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is save to server", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    } else {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }catch (Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

        }
    }
}
