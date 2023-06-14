package com.example.mobappproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signUpButton, signInButton;
    DBHelper DB;

    SharedPreferences sharedPreferences;

    public static final String filename = "validation";
    public static final String uName = "username";
    public static final String pWord = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signUpButton = findViewById(R.id.signUpButton);
        signInButton = findViewById(R.id.loginButton);
        DB = new DBHelper(this);

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);

                        if(checkuser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert == true){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(uName, user);
                                editor.putString(pWord, pass);
                                editor.commit();
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords Do Not Match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}