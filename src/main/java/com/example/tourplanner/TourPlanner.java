package com.example.tourplanner;

import com.example.tourplanner.BL.models.ModelFactory;
import com.example.tourplanner.View.ViewHandler;
import com.example.tourplanner.viewmodels.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class TourPlanner extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(stage, vmf);
        vh.start();
    }
}
