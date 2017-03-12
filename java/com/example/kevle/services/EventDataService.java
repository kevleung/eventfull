package com.example.kevle.services;

import com.example.kevle.evenfull.models.Event;

import java.util.ArrayList;

/**
 * Created by kevle on 3/11/2017.
 */

public class EventDataService {
    private static final EventDataService ourInstance = new EventDataService();

    public static EventDataService getInstance() {
        return ourInstance;
    }

    private EventDataService() {
    }

    public ArrayList<Event> getEvents() {
        // Pretend we just got data from Firebase
        ArrayList<Event> list = new ArrayList<>();
        list.add(new Event("Sushi nom", "22:00", "23:00", "03/12/2017", "Nom nom nom on sushi", "Toronto", "Kevin"));
        list.add(new Event("Drinking nom", "22:00", "23:00", "03/12/2017", "Nom nom nom on sushi", "Toronto", "Kevin"));

        return list;
    }


}
