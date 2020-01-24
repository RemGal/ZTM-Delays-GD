package com.example.lasthope.model;

public class Stop {
    private int stopId;
    //        boolean stopCode;
//        String stopName;
//        String stopShortName;
    private String stopDesc;
//        String subName;
//        String date;
    private float stopLat;
    private float stopLon;
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
    public void setStopDesc(String stopDesc) { this.stopDesc = stopDesc; }
    public void setStopLat(float stopLat) { this.stopLat = stopLat; }
    public void setStopLon(float stopLon) { this.stopLon = stopLon; }

    public int getStopId() { return stopId; }
    public String getStopDesc() {
        return stopDesc;
    }
    public float getStopLat() {
        return stopLat;
    }
    public float getStopLon() {
        return stopLon;
    }
}