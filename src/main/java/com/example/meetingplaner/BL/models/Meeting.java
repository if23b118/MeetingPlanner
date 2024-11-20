package com.example.meetingplaner.BL.models;

import lombok.Getter;


@Getter
public class Meeting {

    private int id;

    private String title;

    private String fromDate;

    private String toDate;

    private String agenda;

    public Meeting(int id, String title, String fromDate, String toDate, String agenda){
        this.id = id;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.agenda = agenda;
    }

    public Meeting(){
        this(0, "", "", "", "");
    }

    public String toString() {
        return id + "," + title + "," + fromDate + "," + toDate + "," + agenda;
    }

    public static Meeting fromString(String line) {
        String[] parts = line.split(",");
        return new Meeting(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4]
        );
    }

    public void decreaseId() {
        this.id--;
    }

    public void update(String title, String fromDate, String toDate, String agenda){
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.agenda = agenda;
    }
}
