package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class TicTacToeActivity extends AppCompatActivity {
  Button btn1,btn2,btn3 ,btn4, btn5,btn6,btn7,btn8,btn9,restartBtn;
  String b1,b2,b3,b4,b5,b6,b7,b8,b9;
  TextView winnerText;
  Boolean flag = true;
  int moves = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        init();
        Intent fromActivity = getIntent();
        //by accessing string values of first screen we pass only key and here default value is by default empty string no need to explicitly mention
        String title = fromActivity.getStringExtra("Title");
        //by accessing integer values of first screen we pass key and default value
        int players = fromActivity.getIntExtra("Players",0);
        getSupportActionBar().setTitle(title +" "+players+" Players can play");
    }
    public  void init(){
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        restartBtn = findViewById(R.id.restart_btn);
        winnerText = findViewById(R.id.winner_text);
    }
    public void handleClick(View view){
     Button curBtn = (Button)view;
     //allow moves only when player not take any move yet
     if(curBtn.getText().toString().equals("")){
         moves++;
         if(flag){
             curBtn.setText("X");
             flag = false;
         }else{
             curBtn.setText("O");
             flag = true;
         }
         boolean isWinnerFound = false;
         if(moves>4){
             b1 = btn1.getText().toString();
             b2 = btn2.getText().toString();
             b3 = btn3.getText().toString();
             b4 = btn4.getText().toString();
             b5 = btn5.getText().toString();
             b6 = btn6.getText().toString();
             b7 = btn7.getText().toString();
             b8 = btn8.getText().toString();
             b9 = btn9.getText().toString();

             //wining conditions
             String winner = "";
             if(b1.equals(b2) && b2.equals(b3) && !b1.equals("")){
                 isWinnerFound = true;
                // Toast.makeText(this,"winner is: "+b1,Toast.LENGTH_LONG).show();
                 winner = b1.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
             }else if(b1.equals(b4) && b4.equals(b7) && !b1.equals("")){
                 isWinnerFound = true;
                 winner = b1.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
             } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                 isWinnerFound = true;
                 winner = b3.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
             } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                 isWinnerFound = true;
                 winner = b1.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);

             }else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                 isWinnerFound = true;
                 winner = b3.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);

             }else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                 isWinnerFound = true;
                 winner = b4.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);

             }else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                 isWinnerFound = true;
                 winner = b7.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);

             }else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                 isWinnerFound = true;
                 winner = b2.equals("X")?"1":"2";
                 winnerText.setText("    \uD83C\uDF8A Winner is Player "+winner+  "\uD83C\uDF8A \n \uD83C\uDF81Now Player "+ winner+" will start first");
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
             }
             if(moves == 9 && !isWinnerFound){
                 Toast.makeText(this,"Oops! no one is winner, It's a draw",Toast.LENGTH_LONG).show();
                 clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
             }
         }
      }
     restartBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             clearDataAndRestart(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
         }
     });
    }
    public void clearDataAndRestart(Button v,Button v2,Button v3, Button v4,Button v5,Button v6,Button v7,Button v8,Button v9){

        //clearing after a short delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Your function or code here
                v.setText("");
                v2.setText("");
                v3.setText("");
                v4.setText("");
                v5.setText("");
                v6.setText("");
                v7.setText("");
                v8.setText("");
                v9.setText("");
                moves = 0;
                flag = !flag;
                winnerText.setText("");
            }
        }, 5000); // Delay in milliseconds (1000 ms = 1 seconds)
      }
}