package com.example.celebsrating.beans;

public class Celeb {
    private int id;
    private String fullName;
    private String photoUrl;
    private float rating;
    private static int counter = 0;

    public Celeb(String fullName, String photoUrl, float rating) {
        this.id       = ++counter;
        this.fullName = fullName;
        this.photoUrl = photoUrl;
        this.rating   = rating;
    }

    public int    getId()       { return id; }
    public String getFullName() { return fullName; }
    public String getPhotoUrl() { return photoUrl; }
    public float  getRating()   { return rating; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public void setRating(float rating)      { this.rating   = rating; }
}