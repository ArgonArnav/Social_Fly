package com.example.arnavanand.instagram_clone_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail ,edtLoginPassword;
    private Button btnSignUpLoginActivity ,btnLoginActivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        btnSignUpLoginActivity.setOnClickListener(this);
        btnLoginActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null) {

            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnLoginActivity :
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(),
                        new LogInCallback()
                        {
                            @Override
                            public void done(ParseUser user, ParseException e)
                            {

                                if(user != null && e == null)
                                {
                                    FancyToast.makeText(LoginActivity.this, user.getUsername() +
                                            " is Logged in successfully" ,Toast.LENGTH_LONG ,FancyToast.SUCCESS
                                            ,true).show();

                                    transitionToSocialMediaActivity();
                                }
                            }
                        });

                if(edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")) {

                    FancyToast.makeText(LoginActivity.this, "Email and Password required ",
                            Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                }
                break;

            case R.id.btnSignUpLoginActivity :

                Intent intent = new Intent(LoginActivity.this, SignUP.class);
                startActivity(intent);

                break;
        }
    }

    public void LayoutTapped(View view)
    {

        try
        {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e)
        {

             e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity ()
    {

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}