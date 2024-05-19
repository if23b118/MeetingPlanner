package com.example.tourplanner.viewmodels;

import com.example.tourplanner.BL.models.ModelFactory;

public class ViewModelFactory {

    private TourPlannerViewModel tourPlannerViewModel;

    public ViewModelFactory(ModelFactory mf) {
        tourPlannerViewModel = new TourPlannerViewModel(mf.getDataModel());
    }

    public TourPlannerViewModel getTourPlannerViewModel(){
        return tourPlannerViewModel;
    }
}
