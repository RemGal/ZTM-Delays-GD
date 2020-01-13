package com.example.lasthope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.lasthope.model.Delay;
import com.example.lasthope.model.ListData;
import com.example.lasthope.model.Stop;
import com.example.lasthope.model.Vehicle;
import com.example.lasthope.util.CustomAdapter;
//import com.example.lasthope.util.GPSTracker;
import com.example.lasthope.util.JSONDeserializer;
import com.example.lasthope.util.JSONReader;
import com.example.lasthope.util.Mathematics;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public Stop currentStop;
    public List<Delay> delays;
    public Vehicle trackedVehicle;

    Location currLocation;

    Toolbar toolbar;
    AppCompatTextView stopTextView;
    SwitchCompat themeSwitch;
    AppCompatImageButton activityButton;

    ListView listView;
    ArrayList<ListData> dataModels;
    private static CustomAdapter adapter;

    //GPSTracker gps;

    JSONReader reader;
    JSONDeserializer deserializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        stopTextView = (AppCompatTextView) toolbar.findViewById(R.id.toolbar_title);
        themeSwitch = (SwitchCompat) toolbar.findViewById(R.id.toolbar_switch);
        activityButton = (AppCompatImageButton) toolbar.findViewById(R.id.toolbar_imgbutton);

        activityButton.setImageDrawable(getResources().getDrawable(R.drawable.placeholder1));


//        gps = new GPSTracker(getApplicationContext());
//        if (gps.canGetLocation()) {
//
//            stopTextView.setText(gps.getLatitude() + " x "+ gps.getLongitude());
////            gps.getLatitude();
////            gps.getLongitude();
//
//        }


        stopTextView.setText("Przystanek");
        themeSwitch.setChecked(true);

        activityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TicketMashineActivity.class));
            }
        });

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
                else {

                }
                //setTheme();
            }
        });

        reader = new JSONReader();
        deserializer = new JSONDeserializer();

        ListView listView = (ListView) findViewById(R.id.list);
        dataModels= new ArrayList<>();

        //currentStop = deserializer.getNearestStop(json);
        //delays = deserializer.getDelays(getApplicationContext(),reader.readJSONfromUrl("http://ckan2.multimediagdansk.pl/delays?stopId=1634"));

        Mathematics mat = new Mathematics();

        dataModels.add(new ListData("227", "Wrzszcz", "3minuty", 22));
        dataModels.add(new ListData("111", "Pirwsz", "7minut", 22));
        dataModels.add(new ListData("99", "AlaMaKota", "12minut", 33));

        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListData dataModel= dataModels.get(position);

                startActivity(new Intent(getApplicationContext(), VehicleTrackerActivity.class));

                Snackbar.make(view, Integer.toString(dataModel.getVehicleCode()), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });


    }



}