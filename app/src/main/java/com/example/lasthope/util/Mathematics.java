package com.example.lasthope.util;


import org.threeten.bp.LocalTime;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.format.DateTimeFormatter;

public class Mathematics {

    public Mathematics() {}

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    public long timeTo(String expectedTime) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time1 =  LocalTime.parse(currentTime.format(formatter));
        LocalTime time2 = LocalTime.parse(expectedTime);
        return ChronoUnit.MINUTES.between(time1, time2);
    }


}
