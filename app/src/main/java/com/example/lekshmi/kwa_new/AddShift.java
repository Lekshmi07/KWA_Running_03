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
    Button buttonSetAlarm;
    int alarmID,count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);


        EditText ph=findViewById(R.id.Phone);
        final String PhNo=ph.getText().toString();
        Intent i=getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            alarmID=b.getInt("ID");
        }

        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, 20);

        pickerTime = findViewById(R.id.pickertime);



        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

        buttonSetAlarm = findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {

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
                myIntent.putExtra("Number",PhNo);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, myIntent, 0);

                assert manager!=null;
                //manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                Log.i(TAG, "onClick: Shift set for " + cal.getTimeInMillis());
                Toast.makeText(context, "Shift set", Toast.LENGTH_SHORT).show();




                finish();
            }
        });
    }

}
