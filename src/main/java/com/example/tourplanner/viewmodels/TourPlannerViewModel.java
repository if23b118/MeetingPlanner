package com.example.tourplanner.viewmodels;

import com.example.tourplanner.BL.models.DataModel;
import com.example.tourplanner.BL.models.TourEntry;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class TourPlannerViewModel {

    private StringProperty searchField;
    private ListProperty<String> toursListView;
    private ObservableList<TourEntry> data =
            FXCollections.observableArrayList(
                    new TourEntry("1.1.1","1","1"),
                    new TourEntry("2.2.2","2","2")
            );

    private DataModel model;

    public TourPlannerViewModel(DataModel model) {
        this.model = model;
        searchField = new SimpleStringProperty();
        toursListView = new SimpleListProperty<>();
    }

    public void saveDataToList(){
        new TourEntry("3.3.3","3","3");
    }

    public StringProperty searchFieldProperty() {
        return searchField;
    }

    public Property<ObservableList<String>> toursListViewProperty() {
        return toursListView;
    }

    public ObservableList<TourEntry> getData() {
        return data;
    }

    public void onSearchButton() {
        System.out.println("search for: "+searchField.getValueSafe());
    }
}
