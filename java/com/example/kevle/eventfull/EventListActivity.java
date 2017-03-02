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
                startActivityForResult(intent, RESULT_OK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
//            ListView eventList = (ListView) findViewById(R.id.event_list_view);
//            ArrayList<Event> events = new ArrayList<Event>();
//
//            Event e = new Event(data.getStringExtra("nameValue"), data.getStringExtra("startTimeValue"),
//                    data.getStringExtra("endTimeValue"), data.getStringExtra("dateValue"), data.getStringExtra("descValue"),
//                    data.getStringExtra("locationValue"), "123");
//
//            events.add(e);
            Toast.makeText(EventListActivity.this, data.getStringExtra("nameValue"), Toast.LENGTH_SHORT).show();
        }
    }

//    public class EventsAdapter extends ArrayAdapter<Event> {
//        public EventsAdapter(Context context, ArrayList<Event> event) {
//            super(context, 0, event);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get the data item for this position
//            Event event = getItem(position);
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
//            }
//            // Lookup view for data population
//            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
//            // Populate the data into the template view using the data object
//
//            // Return the completed view to render on screen
//            return convertView;
//        }
//    }
}
