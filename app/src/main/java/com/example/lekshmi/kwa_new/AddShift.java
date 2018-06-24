package com.example.lekshmi.kwa_new;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddShift extends AppCompatActivity {

    private static final String TAG = "npkTest: AddShift";


    TimePicker pickerTime;
    Button Pump_on,Pump_off;
    int alarmID,count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);



        Intent i=getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            alarmID=b.getInt("ID");
        }

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 1);

        pickerTime = findViewById(R.id.pickertime);



        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

        //To turn on the pump

        Pump_on = findViewById(R.id.on);
        Pump_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Calendar cal = Calendar.getInstance();
                cal.set(
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                        0);



                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Context context = getApplicationContext();
                Intent myIntent = new Intent(context, AlarmReceiver.class);

                EditText ph=findViewById(R.id.Phone);
                String PhNo=ph.getText().toString();

                myIntent.putExtra("Number",PhNo);
                myIntent.putExtra("Input",1);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, myIntent, 0);

                assert manager!=null;
                //manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                Log.i(TAG, "onClick: Shift set for " + cal.getTimeInMillis());
                Log.v("EditText value=", ph.getText().toString());
                Toast.makeText(context, cal.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Shift set", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, PhNo, Toast.LENGTH_SHORT).show();




                finish();
            }
        });

/*
        //To turn off the pump

        Pump_off = findViewById(R.id.off);
        Pump_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Calendar cal = Calendar.getInstance();
                cal.set(
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                        0);



                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Context context = getApplicationContext();
                Intent myIntent = new Intent(context, AlarmReceiver.class);

                EditText ph=findViewById(R.id.Phone);
                String PhNo=ph.getText().toString();

                myIntent.putExtra("Number",PhNo);
                myIntent.putExtra("Input",2);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, myIntent, 0);

                assert manager!=null;
                //manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                Log.i(TAG, "onClick: Shift set for " + cal.getTimeInMillis());
                Log.v("EditText value=", ph.getText().toString());
                Toast.makeText(context, "Shift set", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, PhNo, Toast.LENGTH_SHORT).show();




                finish();
            }
        });
        */
    }

}
