package ru.savrey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * 2. С помощью JPA(Hibernate) выполнить:
 * 2.1 Описать сущность Book из пункта 1.1
 * 2.2 Создать Session и сохранить в таблицу 10 книг
 * 2.3 Выгрузить список книг какого-то автора
 * *
 *  * 3.* Создать сущность Автор (id bigint, name varchar), и в сущности Book сделать поле типа Author (OneToOne)
 *  * 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
 *  * 3.2 ** В классе Author создать поле List<Book>, которое описывает список всех книг этого автора. (OneToMany)
 */
public class Homework_hibernate {
    public static void main(String[] args) {

        Author author1 = new Author();
        author1.setName("Bram Stoker");
        author1.setBirthyear(1847);
        author1.setLanguage("Ирландский");

        Author author2 = new Author();
        author2.setName("Гомер");
        author2.setBirthyear(-850);
        author2.setLanguage("Древнегреческий");

        Author author3 = new Author();
        author3.setName("Stephen King");
        author3.setBirthyear(1947);
        author3.setLanguage("Американский");

        Author author4 = new Author();
        author4.setName("Howard Lovecraft");
        author4.setBirthyear(1890);
        author4.setLanguage("Американский");

        Author author5 = new Author();
        author5.setName("Жюль Верн");
        author5.setBirthyear(1828);
        author5.setLanguage("Французский");

        Author author6 = new Author();
        author6.setName("Фенимор Купер");
        author6.setBirthyear(1789);
        author6.setLanguage("Американский");

        Author author7 = new Author();
        author7.setName("Майн Рид");
        author7.setBirthyear(1818);
        author7.setLanguage("Английский");

        Author author8 = new Author();
        author8.setName("Михаил Булгаков");
        author8.setBirthyear(1891);
        author8.setLanguage("Русский");

        Author author9 = new Author();
        author9.setName("Сергей Лукьяненко");
        author9.setBirthyear(1968);
        author9.setLanguage("Русский");

        Author author10 = new Author();
        author10.setName("Glen Cook");
        author10.setBirthyear(1944);
        author10.setLanguage("Американский");

        try(final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory()) {

            // 3
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                session.persist(author1);
                session.persist(author2);
                session.persist(author3);
                session.persist(author4);
                session.persist(author5);
                session.persist(author6);
                session.persist(author7);
                session.persist(author8);
                session.persist(author9);
                session.persist(author10);

                session.getTransaction().commit();
            }

            // 2.2
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Book book1 = new Book();
                book1.setTitle("Dracula");
                book1.setAuthor(author1);
                book1.setPub_year(1897);
                session.persist(book1);

                Book book2 = new Book();
                book2.setTitle("Илиада");
                book2.setAuthor(author2);
                book2.setPub_year(1892);
                session.persist(book2);

                Book book3 = new Book();
                book3.setTitle("It");
                book3.setAuthor(author3);
                book3.setPub_year(1986);
                session.persist(book3);

                Book book4 = new Book();
                book4.setTitle("Dagon");
                book4.setAuthor(author4);
                book4.setPub_year(1919);
                session.persist(book4);

                Book book5 = new Book();
                book5.setTitle("Малыш");
                book5.setAuthor(author5);
                book5.setPub_year(1893);
                session.persist(book5);

                Book book6 = new Book();
                book6.setTitle("Прерия");
                book6.setAuthor(author6);
                book6.setPub_year(1827);
                session.persist(book6);

                Book book7 = new Book();
                book7.setTitle("Квартеронка");
                book7.setAuthor(author7);
                book7.setPub_year(1856);
                session.persist(book7);

                Book book8 = new Book();
                book8.setTitle("Дьяволиада");
                book8.setAuthor(author8);
                book8.setPub_year(1923);
                session.persist(book8);

                Book book9 = new Book();
                book9.setTitle("Черновик");
                book9.setAuthor(author9);
                book9.setPub_year(2005);
                session.persist(book9);

                Book book10 = new Book();
                book10.setTitle("Raker");
                book10.setAuthor(author10);
                book10.setPub_year(1982);
                session.persist(book10);

                session.getTransaction().commit();
            }

            // 2.3
            try(Session session = sessionFactory.openSession()) {

                List<Book> selectedBooks = session.createQuery("SELECT u FROM Book u",
                        Book.class).getResultList();
                System.out.println(selectedBooks);
            }
        }
    }
}
