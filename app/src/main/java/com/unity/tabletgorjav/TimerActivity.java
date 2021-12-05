package com.unity.tabletgorjav;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

public class TimerActivity extends Activity {
    TextView airTime, socketTime;
    TimePicker timepicker;
    String lAIRTime, lSockTime;
    MaterialButton clickAIR, clickSocket, cancelAIR, cancelSocket;
    Button setTimeBtn;
    Button setTimeBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        // declare variables
        airTime = findViewById(R.id.tAirTime);
        socketTime = findViewById(R.id.tSocketTime);
        timepicker = findViewById(R.id.timePicker);
        //Uncomment the below line of code for 24 hour view
        timepicker.setIs24HourView(true);
        clickAIR = findViewById(R.id.clickAir);
        clickSocket = findViewById(R.id.clickSocket);
        cancelAIR = findViewById(R.id.cancelAir);
        cancelSocket = findViewById(R.id.cancelSocket);
        setTimeBtn = findViewById(R.id.BtnSetTime);
        setTimeBtn2 = findViewById(R.id.BtnSetTime2);

        // load data from PrefConfig
        lAIRTime = PrefConfig.loadAIRTimeKey(this);
        lSockTime = PrefConfig.loadSOCKTimeKey(this);

        airTime.setText(lAIRTime);
        socketTime.setText(lSockTime);

        // set alarm manager like timer
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent2 = new Intent(getApplicationContext(), AlarmReceiver2.class);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(getApplicationContext(), 100, notificationIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

        setTimeBtn.setOnClickListener(v -> {
            airTime.setText(getCurrentTime());

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());
            cal.set(Calendar.MINUTE, timepicker.getCurrentMinute());
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
            timepicker.setVisibility(View.GONE);
            setTimeBtn.setVisibility(View.GONE);

            PrefConfig.saveAIRTimeKey(getApplicationContext(), getCurrentTime());
        });
        setTimeBtn2.setOnClickListener(v -> {
            socketTime.setText(getCurrentTime());

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());
            cal.set(Calendar.MINUTE, timepicker.getCurrentMinute());
            alarmManager2.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast2);
            timepicker.setVisibility(View.GONE);
            setTimeBtn2.setVisibility(View.GONE);

            PrefConfig.saveSOCKTimeKey(getApplicationContext(), getCurrentTime());
        });

        clickAIR.setOnClickListener(v -> {
            timepicker.setVisibility(View.VISIBLE);
            setTimeBtn.setVisibility(View.VISIBLE);
        });
        clickSocket.setOnClickListener(v -> {
            timepicker.setVisibility(View.VISIBLE);
            setTimeBtn2.setVisibility(View.VISIBLE);
        });
        cancelAIR.setOnClickListener(v -> {
            alarmManager.cancel(broadcast);
            airTime.setText("_ _ : _ _");

        });
        cancelSocket.setOnClickListener(v -> {
            alarmManager2.cancel(broadcast2);
            socketTime.setText("_ _ : _ _");
        });
    }
    public String getCurrentTime(){
        String currentTime=timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> " + currentTime);
        return currentTime;
    }
}