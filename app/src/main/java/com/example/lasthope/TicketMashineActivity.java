package com.example.lasthope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.lasthope.util.Constants;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class TicketMashineActivity extends AppCompatActivity {


    MapView map;

    Toolbar toolbar;
    AppCompatTextView stopTextView;
    SwitchCompat themeSwitch;
    AppCompatImageButton activityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(),false)) {setTheme(R.style.AppThemeD);}
        setContentView(R.layout.activity_ticket_mashine);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        stopTextView = (AppCompatTextView) toolbar.findViewById(R.id.toolbar_title);
        themeSwitch = (SwitchCompat) toolbar.findViewById(R.id.toolbar_switch);
        activityButton = (AppCompatImageButton) toolbar.findViewById(R.id.toolbar_imgbutton);

        activityButton.setImageDrawable(getResources().getDrawable(R.drawable.placeholder2));
        stopTextView.setText("Biletomaty");


        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        themeSwitch.setChecked(getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(), false));
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                startTicketMashineActivity();
            }
        });

        map = (MapView) findViewById(R.id.mapView);
        IMapController mapController = map.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(getIntent().getFloatExtra(new Constants().getCURRENT_LOCATION_LAT(), 0), getIntent().getFloatExtra(new Constants().getCURRENT_LOCATION_LON(), 0));
        mapController.setCenter(startPoint);
        map.setMultiTouchControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);

    }

    @Override
    public void onBackPressed()
    {
        startMainActivity();
    }

    private void startTicketMashineActivity() {

        Intent intentTMA = new Intent(this, TicketMashineActivity.class);

        intentTMA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());
        startActivity(intentTMA);
    }

    private void startMainActivity() {
        Intent intentMA = new Intent(this, MainActivity.class);
        intentMA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());
        startActivity(intentMA);
    }

}
