package ru.savrey;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws SQLException {
        // JDBC Java DataBase Connectivity

        // java.sql.Driver
        //Class.forName("org.h2.Driver");

        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database");
        // DriverManager.getConnection("jdbc:h2:file:/data/database");
        prepareTables(connection);
        insertData(connection);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name FROM users WHERE name = ?"
        )) {
            preparedStatement.setString(1, "Smythe");
        }


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM users");
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");

                System.out.println("id = " + userId + ". name = " + userName);
            }
        }

        connection.close();
    }

    private static void prepareTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                CREATE TABLE if not exists users (
                   id bigint,
                   name varchar(255)
                )
                """);
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    INSERT INTO users(id, name)
                        values
                           (1, 'Igor'),
                           (2, 'Frank'),
                           (3, 'Jezebel'),
                           (4, 'Bethany')
                    """);
        }
    }

    private static void updateData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int updatedRows = statement.executeUpdate("" +
                    "UPDATE users " +
                    "SET name = 'unknown'" +
                    "WHERE id > 2");
        }
    }

    private static void deleteData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int deletedRows = statement.executeUpdate("" +
                    "DELETE FROM users " +
                    "WHERE id = 3");
        }
    }
}