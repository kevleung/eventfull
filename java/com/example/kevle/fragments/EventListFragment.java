package com.example.kevle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevle.adapters.EventListAdapter;
import com.example.kevle.evenfull.models.Event;
import com.example.kevle.eventfull.DividerItemDecoration;
import com.example.kevle.eventfull.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public ArrayList<Event> loadedEventList = new ArrayList<>();
    public EventListAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EventListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventListFragment newInstance(String param1, String param2) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_list, container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.events_recycler_view);
        recyclerView.setHasFixedSize(true);

        EventDataService service = new EventDataService();
        service.getEvents();
        adapter = new EventListAdapter(loadedEventList);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }

    public class EventDataService {
        private DatabaseReference mDatabase;

        public EventDataService() {
        }

        public void getEvents() {
            mDatabase = FirebaseDatabase.getInstance().getReference("events");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    for (DataSnapshot eventSnapShot: dataSnapshot.getChildren()) {
                        Event e = eventSnapShot.getValue(Event.class);
                        loadedEventList.add(e);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "loadEvents:onCancelled", databaseError.toException());
                }
            });
        }
    }
}


//
//class SpaceItemDecorator extends RecyclerView.ItemDecoration {
//    private final int spacer;
//
//    public SpaceItemDecorator(int spacer) {
//        this.spacer = spacer;
//    }
//
//    @Override
//    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//        outRect.bottom = spacer;
//    }
//}
