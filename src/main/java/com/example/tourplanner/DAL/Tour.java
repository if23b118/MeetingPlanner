package com.example.tourplanner.DAL;




import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tours")
public class Tour{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tour_from")
    private String startLocation;

    @Column(name = "tour_to")
    private String endLocation;

    @Column(name = "transport_type")
    private String transportType;

    @Column(name = "tour_distance")
    private int tourDistance;

    @Column(name = "estimated_time")
    private String estimatedTime;

    @Column(name = "route_info")
    private String routeInfo;

    public Tour(int id, String name, String description, String startLocation, String endLocation,
         String transportType, int tourDistance, String estimatedTime, String routeInfo){
        this.id = id;
        this.name = name;
        this.description = description;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.transportType = transportType;
        this.tourDistance = tourDistance;
        this.estimatedTime = estimatedTime;
        this.routeInfo = routeInfo;
    }

    public Tour(){
        this(0, "", "", "", "", "", 0, "", "");
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStartLocation() {
        return this.startLocation;
    }
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }
    public String getEndLocation() {
        return this.endLocation;
    }
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
    public String getTransportType() {
        return this.transportType;
    }
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }
    public int getTourDistance() {
        return this.tourDistance;
    }
    public void setTourDistance(int tourDistance) {
        this.tourDistance = tourDistance;
    }
    public String getEstimatedTime() {
        return this.estimatedTime;
    }
    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
    public String getRouteInfo() {
        return this.routeInfo;
    }
    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }


}
