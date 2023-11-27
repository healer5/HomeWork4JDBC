package org.example;

import org.example.database.*;
import org.example.services.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseInitService.main(args);

        DatabasePopulateService.main(args);

        DatabaseQueryService queryService = new DatabaseQueryService();

        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();
        printMaxProjectCountClients(maxProjectCountClients);

        List<MaxSalaryWorker> maxSalaryWorkers = queryService.findMaxSalaryWorker();
        printMaxSalaryWorkers(maxSalaryWorkers);

        List<LongestProject> longestProjects = queryService.findLongestProject();
        printLongestProjects(longestProjects);

        List<YoungestEldestWorkers> youngestEldestWorkers = queryService.findYoungestEldestWorkers();
        printYoungestEldestWorkers(youngestEldestWorkers);

        List<ProjectPrice> projectPrices = queryService.findProjectPrice();
        printProjectPrices(projectPrices);
    }

    private static void printMaxProjectCountClients(List<MaxProjectCountClient> clients) {
        System.out.println("Max Project Count Clients:");
        for (MaxProjectCountClient client : clients) {
            System.out.println("Name: " + client.getName() + ", Project Count: " + client.getProjectCount());
        }
        System.out.println();
    }

    private static void printMaxSalaryWorkers(List<MaxSalaryWorker> workers) {
        System.out.println("Max Salary Workers:");
        for (MaxSalaryWorker worker : workers) {
            System.out.println("Name: " + worker.getName() + ", Salary: " + worker.getSalary());
        }
        System.out.println();
    }

    private static void printLongestProjects(List<LongestProject> projects) {
        System.out.println("Longest Projects:");
        for (LongestProject project : projects) {
            System.out.println("Name: " + project.getName() + ", Duration: " + project.getDuration());
        }
        System.out.println();
    }

    private static void printYoungestEldestWorkers(List<YoungestEldestWorkers> workers) {
        System.out.println("Youngest and Eldest Workers:");
        for (YoungestEldestWorkers worker : workers) {
            System.out.println("Youngest Name: " + worker.getYoungestName() + ", Youngest Age: " + worker.getYoungestAge() +
                    ", Eldest Name: " + worker.getEldestName() + ", Eldest Age: " + worker.getEldestAge());
        }
        System.out.println();
    }

    private static void printProjectPrices(List<ProjectPrice> projectPrices) {
        System.out.println("Project Prices:");
        for (ProjectPrice projectPrice : projectPrices) {
            System.out.println("Project Name: " + projectPrice.getProjectName() + ", Price: " + projectPrice.getPrice());
        }
        System.out.println();
    }
}