package com.example.lasthope.model;

public class Vehicle {

    //        "DataGenerated":"2020-01-09 13:41:05";
        String Line;
//        "Route":"11";
    private String VehicleCode;
    //        "VehicleService":"207-04";
//        "VehicleId":145648;
//        "Speed":25;
//        "Delay":368;
//        "GPSQuality":3
    private double Lat;
    private double Lon;

    public void setLine(String line) {Line = line;}
    public void setVehicleCode(String vehicleCode) { VehicleCode = vehicleCode; }
    public void setLat(double lat) { Lat = lat; }
    public void setLon(double lon) { Lon = lon; }

    public String getLine() {return Line;}
    public String getVehicleCode() { return VehicleCode; }
    public double getLat() { return Lat; }
    public double getLon() { return Lon; }
}