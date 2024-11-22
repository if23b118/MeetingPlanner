package com.example.meetingplaner.UI.viewmodels;

import com.example.meetingplaner.BL.models.*;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.layout.properties.TextAlignment;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.util.List;

@Getter
public class MeetingPlanerViewModel {
    private static final Logger logger = LogManager.getLogger(MeetingPlanerViewModel.class);

    private final ObservableList<Note> notes = FXCollections.observableArrayList();
    private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

    private final StringProperty searchField, titleField, fromTimeField, toTimeField, newNoteField;
    private final ObservableList<String> notesListView = FXCollections.observableArrayList();
    private final ObservableList<String> meetingsListView = FXCollections.observableArrayList();
    private final StringProperty agendaTextArea;

    private int chosenMeetingById;

    private final DataModel model;

    public MeetingPlanerViewModel(DataModel model) {
        this.model = model;
        searchField = new SimpleStringProperty();
        titleField = new SimpleStringProperty();
        fromTimeField = new SimpleStringProperty();
        toTimeField = new SimpleStringProperty();
        newNoteField = new SimpleStringProperty();
        agendaTextArea = new SimpleStringProperty();
        this.loadMeetings();
        this.loadNotes();
        this.fillMeetingListViews();
    }


    public StringProperty searchFieldProperty() {
        return searchField;
    }

    public Property<String> titleFieldProperty() {
        return titleField;
    }

    public Property<String> fromTimeFieldProperty() {
        return fromTimeField;
    }

    public Property<String> toTimeFieldProperty() {
        return toTimeField;
    }

    public Property<String> newNoteFieldProperty() {
        return newNoteField;
    }

    public Property<String> agendaTextAreaProperty() {
        return agendaTextArea;
    }

    public void onSearchButton() {
        String lowerCaseSearch = searchField.get().toLowerCase();
        this.chosenMeetingById = -1;

        for(Meeting meeting : meetings) {
            for(Note note : notes) {
                if(note.getMeetingId() != meeting.getId())continue;
                if(note.getContent().toLowerCase().contains(lowerCaseSearch)) {
                    meetingsListView.add(meeting.getTitle());
                    break;
                }
            }

            if(!meetingsListView.isEmpty()) {
                if(meetingsListView.getLast().equals(meeting.getTitle()))continue;
            }

            for(String text : meeting.toString().split(",")){
                if(text.toLowerCase().contains(lowerCaseSearch)){
                    meetingsListView.add(meeting.getTitle());
                    break;
                }
            }
        }
    }

    public void loadMeetings(){
        meetings.clear();
        List<Meeting> meetingList = model.getAllMeetings();
        meetings.addAll(meetingList);
    }

    public void loadNotes(){
        notes.clear();
        List<Note> noteList = model.getAllNotes();
        notes.addAll(noteList);
    }

    public void fillMeetingListViews(){
        meetingsListView.clear();
        for (Meeting meeting : meetings) {
            meetingsListView.add(meeting.getTitle());
        }
    }

    public void fillNoteListViewsByMeetingId(){
        notesListView.clear();
        for(Note note : notes){
            if(note.getMeetingId() == chosenMeetingById){
                notesListView.add(note.getContent());
            }
        }
    }

    public Meeting newMeeting(){
        Meeting newMeeting = new Meeting(meetings.size(),"(Title)","(YYYY-MM-DD TT:TT)","(YYYY-MM-DD TT:TT)","(agenda)");
        meetings.add(newMeeting);
        logger.info("Creating new Meeting: {}", newMeeting);
        this.chosenMeetingById = newMeeting.getId();
        this.fillMeetingListViews();
        this.fillNoteListViewsByMeetingId();
        return newMeeting;
    }

    public void deleteChosenMeeting(){
        if(this.chosenMeetingById < 0){
            logger.info("Nothing chosen to be deleted");
            return;
        }
        Meeting chosenMeeting = meetings.get(chosenMeetingById);
        meetings.remove(chosenMeeting);

        notes.removeIf(note -> note.getMeetingId() == chosenMeeting.getId());

        for(Meeting meeting : meetings){
            if(meeting.getId() > chosenMeeting.getId()){
                meeting.decreaseId();
            }
        }

        for(Note note : notes){
            if(note.getMeetingId() > chosenMeeting.getId()){
                note.decreaseMeetingId();
            }
        }

        this.chosenMeetingById = -1;
        this.fillMeetingListViews();
        this.fillNoteListViewsByMeetingId();
        logger.info("Deleted Meeting: {}", chosenMeeting);
        model.saveAll(meetings, notes);
    }

