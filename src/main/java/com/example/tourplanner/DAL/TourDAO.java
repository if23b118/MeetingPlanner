package com.example.tourplanner.DAL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;

import java.util.List;

public class TourDAO {
    private static final Logger logger = LogManager.getLogger(TourDAO.class);

    public void saveTour(Tour tour) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tour);
            transaction.commit();
            logger.info("Tour saved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error saving tour: " + e.getMessage());
            logger.error("Error saving tour: " + e.getMessage(), e);
        }
    }

    public List<Tour> getAllTours() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Tour", Tour.class).list();
        } catch (Exception e) {
            logger.error("Error fetching tours: " + e.getMessage(), e);
            return null;
        }
    }

    public Tour getTourById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            Tour tour = session.get(Tour.class, id);
            System.out.println("Tour found: " + tour.getName());
            return tour;
        } catch (Exception e) {
            logger.error("Error fetching tour by id: " + e.getMessage(), e);
            return null;
        }
    }

    public void updateTour(Tour tour) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tour);
            transaction.commit();
            logger.info("Tour updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error updating tour: " + e.getMessage(), e);
        }
    }

    public void deleteTour(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tour tour = session.get(Tour.class, id);
            if (tour != null) {
                session.delete(tour);
                transaction.commit();
                logger.info("Tour deleted successfully.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting tour: " + e.getMessage(), e);
        }
    }
}
