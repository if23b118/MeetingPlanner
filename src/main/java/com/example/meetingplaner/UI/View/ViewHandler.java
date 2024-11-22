package com.example.meetingplaner.UI.View;

import com.example.meetingplaner.UI.View.controller.MainWindowController;
import com.example.meetingplaner.BL.viewmodels.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewHandler {

    private final Stage stage;
    private final ViewModelFactory viewModelFactory;

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
            loader.setLocation(getClass().getResource("/com/example/meetingplaner/MainWindow.fxml"));
            root = loader.load();
            MainWindowController view = loader.getController();
            view.init(viewModelFactory.getTourPlannerViewModel());
            stage.setTitle("Meeting Planner");
        }

        scene = new Scene(root, 1000, 600);
        stage.setTitle("Meeting Planner");
        stage.setScene(scene);
        stage.show();
    }
}
