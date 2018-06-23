package com.example.lekshmi.kwa_new;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "npkTest: MainActivity"; 

    private int PHONE_REQUEST = 101;

    private Button alarm1,alarm0,alarm2,alarm3,alarm4;
    private Button cancel0,ccancel1,cancel2,cancel3,cancel4;
    private TextView tv0,tv1,tv2,tv3,tv4;
    int alarmID0,alarmID1,alarmId2,alarmID3,alarmID4;
    int id;
    String s;
    Boolean b_cancel0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        CharSequence can_0_text=cancel0.getText();
        outState.putCharSequence("Cancel",can_0_text);
        outState.putBoolean("Cancel",b_cancel0);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState!=null)
        {
            cancel0.setEnabled(savedInstanceState.getBoolean("Cancel"));
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm0=findViewById(R.id.bt_add_alarm_0);
        alarm1=findViewById(R.id.bt_add_alarm_1);
        alarm2=findViewById(R.id.bt_add_alarm_2);
        alarm3=findViewById(R.id.bt_add_alarm_3);
        alarm4=findViewById(R.id.bt_add_alarm_4);

        cancel0=findViewById(R.id.bt_cancel_alarm_0);
        ccancel1=findViewById(R.id.bt_cancel_alarm_1);
        cancel2=findViewById(R.id.bt_cancel_alarm_2);
        cancel3=findViewById(R.id.bt_cancel_alarm_3);
        cancel4=findViewById(R.id.bt_cancel_alarm_4);

        tv0=findViewById(R.id.tv_alarm0);
        tv1=findViewById(R.id.tv_alarm1);
        tv2=findViewById(R.id.tv_alarm2);
        tv3=findViewById(R.id.tv_alarm3);
        tv4=findViewById(R.id.tv_alarm4);


        alarm0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(MainActivity.this,AddShift.class);
                alarmID0 = (int) System.currentTimeMillis();
                setAlarm(alarmID0);
                //intent.putExtra("ID",alarmID0);
                //startActivity(intent);
                cancel0.setEnabled(true); b_cancel0=true;
                alarm0.setText("Edit");

            }
        });

        alarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(MainActivity.this,AddShift.class);
                alarmID1 = (int) System.currentTimeMillis();
                setAlarm(alarmID1);
                //intent.putExtra("ID",alarmID1);
                //startActivity(intent);
                ccancel1.setEnabled(true);
                alarm1.setText("Edit");
            }
        });

        alarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(MainActivity.this,AddShift.class);
                alarmId2 = (int) System.currentTimeMillis();
                setAlarm(alarmId2);
                //intent.putExtra("ID",alarmId2);
               // startActivity(intent);
               cancel2.setEnabled(true);
                alarm2.setText("Edit");
            }
        });

        alarm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(MainActivity.this,AddShift.class);
                alarmID3 = (int) System.currentTimeMillis();
                setAlarm(alarmID3);
                //intent.putExtra("ID",alarmID3);
                //startActivity(intent);
                cancel3.setEnabled(true);
                alarm3.setText("Edit");
            }
        });

        alarm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(MainActivity.this,AddShift.class);
                alarmID4 = (int) System.currentTimeMillis();
                setAlarm(alarmID4);
                //intent.putExtra("ID",alarmID4);
                //startActivity(intent);
                cancel4.setEnabled(true);
                alarm4.setText("Edit");
            }
        });

        cancel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm(alarmID0);
                alarm0.setText("ADD");

                cancel0.setEnabled(false); b_cancel0=false;

            }
        });

        ccancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm(alarmID1);
                alarm1.setText("ADD");
                ccancel1.setEnabled(false);

            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm(alarmId2);
                alarm2.setText("ADD");

                cancel2.setEnabled(false);

            }
        });
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm(alarmID3);
                alarm3.setText("ADD");

                cancel3.setEnabled(false);

            }
        });
        cancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm(alarmID4);
                alarm4.setText("ADD");
                cancel4.setEnabled(false);
            }
        });

        /*
        Intent i=getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            id=b.getInt("ID");
            s=b.getString("time");

        }
        switch (id)
        {
            case 0 :
                tv0.setText(s);break;
            case 1 :
                tv1.setText(s);break;
            case 2:
                tv2.setText(s);break;
            case 3:
                tv3.setText(s);break;
            case 4 :
                tv4.setText(s);break;
        }
*/

        Log.i(TAG, "onCreate: Android OS: " + android.os.Build.VERSION.SDK_INT);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onCreate: No permission.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_REQUEST);
        } else {
            Log.i(TAG, "onCreate: Phone permitted");
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PHONE_REQUEST) {
            if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: Permission granted");
                Toast.makeText(this, "No permission to use Phone. App won't work as expected", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "onRequestPermissionsResult: Permission denied");
            }
        }

    }

    private void setAlarm(int ID)
    {
        cancelAlarm(ID);

        Intent intent=new Intent(MainActivity.this,AddShift.class);
        intent.putExtra("ID",alarmID4);
        startActivity(intent);

    }

    private void cancelAlarm( int ID){



        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);




    }
}
