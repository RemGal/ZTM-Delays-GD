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
import android.widget.TextView;

import com.example.lasthope.util.Constants;
import com.example.lasthope.util.GUIRefresher;

public class VehicleTrackerActivity extends AppCompatActivity {

    GUIRefresher gui;

    Toolbar toolbar;
    AppCompatTextView stopTextView;
    SwitchCompat themeSwitch;
    AppCompatImageButton activityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(),false)) {setTheme(R.style.AppThemeD);}
        setContentView(R.layout.activity_vehicle_tracker);

        gui = new GUIRefresher();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        stopTextView = (AppCompatTextView) toolbar.findViewById(R.id.toolbar_title);
        themeSwitch = (SwitchCompat) toolbar.findViewById(R.id.toolbar_switch);
        activityButton = (AppCompatImageButton) toolbar.findViewById(R.id.toolbar_imgbutton);

        stopTextView.setText(getIntent().getStringExtra(new Constants().getCURRENT_STOP_NAME()));

        TextView temp = findViewById(R.id.lineTextView);
        temp.setText(Integer.toString(getIntent().getIntExtra(new Constants().getVEHICLE_CODE(), 0)));

        activityButton.setVisibility(View.INVISIBLE);

        themeSwitch.setChecked(getIntent().getBooleanExtra(new Constants().getSWITCH_STATE(), false));
        gui.theme(getApplicationContext(), themeSwitch.isChecked());
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                startVehicleTrackerActivity();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        startMainActivity();
    }

    public void startVehicleTrackerActivity() {
        Intent intentVTA = new Intent(this, VehicleTrackerActivity.class);

        intentVTA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());
        intentVTA.putExtra(new Constants().getCURRENT_STOP_NAME(), getIntent().getStringExtra(new Constants().getCURRENT_STOP_NAME()));
        intentVTA.putExtra(new Constants().getVEHICLE_CODE(), getIntent().getIntExtra(new Constants().getVEHICLE_CODE(), 0));

        startActivity(intentVTA);
    }

    private void startMainActivity() {
        Intent intentMA = new Intent(this, MainActivity.class);

        intentMA.putExtra(new Constants().getSWITCH_STATE(), themeSwitch.isChecked());

        startActivity(intentMA);
    }
}
