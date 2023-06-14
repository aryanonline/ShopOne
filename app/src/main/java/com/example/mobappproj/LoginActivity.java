package com.example.mobappproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signInButton1, signUpBtn;
    DBHelper DB;

    SharedPreferences sharedPreferences;

    public static final String filename = "validation";
    public static final String uName = "username";
    public static final String pWord = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signInButton1 = findViewById(R.id.signInButton1);
        signUpBtn = findViewById(R.id.signUpBtn);

        DB = new DBHelper(this);

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(uName)){
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }
//        else{
//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(i);
//        }

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        signInButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();
                else{
                    Boolean checkUserNamePassword = DB.checkUsernamePassword(user, pass);
                    if(checkUserNamePassword == true){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(uName, user);
                        editor.putString(pWord, pass);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}