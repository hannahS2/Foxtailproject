package com.example.hannah.foxtailproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private EditText email;
    private EditText password;
    private Button login;
    private Button signup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layout = (RelativeLayout) findViewById(R.id.login_layout);
        email = (EditText) findViewById(R.id.email_edit);
        password = (EditText) findViewById(R.id.password_edit);
        login = (Button) findViewById(R.id.login_button);
        signup = (Button) findViewById(R.id.signup_button);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);




    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        login.setEnabled(false);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        login.setEnabled(true);
    }

}
