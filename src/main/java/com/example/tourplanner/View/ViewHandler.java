package com.example.tourplanner.View;

import com.example.tourplanner.View.controller.MainWindowController;
import com.example.tourplanner.viewmodels.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;

public class ViewHandler {

    private Stage stage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(Stage stage, ViewModelFactory vmf) {
        this.stage = stage;
        this.viewModelFactory = vmf;
    }

    public void start() throws Exception{
        openView("MainWindow");
    }

    private void openView(String viewToOpen) throws Exception{
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        if("MainWindow".equals(viewToOpen)){
            loader.setLocation(getClass().getResource("/com/example/tourplanner/MainWindow.fxml"));
            root = loader.load();
            MainWindowController view = loader.getController();
            view.init(viewModelFactory.getTourPlannerViewModel());
            stage.setTitle("Tour Planner");
        }

        scene = new Scene(root, 1000, 600);
        stage.setTitle("Tour Planner");
        stage.setScene(scene);
        stage.show();
    }
}
