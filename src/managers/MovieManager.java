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

    // CORE LOGIC
    public void addMovie(Movie movie) { movies.add(movie); }

    public Movie findMovie(String value) {
        for (Movie m : movies) {
            if (m.getMovieId().equalsIgnoreCase(value) || m.getMovieTitle().equalsIgnoreCase(value)) return m;
        }
        System.out.println("Error: Movie not found.");
        return null;
    }

    public void addCustomer(Customer customer) { customers.add(customer); }

    public Customer findCustomer(String value) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(value) || c.getName().equalsIgnoreCase(value)) return c;
        }
        System.out.println("Error: Customer not found.");
        return null;
    }

    public void findActiveRentals(Customer c) {
        for (Renting r : rentals) {
            if (c.equals(r.getCustomer())) {
                r.displayInfo();
            }
        }
    }

    public boolean rentMovie(String customerVal, String movieVal, int days) {
        Customer c = findCustomer(customerVal);
        Movie m = findMovie(movieVal);

        if (c != null && m != null) {
            if (c.getActiveRentals() >= c.getMaxRentals()) {
                System.out.println("Error: Rental limit reached.");
                return false;
            }
            if (m.rentMovie()) {
                rentals.add(new Renting(c, m, days));
                c.addActiveRentals();
                c.addTotalRentals();
                System.out.println("Rental successful!");
                c.addPoints(100);
                return true;
            }
        }
        return false;
    }

    public boolean completeRenting(String customerId, String movieId) {
        for (Renting r : rentals) {
            if (r.getCustomer().getId().equalsIgnoreCase(customerId) && 
                r.getMovie().getMovieId().equalsIgnoreCase(movieId) && 
                r.getStatus() == RentingStatus.ACTIVE) {
                r.completeRenting();
                System.out.println("Movie returned successfully.");
                return true;
            }
        }
        System.out.println("Error: Active rental record not found.");
        return false;
    }

    public void addEmployee(Employee e) { employees.add(e); }

    public Employee findEmployee(String value) {
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(value) || e.getName().equalsIgnoreCase(value)) return e;
        }
        System.out.println("Error: Employee not found.");
        return null;
    }    

    public double calculateRevenue() {
        double total = 0;
        for (Renting r : rentals) {
            if (r.getStatus() != RentingStatus.CANCELLED) total += r.calculateCost();
        }
        return total;
    }

    public String findTopCustomer() {
        if (customers.isEmpty()) return "No customers registered";
        Customer top = customers.get(0);
        for (Customer c : customers) {
            if (c.getTotalRentals() > top.getTotalRentals()) top = c;
        }
        return top.getName();
    }

    // REPORTS LOGIC
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