package com.example.meetingplaner;

import com.example.meetingplaner.BL.models.ModelFactory;
import com.example.meetingplaner.UI.View.ViewHandler;
import com.example.meetingplaner.BL.viewmodels.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MeetingPlaner extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(stage, vmf);
        vh.start();
    }
}
