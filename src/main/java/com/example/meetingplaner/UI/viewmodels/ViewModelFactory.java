package com.example.meetingplaner.UI.viewmodels;

import com.example.meetingplaner.BL.models.ModelFactory;

public class ViewModelFactory {

    private MeetingPlanerViewModel tourPlannerViewModel;

    public ViewModelFactory(ModelFactory mf) {
        tourPlannerViewModel = new MeetingPlanerViewModel(mf.getDataModel());
    }

    public MeetingPlanerViewModel getTourPlannerViewModel(){
        return tourPlannerViewModel;
    }
}
