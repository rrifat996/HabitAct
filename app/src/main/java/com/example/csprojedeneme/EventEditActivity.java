package com.example.csprojedeneme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private LocalTime time;
    String content= "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date : " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time : " + CalendarUtils.formattedTime(time));


    }

    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
        Event.eventsList.add(newEvent);

        File path  = getApplicationContext().getFilesDir();
        FileOutputStream writer = null;
        try {
            writer = new FileOutputStream(new File(path, "events.txt"),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=Event.eventsList.size(); i < Event.eventsList.size()+1;i++){
            Event add = Event.eventsList.get(i-1);
            String addName = add.getName();
            String addDate = add.getDate().toString();
            String addTime = add.getTime().toString();
            content += addName +";";
            content += addDate +";";
            content += addTime +"\n";
            try {
                writer.write(content.getBytes());
                Toast.makeText(EventEditActivity.this,"Event added", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        finish();
    }
}