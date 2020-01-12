package com.example.lasthope.model;

public class Delay {

    //"id":"T12R227",
    //"delayInSeconds":3,
    private int routeId;
    private String headsign;
    private String estimatedTime;
    //"tripId":12,
    //"status":"REALTIME",
    private String theoreticalTime;
    //"timestamp":"13:42:42",
    //"trip":293069,
    private int vehicleCode;
    //"vehicleId":201

    public void setRouteId(int routeId) { this.routeId = routeId; }
    public void setHeadsign(String headsign) { this.headsign = headsign; }
    public void setEstimatedTime(String estimatedTime) { this.estimatedTime = estimatedTime; }
    public void setTheoreticalTime(String theoreticalTime) { this.theoreticalTime = theoreticalTime; }
    public void setVehicleCode(int vehicleCode) { this.vehicleCode = vehicleCode; }

    public int getRouteId() { return routeId; }
    public String getHeadsign() { return headsign; }
    public String getEstimatedTime() { return estimatedTime; }
    public String getTheoreticalTime() { return theoreticalTime; }
    public int getVehicleCode() { return vehicleCode; }
}