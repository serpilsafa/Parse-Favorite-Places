package com.safa.fourquareapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.safa.fourquareapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, nameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);

        emailEditText.setText("serpil@text.com");
        passwordEditText.setText("123456");
        nameEditText.setText("serpil");


        ParseUser user = ParseUser.getCurrentUser();
        if (user != null){
           getListActivity();
        }

    }

    public void onSignUp(View view) {
        if(!emailEditText.getText().toString().equals("") &&
                !passwordEditText.getText().toString().equals("") &&
                !nameEditText.getText().toString().equals("")){

                    final ParseUser user = new ParseUser();
                    user.setEmail(emailEditText.getText().toString());
                    user.setPassword(passwordEditText.getText().toString());
                    user.setUsername("serpil");
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null){
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Welcome "+ user.getUsername(),Toast.LENGTH_LONG).show();
                                getListActivity();
                            }
                        }
                    });
        }
    }

    public void onLogin(View view) {
        if(!emailEditText.getText().toString().equals("") &&
                !passwordEditText.getText().toString().equals("") &&
                !nameEditText.getText().toString().equals("")){

                    ParseUser.logInInBackground(emailEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e != null){
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Welcome "+ user.getUsername(),Toast.LENGTH_LONG).show();
                                getListActivity();
                            }
                        }
                    });
        }
    }



    private void getListActivity(){
        Intent intent = new Intent(this, ListPinActivity.class);
        startActivity(intent);
        finish();
    }


}
