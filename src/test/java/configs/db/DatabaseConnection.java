package configs.db;

import configs.reporting.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            String connectionUrl = ""; //TODO add DB connection url
            this.connection = driver.connect(connectionUrl, new Properties());
            if (this.connection != null) {
                Log.info("Connected to DB");
            } else {
                throw new NullPointerException();
            }
        } catch (ClassNotFoundException | SQLException | IllegalAccessException ex) {
            Log.info("Database Connection Creation Failure Message: " + ex.getMessage());
            Log.info("Database Connection Creation Failure Cause: " + ex.getCause());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
