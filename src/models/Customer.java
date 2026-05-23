package models;

import enums.*;
import java.util.UUID;

public class Customer extends Person {

    private final String id;
    private int customerPoints;
    private CustomerType customerType;
    private int totalRentals;
    private int activeRentals;

    public Customer(String name, int age, String email) {
        super(name, age, email);
        this.id = "CUST-" + UUID.randomUUID().toString().substring(0, 5);
        this.customerPoints = 0;
        this.customerType = CustomerType.STANDARD;
        this.totalRentals = 0;
        this.activeRentals = 0;
    }

    public void addPoints(int amount) {
        this.customerPoints += amount;
    }

    public void upgradeToPremium() {
        this.customerType = CustomerType.PREMIUM;
    }

    public void downgradeToStandard() {
        this.customerType = CustomerType.STANDARD;
    }

    public String getId() {
        return id;
    }
    public int getPoints() {
        return customerPoints;
    }

    public CustomerType getType() {
        return customerType;
    }

    public int getTotalRentals() {
        return totalRentals;
    }

    public int getMaxRentals() {
        if (customerType == CustomerType.PREMIUM) {
        return 5;
        }
        return 2; // STANDARD
    }

    public int getActiveRentals() {
        return activeRentals;
    }

    public void addTotalRentals() {
        this.totalRentals++;
    }

    public void addActiveRentals() {
        this.activeRentals++;
    }

    public void subtractActiveRentals() {
        this.activeRentals--;
    }

    @Override
    public void displayInfo() {
        System.out.println(
        "ID: " + id +
        "\nName: " + name +
        "\nEmail: " + email +
        "\nPoints: " + customerPoints +
        "\nMembership: " + customerType +
        "\nActive Rentals: " + activeRentals +
        "\nTotal Rentals: " + totalRentals);  
    }
}    
