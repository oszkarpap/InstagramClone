package com.example.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnGetAllData, btnTransition;
    private EditText edtName, edtPS, edtPP, edtKS, edtKP;
    private TextView textView;
    private String allKickBoxers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.button);
        btnSave.setOnClickListener(SignUp.this);
        btnGetAllData = findViewById(R.id.btngetAllData);
        edtName = findViewById(R.id.editName);
        edtPS = findViewById(R.id.editPS);
        edtPP = findViewById(R.id.editPP);
        edtKS = findViewById(R.id.editKS);
        edtKP = findViewById(R.id.editKP);

        textView = findViewById(R.id.txt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("PObAnB1g26", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e == null){
                            textView.setText(object.get("name")+" - "+object.get("punchPower") );
                        }
                    }
                });
            }
        });


        btnGetAllData.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.whereGreaterThan("punchPower", 100);
                queryAll.setLimit(1);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if(objects.size() > 0){
                                for (ParseObject kickBoxer : objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";

                                }


                                Toast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUp.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        btnTransition = findViewById(R.id.btnTransition);
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);
            }
        });
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

        } catch (Exception e) {
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

        }
    }
}
