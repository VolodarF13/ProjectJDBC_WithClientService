package com.client.jdbc.dao;

import com.client.jdbc.database.Database;
import com.client.jdbc.entity.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static final Database INSTANCE = Database.getInstance();

    @Override
    public long create(String name) {
        String create = "INSERT INTO client(name) VALUES (?)";
        try (Connection connection = INSTANCE.getConnection()) {
            try (PreparedStatement statement =
                         connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("ID");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating client: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public String getById(long id) {
        String sql = "SELECT * FROM client WHERE id = ?";

        try (Connection connection = INSTANCE.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("name");
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting client: " + e.getMessage());
        }
    }

    @Override
    public void setName(long id, String name) {
        String sql = "UPDATE client SET name = ? WHERE id = ?";

        try (Connection connection = INSTANCE.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating client name: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection connection = INSTANCE.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting client name: " + e.getMessage());
        }
    }

    @Override
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, name FROM client;";
        try (Connection connection = INSTANCE.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        clients.add(Client.builder()
                                .id(resultSet.getLong("id"))
                                .name(resultSet.getString("name"))
                                .build());
                    }
                    return clients;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting list of clients: " + e.getMessage());
        }
    }
}
