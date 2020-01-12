package com.example.lasthope.model;

public class Stop {
    private int stopId;
    //        boolean stopCode;
//        String stopName;
//        String stopShortName;
//        String stopDesc;
//        String subName;
//        String date;
    private double stopLat;
    private double stopLon;
//        boolean zoneId;
//        boolean zoneName;
//        String stopUrl;
//        boolean locationType;
//        boolean parentStation;
//        String stopTimezone;
//        boolean wheelchairBoarding;
//        boolean virtual;
//        boolean nonpassenger;
//        boolean depot;
//        boolean ticketZoneBorder;
//        boolean onDemand;
//        String activationDate;

    public void setStopId(int stopId) { this.stopId = stopId; }
    public void setStopLat(double stopLat) { this.stopLat = stopLat; }
    public void setStopLon(double stopLon) { this.stopLon = stopLon; }

    public int getStopId() { return stopId; }
    public double getStopLat() {
        return stopLat;
    }
    public double getStopLon() {
        return stopLon;
    }
}