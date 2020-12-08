package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login_activity extends AppCompatActivity {
    TextView register;
    Button login;
    EditText user,pass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_activity.this, Register.class);
                startActivity(intent);
            }
        });
        login=findViewById(R.id.login);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getText().toString().trim();
                String password= pass.getText().toString().trim();
                if(email.isEmpty()){
                    user.setError("Vui lòng nhập tên đăng nhập");
                    user.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    user.setError("Vui lòng nhập đúng định dạng Email");
                    user.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    pass.setError("Vui lòng nhập mật khẩu");
                    pass.requestFocus();
                    return;
                }
                if(password.length()<6){
                    pass.setError("Vui lòng nhập mật khẩu nhiều hơn 6 ký tự");
                    pass.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()){
                                Intent intent=new Intent(Login_activity.this,Home.class);
                                startActivities(new Intent[]{intent});
                            }
                            else {
                                user.sendEmailVerification();
                                Toast.makeText(Login_activity.this,"Check Your Email To Verification",Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            Toast.makeText(Login_activity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}

