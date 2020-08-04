package com.example.lasthope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lasthope.model.Delay;
import com.example.lasthope.model.ListData;
import com.example.lasthope.model.Stop;
import com.example.lasthope.util.Constants;
import com.example.lasthope.util.CustomAdapter;
import com.example.lasthope.util.JSONDeserializer;
import com.example.lasthope.util.JSONReader;
import com.example.lasthope.util.Mathematics;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    AppCompatTextView stopTextView;
    SwitchCompat themeSwitch;
    AppCompatImageButton activityButton;

    ListView listView;
    ListData dataModel;
    ArrayList<ListData> dataModels;
    private static CustomAdapter adapter;

    JSONReader reader;
    JSONDeserializer deserializer;

    Stop currentStop;
    List<Delay> delays;

    Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(),false)) {setTheme(R.style.AppThemeD);}
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





        activityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                startTicketMashineActivity();

            }
        });

        themeSwitch.setChecked(getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(), false));
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                startMainActivity();
            }
        });

        reader = new JSONReader(getApplicationContext());
        deserializer = new JSONDeserializer();

        currentStop = deserializer.getNearestStop(54.38326,18.59922,reader.readJSONfromTextfile(R.raw.stops));
        stopTextView.setText(currentStop.getStopDesc());

        delays = deserializer.getDelays( "{\"lastUpdate\":\"2020-01-19 14:25:03\",\"delay\":[{\"id\":\"T122R127\",\"delayInSeconds\":95,\"estimatedTime\":\"14:30\",\"headsign\":\"Oliwa PKP\",\"routeId\":127,\"tripId\":122,\"status\":\"REALTIME\",\"theoreticalTime\":\"14:29\",\"timestamp\":\"14:25:03\",\"trip\":638956,\"vehicleCode\":2522,\"vehicleId\":188},{\"id\":\"T12R227\",\"delayInSeconds\":282,\"estimatedTime\":\"14:37\",\"headsign\":\"Jelitkowo\",\"routeId\":227,\"tripId\":12,\"status\":\"REALTIME\",\"theoreticalTime\":\"14:33\",\"timestamp\":\"14:25:00\",\"trip\":641224,\"vehicleCode\":2642,\"vehicleId\":460},{\"id\":\"T12R158\",\"delayInSeconds\":39,\"estimatedTime\":\"14:40\",\"headsign\":\"Stogi Plaża\",\"routeId\":158,\"tripId\":12,\"status\":\"REALTIME\",\"theoreticalTime\":\"14:40\",\"timestamp\":\"14:25:02\",\"trip\":644240,\"vehicleCode\":2800,\"vehicleId\":177},{\"id\":\"T122R127\",\"delayInSeconds\":-162,\"estimatedTime\":\"14:46\",\"headsign\":\"Oliwa PKP\",\"routeId\":127,\"tripId\":122,\"status\":\"REALTIME\",\"theoreticalTime\":\"14:49\",\"timestamp\":\"14:25:02\",\"trip\":640254,\"vehicleCode\":2536,\"vehicleId\":202}]}");
        //ListView listView = (ListView) findViewById(R.id.list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dataModels= new ArrayList<>();
        for (int i =0; i < delays.size(); i++) {
            dataModels.add(new ListData(Integer.toString(delays.get(i).getRouteId()), delays.get(i).getHeadsign(), delays.get(i).getEstimatedTime(), delays.get(i).getVehicleCode()));
        }

        Mathematics mat = new Mathematics();

        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel= dataModels.get(position);

                startVehicleTrackerActivity();

//                Snackbar.make(view, Integer.toString(dataModel.getVehicleCode()), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
            }
        });

    }

    private void startTicketMashineActivity() {
        Intent intentTMA = new Intent(this, TicketMashineActivity.class);

        intentTMA.putExtra(new Constants().getCURRENT_LOCATION_LAT(), 54.39141f);
        intentTMA.putExtra(new Constants().getCURRENT_LOCATION_LON(), 18.59584f);
        intentTMA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());

        startActivity(intentTMA);
    }

    public void startVehicleTrackerActivity() {
        Intent intentVTA = new Intent(this, VehicleTrackerActivity.class);

        intentVTA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());
        intentVTA.putExtra(new Constants().getCURRENT_STOP_NAME(), stopTextView.getText());
        intentVTA.putExtra(new Constants().getCURRENT_LOCATION_LAT(), currentStop.getStopLat());
        intentVTA.putExtra(new Constants().getCURRENT_LOCATION_LON(), currentStop.getStopLon());
        intentVTA.putExtra(new Constants().getVEHICLE_CODE(), dataModel.getVehicleCode());

        startActivity(intentVTA);
    }

    private void startMainActivity() {
        Intent intentMA = new Intent(this, MainActivity.class);
        intentMA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());
        startActivity(intentMA);
    }

    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
    }


}