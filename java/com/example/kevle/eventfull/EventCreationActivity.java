package com.example.kevle.eventfull;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.kevle.evenfull.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.UUID;

public class EventCreationActivity extends AppCompatActivity{
    public EditText dateValue;
    public EditText startTimeValue;
    public EditText endTimeValue;
    public EditText nameValue;
    public EditText descValue;
    public EditText locationValue;
    public Button confirmBtn;
    public Button cancelBtn;
    public int hr;
    public int min;
    boolean timeBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);

        dateValue = (EditText) findViewById(R.id.editText);
        dateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EventCreationActivity.this, date, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startTimeValue = (EditText) findViewById(R.id.start_time);
        startTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(EventCreationActivity.this, time, 12, 0, true).show();
                timeBool = true;
            }
        });

        endTimeValue = (EditText) findViewById(R.id.end_time);
        endTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(EventCreationActivity.this, time, 12, 0, true).show();
                timeBool = false;
            }
        });

        // Save the newly created event in the database
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                nameValue = (EditText) findViewById(R.id.event_name);
                nameValue.getText().toString();

                descValue = (EditText) findViewById(R.id.desc_value);
                descValue.getText().toString();

                locationValue = (EditText) findViewById(R.id.location_value);
                locationValue.getText().toString();

                String eventID = UUID.randomUUID().toString();

                Event newEvent = new Event(nameValue.getText().toString(), startTimeValue.getText().toString(),
                        endTimeValue.getText().toString(), dateValue.getText().toString(),
                        descValue.getText().toString(), locationValue.getText().toString(), uid);

                FirebaseDatabase.getInstance().getReference("events").child(eventID).setValue(newEvent);

                // Check for database error

                // Add Toast for success/failure

                // Add loader

                // Pass back data to EventActivity and finish EventCreationActivity
                // REFACTOR : use Bundle
                Intent intent = new Intent();
                intent.putExtra("dateValue", dateValue.getText().toString());
                intent.putExtra("startTimeValue", startTimeValue.getText().toString());
                intent.putExtra("endTimeValue", endTimeValue.getText().toString());
                intent.putExtra("nameValue", nameValue.getText().toString());
                intent.putExtra("descValue", descValue.getText().toString());
                intent.putExtra("locationValue", locationValue.getText().toString());

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // NEED TO REFACTOR THIS: JUST RESUME EventActivity instead of re-creating it.
                Intent intent = new Intent(getBaseContext(), EventActivity.class);
                startActivity(intent);
            }
        });
    }

    Calendar cal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };

    private void updateDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateValue.setText(sdf.format(cal.getTime()));
    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view,int hour, int minute) {
            hr = hour;
            min = minute;
            updateTime();
        }
    };

    private void updateTime() {
        if (timeBool) {
            startTimeValue.setText(new StringBuilder().append(hr).append(":").append(min));
        } else {
            endTimeValue.setText(new StringBuilder().append(hr).append(":").append(min));
        }
    }
}
