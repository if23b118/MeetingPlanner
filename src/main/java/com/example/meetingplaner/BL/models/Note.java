package com.example.meetingplaner.BL.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {

    private int meetingId;

    private String content;

    public Note(int meetingId, String content) {
        this.meetingId = meetingId;
        this.content = content;
    }

    public Note(){this( 0, "");}

    public String toString() {
        return meetingId + "," + content;
    }

    public static Note fromString(String line) {
        String[] parts = line.split(",");
        return new Note(
                Integer.parseInt(parts[0]),
                parts[1]
        );
    }

    public void decreaseMeetingId() {
        this.meetingId--;
    }
}