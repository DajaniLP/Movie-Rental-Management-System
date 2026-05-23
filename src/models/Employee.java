package models;

import enums.EmployeeRole;
import java.util.UUID;

public class Employee extends Person {

    private final String id;
    private EmployeeRole role; 
    private double salary;

    public Employee(String name, int age, String email, EmployeeRole role, double salary) {
        super(name, age, email);
        this.id = "EMPL-" + UUID.randomUUID().toString().substring(0, 5);
        this.role = role;
        this.salary = salary;
    }

    public void updateSalary(double percentage) {
        this.salary = salary * (1 + percentage / 100);
    }

    public void setSalary(double amount) {
        this.salary = amount;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Employee Name: " + name +
            "\nID: " + id +
            "\nEmail: " + email +
            "\nAge: " + age +
            "\nRole: " + role +
            "\nSalary: " + salary
        );
    }
}