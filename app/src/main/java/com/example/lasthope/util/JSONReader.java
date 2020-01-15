package com.example.lasthope.util;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONReader {

    Context mContext;

    public JSONReader(Context context) { this.mContext = context;}

//NIE DZIALA
    public String readJSONfromUrl(String link) {
        try {
            URL url = new URL("http://www.android.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader buffreader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = buffreader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            String text = sb.toString();
            if (text.charAt(0) == '{') {
                text = text.substring(text.indexOf('['), text.indexOf(']') + 1);
            }
            urlConnection.disconnect();
            return text;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String readJSONfromTextfile(int resId)
    {
        InputStream inputStream = mContext.getResources().openRawResource(resId);
        BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            while (( line = buffreader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            String text = sb.toString();
            if (text.charAt(0) == '{') {
                text = text.substring(text.indexOf('['), text.indexOf(']')+1);
            }
            return text;
        } catch (IOException e) {
            return "";
        }
    }
}
