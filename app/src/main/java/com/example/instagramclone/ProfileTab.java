package com.example.instagramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtprofileBio, edtProfileProfession, edtprofileHobbies, edtprofileFavSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.et01);
        edtprofileBio = view.findViewById(R.id.et02);
        edtProfileProfession = view.findViewById(R.id.et03);
        edtprofileHobbies = view.findViewById(R.id.et04);
        edtprofileFavSport = view.findViewById(R.id.et05);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null) {
            edtProfileName.setText("");
            edtprofileBio.setText("");
            edtProfileProfession.setText("");
            edtprofileHobbies.setText("");
            edtprofileFavSport.setText("");
        } else {
            edtProfileName.setText(parseUser.get("profileName") + "");
            edtprofileBio.setText(parseUser.get("profileBio") + "");
            edtProfileProfession.setText(parseUser.get("profileProfession") + "");
            edtprofileHobbies.setText(parseUser.get("profileHobbies") + "");
            edtprofileFavSport.setText(parseUser.get("profilefavSport") + "");
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtprofileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies", edtprofileHobbies.getText().toString());
                parseUser.put("profilefavSport", edtprofileFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getContext(), "Info Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), e.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        return view;
    }

}
