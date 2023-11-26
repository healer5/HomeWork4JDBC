package org.example.database;

import org.example.services.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private final Connection connection;

    public DatabaseQueryService() {
        this.connection = Database.getInstance().getConnection();
    }

    public List<Object[]> executeSqlScript(String filePath) {
        String sqlScript = readSqlScript(filePath);
        return executeQuery(sqlScript);
    }

    private String readSqlScript(String filePath) {
        StringBuilder script = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return script.toString();
    }

    private List<Object[]> executeQuery(String sqlScript) {
        List<Object[]> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String[] queries = sqlScript.split(";");
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    public static void main(String[] args) {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        // Find Max Salary Worker
        List<Object[]> maxSalaryWorkers = MaxSalaryWorkerQuery.execute(databaseQueryService.getConnection());
        printResults("Max Salary Worker", maxSalaryWorkers, "Name", "Salary");

        // Find Max Projects Client
        List<Object[]> maxProjectCountClients = MaxProjectsClientQuery.execute(databaseQueryService.getConnection());
        printResults("Max Projects Client", maxProjectCountClients, "Client Name", "Project Count");

        // Find Longest Project
        List<Object[]> longestProjects = LongestProjectQuery.execute(databaseQueryService.getConnection());
        printResults("Longest Project", longestProjects, "Project ID", "Client ID", "Client Name", "Start Date", "Finish Date", "Month Count");

        // Find Youngest and Eldest Workers
        List<Object[]> youngestEldestWorkers = YoungestEldestWorkersQuery.execute(databaseQueryService.getConnection());
        printResults("Youngest and Eldest Workers", youngestEldestWorkers, "Type", "Name", "Birthday");

        // Find Project Prices
        ProjectPricesQuery projectPricesQuery = new ProjectPricesQuery(databaseQueryService.getConnection());
        List<Object[]> projectPrices = projectPricesQuery.execute();
        printResults("Project Prices", projectPrices, "Project ID", "Client ID", "Client Name", "Start Date", "Finish Date", "Price");

        databaseQueryService.closeConnection();
    }

    private static void printResults(String queryName, List<Object[]> results, String... columnNames) {
        System.out.println(queryName + " Results:");

        results.forEach(result -> {
            for (int i = 0; i < columnNames.length; i++) {
                System.out.println(columnNames[i] + ": " + result[i]);
            }
            System.out.println("----------------------");
        });
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}