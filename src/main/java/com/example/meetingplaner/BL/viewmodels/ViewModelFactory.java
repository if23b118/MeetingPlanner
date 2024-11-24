package com.example.meetingplaner.BL.viewmodels;

import com.example.meetingplaner.BL.models.ModelFactory;

public class ViewModelFactory {

    private MeetingPlanerViewModel meetingPlanerViewModel;

    public ViewModelFactory(ModelFactory mf) {
        meetingPlanerViewModel = new MeetingPlanerViewModel(mf.getDataModel());
    }

    public MeetingPlanerViewModel getMeetingPlanerViewModel(){
        return meetingPlanerViewModel;
    }
}
