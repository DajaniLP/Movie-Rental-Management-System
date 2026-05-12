package managers;

import enums.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import models.*;

public class MovieManager {

    private final ArrayList<Movie> movies = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<Renting> rentals = new ArrayList<>();    

    public ArrayList<Movie> getMovies() { return movies; }
    public ArrayList<Customer> getCustomers() { return customers; }
    public ArrayList<Employee> getEmployees() { return employees; }
    public ArrayList<Renting> getRentals() { return rentals; }

    // Movie
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Movie findMovie(String value) {
        for (Movie m : movies) {
            if (m.getMovieId().equalsIgnoreCase(value) || m.getMovieTitle().equalsIgnoreCase(value)) {
                return m;
            }
        }
        System.out.println("Error: ID/Name invalid or movie doesn't exist.");
        return null;
    }

    // Customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String value) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(value) || c.getName().equalsIgnoreCase(value)) {
                return c;
            }
        }
        System.out.println("Error: ID/Name invalid or customer doesn't exist.");
        return null;
    }

    // Employee

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee findEmployee(String value) {
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(value) || e.getName().equalsIgnoreCase(value)) {
                return e;
            }
        }
        System.out.println("Error: ID/Name invalid or employee doesn't exist.");
        return null;
    }
    
    // Renting

    public boolean rentMovie(String customerId, String movieTitle, int days) {

        Customer customer = findCustomer(customerId);
        Movie movie = findMovie(movieTitle);
        
        if (customer == null || movie == null) {
            System.out.println("Denied: Invalid customer or movie.");
            return false;
        }

        int age = customer.getAge();
        CustomerType type = customer.getType();
        MovieRating rating = movie.getMovieRating();

        if ((rating == MovieRating.R && age < 17) ||
            (rating == MovieRating.PG_13 && age < 13) ||
            (rating == MovieRating.PG_7 && age < 7)) {

            System.out.println("Denied: Age restriction.");
            return false;
        }

        if ((type == CustomerType.STANDARD && customer.getActiveRentals() >= 2) ||
            (type == CustomerType.PREMIUM && customer.getActiveRentals() >= 5)) {

            System.out.println("Denied: Maximum amount of rentals reached.");
            return false;
        }

        if (!movie.rentMovie()) {
            System.err.println("Denied: Movie not available.");
            return false;
        }

        Renting newRent = new Renting(customer, movie, days);
        rentals.add(newRent);

        customer.addActiveRentals();
        customer.addTotalRentals();

        return true;
    }

    // Complete renting

    public boolean completeRenting(String customerId, String movieId) {

        for (Renting r : rentals) {

            if (r.getCustomer().getId().equalsIgnoreCase(customerId) &&
                r.getMovie().getMovieId().equalsIgnoreCase(movieId) &&
                r.getStatus() == RentingStatus.ACTIVE) {

                r.completeRenting();

                return true;
            }
        }

        System.err.println("Error: ID/Name invalid or no active renting found.");
        return false;
    }

    // Revenue

    public double calculateRevenue() {
        double total = 0;

        for (Renting r : rentals) {
            if (r.getStatus() == RentingStatus.ACTIVE ||
                r.getStatus() == RentingStatus.COMPLETED) {

                total += r.calculateCost();
            }
        }
        return total;
    }

    // Statistics

    public String findTopCustomer() {

        Customer topCustomer = customers.get(0);

        for (Customer c : customers) {

            if (c.getTotalRentals() > topCustomer.getActiveRentals()) {
                topCustomer = c;
            }
        }

        return topCustomer.getName();
    }

    public void saveReport() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("MovieRentalsReport.txt"))) {
            writer.println("=== MOVIE RENTALS MANAGEMENT REPORT ===");
            writer.println("Total Revenue: $" + calculateRevenue());
            writer.println("\n");

            writer.println("Total Movies Registered: " + movies.size());
            writer.println("Total Customers Registered: " + customers.size());
            writer.println("Top Customer: " + findTopCustomer());            
            writer.println("Total Employees Employed: " + employees.size());            
            writer.println("Total Rentals Processed: " + rentals.size());
            System.out.println("Report successfully saved to MovieRentalsReport.txt");
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }
    }
}