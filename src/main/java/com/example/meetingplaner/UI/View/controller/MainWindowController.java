package com.example.meetingplaner.UI.View.controller;

import com.example.meetingplaner.UI.viewmodels.MeetingPlanerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindowController {

    @FXML
    private ListView<String> meetingListView;

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


        meetingListView.setItems(viewModel.getMeetingsListView());
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
            System.out.println(newValue);
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
        viewModel.onSearchButton();
    }

    @FXML
    public void plusButton(){
        System.out.println("Plus button clicked!");
        viewModel.newMeeting();
    }

    @FXML
    public void minusButton(){
        System.out.println("Minus button clicked!");
        viewModel.deleteChosenMeeting();
    }

    @FXML
    public void noteButton() {
        System.out.println("Add note button clicked!");
        if(noteButton.getText().equals("Add Note"))viewModel.addNote();
        if(noteButton.getText().equals("Update Note"))viewModel.updateNote(notesListView.getItems().get(selectedNote));
        selectedNote = -1;
    }

    @FXML
    public void saveMeetingButton() {
        System.out.println("Save meeting button clicked!");
        viewModel.saveMeeting();
    }

    @FXML
    public void meetingClicked() {
        String selectedMeetingByTitle = meetingListView.getSelectionModel().getSelectedItem();
        if (selectedMeetingByTitle != null) {
            System.out.println("Clicked on: " + selectedMeetingByTitle);
            viewModel.selectMeeting(selectedMeetingByTitle);
        }
    }
}