package com.example.relativelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String CHANNEL_ID = "MESSAGE_CHANNEL";
    private static final int NOTIFICATION_ID = 178;
    private  static final int ALARM_SERVICE_CODE = 10;
    EditText et1,et2,et3,alarmInput;
    Button btn,gameBtn,animationBtn,lottieAnimation,cardBtn,customBtn,callBtn,msgBtn,shareBtn;
    Button gmailBtn,gotToFragment,tabsButton,bottomTabsBtn,drawerBtn,mapsBtn,btnFastNetwork,backgroundServiceBtn;
    Button backgroundServiceBtnStop,alarmButton;
    TextView txt,tvs,tvs2,tvs3;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.weight);
        et2 = findViewById(R.id.height_inch);
        et3 = findViewById(R.id.height_feet);
        btn = findViewById(R.id.calBtn);
        txt = findViewById(R.id.my_bmi);
        gameBtn = findViewById(R.id.game_btn);
        animationBtn = findViewById(R.id.anim_btn);
        lottieAnimation = findViewById(R.id.lottie_btn);
        cardBtn = findViewById(R.id.card_btn);
        toolbar= findViewById(R.id.toolbar);
        customBtn = findViewById(R.id.custom_btn);
        callBtn = findViewById(R.id.btn_call);
        gmailBtn = findViewById(R.id.btn_gmail);
        msgBtn = findViewById(R.id.btn_message);
        shareBtn = findViewById(R.id.btn_share);
        gotToFragment = findViewById(R.id.go_to_fragment);
        tabsButton = findViewById(R.id.tabs_btn);
        bottomTabsBtn = findViewById(R.id.bottom_tabs_btn);
        drawerBtn = findViewById(R.id.drawer_btn);
        mapsBtn = findViewById(R.id.btn_google_maps);
        btnFastNetwork = findViewById(R.id.btn_fast_network);
        tvs = findViewById(R.id.sensor_values);
        tvs2 = findViewById(R.id.sensor_values2);
        tvs3 = findViewById(R.id.sensor_values3);
        backgroundServiceBtn = findViewById(R.id.background_service_btn);
        backgroundServiceBtnStop = findViewById(R.id.background_service_btn_stop);
        alarmButton = findViewById(R.id.set_alarm);
        alarmInput = findViewById(R.id.alarm_input);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){          //to avoid null pointer exception
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Android Home");
        }
        toolbar.setTitle("Android Home"); //due to low priority this may unable to set toolbar title
        toolbar.setSubtitle("Here are all buttons to go some screens");

        //Sensor implementation
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(sensorManager != null){

            //accelerometer sensor
            Sensor accelerometerSen = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(accelerometerSen != null){
                sensorManager.registerListener(this,accelerometerSen,SensorManager.SENSOR_DELAY_NORMAL);
            }

            //accelerometer sensor
            Sensor proximitySen = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(proximitySen != null){
                sensorManager.registerListener(this,proximitySen,SensorManager.SENSOR_DELAY_NORMAL);
            }

            //light sensor
            Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if(lightSensor != null){
                sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else{
            Toast.makeText(this, "Sensor service not detected in device", Toast.LENGTH_SHORT).show();
        }

            
        //Notification implementation
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.akanksha,null);
        BitmapDrawable bd = (BitmapDrawable)drawable;
        Bitmap largeIcon = bd.getBitmap();
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(this).setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                                    .setContentText("Hey! Himanshu You will be always in my heart and wishes")
                                            .setSubText("I LOVE♥️YOU")
                    .setChannelId(CHANNEL_ID)
                            .build();
                    nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Message",NotificationManager.IMPORTANCE_HIGH));
        }else {
            notification = new Notification.Builder(this).setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText("Hey! Himanshu You will be always in my heart and wishes")
                    .setSubText("I LOVE♥️YOU")
                    .build();
        }

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:+916395909884"));
                startActivity(dialIntent);
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msgIntent = new Intent(Intent.ACTION_SENDTO);
                msgIntent.setData(Uri.parse("smsto:"+Uri.encode("6395909884")));
                msgIntent.putExtra("sms_body","Hey Akanksha can you share your resume");
                startActivity(msgIntent);
            }
        });

        gmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gmailIntent = new Intent(Intent.ACTION_SEND);
                gmailIntent.setType("message/rfc822");
                gmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gautamakansha312@gmail.com","shivaginagarbareilly@gmail.com"});
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT,"SDE-1 Interview");
                gmailIntent.putExtra(Intent.EXTRA_TEXT,"Hope you are doing well i am impressed by you profile please find below attached form for job opening");
                startActivity(Intent.createChooser(gmailIntent,"Email via"));
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareI = new Intent(Intent.ACTION_SEND);
                shareI.setType("text/plain");
                shareI.putExtra(Intent.EXTRA_TEXT,"Here's Referral code,https://play.google.com/store/apps/details?id=com.immediasemi.android.blink&hl=en");
                startActivity(Intent.createChooser(shareI,"share via"));
            }
        });

        gotToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,FragmentHolderActivity.class);
                startActivity(i);
            }
        });

        tabsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,TabsLayoutScreen.class);
                startActivity(i);
            }
        });
        bottomTabsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,BottomTabsScreen.class);
                startActivity(i);
            }
        });
        drawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i = new Intent(MainActivity.this,DrawerLayoutActivity.class);
                 startActivity(i);
            }
        });

        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });

        btnFastNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,DataWithFastNetwork.class);
                startActivity(i);
            }
        });
        nm.notify(NOTIFICATION_ID,notification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int weight = Integer.parseInt(et1.getText().toString());
            int height_inch = Integer.parseInt(et2.getText().toString());
            int height_ft = Integer.parseInt(et2.getText().toString());

            int totalInch = height_ft*12 + height_inch;
            double totalCm = totalInch*2.53;
            double totalMtr = totalCm/100;
            double bmi = weight/(totalMtr*totalMtr);
            if(bmi>25){
                txt.setText("You are overweight");
            }else if(bmi<18){
                txt.setText("You are underweighted");
            }else{
                txt.setText("Your weight is good based on your height");
            }
            }
        });
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TicTacToeActivity.class);
                intent.putExtra("Title","TicTacToe");
                intent.putExtra("Players",2);
                startActivity(intent);
            }
        });
        animationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AnimationViewActivity.class);
                startActivity(intent);
            }
        });
        lottieAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LottieAnimation.class);
                startActivity(intent);
            }
        });
        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CardActivity.class);
                startActivity(intent);
            }
        });
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm.notify(NOTIFICATION_ID,notification);
                Log.i("Info","Here is my information");
                Log.d("debug ","I will use this log to debug");
                Log.e("Error","error come");
            }
        });

        //starting background service
        backgroundServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startService(new Intent(MainActivity.this,MusicService.class));

            }
        });
        backgroundServiceBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,MusicService.class));
            }
        });

        //implementation of alarm service
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.parseInt(alarmInput.getText().toString());
                long alarmTriggerTime = System.currentTimeMillis()+(time*1000);//time is multiply by 1000 to convert in milliseconds as in the input it is in seconds
                Intent broadcastIntent = new Intent(MainActivity.this,BroadcastReceiver.class);
                /*
                * Starting from Android 12 (API level 31), when you create a PendingIntent,
                * you need to specify either FLAG_IMMUTABLE or FLAG_MUTABLE. This change was
                * introduced to improve security
                * If you need the PendingIntent to be mutable (i.e., it needs to be modified after being created),
                * you would use FLAG_MUTABLE instead. However, in most cases,
                * FLAG_IMMUTABLE is preferred for security reasons.
                * */
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,ALARM_SERVICE_CODE,broadcastIntent,PendingIntent.FLAG_IMMUTABLE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmTriggerTime,pendingIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_Id = item.getItemId();
        switch (item_Id){
            case R.id.first:
                Toast.makeText(this,"First ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.second:
                //custom toast
                Toast toast = new Toast(this);
                View view = getLayoutInflater().inflate(R.layout.custom_toast_layout,(ViewGroup)findViewById(R.id.toast_view_content));
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
                /* to set text from here
                * TextView text = view.findViewById(R.id.message_text);
                * text.setText("Set your custom message here")
                * */
                break;
            case R.id.third:
                Dialog dialogBox = new Dialog(this);
                dialogBox.setContentView(R.layout.custom_alert_box);
                Button okButton = dialogBox.findViewById(R.id.ok_button);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBox.dismiss();
                    }
                });
                //to prevent dialog box to close when user clicks outside the dialog box
                dialogBox.setCancelable(false);
                dialogBox.show();
                break;
            case R.id.fourth:
                SharedPreferences sp = getSharedPreferences("IS_LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("login_flag",false);
                editor.apply();         //important line to apply changes
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
                break;
            case R.id.fifth:
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("Terms And Conditions");
                dialog.setIcon(R.drawable.baseline_privacy_tip_24);
                dialog.setMessage("Have you read all terms and conditions?");
                dialog.setButton("Yes I've read ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Okay", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case android.R.id.home:
                Toast.makeText(this,"Back Button ",Toast.LENGTH_SHORT).show();
                super.onBackPressed();
                break;
            default:
                Toast.makeText(this,"Back Button in default ",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            tvs.setText("Values of accelerometer sensors are "+ "x: "+sensorEvent.values[0]+" y: "+sensorEvent.values[1]+ " z: "+sensorEvent.values[2]);
        } else if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY) {
            tvs2.setText("Value of Proximity sensor is "+sensorEvent.values[0]);
            if(sensorEvent.values[0]>0){
                Toast.makeText(this, "Object is Far", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Object is near", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}