    public void saveMeeting(){
        String newtitleField = titleField.getValueSafe();
        String newfromTimeField = fromTimeField.getValueSafe();
        String newtoTimeField = toTimeField.getValueSafe();
        String newagendaField = agendaTextArea.getValueSafe();

        if(newtitleField.isEmpty()) newtitleField = " ";
        if(newfromTimeField.isEmpty()) newfromTimeField = " ";
        if(newtoTimeField.isEmpty()) newtoTimeField = " ";
        if(newagendaField.isEmpty()) newagendaField = " ";
        newagendaField = newagendaField.replaceAll("\\r?\\n", "-");

        for(Meeting meeting: meetings){
            if(meeting.getTitle().equals(newtitleField)){
                this.titleField.set("");
                logger.info("Title already taken: {}", meeting.getTitle());
                return;
            }
        }

        meetings.get(chosenMeetingById).update(newtitleField,
                                                newfromTimeField,
                                                newtoTimeField,
                                                newagendaField);
        logger.info("Saved Meeting: {}", meetings.get(chosenMeetingById));
        model.saveAll(meetings, notes);
        this.fillMeetingListViews();
    }

    public void addNote(){
        String newNote = newNoteField.getValueSafe();
        if(newNote.isEmpty()){
            logger.info("Note cannot be empty!");
            return;
        }
        notes.add(new Note(this.chosenMeetingById, newNote));
        logger.info("Added Note: {}", newNote);
        model.saveAll(meetings, notes);
        this.fillMeetingListViews();
        this.fillNoteListViewsByMeetingId();
    }

    public void updateNote(String oldNoteText) {
        int chosenNoteIndex = -1;
        for(Note note : notes){
            if(note.getContent().equals(oldNoteText)){
                chosenNoteIndex = notes.indexOf(note);
                break;
            }
        }
        if(chosenNoteIndex < 0) return;
        if(newNoteField.getValueSafe().isEmpty())notes.remove(chosenNoteIndex);
        else notes.get(chosenNoteIndex).setContent(newNoteField.getValueSafe());
        logger.info("Note updated: {}", newNoteField.getValueSafe());
        model.saveAll(meetings, notes);
        this.fillMeetingListViews();
        this.fillNoteListViewsByMeetingId();
    }

    public void generateReport(){
        String outputPath = "meeting_report_"+meetings.get(chosenMeetingById).getTitle()+".pdf";
        Meeting chosenMeeting = meetings.get(chosenMeetingById);

        try {
            // Create PDFWriter instance
            PdfWriter writer = new PdfWriter(outputPath);

            // Create PDFDocument instance
            PdfDocument pdf = new PdfDocument(writer);

            // Create Document instance
            Document document = new Document(pdf);

            // Add a title
            Text title = new Text("Meeting Report\n").setFontSize(18).setBold();
            document.add(new Paragraph(title).setTextAlignment(TextAlignment.CENTER));

            // Add the report content
            document.add(new Paragraph(("Title: " + chosenMeeting.getTitle())));
            document.add(new Paragraph(("From: " + chosenMeeting.getFromDate())));
            document.add(new Paragraph(("To: " + chosenMeeting.getToDate())));
            document.add(new Paragraph(("Agenda: " + chosenMeeting.getAgenda())));
            document.add(new Paragraph(("Notes: ")));

            for(Note note : notes){
                if(note.getMeetingId() == chosenMeeting.getId()){
                    document.add(new Paragraph(note.getContent()));
                }
            }

            // Close the document
            document.close();
            logger.info("PDF report created at: {}", outputPath);

        } catch (IOException | FileNotFoundException e) {
            logger.error("Error creating PDF report", e);
        }
    }

    public Meeting selectMeeting(String chosenTitle){
        for(Meeting meeting : meetings){
            if(meeting.getTitle().equals(chosenTitle)){
                this.chosenMeetingById = meeting.getId();
                this.fillNoteListViewsByMeetingId();
                return meeting;
            }
        }
        return null;
    }
}
