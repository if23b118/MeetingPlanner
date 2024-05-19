package com.example.tourplanner.BL.models;

import java.util.Date;

public class TourEntry {
    private String date;
    private String duration;
    private String distance;

    public TourEntry(String date, String duration, String distance) {
        this.date = date;
        this.duration = duration;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
