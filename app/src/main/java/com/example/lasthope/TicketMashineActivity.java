package com.example.lasthope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.example.lasthope.util.Constants;
import com.example.lasthope.util.MapOverlayPrinter;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TicketMashineActivity extends AppCompatActivity {


    MapView map;
    MapOverlayPrinter mapOverlayPrinter;

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
        mapOverlayPrinter = new MapOverlayPrinter(getApplicationContext(),map);
        Configuration.getInstance().setUserAgentValue(getApplicationContext().getPackageName());
        IMapController mapController = map.getController();
        mapController.setZoom(15.5);
        GeoPoint startPoint = new GeoPoint(getIntent().getFloatExtra(new Constants().getCURRENT_LOCATION_LAT(), 0), getIntent().getFloatExtra(new Constants().getCURRENT_LOCATION_LON(), 0));
        mapController.setCenter(startPoint);
        map.setMultiTouchControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);


        mapOverlayPrinter.addUserMarker(startPoint);

        InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.biletomaty);
        BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputStream));
        String input;
        try {
            while (( input = buffreader.readLine()) != null) {
                String[] line = input.split(",");
                mapOverlayPrinter.addMashineMarker(new GeoPoint(Double.parseDouble(line[0]),Double.parseDouble(line[1])));
            }
        } catch (IOException e) {
        }

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
