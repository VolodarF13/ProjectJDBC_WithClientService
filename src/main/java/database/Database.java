package database;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database INSTANCE = new Database();

    private static final String DATABASE_URL = "jdbc:h2:./test;AUTO_SERVER=TRUE";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASS = "";

    private Database() {
        Flyway flyway = Flyway.configure()
                .dataSource(DATABASE_URL, DATABASE_USER, DATABASE_PASS)
                .load();

        flyway.migrate();
    }


    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
    }
}
