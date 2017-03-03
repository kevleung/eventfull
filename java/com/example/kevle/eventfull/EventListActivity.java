package com.example.kevle.eventfull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevle.evenfull.models.Event;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    private ArrayAdapter<Event> eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), EventCreationActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Create an ArrayList to hold Events
        ListView eventList = (ListView) findViewById(R.id.event_list_view);

        ArrayList<Event> events = new ArrayList<Event>();

        Event e = new Event(data.getStringExtra("nameValue"), data.getStringExtra("startTimeValue"),
                data.getStringExtra("endTimeValue"), data.getStringExtra("dateValue"), data.getStringExtra("descValue"),
                data.getStringExtra("locationValue"), "123");

        events.add(e);

        // Create ArrayAdapter using the events list
        eventsAdapter = new EventsAdapter(this, events);
        eventList.setAdapter(eventsAdapter);

        Toast.makeText(EventListActivity.this, "New Event Created", Toast.LENGTH_SHORT).show();
    }

    public class EventsAdapter extends ArrayAdapter<Event> {
        public EventsAdapter(Context context, ArrayList<Event> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get data for the item at that position
            Event event = getItem(position);

            // Check if an existing view is being used; otherwise inflate it
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_row, parent, false);
            }
            TextView t = (TextView) convertView.findViewById(R.id.rowTextView);
            t.setText(event.eventName);
            return convertView;
        }
    }
}
