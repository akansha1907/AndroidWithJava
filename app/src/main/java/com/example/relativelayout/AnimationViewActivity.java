package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AnimationViewActivity extends AppCompatActivity {
    TextView textView;
    Button transBtn,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_view);
        textView = findViewById(R.id.text_anim);
        transBtn = findViewById(R.id.trans_btn);
        btn2 = findViewById(R.id.alpha_btn);
        btn3 = findViewById(R.id.rotation_btn);
        btn4 = findViewById(R.id.scale_btn);
        Animation move = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);

        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);

        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);

        transBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.startAnimation(move);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.startAnimation(alpha);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.startAnimation(rotate);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.startAnimation(scale);
            }
        });
    }
}