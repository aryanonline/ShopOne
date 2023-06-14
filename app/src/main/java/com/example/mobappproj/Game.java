package com.example.mobappproj;

public class Game {

    private String title;
    private String genre;
    private String publisher;
    private String price;

    public Game(){

    }

    public Game (String title, String genre, String publisher, String price){
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
