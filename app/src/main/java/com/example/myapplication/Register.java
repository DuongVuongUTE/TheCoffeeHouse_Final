package com.example.myapplication;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText name,age,gmail,phone,password;
    private Button register;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        phone=findViewById(R.id.phone);
        gmail=findViewById(R.id.gmail);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        progressBar= findViewById(R.id.progressBar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= gmail.getText().toString().trim();
                String pass= password.getText().toString().trim();
                String fullname=name.getText().toString().trim();
                String ages=age.getText().toString().trim();
                String phones=phone.getText().toString().trim();

                if (fullname.isEmpty()){
                    name.setError("Không được để trống");
                    name.requestFocus();
                    return;
                }
                if (ages.isEmpty()){
                    age.setError("Không được để trống");
                    age.requestFocus();
                    return;
                }
                if (phones.isEmpty()){
                    phone.setError("Không được để trống");
                    phone.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    gmail.setError("Không được để trống");
                    gmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    gmail.setError("Vui lòng nhập đúng định dạng Email");
                    gmail.requestFocus();
                    return;
                }
                if (pass.isEmpty()){
                    password.setError("Không được để trống");
                    password.requestFocus();
                    return;
                }
                if (pass.length()<6){
                    password.setError("Mật khẩu phải lớn hơn 6 ký tự!");
                    password.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user=new User(fullname,ages,phones,email);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this,"Đăng ký thành công !",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }
                                    else {
                                        Toast.makeText(Register.this,"Đăng ký không thành công !",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                        else {
                           Toast.makeText(Register.this,"Đăng ký không thành công !",Toast.LENGTH_LONG).show();
                           progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        }
    }