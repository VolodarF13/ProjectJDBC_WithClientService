package service;

import database.*;
import entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final Database INSTANCE = Database.getInstance();

    public long create(String name) {
        isNameValid(name);
        try (Connection connection = INSTANCE.getConnection()) {
            String create = "INSERT INTO client(name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("ID");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error creating database connection" + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating database connection" + e.getMessage());
        }
        return -1;
    }

    private static void isNameValid(String name) {
        if (name == null || name.isBlank() || name.length() <= 2 || name.length() >= 1000) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public String getById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }
        String sql = "SELECT * FROM client WHERE id = ?";

        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                } else {
                    throw new IllegalArgumentException("No client with id " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting database connection" + e.getMessage());
        }
    }

    public void setName(long id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }
        isNameValid(name);
        String sql = "UPDATE client SET name = ? WHERE id = ?";

        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting database connection" + e.getMessage());
        }
    }

    public void deleteById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Користувача з ID " + id + " видалено успішно!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting database connection" + e.getMessage());
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, name FROM client;";
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    clients.add(Client.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .build());
                }
                return clients;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting database connection" + e.getMessage());
        }
    }

    /*public static void main(String[] args) {
        ClientService clientService = new ClientService();

        try {
            System.out.println("--- 1. Тестування CREATE ---");
            long newId = clientService.create("Artem");
            long secondId = clientService.create("Olena");
            System.out.println("Створено клієнтів з ID: " + newId + ", " + secondId);

            System.out.println("\n--- 2. Тестування GET BY ID ---");
            String name = clientService.getById(newId);
            System.out.println("Клієнт з ID " + newId + ": " + name);

            System.out.println("\n--- 3. Тестування SET NAME ---");
            clientService.setName(newId, "Artem Updated");
            System.out.println("Ім'я оновлено для ID " + newId);
            System.out.println("Нове ім'я в базі: " + clientService.getById(newId));

            System.out.println("\n--- 4. Тестування LIST ALL ---");
            List<Client> allClients = clientService.listAll();
            allClients.forEach(System.out::println);

            System.out.println("\n--- 5. Тестування DELETE ---");
            clientService.deleteById(secondId);
            System.out.println("Список після видалення клієнта " + secondId + ":");
            clientService.listAll().forEach(System.out::println);

            System.out.println("\n--- 6. Тестування ВАЛІДАЦІЇ ---");
            // Це має викинути Exception
            clientService.create("A");

        } catch (Exception e) {
            System.err.println("Помилка валідації або БД: " + e.getMessage());
        }
    }*/
}
