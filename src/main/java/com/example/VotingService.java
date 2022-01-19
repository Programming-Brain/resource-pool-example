package com.example;

import org.mariadb.jdbc.MariaDbPoolDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VotingService {

    private static VotingService instance;
    private static MariaDbPoolDataSource dataSource;

    public static synchronized VotingService getInstance() {
        return instance == null ? instance = new VotingService() : instance;
    }

    private VotingService() {
        try {
            dataSource = new MariaDbPoolDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/jdbc_demo");
            dataSource.setUser("user");
            dataSource.setPassword("password");
            dataSource.setMaxPoolSize(10);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        dataSource.close();
        instance = null;
    }

    public String getRatings() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name, votes FROM programming_language"
            );
            ResultSet resultSet = statement.executeQuery();
            String output = "";
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int votes = resultSet.getInt("votes");
                output += name + ": " + votes + ". ";
            }
            return output;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void vote(String name) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE programming_language SET votes = votes + 1 WHERE name = ?"
            );
            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
