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

            choice = getSafeInt();

            switch (choice) {
                case 1 -> movieMenu();
                case 2 -> customerMenu();
                case 3 -> rentingMenu();
                case 4 -> employeeMenu();
                case 5 -> reportMenu();
                case 6 -> dashboard();
                case 0 -> System.out.println("\nLogging out...");
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
            System.out.println(" 2 - View Movies");
            System.out.println(" 3 - Find Movie");
            System.out.println(" 4 - Delete Movie");
            System.out.println(" 5 - Update Movie");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getSafeInt();

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("ID: "); String id = keyboard.nextLine();
                        System.out.print("Title: "); String title = keyboard.nextLine();
                        System.out.print("Genre (ACTION/COMEDY/HORROR/SCI_FI): "); 
                        MovieGenre g = MovieGenre.valueOf(keyboard.nextLine().toUpperCase());
                        System.out.print("Rating (G/PG/PG_13/R): "); 
                        MovieRating r = MovieRating.valueOf(keyboard.nextLine().toUpperCase());
                        System.out.print("Stock: "); int s = getSafeInt();
                        System.out.print("Price: "); double p = getSafeDouble();
                        System.out.print("Year: "); int y = getSafeInt();
                        manager.addMovie(new Movie(id, title, g, r, s, p, y));
                        System.out.println(">>> Movie Added.");
                    } catch (Exception e) { System.out.println("!!! Error: Check Genre/Rating spelling !!!"); }
                }
                case 2 -> {
                    System.out.println("\n--- INVENTORY LIST ---");
                    manager.getMovies().forEach(m -> System.out.println("[" + m.getMovieId() + "] " + m.getMovieTitle() + " (" + m.getMovieStatus() + ")"));
                    pause();
                }
                case 3 -> {
                    System.out.print("Enter Movie ID or Title: ");
                    Movie m = manager.findMovie(keyboard.nextLine());
                    if (m != null) m.displayInfo();
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter ID to Delete: ");
                    String id = keyboard.nextLine();
                    boolean removed = manager.getMovies().removeIf(m -> m.getMovieId().equalsIgnoreCase(id));
                    System.out.println(removed ? ">>> Deleted." : "!!! Movie Not Found !!!");
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
            System.out.println(" 2 - View Customers");
            System.out.println(" 3 - Find Customer");
            System.out.println(" 4 - Upgrade Customer");
            System.out.println(" 5 - View Customer Rentals");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getSafeInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: "); String id = keyboard.nextLine();
                    System.out.print("Name: "); String n = keyboard.nextLine();
                    System.out.print("Age: "); int a = getSafeInt();
                    System.out.print("Email: "); String e = keyboard.nextLine();
                    manager.addCustomer(new Customer(id, n, a, e));
                }
                case 2 -> {
                    manager.getCustomers().forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName() + " - " + c.getType()));
                    pause();
                }
                case 3 -> {
                    System.out.print("Enter ID/Name: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) c.displayInfo();
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter ID: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) { c.upgradeToPremium(); System.out.println(">>> Customer is now PREMIUM."); }
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
            System.out.println("           RENTING MANAGEMENT           ");
            System.out.println("========================================");
            System.out.println(" 1 - Rent Movie");
            System.out.println(" 2 - Complete Renting (Return)");
            System.out.println(" 3 - Cancel Renting");
            System.out.println(" 4 - View Active Rentals");
            System.out.println(" 5 - View All Rentals");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getSafeInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Customer ID: "); String cid = keyboard.nextLine();
                    System.out.print("Movie Title: "); String mtitle = keyboard.nextLine();
                    System.out.print("Days: "); int d = getSafeInt();
                    manager.rentMovie(cid, mtitle, d);
                }
                case 2 -> {
                    System.out.print("Customer ID: "); String cid = keyboard.nextLine();
                    System.out.print("Movie ID: "); String mid = keyboard.nextLine();
                    manager.completeRenting(cid, mid);
                }
                case 4 -> {
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
            System.out.println(" 2 - View Employees");
            System.out.println(" 3 - Find Employee");
            System.out.println(" 4 - Remove Employee");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getSafeInt();

            if (choice == 1) {
                try {
                    System.out.print("ID: "); String id = keyboard.nextLine();
                    System.out.print("Name: "); String n = keyboard.nextLine();
                    System.out.print("Age: "); int a = getSafeInt();
                    System.out.print("Email: "); String e = keyboard.nextLine();
                    System.out.print("Role (MANAGER/CASHIER/RESTOCKER): "); 
                    EmployeeRole r = EmployeeRole.valueOf(keyboard.nextLine().toUpperCase());
                    System.out.print("Salary: "); double s = getSafeDouble();
                    manager.addEmployee(new Employee(id, n, a, e, r, s));
                } catch (Exception ex) { System.out.println("!!! Invalid Input !!!"); }
            } else if (choice == 2) {
                manager.getEmployees().forEach(e -> System.out.println(e.getName() + " [" + e.getRole() + "]"));
                pause();
            }
        }
    }

    // ==========================================
    // 5. REPORTS
    // ==========================================
    private void reportMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("                REPORTS                 ");
            System.out.println("========================================");
            System.out.println(" 1 - Total Revenue");
            System.out.println(" 2 - Most Rented Movie");
            System.out.println(" 3 - Top Customer");
            System.out.println(" 4 - System Summary");
            System.out.println(" 5 - Export Report (File)");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            choice = getSafeInt();

            switch (choice) {
                case 1 -> { System.out.println("Total Revenue: $" + manager.calculateRevenue()); pause(); }
                case 3 -> { System.out.println("Top Customer: " + manager.findTopCustomer()); pause(); }
                case 5 -> manager.saveReport(); // Uses your specific method
            }
        }
    }

    // ==========================================
    // 6. DASHBOARD
    // ==========================================
    private void dashboard() {
        System.out.println("\n========================================");
        System.out.println("               DASHBOARD                ");
        System.out.println("========================================");
        System.out.println("- Total Movies      : " + manager.getMovies().size());
        System.out.println("- Total Customers   : " + manager.getCustomers().size());
        System.out.println("- Total Employees   : " + manager.getEmployees().size());
        System.out.println("- Active Rentals    : " + manager.getRentals().stream().filter(r -> r.getStatus() == RentingStatus.ACTIVE).count());
        System.out.println("- Total Revenue     : $" + manager.calculateRevenue());
        System.out.println("----------------------------------------");
        pause();
    }

    // CRASH PROTECTION
    private int getSafeInt() {
        while (true) {
            try { return Integer.parseInt(keyboard.nextLine()); } 
            catch (Exception e) { System.out.print("Please enter a number: "); }
        }
    }

    private double getSafeDouble() {
        while (true) {
            try { return Double.parseDouble(keyboard.nextLine()); } 
            catch (Exception e) { System.out.print("Please enter a decimal: "); }
        }
    }

    private void pause() {
        System.out.println("Press ENTER to continue...");
        keyboard.nextLine();
    }
}
