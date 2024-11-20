package com.example.meetingplaner.BL.models;

import com.example.meetingplaner.DAL.FileDAO;
import javafx.collections.ObservableList;

import java.util.List;

public class DataModelManager implements DataModel{
    private final FileDAO fileDAO = new FileDAO();

    @Override
    public List<Meeting> getAllMeetings() {
        return fileDAO.getAllMeetings();
    }

    @Override
    public List<Note> getAllNotes() {
        return fileDAO.getAllNotes();
    }

    @Override
    public void saveAll(ObservableList<Meeting> meetings, ObservableList<Note> notes) {
        fileDAO.saveMeetings(meetings);
        fileDAO.saveNotes(notes);
    }
}
