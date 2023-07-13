package com.darkness.sparkwomen;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.darkness.sparkwomen.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper= new DatabaseHelper(this);

        sp=getSharedPreferences("Data",MODE_PRIVATE);
        editor=sp.edit();
        boolean login=sp.getBoolean("ISLOGGEDIN",false);
        if(login==true){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        binding.loginButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String email= binding.loginEmail.getText().toString();
                 String password= binding.loginPassword.getText().toString();

                 if(email.equals("") || password.equals("")){
                     Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Boolean checkCredentials = databaseHelper.checkEmailPassword(email,password);

                     if(checkCredentials==true){
                         if(binding.checkBox.isChecked()){
                             editor.putString("email",email);
                             editor.putString("password",password);
                             editor.putBoolean("ISLOGGEDIN",true);
                             editor.apply();
                             Toast.makeText(LoginActivity.this, "Login Successful !!!", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
                             startActivity(intent);
                         }else {
                             Toast.makeText(LoginActivity.this, "Login Successful !!!", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
                             startActivity(intent);
                         }
                     }
                     else{
                         Toast.makeText(LoginActivity.this, "Login Failed !!!", Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });
        binding.signUpRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
