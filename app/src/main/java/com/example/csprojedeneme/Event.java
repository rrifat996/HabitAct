package com.example.csprojedeneme;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import androidx.annotation.Nullable;
public class Event {

    public static ArrayList<Event> eventsList = new ArrayList<>();

    private String name;
    private LocalDate date;
    private LocalTime time;
    public static int counter = 0;
    public static int trigger = 0;



    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        Event ref = (Event) obj;
        return this.getName().equals(ref.getName());
    }


    public static ArrayList<Event> eventsForDate(LocalDate date){
        ArrayList<Event> events = new ArrayList<>();
        for(Event event : eventsList){
            if(event.getDate().equals(date))
                events.add(event);
        }
        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
