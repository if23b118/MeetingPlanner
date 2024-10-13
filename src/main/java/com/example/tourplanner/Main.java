package com.example.tourplanner;

import com.example.tourplanner.DAL.HibernateUtil;
import com.example.tourplanner.DAL.Tour;
import com.example.tourplanner.DAL.TourDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception{
        Application.launch(TourPlanner.class);
        TourDAO dao = new TourDAO();
        Tour t = dao.getTourById(1);
        System.out.println(t.getName());
        //dao.saveTour(new Tour(19, "","","","","",9.0,"",""));
    }
}