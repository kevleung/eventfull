package com.example.kevle.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kevle.evenfull.models.Event;
import com.example.kevle.eventfull.R;
import com.google.android.gms.vision.text.Text;

/**
 * Created by kevle on 3/5/2017.
 */

public class EventListViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public EventListViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.rowTextView);
    }
}
