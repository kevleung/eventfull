package com.example.kevle.services;

import android.util.Log;

import com.example.kevle.adapters.EventListAdapter;
import com.example.kevle.evenfull.models.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by kevle on 3/11/2017.
 */

public class EventDataService {
    private static final EventDataService ourInstance = new EventDataService();
    private DatabaseReference mDatabase;
    EventListAdapter eventListAdapter;
    public ArrayList<Event> eventList = new ArrayList<>();

    public static EventDataService getInstance() {
        return ourInstance;
    }

    private EventDataService() {
    }

    public ArrayList<Event> getEvents() {
        mDatabase = FirebaseDatabase.getInstance().getReference("events");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot eventSnapShot: dataSnapshot.getChildren()) {
                    Event e = eventSnapShot.getValue(Event.class);
                    eventList.add(e);
                }
                eventListAdapter = new EventListAdapter(eventList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadEvents:onCancelled", databaseError.toException());
            }
        });
        return eventList;
    }
}
