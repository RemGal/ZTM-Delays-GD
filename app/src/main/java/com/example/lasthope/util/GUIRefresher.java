package com.example.lasthope.util;

import android.content.Context;

import com.example.lasthope.R;

public class GUIRefresher {



    public GUIRefresher() {}

    public void theme(Context context,boolean isChecked) {
        if (isChecked) {
            context.setTheme(R.style.AppThemeD);
        }
        else {
            context.setTheme(R.style.AppThemeD);
        }
    }

}
