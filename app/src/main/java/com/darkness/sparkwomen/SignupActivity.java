package com.darkness.sparkwomen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.darkness.sparkwomen.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password= binding.signupPassword.getText().toString();
                String address = binding.signupAddress.getText().toString();
                String username = binding.signupUsername.getText().toString();
                String name = binding.signupName.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(SignupActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUserEmail =databaseHelper.checkEmail(email);
                    if(checkUserEmail==false){
                        Boolean insert =databaseHelper.insertData(email,password);
                        if(insert == true){
                            Toast.makeText(SignupActivity.this, "Signup Successful !!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Failed to Signup", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "User already exists, please login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
