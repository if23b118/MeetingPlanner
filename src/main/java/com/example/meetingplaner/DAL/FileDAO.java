package com.example.meetingplaner.DAL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.example.meetingplaner.BL.models.Meeting;
import com.example.meetingplaner.BL.models.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDAO {
    private static final Logger logger = LogManager.getLogger(FileDAO.class);

    private static final String MEETING_FILE = Config.get("meetingFile");
    private static final String NOTE_FILE = Config.get("noteFile");

    private static final FileDAO INSTANCE = new FileDAO();

    private FileDAO(){}

    public static FileDAO getInstance(){
        return INSTANCE;
    }

    public void saveMeetings(List<Meeting> meetings) {
        try (FileWriter writer = new FileWriter(MEETING_FILE, false)) {
            for(Meeting meeting : meetings) {
                writer.write(meeting.toString() + "\n");
            }
            logger.info("All Meetings saved successfully.");
        } catch (IOException e) {
            logger.error("Error saving Meetings: {}", e.getMessage(), e);
        }
    }

    public void saveNotes(List<Note> notes) {
        try (FileWriter writer = new FileWriter(NOTE_FILE, false)) {
            for(Note note : notes) {
                writer.write(note.toString() + "\n");
            }
            logger.info("All Notes saved successfully.");
        } catch (IOException e) {
            logger.error("Error saving Notes: {}", e.getMessage(), e);
        }
    }

    public List<Meeting> getAllMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MEETING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                meetings.add(Meeting.fromString(line));
            }
            logger.info("All Meetings loaded Successfully.");
        } catch (IOException e) {
            logger.error("Error loading Meetings: {}", e.getMessage(), e);
        }
        return meetings;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(Note.fromString(line));
            }
            logger.info("All Notes loaded successfully.");
        } catch (IOException e) {
            logger.error("Error loading Notes: {}", e.getMessage(), e);
        }
        return notes;
    }
}
