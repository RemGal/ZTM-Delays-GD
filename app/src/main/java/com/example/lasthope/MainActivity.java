package com.example.lasthope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.lasthope.model.ListData;
import com.example.lasthope.util.Constants;
import com.example.lasthope.util.CustomAdapter;
import com.example.lasthope.util.GUIRefresher;
import com.example.lasthope.util.JSONDeserializer;
import com.example.lasthope.util.JSONReader;
import com.example.lasthope.util.Mathematics;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    GUIRefresher gui;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(),false)) {setTheme(R.style.AppThemeD);}
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        gui = new GUIRefresher();

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

        activityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                startTicketMashineActivity();

            }
        });

        themeSwitch.setChecked(getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(), false));
        gui.theme(getApplicationContext(), themeSwitch.isChecked());
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                startMainActivity();
            }
        });

        reader = new JSONReader(getApplicationContext());
        deserializer = new JSONDeserializer();

        ListView listView = (ListView) findViewById(R.id.list);
        dataModels= new ArrayList<>();

        //currentStop = deserializer.getNearestStop(json);
        //delays = deserializer.getDelays(getApplicationContext(),reader.readJSONfromUrl("http://ckan2.multimediagdansk.pl/delays?stopId=1634"));

        Mathematics mat = new Mathematics();

        dataModels.add(new ListData("227", "Wrzeszcz", "3minuty", 11));
        dataModels.add(new ListData("111", "Pirwsz", "7minut", 22));
        dataModels.add(new ListData("991", "AlaMaKota", "12minut", 33));

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

        intentTMA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());

        startActivity(intentTMA);
    }

    public void startVehicleTrackerActivity() {
        Intent intentVTA = new Intent(this, VehicleTrackerActivity.class);

        intentVTA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());
        intentVTA.putExtra(new Constants().getCURRENT_STOP_NAME(), stopTextView.getText());
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