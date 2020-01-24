package com.example.lasthope.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.PictureDrawable;
import android.graphics.drawable.VectorDrawable;


import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.example.lasthope.R;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapOverlayPrinter {

    public MapOverlayPrinter(Context context, MapView mapView) {this.mContext = context; this.osm = mapView;}

    Context mContext;
    MapView osm;

    public void addUserMarker(GeoPoint center) {
        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setInfoWindow(null);
        SVG svg = null;
        try {
            svg = SVG.getFromResource(mContext, R.raw.user_pin);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        marker.setIcon(new PictureDrawable(svg.renderToPicture()));

        osm.getOverlays().add(marker);
        osm.invalidate();
    }
    public void addStopMarker(GeoPoint center) {
        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setInfoWindow(null);
        SVG svg = null;
        try {
            svg = SVG.getFromResource(mContext, R.raw.stop_pin);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        marker.setIcon(new PictureDrawable(svg.renderToPicture()));

        osm.getOverlays().add(marker);
        osm.invalidate();
    }
    public void addVehicleMarker(GeoPoint center, String line) {

        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(line);
        SVG svg = null;
        try {
            svg = SVG.getFromResource(mContext, R.raw.vehicle_pin);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        marker.setIcon(new PictureDrawable(svg.renderToPicture()));

        osm.getOverlays().add(marker);
        osm.invalidate();
    }
    public void addMashineMarker(GeoPoint center) {
        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setInfoWindow(null);
        SVG svg = null;
        try {
            svg = SVG.getFromResource(mContext, R.raw.mashine_pin);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        marker.setIcon(new PictureDrawable(svg.renderToPicture()));

        osm.getOverlays().add(marker);
        osm.invalidate();
    }

}
