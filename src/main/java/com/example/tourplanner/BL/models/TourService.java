package com.example.tourplanner.BL.models;

import com.example.tourplanner.DAL.Tour;
import com.example.tourplanner.DAL.TourLog;
import com.example.tourplanner.DAL.TourLogDAO;

import java.util.List;

public class TourService {

    // Dependency on DAO to fetch TourLog data
    private final TourLogDAO tourLogDAO;

    // Constructor to inject DAO
    public TourService(TourLogDAO tourLogDAO) {
        this.tourLogDAO = tourLogDAO;
    }

    /**
     * Compute the popularity of the tour based on the number of logs.
     * @param tour The tour for which popularity is calculated.
     * @return The number of tour logs as the popularity score.
     */
    public int computePopularity(Tour tour) {
        // Fetch all the logs associated with the tour
        //List<TourLog> logs = tourLogDAO.getLogsForTour(tour.getId());

        // Popularity is defined as the count of logs
        return 0;
        //return logs.size();
    }
}