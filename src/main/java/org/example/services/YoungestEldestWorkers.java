package org.example.services;

public class YoungestEldestWorkers {
    private String youngestName;
    private int youngestAge;
    private String eldestName;
    private int eldestAge;

    public void setYoungestName(String youngestName) {
        this.youngestName = youngestName;
    }

    public void setYoungestAge(int youngestAge) {
        this.youngestAge = youngestAge;
    }

    public void setEldestName(String eldestName) {
        this.eldestName = eldestName;
    }

    public void setEldestAge(int eldestAge) {
        this.eldestAge = eldestAge;
    }

    public String getYoungestName() {
        return youngestName;
    }

    public int getYoungestAge() {
        return youngestAge;
    }

    public String getEldestName() {
        return eldestName;
    }

    public int getEldestAge() {
        return eldestAge;
    }
}