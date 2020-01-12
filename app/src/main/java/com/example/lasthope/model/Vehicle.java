package com.example.lasthope.model;

public class Vehicle {

    //        "DataGenerated":"2020-01-09 13:41:05";
//        "Line":"207";
//        "Route":"11";
    private String VehicleCode;
    //        "VehicleService":"207-04";
//        "VehicleId":145648;
//        "Speed":25;
//        "Delay":368;
//        "GPSQuality":3
    private double Lat;
    private double Lon;

    public void setVehicleCode(String vehicleCode) { VehicleCode = vehicleCode; }
    public void setLat(double lat) { Lat = lat; }
    public void setLon(double lon) { Lon = lon; }

    public String getVehicleCode() { return VehicleCode; }
    public double getLat() { return Lat; }
    public double getLon() { return Lon; }
}