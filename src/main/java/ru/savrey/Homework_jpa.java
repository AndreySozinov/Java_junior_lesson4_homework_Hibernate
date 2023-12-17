package ru.savrey;

import java.sql.*;

/**
 * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgres, mysql, sqllite, h2, ...)
 *
 * 1. С помощью JDBC выполнить:
 * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
 * 1.2 Добавить в таблицу 10 книг
 * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
 */
public class Homework_jpa {
    public static void main(String[] args) throws SQLException {
        // JDBC
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database");
        // 1.1
        createTables(connection);
        // 1.2
        insertData(connection);
        // 1.3
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE author = 'Гомер'");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("pub_year");

                System.out.println("id: " + id + "; title: " + title + "; author: " + author +
                        "; year: " + year);
            }
        }

        connection.close();
    }


    private static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                CREATE TABLE if not exists books (
                   id bigint,
                   title varchar(255),
                   author varchar(255),
                   pub_year int
                )
                """);
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    INSERT INTO books(id, title, author, pub_year)
                        values
                           (1, 'Dracula', 'Bram Stoker', 1897),
                           (2, 'Илиада', 'Гомер', 1892),
                           (3, 'It', 'Stephen King', 1986),
                           (4, 'Dagon', 'Howard Lovecraft', 1919),
                           (5, 'Малыш', 'Жюль Верн', 1893),
                           (6, 'Прерия', 'Фенимор Купер', 1827),
                           (7, 'Квартеронка', 'Майн Рил', 1856),
                           (8, 'Дьяволиада', 'Михаил Булгаков', 1923),
                           (9, 'Черновик', 'Сергей Лукьяненко', 2005),
                           (10, 'Raker', 'Glen Cook', 1982)
                    """);
        }
    }
}
