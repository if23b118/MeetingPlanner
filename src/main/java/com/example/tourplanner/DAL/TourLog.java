package com.example.tourplanner.DAL;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tour_logs")
public class TourLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int logId;

    @JoinColumn(name = "tour_id")
    private int tourId;

    @Column(name = "date_time")
    private Date logDatetime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "total_distance")
    private Double totalDistance;

    @Column(name = "total_time")
    private String totalTime;

    @Column(name = "rating")
    private int rating;

    public TourLog() {

    }

    // Getters and Setters
    // Constructors (default and parameterized)
}
