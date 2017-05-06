package com.example.kevle.eventfull;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.kevle.evenfull.models.Event;
import com.example.kevle.fragments.EventChatFragment;
import com.example.kevle.fragments.EventCreationFragment;
import com.example.kevle.fragments.EventDetailsFragment;
import com.example.kevle.fragments.EventListFragment;

public class EventActivity extends AppCompatActivity {

    //private ArrayAdapter<Event> eventsAdapter;
    private static EventActivity eventActivity;

    public static void setEventActivity(EventActivity eventActivity) {
        EventActivity.eventActivity = eventActivity;
    }

    public static EventActivity getEventActivity() {
        return eventActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        EventActivity.setEventActivity(this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.framelayout_id);
        //EventListFragment eventListFragment = (EventListFragment) fm.findFragmentById(R.id.events_recycler_view);

        if (fragment == null) {
            fragment = new EventListFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.framelayout_id, fragment);
            transaction.commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), EventCreationActivity.class);
//                startActivity(intent);
                loadEventCreationFragment();
            }
        });
    }

    public void loadEventCreationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_id, new EventCreationFragment())
                .addToBackStack(null).commit();
    }

    public void loadEventMainActivity (Event selectedEvent) {
        Intent intent = new Intent(getBaseContext(), EventMainActivity.class);
        intent.putExtra("startTime", selectedEvent.getEventStartTime());
        intent.putExtra("name", selectedEvent.getEventName());
        // ...

        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Inform user that the new event was created
//        Toast.makeText(EventActivity.this, "New Event Created", Toast.LENGTH_SHORT).show();
//
//        // Create an ArrayList to hold Events
//        ListView eventList = (ListView) findViewById(R.id.event_list_view);
//
//        ArrayList<Event> events = new ArrayList<Event>();
//
//        Event e = new Event(data.getStringExtra("nameValue"), data.getStringExtra("startTimeValue"),
//                data.getStringExtra("endTimeValue"), data.getStringExtra("dateValue"), data.getStringExtra("descValue"),
//                data.getStringExtra("locationValue"), "123");
//
//        events.add(e);
//
//        // Create EventsAdapter and set it to the ListView
//        eventsAdapter = new EventsAdapter(this, events);
//        eventList.setAdapter(eventsAdapter);
//
//        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getBaseContext(), EventMainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    public class EventsAdapter extends ArrayAdapter<Event> {
//        public EventsAdapter(Context context, ArrayList<Event> users) {
//            super(context, 0, users);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get data for the item at that position
//            Event event = getItem(position);
//
//            // Check if an existing view is being used; otherwise inflate it
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_row, parent, false);
//            }
//            TextView t = (TextView) convertView.findViewById(R.id.rowTextView);
//            t.setText(event.eventName);
//            return convertView;
//        }
//    }
}
