package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Details extends AppCompatActivity {
    ImageView back,image;
    TextView title,text,title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        back=findViewById(R.id.back);
        image=findViewById(R.id.image);
        title=findViewById(R.id.title);
        title_bar=findViewById(R.id.title_bar);
        text=findViewById(R.id.text);
        Intent intent=getIntent();
        text.setText(intent.getStringExtra("text"));
        title.setText(intent.getStringExtra("title"));
        title_bar.setText(intent.getStringExtra("title"));
        image.setImageResource(intent.getIntExtra("image",0));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Details.this,Home.class);
                startActivity(intent1);
            }
        });
    }
}