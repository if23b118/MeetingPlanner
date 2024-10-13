package com.example.tourplanner.DAL;

import org.hibernate.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;

import java.util.List;

public class TourLogDAO {
    private static final Logger logger = LogManager.getLogger(TourLogDAO.class);

    public void saveTourLog(TourLog log) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(log);
            transaction.commit();
            logger.info("TourLog saved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error saving tour log: " + e.getMessage(), e);
        }
    }

    public List<TourLog> getAllTourLogs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TourLog", TourLog.class).list();
        } catch (Exception e) {
            logger.error("Error fetching tour logs: " + e.getMessage(), e);
            return null;
        }
    }

    public TourLog getTourLogById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TourLog.class, id);
        } catch (Exception e) {
            logger.error("Error fetching tour log by id: " + e.getMessage(), e);
            return null;
        }
    }

    public void updateTourLog(TourLog log) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(log);
            transaction.commit();
            logger.info("TourLog updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error updating tour log: " + e.getMessage(), e);
        }
    }

    public void deleteTourLog(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TourLog log = session.get(TourLog.class, id);
            if (log != null) {
                session.delete(log);
                transaction.commit();
                logger.info("TourLog deleted successfully.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting tour log: " + e.getMessage(), e);
        }
    }
}
