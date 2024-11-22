package com.example.meetingplaner.UI.View.controller;

import com.example.meetingplaner.BL.models.Meeting;
import com.example.meetingplaner.BL.viewmodels.MeetingPlanerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindowController {

    @FXML
    private ListView<String> meetingsListView;

    @FXML
    private ListView<String> notesListView;

    @FXML
    public Label header;

    @FXML
    private TextField searchField, titleField, fromTimeField, toTimeField, newNoteField;

    @FXML
    private TextArea agendaTextArea;

    @FXML
    private Button noteButton;

    private MeetingPlanerViewModel viewModel;
    private int selectedNote;

    public MainWindowController() {

    }

    public void init(MeetingPlanerViewModel meetingPlannerViewModel) {
        this.viewModel = meetingPlannerViewModel;

        this.header.textProperty().bindBidirectional(viewModel.titleFieldProperty());

        this.searchField.textProperty().bindBidirectional(viewModel.searchFieldProperty());
        this.titleField.textProperty().bindBidirectional(viewModel.titleFieldProperty());
        this.fromTimeField.textProperty().bindBidirectional(viewModel.fromTimeFieldProperty());
        this.toTimeField.textProperty().bindBidirectional(viewModel.toTimeFieldProperty());
        this.newNoteField.textProperty().bindBidirectional(viewModel.newNoteFieldProperty());
        this.agendaTextArea.textProperty().bindBidirectional(viewModel.agendaTextAreaProperty());


        meetingsListView.setItems(viewModel.getMeetingsListView());
        notesListView.setItems(viewModel.getNotesListView());

        notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                newNoteField.clear();
                noteButton.setText("Add Note");
                selectedNote = -1;
            } else {
                newNoteField.setText(newValue);
                noteButton.setText("Update Note");
                selectedNote = notesListView.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    public void generateReportButton(){
        System.out.println("Generating report button");
        viewModel.generateReport();
    }

    @FXML
    public void onSearchButton() {
        System.out.println("Search button clicked!");
        this.meetingsListView.getItems().clear();
        this.notesListView.getItems().clear();
        this.titleField.setText(null);
        this.fromTimeField.setText(null);
        this.toTimeField.setText(null);
        this.agendaTextArea.setText(null);
        viewModel.onSearchButton();
    }

    @FXML
    public void plusButton(){
        System.out.println("Plus button clicked!");
        Meeting newMeeting = viewModel.newMeeting();
        this.titleField.setText(newMeeting.getTitle());
        this.fromTimeField.setText(newMeeting.getFromDate());
        this.toTimeField.setText(newMeeting.getToDate());
        this.agendaTextArea.setText(newMeeting.getAgenda());
        this.searchField.setText("");
    }

    @FXML
    public void minusButton(){
        System.out.println("Minus button clicked!");
        viewModel.deleteChosenMeeting();
        this.titleField.setText(null);
        this.fromTimeField.setText(null);
        this.toTimeField.setText(null);
        this.agendaTextArea.setText(null);
        this.searchField.setText("");
    }

    @FXML
    public void noteButton() {
        System.out.println("Add note button clicked!");
        if(noteButton.getText().equals("Add Note")){
            viewModel.addNote();
            this.newNoteField.setText(null);
            this.searchField.setText("");
        }
        if(noteButton.getText().equals("Update Note")){
            viewModel.updateNote(notesListView.getItems().get(selectedNote));
            this.newNoteField.setText(null);
            this.searchField.setText("");
        }
        selectedNote = -1;
    }

    @FXML
    public void saveMeetingButton() {
        System.out.println("Save meeting button clicked!");
        viewModel.saveMeeting();
        this.searchField.setText("");
    }

    @FXML
    public void meetingClicked() {
        String selectedMeetingByTitle = meetingsListView.getSelectionModel().getSelectedItem();
        if (selectedMeetingByTitle != null) {
            System.out.println("Clicked on: " + selectedMeetingByTitle);
            Meeting clickedMeeting = viewModel.selectMeeting(selectedMeetingByTitle);
            if(clickedMeeting == null)return;
            this.titleField.setText(clickedMeeting.getTitle());
            this.fromTimeField.setText(clickedMeeting.getFromDate());
            this.toTimeField.setText(clickedMeeting.getToDate());
            this.agendaTextArea.setText(clickedMeeting.getAgenda());
        }
    }
}