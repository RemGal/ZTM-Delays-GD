package com.example.lasthope.util;

import android.content.Context;
import android.location.Location;

import com.example.lasthope.model.Delay;
import com.example.lasthope.model.Stop;
import com.example.lasthope.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.lasthope.util.Mathematics;



import java.lang.reflect.Type;
import java.util.List;

public class JSONDeserializer {


    public JSONDeserializer() {}

    public Vehicle getVehicle(int vehicleNumber, String json) {
        json = json.substring(json.indexOf('['), json.indexOf(']')+1);
        Gson gson = new Gson();
        Type vehicleType = new TypeToken<List<Vehicle>>(){}.getType();
        List<Vehicle> vehicles = gson.fromJson(json, vehicleType);
        int indexOfVehicle = 0;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicleNumber == Integer.valueOf(vehicles.get(i).getVehicleCode())) { indexOfVehicle = i; break;}
        }
        return vehicles.get(indexOfVehicle);
    }

    public List<Delay> getDelays(String json) {
        json = json.substring(json.indexOf('['), json.indexOf(']')+1);
        Gson gson = new Gson();
        Type delaysType = new TypeToken<List<Delay>>(){}.getType();
        List<Delay> delays = gson.fromJson(json, delaysType);
        return delays;
    }

    public Stop getNearestStop(double lat, double lon, String json) {
        Gson gson = new Gson();
        Mathematics mat = new Mathematics();
        Type stopsType = new TypeToken<List<Stop>>(){}.getType();
        List<Stop> stops = gson.fromJson(json, stopsType);
        double tempDistance;
        double currDistance = mat.distance(lat, lon, stops.get(0).getStopLat(), stops.get(0).getStopLon());
        int NearrestStopId = 0;
        int iterator = 1;
        do {
            tempDistance = mat.distance(lat, lon, stops.get(iterator).getStopLat(), stops.get(iterator).getStopLon());
            if (currDistance > tempDistance) {currDistance = tempDistance; NearrestStopId = stops.get(iterator).getStopId();}
            iterator++;
        }
        while (currDistance > 0.012 && iterator < stops.size());

        return stops.get(iterator);
    }

}
