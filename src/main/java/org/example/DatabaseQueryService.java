package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private final String FILE_FOR_FIND_MAX_SALARY_WORKER = "src/sql/find_max_salary_worker.sql";
    private final String FILE_FOR_FIND_MAX_PROJECTS_CLIENT = "src/sql/find_max_projects_client.sql";
    private final String FILE_FOR_FIND_LONGEST_PROJECT = "src/sql/find_longest_project.sql";
    private final String FILE_FOR_FIND_YOUNGEST_ELDEST_WORKERS = "src/sql/find_youngest_eldest_workers.sql";
    private final String FILE_FOR_FIND_PROJECT_PRICE = "src/sql/print_project_prices.sql";

    private final Connection connection;

    public DatabaseQueryService() {
        this.connection = Database.getInstance().getConnection();
    }

    public List<Object[]> findMaxSalaryWorker() {
        return executeQuery(FILE_FOR_FIND_MAX_SALARY_WORKER);
    }

    public List<Object[]> findMaxProjectsClient() {
        return executeQuery(FILE_FOR_FIND_MAX_PROJECTS_CLIENT);
    }

    public List<Object[]> findLongestProject() {
        return executeQuery(FILE_FOR_FIND_LONGEST_PROJECT);
    }

    public List<Object[]> findYoungestEldestWorkers() {
        return executeQuery(FILE_FOR_FIND_YOUNGEST_ELDEST_WORKERS);
    }

    public List<Object[]> findProjectPrice() {
        return executeQuery(FILE_FOR_FIND_PROJECT_PRICE);
    }

    private List<Object[]> executeQuery(String filePath) {
        List<Object[]> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(filePath));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] result = new Object[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    result[i] = resultSet.getObject(i + 1);
                }

                resultList.add(result);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    public static void main(String[] args) {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        // Find Max Salary Worker
        List<Object[]> maxSalaryWorkers = databaseQueryService.findMaxSalaryWorker();
        maxSalaryWorkers.forEach(result -> {
            String name = (String) result[0];
            int salary = (int) result[1];
            System.out.println("Max Salary Worker - Name: " + name + ", Salary: " + salary);
        });


        // Find Max Projects Client
        List<Object[]> maxProjectCountClients = databaseQueryService.findMaxProjectsClient();
        maxProjectCountClients.forEach(result -> {
            String clientName = (String) result[0];
            int projectCount = (int) result[1];
            System.out.println("Max Projects Client - Client Name: " + clientName + ", Project Count: " + projectCount);
        });


        List<Object[]> longestProjects = databaseQueryService.findLongestProject();
        longestProjects.forEach(result -> {
            int projectId = (int) result[0];
            int clientId = (int) result[1];
            String clientName = (String) result[2];
            String startDate = result[3].toString();
            String finishDate = result[4].toString();
            int monthCount = (int) result[5];
            System.out.println("Longest Project - Project ID: " + projectId + ", Client ID: " + clientId +
                    ", Client Name: " + clientName + ", Start Date: " + startDate +
                    ", Finish Date: " + finishDate + ", Month Count: " + monthCount);
        });


        // Find Youngest and Eldest Workers
        List<Object[]> youngestEldestWorkers = databaseQueryService.findYoungestEldestWorkers();
        youngestEldestWorkers.forEach(result -> {
            String type = (String) result[0];
            String name = (String) result[1];
            String birthday = result[2].toString();
            System.out.println(type + " Worker - Name: " + name + ", Birthday: " + birthday);
        });

        System.out.println("----------------------");

        // Find Project Prices
        List<Object[]> projectPrices = databaseQueryService.findProjectPrice();
        projectPrices.forEach(result -> {
            int projectId = (int) result[0];
            int clientId = (int) result[1];
            String clientName = (String) result[2];
            String startDate = result[3].toString();
            String finishDate = result[4].toString();
            int price = (int) result[5];
            System.out.println("Project Price - Project ID: " + projectId + ", Client ID: " + clientId +
                    ", Client Name: " + clientName + ", Start Date: " + startDate +
                    ", Finish Date: " + finishDate + ", Price: " + price);
        });

        Database.getInstance().closeConnection();
    }
}