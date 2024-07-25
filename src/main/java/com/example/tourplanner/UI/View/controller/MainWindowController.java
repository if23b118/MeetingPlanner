package com.example.tourplanner.UI.View.controller;

import com.example.tourplanner.BL.models.TourEntry;
import com.example.tourplanner.UI.viewmodels.TourPlannerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowController {

    public TableColumn<TourEntry, String> dateColumn;
    public TableColumn<TourEntry, String> durationColumn;
    public TableColumn<TourEntry, String> distanceColumn;
    public Button searchButton;
    public Menu menu1;
    public Menu menu2;
    @FXML
    TextField searchField;

    @FXML
    ListView<String> toursListView;

    @FXML
    TableView<TourEntry> tourLogsTableView;

    private TourPlannerViewModel viewModel;

    public MainWindowController() {

    }

    public void init(TourPlannerViewModel tourPlannerViewModel) {
        this.viewModel = tourPlannerViewModel;

        searchField.textProperty().bindBidirectional(viewModel.searchFieldProperty());

        tourLogsTableView.setItems(viewModel.getData());

        dateColumn.setCellValueFactory(new PropertyValueFactory<TourEntry, String>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<TourEntry, String>("duration"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<TourEntry, String>("distance"));

        toursListView.setItems(viewModel.getToursListView());
    }

    @FXML
    public void onSearchButton() {
        System.out.println("Search button clicked!");
        viewModel.onSearchButton();
    }

    public void addTour(ActionEvent actionEvent) {
        System.out.println(actionEvent);
    }

    public void deleteTour(ActionEvent actionEvent) {
    }

    public void modifyTour(ActionEvent actionEvent) {
    }

    public void addLog(ActionEvent actionEvent) {
    }

    public void deleteLog(ActionEvent actionEvent) {
    }

    public void modifyLog(ActionEvent actionEvent) {
    }
}