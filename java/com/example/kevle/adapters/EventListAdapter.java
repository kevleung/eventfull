package com.example.kevle.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevle.evenfull.models.Event;
import com.example.kevle.eventfull.EventActivity;
import com.example.kevle.eventfull.EventMainActivity;
import com.example.kevle.eventfull.R;
import com.example.kevle.holders.EventListViewHolder;

import java.util.ArrayList;

/**
 * Created by kevle on 3/5/2017.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListViewHolder> {

    private ArrayList<Event> eventsList;

    public EventListAdapter(ArrayList<Event> events) {
        this.eventsList = events;
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);

        return new EventListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventListViewHolder holder, int position) {
        final Event event = eventsList.get(position);
        holder.name.setText(event.eventName);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            // load the event details fragment
            EventActivity.getEventActivity().loadEventDetailsScreen(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public void clear() {
        this.eventsList.clear();
    }
}
