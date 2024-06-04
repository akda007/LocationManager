package com.locationmanager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.locationmanager.model.CityData;
import com.locationmanager.model.StateData;

public class DatabaseController {
    private static Session getSession() {
        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();

        return session;
    }

    public static List<CityData> getCities() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Query q = session.createQuery("from CityData");
        
        @SuppressWarnings("unchecked")
        List<CityData> cities = q.list();
        transaction.commit();

        return cities;
    }

    public static List<StateData> getStates() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Query q = session.createQuery("from StateData");
        
        @SuppressWarnings("unchecked")
        List<StateData> states = q.list();
        transaction.commit();

        return states;
    }

    public static void saveState(StateData state) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.save(state);
        
        transaction.commit();
    }

    public static void saveCity(CityData city) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.save(city);
        transaction.commit();
    }
}
