package org.example.repository.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Slf4j
public class HibernateUtils {
   static EntityManagerFactory entityManagerFactory;
   static EntityManager entityManager;

   static {
       entityManagerFactory = Persistence.createEntityManagerFactory("default");
       entityManager = entityManagerFactory.createEntityManager();
   }

}
