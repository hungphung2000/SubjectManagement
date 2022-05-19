package com.example.studentmanagement.model;

public class Subject {
    private int id;
    private String title;
    private int credit;
    private String time;
    private String place;

    public Subject(String title, int credit, String time, String place) {
        this.title = title;
        this.credit = credit;
        this.time = time;
        this.place = place;
    }

    public Subject(int id, String title, int credit, String time, String place) {
        this.id = id;
        this.title = title;
        this.credit = credit;
        this.time = time;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCredit() {
        return credit;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
