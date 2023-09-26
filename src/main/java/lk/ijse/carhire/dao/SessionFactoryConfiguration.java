package lk.ijse.carhire.dao;

import lk.ijse.carhire.entity.CarCategoryEntity;
import lk.ijse.carhire.entity.CarEntity;
import lk.ijse.carhire.entity.RentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lk.ijse.carhire.entity.CustomerEntity;


public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;

    private static SessionFactory sessionFactory;

    private SessionFactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(RentEntity.class)
                .addAnnotatedClass(CarEntity.class)
                .addAnnotatedClass(CarCategoryEntity.class);


        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance() {
        return sessionFactoryConfiguration == null ?
                sessionFactoryConfiguration = new SessionFactoryConfiguration()
                : sessionFactoryConfiguration;
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

}