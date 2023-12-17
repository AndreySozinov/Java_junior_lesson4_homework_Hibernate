package ru.savrey;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.LongStream;

public class Jpa {

    public static void main(String[] args) {
        // JPA Java Persistence API



        try(final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory()) {

            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                List<User> users = LongStream.rangeClosed(1, 10)
                        .mapToObj(User::new)
                        .peek(it -> it.setName("User #" + it.getId()))
                        .peek(session::persist).toList();


                session.getTransaction().commit();
            }

            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                User loadedUser = session.get(User.class, 1);
                loadedUser.setName("updated");

                session.merge(loadedUser);
                session.getTransaction().commit();
            }

            try(Session session = sessionFactory.openSession()) {
                User loadedUser = session.get(User.class, 1);
                System.out.println("User: " + loadedUser.getName());

                // jql - java query language
                List<User> selectedUsers = session.createQuery("SELECT u FROM User u WHERE id > 1",
                        User.class).getResultList();
                System.out.println(selectedUsers);
            }
        }

    }
}
