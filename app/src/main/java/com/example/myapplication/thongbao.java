package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class thongbao extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    Button tieptuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        back=findViewById(R.id.back);
        tieptuc=findViewById(R.id.tieptuc);
        back.setOnClickListener(this);
        tieptuc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==back){
            Intent back=new Intent(thongbao.this,Home.class);
            startActivity(back);
        }
        if (v==tieptuc){
            Intent tieptuc=new Intent(thongbao.this,Home.class);
            startActivity(tieptuc);
        }
    }
}