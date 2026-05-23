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
        int movieChoice = -1;
        while (movieChoice != 0) {
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
            movieChoice = getSafeInt();

            switch (movieChoice) {
                case 1 -> {
                    try {
                        System.out.print("Title: ");
                        String title = keyboard.nextLine();

                        System.out.print("Genre (1-ACTION/2-COMEDY/3-HORROR/4-SCI_FI/5-THRILLER/6-ROMANCE/7-DRAMA/8-FANTASY): ");
                        int choice1 = getSafeInt();
                        MovieGenre g;
                        switch (choice1) {
                            case 1 -> g = MovieGenre.ACTION;
                            case 2 -> g = MovieGenre.COMEDY;
                            case 3 -> g = MovieGenre.HORROR;
                            case 4 -> g = MovieGenre.SCI_FI;
                            case 5 -> g = MovieGenre.THRILLER;
                            case 6 -> g = MovieGenre.ROMANCE;
                            case 7 -> g = MovieGenre.DRAMA;
                            case 8 -> g = MovieGenre.FANTASY;
                            default -> throw new IllegalArgumentException("Invalid Genre Selection");
                        }

                        System.out.print("Rating (1-E/2-PG_7/3-PG_13/4-R): ");
                        int choice2 = getSafeInt();
                        MovieRating r;
                        switch (choice2) {
                            case 1 -> r = MovieRating.E;
                            case 2 -> r = MovieRating.PG_7;
                            case 3 -> r = MovieRating.PG_13;
                            case 4 -> r = MovieRating.R;
                            default -> throw new IllegalArgumentException("Invalid Rating Selection");
                        }

                        System.out.print("Stock Quantity: ");
                        int s = getSafeInt();
                        System.out.print("Price: ");
                        double p = getSafeDouble();
                        System.out.print("Year-of-Release: ");
                        int y = getSafeInt();
                        manager.addMovie(new Movie(title, g, r, s, p, y));
                        System.out.println(">>> Movie Added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("!!! Error: " + e.getMessage() + " !!!");
                    }
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
                    else System.out.println("!!! Movie Not Found !!!");
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter ID to Delete: ");
                    String id = keyboard.nextLine();
                    boolean removed = manager.getMovies().removeIf(m -> m.getMovieId().equalsIgnoreCase(id));
                    System.out.println(removed ? ">>> Deleted." : "!!! Movie Not Found !!!");
                }
                case 5 -> {
                    System.out.println(" 1 - Edit Title");
                    System.out.println(" 2 - Edit Genre");
                    System.out.println(" 3 - Edit Age Rating");
                    System.out.println(" 4 - Edit Stock Quantity");
                    System.out.println(" 5 - Edit Price");
                    System.out.println(" 6 - Edit Year-of-Release");
                    System.out.println(" 0 - Exit");
                    System.out.println("----------------------------------------");
                    System.out.print("Selection > ");

                    int updateChoice = getSafeInt();

                    switch (updateChoice) {
                        case 1 -> {
                            System.out.print("Enter Movie ID or Title: ");
                            Movie m = manager.findMovie(keyboard.nextLine());
                            if (m != null) {
                                System.out.print("Enter New Title: ");
                                String newTitle = keyboard.nextLine();
                                m.setTitle(newTitle);
                                System.out.println(">>> Title Updated.");
                            } else {
                                System.out.println("!!! Error: Movie Not Found !!!");
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter Movie ID or Title: ");
                            Movie m = manager.findMovie(keyboard.nextLine());

                            if (m != null) {
                                System.out.print("""
                                    Genre:
                                    1 - ACTION
                                    2 - COMEDY
                                    3 - HORROR
                                    4 - SCI_FI
                                    5 - THRILLER
                                    6 - ROMANCE
                                    7 - DRAMA
                                    8 - FANTASY
                                    Enter choice: """);

                                int choice1 = getSafeInt();
                                MovieGenre g = null;
                                switch (choice1) {
                                    case 1 -> g = MovieGenre.ACTION;
                                    case 2 -> g = MovieGenre.COMEDY;
                                    case 3 -> g = MovieGenre.HORROR;
                                    case 4 -> g = MovieGenre.SCI_FI;
                                    case 5 -> g = MovieGenre.THRILLER;
                                    case 6 -> g = MovieGenre.ROMANCE;
                                    case 7 -> g = MovieGenre.DRAMA;
                                    case 8 -> g = MovieGenre.FANTASY;
                                    default -> System.out.println("!!! Error: Invalid Genre !!!");
                                }

                                if (g != null) {
                                    m.setGenre(g);
                                    System.out.println(">>> Genre Updated.");
                                }
                            } else {
                                System.out.println("!!! Error: Movie Not Found !!!");
                            }
                        }

                        case 3 -> {
                            System.out.print("Enter Movie ID or Title: ");
                            Movie m = manager.findMovie(keyboard.nextLine());

                            if (m != null) {
                                System.out.print("""
                                    Age Rating:
                                    1 - E
                                    2 - PG_7
                                    3 - PG_13
                                    4 - R
                                    Enter choice: """);

                                int choice1 = getSafeInt();
                                MovieRating e = null;
                                switch (choice1) {
                                    case 1 -> e = MovieRating.E;
                                    case 2 -> e = MovieRating.PG_7;
                                    case 3 -> e = MovieRating.PG_13;
                                    case 4 -> e = MovieRating.R;
                                    default -> System.out.println("!!! Error: Invalid Age Rating !!!");
                                }

                                if (e != null) {
                                    m.setRating(e);
                                    System.out.println(">>> Age Rating Updated.");
                                }
                            } else {
                                System.out.println("!!! Error: Movie Not Found !!!");
                            }
                        }
                        case 4 -> {
                            System.out.print("Enter Movie ID or Title: ");
                            Movie m = manager.findMovie(keyboard.nextLine());
                            if (m != null) {
                                System.out.print("Enter New Stock Quantity: ");
                                m.setStockQuantity(getSafeInt());
                                System.out.println(">>> Stock Quantity Updated.");
                            } else {
                                System.out.println("!!! Error: Movie Not Found !!!");
                            }
                        }
                        case 5 -> {
                            System.out.print("Enter Movie ID or Title: ");
                            Movie m = manager.findMovie(keyboard.nextLine());
                            if (m != null) {
                                System.out.print("Enter New Price: ");
                                m.setPrice(getSafeDouble());
                                System.out.println(">>> Price Updated.");
                            } else {
                                System.out.println("!!! Error: Movie Not Found !!!");
                            }
                        }
                        case 6 -> {
                            System.out.print("Enter Movie ID or Title: ");
                            Movie m = manager.findMovie(keyboard.nextLine());
                            if (m != null) {
                                System.out.print("Enter New Year-of-Release: ");
                                m.setReleaseYear(getSafeInt());
                                System.out.println(">>> Year Updated.");
                            } else {
                                System.out.println("!!! Error: Movie Not Found !!!");
                            }
                        }
                        case 0 -> System.out.println("Returning to management menu...");
                        default -> System.out.println("!!! Invalid Selection !!!");
                    }
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("!!! Invalid Selection !!!");
            }
        }
    }

    // ==========================================
    // 2. CUSTOMER MANAGEMENT
    // ==========================================
    private void customerMenu() {
        int customerChoice = -1;
        while (customerChoice != 0) {
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
            customerChoice = getSafeInt();

            switch (customerChoice) {
                case 1 -> {
                    System.out.print("Name: "); String n = keyboard.nextLine();
                    System.out.print("Age: "); int a = getSafeInt();
                    System.out.print("Email: "); String e = keyboard.nextLine();
                    manager.addCustomer(new Customer(n, a, e));
                    System.out.println(">>> Customer Added.");
                }
                case 2 -> {
                    manager.getCustomers().forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName() + " - " + c.getType()));
                    pause();
                }
                case 3 -> {
                    System.out.print("Enter ID/Name: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) c.displayInfo();
                    else System.out.println("!!! Customer Not Found !!!");
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter ID/Name: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) { c.upgradeToPremium(); System.out.println(">>> Customer is now PREMIUM."); }
                    else System.out.println("!!! Customer Not Found !!!");
                }
                case 5 -> {
                    System.out.print("Enter ID/Name: ");
                    Customer c = manager.findCustomer(keyboard.nextLine());
                    if (c != null) { manager.findActiveRentals(c); }
                    else System.out.println("!!! Customer Not Found !!!");
                    pause();                    
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("!!! Invalid Selection !!!");
            }
        }
    }

    // ==========================================
    // 3. RENTING MANAGEMENT
    // ==========================================
    private void rentingMenu() {
        int rentingChoice = -1;
        while (rentingChoice != 0) {
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
            rentingChoice = getSafeInt();

            switch (rentingChoice) {
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
                case 3 -> {
                    System.out.println("Cancel feature not supported yet.");
                }
                case 4 -> {
                    manager.getRentals().stream()
                        .filter(r -> r.getStatus() == RentingStatus.ACTIVE)
                        .forEach(Renting::displayInfo);
                    pause();
                }
                case 5 -> {
                    manager.getRentals().forEach(Renting::displayInfo);
                    pause();
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("!!! Invalid Selection !!!");
            }
        }
    }

    // ==========================================
    // 4. EMPLOYEE MANAGEMENT
    // ==========================================
    private void employeeMenu() {
        int employeeChoice = -1;
        while (employeeChoice != 0) {
            System.out.println("\n========================================");
            System.out.println("          EMPLOYEE MANAGEMENT           ");
            System.out.println("========================================");
            System.out.println(" 1 - Add Employee");
            System.out.println(" 2 - View Employees");
            System.out.println(" 3 - Find Employee");
            System.out.println(" 4 - Remove Employee");
            System.out.println(" 5 - Edit Employee");
            System.out.println(" 0 - Back");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");
            employeeChoice = getSafeInt();

            switch (employeeChoice) {
                case 1 -> {
                    try {
                        System.out.print("Name: "); String n = keyboard.nextLine();
                        System.out.print("Age: "); int a = getSafeInt();
                        System.out.print("Email: "); String e = keyboard.nextLine();
                        System.out.print("Role (MANAGER/CASHIER/RESTOCKER): ");
                        EmployeeRole r = EmployeeRole.valueOf(keyboard.nextLine().toUpperCase());
                        System.out.print("Salary: "); double s = getSafeDouble();
                        manager.addEmployee(new Employee(n, a, e, r, s));
                        System.out.println(">>> Employee Added.");
                    } catch (IllegalArgumentException ex) { 
                        System.out.println("!!! Error: Invalid Role Selection !!!"); 
                    }
                }
                case 2 -> {
                    manager.getEmployees().forEach(e -> System.out.println(e.getName() + " [" + e.getRole() + "]"));
                    pause();
                }
                case 3 -> {
                    System.out.print("Enter ID/Name: ");
                    Employee e = manager.findEmployee(keyboard.nextLine());
                    if (e != null) e.displayInfo();
                    else System.out.println("!!! Employee Not Found !!!");
                    pause();
                }
                case 4 -> {
                    System.out.print("Enter ID to Delete: ");
                    String id = keyboard.nextLine();
                    boolean removed = manager.getEmployees().removeIf(e -> e.getId().equalsIgnoreCase(id));
                    System.out.println(removed ? ">>> Deleted." : "!!! Employee Not Found !!!");
                }
                case 5 -> {
                    System.out.println(" 1 - Edit Role");
                    System.out.println(" 2 - Edit Salary");
                    System.out.println(" 0 - Exit");
                    System.out.println("----------------------------------------");
                    System.out.print("Selection > ");
                    int editChoice = getSafeInt();

                    switch (editChoice) {
                        case 1 -> {
                            System.out.print("Enter Employee ID: ");
                            Employee e = manager.findEmployee(keyboard.nextLine());
                            if (e != null) {
                                try {
                                    System.out.print("Enter New Role (MANAGER/CASHIER/RESTOCKER): ");
                                    e.setRole(EmployeeRole.valueOf(keyboard.nextLine().toUpperCase()));
                                    System.out.println(">>> Role Updated.");
                                } catch (IllegalArgumentException ex) {
                                    System.out.println("!!! Error: Invalid Role !!!");
                                }
                            } else {
                                System.out.println("!!! Employee Not Found !!!");
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter Employee ID: ");
                            Employee e = manager.findEmployee(keyboard.nextLine());
                            if (e != null) {
                                System.out.print("Enter New Salary: ");
                                e.setSalary(getSafeDouble());
                                System.out.println(">>> Salary Updated.");
                            } else {
                                System.out.println("!!! Employee Not Found !!!");
                            }
                        }
                        case 0 -> System.out.println("Returning to employee menu...");
                        default -> System.out.println("!!! Invalid Selection !!!");
                    }
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("!!! Invalid Selection !!!");
            }
        }
    }

    // ==========================================
    // 5. REPORTS
    // ==========================================
    private void reportMenu() {
        int reportChoice = -1;
        while (reportChoice != 0) {
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
            reportChoice = getSafeInt();

            switch (reportChoice) {
                case 1 -> { System.out.println("Total Revenue: $" + manager.calculateRevenue()); pause(); }
                case 3 -> { System.out.println("Top Customer: " + manager.findTopCustomer()); pause(); }
                case 5 -> { manager.saveReport(); System.out.println(">>> Report Exported."); pause(); }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("!!! Invalid Selection !!!");
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
            catch (NumberFormatException e) { System.out.print("Please enter a number: "); }
        }
    }

    private double getSafeDouble() {
        while (true) {
            try { return Double.parseDouble(keyboard.nextLine()); } 
            catch (NumberFormatException e) { System.out.print("Please enter a decimal: "); }
        }
    }

    private void pause() {
        System.out.println("Press ENTER to continue...");
        keyboard.nextLine();
    }
}