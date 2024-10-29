import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize some users for testing
        initializeUsers();

        while (true) {
            System.out.println("Welcome to the Hospital Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        users.put("admin1", new User("admin1", "password", "Administrator"));
        users.put("doctor1", new User("doctor1", "password", "Doctor"));
        users.put("pharmacist1", new User("pharmacist1", "password", "Pharmacist"));
        users.put("patient1", new User("patient1", "password", "Patient"));
    }

    private static void login() {
        System.out.println("Enter your hospital ID: ");
        String hospitalId = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        User user = users.get(hospitalId);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            if (password.equals("password")) {
                System.out.println("You are using the default password. Please change your password.");
                changePassword(user);
            }
            navigateToMenu(user);
        } else {
            System.out.println("Invalid hospital ID or password. Please try again.");
        }
    }

    private static void changePassword(User user) {
        System.out.println("Enter new password: ");
        String newPassword = sc.nextLine();
        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    private static void navigateToMenu(User user) {
        AppointmentManagement appointmentManagement = new AppointmentManagement();
        MedicalRecordManagement medicalRecordManagement = new MedicalRecordManagement();
        InventoryManagement inventoryManagement = new InventoryManagement();
        PrescriptionManagement prescriptionManagement = new PrescriptionManagement(appointmentManagement);
        StaffManagement staffManagement = new StaffManagement();
       
        switch (user.getRole()) {
            case "Administrator":
                AdministratorMenu adminMenu = new AdministratorMenu(inventoryManagement, staffManagement, appointmentManagement);
                adminMenu.displayMenu();
                break;
            case "Doctor":
                DoctorMenu doctorMenu = new DoctorMenu(medicalRecordManagement, appointmentManagement);
                doctorMenu.displayMenu();
                break;
            case "Pharmacist":
                PharmacistMenu pharmacistMenu = new PharmacistMenu(prescriptionManagement, inventoryManagement);
                pharmacistMenu.displayMenu();
                break;
            case "Patient":
                PatientMenu patientMenu = new PatientMenu(medicalRecordManagement, appointmentManagement);
                patientMenu.displayMenu();
                break;
            default:
                System.out.println("Invalid role. Access denied.");
                break;
        }
    }
}