package org.example.services;

public class ProjectPrice {
    private String projectName;
    private double price;

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProjectName() {
        return projectName;
    }

    public double getPrice() {
        return price;
    }
}