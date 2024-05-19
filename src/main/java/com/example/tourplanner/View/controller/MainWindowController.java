package com.example.tourplanner.View.controller;

import com.example.tourplanner.viewmodels.TourPlannerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowController {
//-----------------------------------------
    public TableColumn<String, String> dateColumn;
    public TableColumn durationColumn;
    public TableColumn distanceColumn;
    @FXML
    TextField searchField;

    @FXML
    ListView<String> toursListView;

    @FXML
    TableView tourLogsTableView;

    private TourPlannerViewModel viewModel;

    public MainWindowController() {

    }

    public void init(TourPlannerViewModel tourPlannerViewModel) {
        this.viewModel = tourPlannerViewModel;

        searchField.textProperty().bindBidirectional(viewModel.searchFieldProperty());
        toursListView.itemsProperty().bind(viewModel.toursListViewProperty());

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        tourLogsTableView.setItems(viewModel.getData());
    }

    @FXML
    public void onSearchButton() {
        System.out.println("Search button clicked!");
        viewModel.onSearchButton();
    }
}