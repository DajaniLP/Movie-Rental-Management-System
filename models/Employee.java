package models;

import enums.EmployeeRole;

public class Employee extends Person {

    private final EmployeeRole role; 
    private  double salary;

    public Employee(String id, String name, int age, String email, EmployeeRole role, double salary) {
        super(id, name, age, email);
        this.role = role;
        this.salary = salary;
    }

    public void updateSalary(double percentage) {
        this.salary = salary * (1 + percentage / 100);
    }

    public void setSalary(double amount) {
        this.salary = amount;
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