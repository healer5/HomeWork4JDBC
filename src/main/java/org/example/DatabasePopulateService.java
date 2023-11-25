package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args) {
        try {
            // Get a connection to the database.
            Connection connection = Database.getInstance().getConnection();

            // Read the SQL script from the populate_db.sql file.
            String sqlScript = readSqlScript("src/main/resources/populate_db.sql");

            // Execute the queries from the SQL script.
            executeSqlScript(connection, sqlScript);

            // Close the connection.
            Database.getInstance().closeConnection();

            System.out.println("Database populated successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately.
        }
    }

    private static String readSqlScript(String filePath) throws IOException {
        StringBuilder script = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }
        }
        return script.toString();
    }

    private static void executeSqlScript(Connection connection, String sqlScript) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Split the script into individual queries using semicolons.
            String[] queries = sqlScript.split(";");
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    // Execute each query.
                    statement.executeUpdate(query);
                }
            }
        }
    }
}
