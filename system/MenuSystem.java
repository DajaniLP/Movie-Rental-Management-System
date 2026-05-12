package system;

import enums.*;
import java.util.Scanner;
import managers.MovieManager;
import models.*;

public class MenuSystem {

    private final MovieManager manager = new MovieManager();
    private final Scanner keyboard = new Scanner(System.in);

    public void start() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("          MOVIE RENTAL SYSTEM           ");
            System.out.println("========================================");
            System.out.println(" 1 - Movie Management");
            System.out.println(" 2 - Customer Management");
            System.out.println(" 3 - Renting Management");
            System.out.println(" 4 - Employee Management");
            System.out.println(" 5 - Reports");
            System.out.println(" 6 - Dashboard");
            System.out.println(" 0 - Logout");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");

            choice = getIntInput();
            switch (choice) {
                case 1 -> movieMenu();
                case 2 -> customerMenu();
                case 3 -> rentingMenu();
                case 4 -> employeeMenu();
                case 5 -> reportMenu();
                case 6 -> dashboard();
                case 0 -> System.out.println("\nLogging out... Returning to Login Screen.");
                default -> System.out.println("!!! Invalid Selection !!!");
            }
        }
    }

    // ==========================================
    // 1. MOVIE MANAGEMENT
    // ==========================================
    private void movieMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("           MOVIE MANAGEMENT             ");
            System.out.println("========================================");
            System.out.println(" 1 - Add Movie");
            System.out.println(" 2 - View All Movies");
            System.out.println(" 3 - Find Movie");
            System.out.println(" 4 - Delete Movie");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: "); String id = keyboard.nextLine();
                    System.out.print("Title: "); String title = keyboard.nextLine();
                    System.out.print("Genre (ACTION/COMEDY/etc): "); MovieGenre g = MovieGenre.valueOf(keyboard.nextLine().toUpperCase());
                    System.out.print("Rating (G/PG/PG_13/R): "); MovieRating r = MovieRating.valueOf(keyboard.nextLine().toUpperCase());
                    System.out.print("Stock Quantity: "); int stock = getIntInput();
                    System.out.print("Daily Price: "); double price = keyboard.nextDouble();
                    System.out.print("Release Year: "); int year = getIntInput();
                    manager.addMovie(new Movie(id, title, g, r, stock, price, year));
                    System.out.println(">>> Movie successfully added.");
                }
                case 2 -> {
                    System.out.println("\n--- ALL REGISTERED MOVIES ---");
                    manager.getMovies().forEach(m -> System.out.println("- " + m.getMovieTitle() + " (ID: " + m.getMovieId() + ") [" + m.getMovieStatus() + "]"));
                    pause();
                }
                case 3 -> {
                    System.out.print("Enter ID or Title: ");
                    Movie m = manager.findMovie(keyboard.nextLine());
                    if (m != null) m.displayInfo();
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter Movie ID to remove: ");
                    String id = keyboard.nextLine();
                    boolean removed = manager.getMovies().removeIf(m -> m.getMovieId().equalsIgnoreCase(id));
                    System.out.println(removed ? ">>> Movie deleted." : "!!! ID Not Found !!!");
                }
            }
        }
    }

    // ==========================================
    // 2. CUSTOMER MANAGEMENT
    // ==========================================
    private void customerMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("          CUSTOMER MANAGEMENT           ");
            System.out.println("========================================");
            System.out.println(" 1 - Add Customer");
            System.out.println(" 2 - View All Customers");
            System.out.println(" 3 - Find Customer");
            System.out.println(" 4 - Upgrade to Premium");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: "); String id = keyboard.nextLine();
                    System.out.print("Name: "); String name = keyboard.nextLine();
                    System.out.print("Age: "); int age = getIntInput();
                    System.out.print("Email: "); String email = keyboard.nextLine();
                    manager.addCustomer(new Customer(id, name, age, email));
                    System.out.println(">>> Customer registered.");
                }
                case 2 -> {
                    manager.getCustomers().forEach(c -> System.out.println("- " + c.getName() + " (ID: " + c.getId() + ") [" + c.getType() + "]"));
                    pause();
                }
                case 3 -> {
                    System.out.print("Enter ID or Name: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) c.displayInfo();
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter ID: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) { c.upgradeToPremium(); System.out.println(">>> Upgraded."); }
                }
            }
        }
    }

    // ==========================================
    // 3. RENTING MANAGEMENT
    // ==========================================
    private void rentingMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("           RENTING OPERATIONS           ");
            System.out.println("========================================");
            System.out.println(" 1 - Rent Movie");
            System.out.println(" 2 - Return Movie (Complete)");
            System.out.println(" 3 - View Active Rentals");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> {
                    System.out.print("Customer ID: "); String cid = keyboard.nextLine();
                    System.out.print("Movie Title: "); String mtitle = keyboard.nextLine();
                    System.out.print("Duration (Days): "); int days = getIntInput();
                    manager.rentMovie(cid, mtitle, days);
                }
                case 2 -> {
                    System.out.print("Customer ID: "); String cid = keyboard.nextLine();
                    System.out.print("Movie ID: "); String mid = keyboard.nextLine();
                    manager.completeRenting(cid, mid);
                }
                case 3 -> {
                    manager.getRentals().stream()
                            .filter(r -> r.getStatus() == RentingStatus.ACTIVE)
                            .forEach(Renting::displayInfo);
                    pause();
                }
            }
        }
    }

    // ==========================================
    // 4. EMPLOYEE MANAGEMENT
    // ==========================================
    private void employeeMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("          EMPLOYEE MANAGEMENT           ");
            System.out.println("========================================");
            System.out.println(" 1 - Add Employee");
            System.out.println(" 2 - View All Employees");
            System.out.println(" 3 - Remove Employee");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getIntInput();

            if (choice == 1) {
                System.out.print("ID: "); String id = keyboard.nextLine();
                System.out.print("Name: "); String name = keyboard.nextLine();
                System.out.print("Age: "); int age = getIntInput();
                System.out.print("Email: "); String email = keyboard.nextLine();
                System.out.print("Role (MANAGER/CASHIER/etc): "); EmployeeRole r = EmployeeRole.valueOf(keyboard.nextLine().toUpperCase());
                System.out.print("Salary: "); double sal = keyboard.nextDouble(); keyboard.nextLine();
                manager.addEmployee(new Employee(id, name, age, email, r, sal));
            } else if (choice == 2) {
                manager.getEmployees().forEach(e -> System.out.println("- " + e.getName() + " [" + e.getRole() + "]"));
                pause();
            }
        }
    }

    // ==========================================
    // 5. REPORTS
    // ==========================================
    private void reportMenu() {
        System.out.println("\n========================================");
        System.out.println("            SYSTEM REPORTS              ");
        System.out.println("========================================");
        System.out.println(" 1 - Total Revenue");
        System.out.println(" 2 - Top Customer");
        System.out.println(" 3 - Export Report (File)");
        System.out.println(" 0 - Back");
        System.out.println("----------------------------------------");
        System.out.print("Selection > ");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> { System.out.println("Total Revenue: $" + manager.calculateRevenue()); pause(); }
            case 2 -> { System.out.println("Top Customer: " + manager.findTopCustomer()); pause(); }
            case 3 -> manager.saveReport();
        }
    }

    // ==========================================
    // 6. DASHBOARD
    // ==========================================
    private void dashboard() {
        System.out.println("\n========================================");
        System.out.println("                DASHBOARD               ");
        System.out.println("========================================");
        System.out.println(" Total Movies     : " + manager.getMovies().size());
        System.out.println(" Total Customers  : " + manager.getCustomers().size());
        System.out.println(" Total Employees  : " + manager.getEmployees().size());
        System.out.println(" Total Revenue    : $" + manager.calculateRevenue());
        System.out.println("----------------------------------------");
        System.out.println("Press ENTER to return...");
        keyboard.nextLine();
    }

    // HELPER: Safely get integer input and clear buffer
    private int getIntInput() {
        try {
            int val = keyboard.nextInt();
            keyboard.nextLine();
            return val;
        } catch (Exception e) {
            keyboard.nextLine();
            return -1;
        }
    }

    // HELPER: Pause screen
    private void pause() {
        System.out.println("\nPress ENTER to continue...");
        keyboard.nextLine();
    }
}