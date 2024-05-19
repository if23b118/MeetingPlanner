package com.example.tourplanner.BL.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class TourEntry {
    private StringProperty date;
    private StringProperty duration;
    private StringProperty distance;

    public TourEntry(String date, String duration, String distance) {
        this.date = new SimpleStringProperty(date);
        this.duration = new SimpleStringProperty(duration);
        this.distance = new SimpleStringProperty(distance);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getDistance() {
        return distance.get();
    }

    public void setDistance(String distance) {
        this.distance.set(distance);
    }
}
