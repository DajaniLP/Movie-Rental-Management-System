package models;

import enums.*;

public class Movie {
    private final String id;
    private final String title;
    private final MovieGenre genre;
    private final MovieRating rating;
    private final int releaseYear;

    private MovieStatus status;
    private int stockQuantity;
    private final double price;
    private int timesRented;

    public Movie(String id, String title, MovieGenre genre, MovieRating rating,
      int stockQuantity, double price, int releaseYear) {
        this.id = id;
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