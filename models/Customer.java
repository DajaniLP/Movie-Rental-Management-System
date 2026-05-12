package models;

import enums.*;

public class Customer extends Person {

    private int customerPoints;
    private CustomerType customerType;
    private int totalRentals;
    private int activeRentals;

    public Customer(String id, String name, int age, String email) {
        super(id, name, age, email);
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
