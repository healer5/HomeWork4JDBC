package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL),
                    PropertiesUtil.get(USER),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;

    }

//    public static synchronized Database getInstance() {
//        if (instance == null) {
//            instance = new Database();
//        }
//        return instance;
//    }

    public Connection getConnection() {
        return connection;
    }
}