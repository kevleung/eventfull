package com.example.kevle.evenfull.models;

/**
 * Created by kevle on 2/28/2017.
 */

public class Event {
    public String eventName;
    public String eventStartTime;
    public String eventEndTime;
    public String eventDate;
    public String eventDescription;
    public String eventLocation;
    public String eventOwner;

    public Event(String name, String start, String end, String date, String desc, String loc, String owner) {
        this.eventName = name;
        this.eventStartTime = start;
        this.eventEndTime = end;
        this.eventDate = date;
        this.eventDescription = desc;
        this.eventLocation = loc;
        this.eventOwner = owner;
    }
}
