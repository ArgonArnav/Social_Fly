package com.example.arnavanand.instagram_clone_app;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName ,edtProfileBio ,edtProfileProfession ,edtProfileHobbies
            , edtProfileFavSport;
    private Button btnUpdateInfo ;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("ProfileName") == null) {
            edtProfileName.setText("");
        }   else {
            edtProfileName.setText(parseUser.get("ProfileName").toString());
        }

        if(parseUser.get("ProfileBio") == null) {
            edtProfileBio.setText("");
        }   else {
            edtProfileBio.setText(parseUser.get("ProfileBio").toString());
        }

        if(parseUser.get("ProfileProfession") == null) {
            edtProfileProfession.setText("");
        }   else {
            edtProfileProfession.setText(parseUser.get("ProfileProfession").toString());
        }

        if(parseUser.get("ProfileHobbies") == null) {
            edtProfileHobbies.setText("");
        }   else {
            edtProfileHobbies.setText(parseUser.get("ProfileHobbies").toString());
        }

        if(parseUser.get("ProfileFavSport") == null) {
            edtProfileFavSport.setText("");
        }   else {
            edtProfileFavSport.setText(parseUser.get("ProfileFavSport").toString());
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("ProfileName", edtProfileName.getText().toString());
                parseUser.put("ProfileBio", edtProfileBio.getText().toString());
                parseUser.put("ProfileProfession", edtProfileProfession.getText().toString());
                parseUser.put("ProfileHobbies", edtProfileHobbies.getText().toString());
                parseUser.put("ProfileFavSport", edtProfileFavSport.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating New Info");
                progressDialog.show();

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                        {
                            FancyToast.makeText(getContext(),"Info Updated",
                                    Toast.LENGTH_SHORT, FancyToast.INFO, true).show();

                        } else {
                            FancyToast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }
                        progressDialog.dismiss();
                    }
                });

            }
        });

        return view;
    }

}
