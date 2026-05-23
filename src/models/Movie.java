package models;

import enums.*;
import java.util.UUID;

public class Movie {
    private final String id;
    private String title;
    private MovieGenre genre;
    private MovieRating rating;
    private int releaseYear;

    private MovieStatus status;
    private int stockQuantity;
    private double price;
    private int timesRented;

    public Movie(String title, MovieGenre genre, MovieRating rating,
      int stockQuantity, double price, int releaseYear) {
        this.id = "MOV-" + UUID.randomUUID().toString().substring(0, 5);
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.status = MovieStatus.AVAILABLE;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.releaseYear = releaseYear;
        this.timesRented = 0;

        if (stockQuantity == 0) {
            this.status = MovieStatus.OUT_OF_STOCK;
        }
    }

    public boolean rentMovie() {
        if (stockQuantity <= 0) {
            return false;
        }

        stockQuantity--;
        timesRented++;
        return true;
    }    

    public void returnMovie() {
        stockQuantity++;
    }

    public String getMovieId() {
        return id;
    }

    public String getMovieTitle() {
        return title;
    }

    public MovieGenre getMovieGenre() {
        return genre;
    }

    public MovieRating getMovieRating() {
        return rating;
    }

    public MovieStatus getMovieStatus() {
        if (stockQuantity <= 0) {
            return MovieStatus.OUT_OF_STOCK;
        }
        return MovieStatus.AVAILABLE;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getTimesRented() {
        return timesRented;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public void setRating(MovieRating rating) {
        this.rating = rating;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public void displayInfo() {
        System.out.println(
        "Title: " + title +
        "\nID: " + id +
        "\nGenre: " + genre +
        "\nAge Rating: " + rating +
        "\nRelease Year: " + releaseYear +
        "\nPrice-Per-Day: " + price +
        "\nStock Quantity: " + stockQuantity +
        "\nTimes Rented: " + timesRented + 
        "\nStatus: + " + getMovieStatus());  
    }
}