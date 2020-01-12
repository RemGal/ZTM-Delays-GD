package com.example.lasthope.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {

    public Reader() {

    }

    //TuMamProblem
    public String readJSONfromUrl(String url) {return null;}

    public String readJSONfromTextfile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        String text = sb.toString();

        if (text.charAt(0) != '[') {
            text = text.substring(text.indexOf('['), text.indexOf(']')+1);
        }

        return text;
    }
}
