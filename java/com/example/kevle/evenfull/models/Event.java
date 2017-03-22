package com.example.kevle.evenfull.models;

/**
 * Created by kevle on 2/28/2017.
 */

public class Event {
    public String eventName;
    private String eventStartTime;
    private String eventEndTime;
    private String eventDate;
    private String eventDescription;
    private String eventLocation;
    private String eventOwner;

    public Event() {};
    
    public Event(String name, String start, String end, String date, String desc, String loc, String owner) {
        this.eventName = name;
        this.setEventStartTime(start);
        this.setEventEndTime(end);
        this.setEventDate(date);
        this.setEventDescription(desc);
        this.setEventLocation(loc);
        this.setEventOwner(owner);
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(String eventOwner) {
        this.eventOwner = eventOwner;
    }
}
