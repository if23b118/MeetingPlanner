package com.example.meetingplaner.BL.models;

import javafx.collections.ObservableList;

import java.util.List;

public interface DataModel {

    List<Meeting> getAllMeetings();

    List<Note> getAllNotes();

    void saveAll(ObservableList<Meeting> meetings, ObservableList<Note> notes);
}
