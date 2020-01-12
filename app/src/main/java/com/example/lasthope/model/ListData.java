package com.example.lasthope.model;

public class ListData {

    String line;
    String direction;
    String delay;

    int vehicleCode;

    public ListData(String line, String direction, String delay, int vehicleCode) {
        this.line=line;
        this.direction=direction;
        this.delay=delay;
        this.vehicleCode=vehicleCode;
    }

    public String getLine() {
        return line;
    }

    public String getDirection() {
        return direction;
    }

    public String getDelay() {
        return delay;
    }

    public int getVehicleCode() {
        return vehicleCode;
    }

}