package system;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {

    private final Scanner keyboard = new Scanner(System.in);
    private final ArrayList<String> usernames = new ArrayList<>();
    private final ArrayList<String> passwords = new ArrayList<>();

    public LoginSystem() {
        usernames.add("admin");
        passwords.add("1234");
    }

    // ==========================================
    // MAIN LOGIN MENU
    // ==========================================
    public void run(MenuSystem menuSystem) {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("            LOGIN SYSTEM MENU           ");
            System.out.println("========================================");
            System.out.println(" [1] Login");
            System.out.println(" [2] Manage Admins");
            System.out.println(" [0] Exit");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");

            choice = keyboard.nextInt();
            keyboard.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> {
                    if (login()) menuSystem.start();
                }
                case 2 -> adminMenu();
                case 0 -> System.out.println("Exiting system...");
                default -> System.out.println("!!! Invalid Choice !!!");
            }
        }
    }

    // ==========================================
    // LOGIN LOGIC
    // ==========================================
    public boolean login() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.println("\n--- LOGIN ATTEMPT (" + attempts + " LEFT) ---");
            System.out.print("Username: ");
            String username = keyboard.nextLine();

            System.out.print("Password: ");
            String password = keyboard.nextLine();

            for (int i = 0; i < usernames.size(); i++) {
                if (usernames.get(i).equals(username) && passwords.get(i).equals(password)) {
                    System.out.println("\n>>> Login successful!");
                    return true;
                }
            }

            attempts--;
            System.out.println("Invalid credentials.");
        }
        return false;
    }

    // ==========================================
    // ADMIN MANAGEMENT MENU
    // ==========================================
    public void adminMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n========================================");
            System.out.println("           MANAGE ADMINISTRATORS        ");
            System.out.println("========================================");
            System.out.println(" [1] View All Admins");
            System.out.println(" [2] Register New Admin");
            System.out.println(" [3] Remove Admin");
            System.out.println(" [0] Back to Main Menu");
            System.out.println("----------------------------------------");
            System.out.print("Selection > ");

            choice = keyboard.nextInt();
            keyboard.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> viewAdmins();
                case 2 -> registerAdmin();
                case 3 -> removeAdmin();
                case 0 -> {} 
                default -> System.out.println("!!! Invalid Choice !!!");
            }
        }
    }

    // ==========================================
    // VIEW ADMINS (Fixed Display)
    // ==========================================
    public void viewAdmins() {
        System.out.println("\n--- CURRENT ADMINS LIST ---");

        if (usernames.isEmpty()) {
            System.out.println("List is currently empty.");
        } else {
            for (int i = 0; i < usernames.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + usernames.get(i));
            }
        }
        
        System.out.println("---------------------------");
        System.out.println("Press ENTER to continue...");
        keyboard.nextLine(); // PAUSE: This keeps the text on screen
    }

    // ==========================================
    // REGISTER ADMIN
    // ==========================================
    public void registerAdmin() {
        System.out.print("Enter new username: ");
        String username = keyboard.nextLine();

        if (usernames.contains(username)) {
            System.out.println("Error: Username already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = keyboard.nextLine();

        usernames.add(username);
        passwords.add(password);
        System.out.println("Admin '" + username + "' added successfully.");
    }

    // ==========================================
    // REMOVE ADMIN
    // ==========================================
    public void removeAdmin() {
        System.out.print("Enter username to remove: ");
        String username = keyboard.nextLine();

        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(username)) {
                usernames.remove(i);
                passwords.remove(i);
                System.out.println("Admin removed successfully.");
                return;
            }
        }
        System.out.println("Error: Admin not found.");
    }
}