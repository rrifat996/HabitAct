package com.example.csprojedeneme;

import static com.example.csprojedeneme.CalendarUtils.daysInWeekArray;
import static com.example.csprojedeneme.CalendarUtils.monthYearFromDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        CalendarUtils.selectedDate = LocalDate.now();
        initWidgets();
        setWeekView();
        if(Event.counter ==0){
            checkFromDatabase();
            Event.counter++;
        }
    }

    private void checkFromDatabase() {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "events.txt");
        try {
            Scanner inFile = new Scanner(readFrom);
            while(inFile.hasNext()){
                String line = inFile.nextLine();
                String [] linesplit = new String[3];
                linesplit = line.split(";");
                String eventName = linesplit[0];
                LocalDate eventDate = LocalDate.parse(linesplit[1]);
                LocalTime eventTime = LocalTime.parse(linesplit[2]);
                Event temp = new Event(eventName, eventDate, eventTime);
                if(!Event.eventsList.contains(temp))
                    Event.eventsList.add(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);

    }


    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }


    public void onItemClick(int position, LocalDate localDate)
    {
        CalendarUtils.selectedDate = localDate;
        setWeekView();
    }

    protected void onResume(){
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }


    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }
}