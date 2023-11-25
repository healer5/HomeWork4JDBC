package org.example;

import java.util.List;

public class Main {

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


        // Find Longest Project
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