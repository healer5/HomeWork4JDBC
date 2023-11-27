package org.example.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/populate_db.sql")));
            Statement statement = connection.createStatement();
            statement.execute(sqlContent);
            System.out.println("Database populated successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
