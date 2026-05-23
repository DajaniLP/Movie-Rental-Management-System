package models;

import enums.*;

public class Renting {

    private final Customer customer;
    private final Movie movie;
    private final int days;
    private RentingStatus status;

    public Renting(Customer customer, Movie movie, int days) {
        this.customer = customer;
        this.movie = movie;
        this.days = days;
        this.status = RentingStatus.ACTIVE;
    }

    public void completeRenting() {
        movie.returnMovie();
        status = RentingStatus.COMPLETED;
        
        customer.subtractActiveRentals();
        customer.addPoints(100 * days);
    }

    public void cancelRenting() {
        movie.returnMovie();
        status = RentingStatus.CANCELLED;
    }

    public double calculateCost() {
        return movie.getPrice() * days;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }

    public RentingStatus getStatus() {
        return status;
    }
    
    public void displayInfo() {
        System.out.println(
        "Customer: " + customer.getName() +
        "\nMovie: " + movie.getMovieTitle() +
        "\nDays: " + days +
        "\nStatus: " + status +
        "\nCost: " + calculateCost()
        );
    }
}
