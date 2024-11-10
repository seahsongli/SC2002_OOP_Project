import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main 
{
    private static Map<String, User> users = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    
    public static void main(String[] args) 
    {
        
        AppointmentManagement appointmentManagement = new AppointmentManagement();
        MedicalRecordManagement medicalRecordManagement = new MedicalRecordManagement();
        InventoryManagement inventoryManagement = new InventoryManagement();
        PrescriptionManagement prescriptionManagement = new PrescriptionManagement(appointmentManagement);
        StaffManagement staffManagement = new StaffManagement();
        // Initialize some users for testing
        initializeUsers(staffManagement);

        while (true)
        {
            System.out.println("Welcome to the Hospital Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) 
            {
                login(appointmentManagement, medicalRecordManagement, inventoryManagement, prescriptionManagement, staffManagement);
            } 
            else if (choice == 2) 
            {
                System.out.println("Exiting the system. Goodbye!");
                break;
            } 
            else 
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // This is to initialize the first few users from the excel file
    private static void initializeUsers(StaffManagement staffManagement) 
    {
        // Staff data: hospitalId, password, staffId, name, role, gender, age
        Object[][] staffData = 
        {
            {"doctor1", "password", "d001", "john smith", "doctor", "male", 45},
            {"doctor2", "password", "d002", "emily clarke", "doctor", "female", 38},
            {"pharmacist1", "password", "p001", "mark Lee", "pharmacist", "male", 29},
            {"admin1", "password", "a001", "sarah lee", "administrator", "female", 40}
        };

        for (int i = 0; i < staffData.length; i++) 
        {
            Object[] data = staffData[i];
            String hospitalId = (String) data[0];
            String password = (String) data[1];
            String staffId = (String) data[2];
            String name = (String) data[3];
            String role = (String) data[4];
            String gender = (String) data[5];
            int age = (int) data[6];

            if (!Roles.ALLOWED_ROLES.contains(role)) 
            {
                System.out.println("Invalid role '" + role + "' for staff member " + name + ". Staff not added.");
            }
            else
            {
                
                Staff staff = new Staff(hospitalId, password, staffId, name, role, gender, age);
                users.put(hospitalId, staff);
                System.out.println("Staff with Hospital ID " + hospitalId + " and Staff ID " + staffId + " added to users map.");
                // Add to staff list using staffManagement.addStaff()
                staffManagement.addStaff(hospitalId, password, staffId, name, role, gender, age);
            }
            
        }

        // Create patient user and add to users map only
        String patientHospitalId = "patient1";
        if (users.containsKey(patientHospitalId)) 
        {
            System.out.println("Hospital ID " + patientHospitalId + " already exists. Please try again.");
        } 
        else 
        {
            User patient = new User(patientHospitalId, "password", "Patient");
            users.put(patientHospitalId, patient);
            System.out.println("User with Hospital ID " + patientHospitalId + " added successfully.");
        }
    
    }

    private static void login(AppointmentManagement appointmentManagement, MedicalRecordManagement medicalRecordManagement, InventoryManagement inventoryManagement, PrescriptionManagement prescriptionManagement, StaffManagement staffManagement)
    {
        System.out.println("Enter your hospital ID: ");
        String hospitalId = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        User user = users.get(hospitalId);

        if (user != null && user.getPassword().equals(password)) 
        {
            System.out.println("Login successful!");
            if (password.equals("password")) 
            {
                System.out.println("You are using the default password. Please change your password.");
                changePassword(user);
            }
            navigateToMenu(user, appointmentManagement, medicalRecordManagement, inventoryManagement, prescriptionManagement, staffManagement);
        } 
        else 
        {
            System.out.println("Invalid hospital ID or password. Please try again.");
        }
    }

    private static void changePassword(User user) 
    {
        System.out.println("Enter new password: ");
        String newPassword = sc.nextLine();
        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    private static void navigateToMenu(User user, AppointmentManagement appointmentManagement, MedicalRecordManagement medicalRecordManagement, InventoryManagement inventoryManagement ,PrescriptionManagement prescriptionManagement ,StaffManagement staffManagement) 
    {
       
        switch (user.getRole()) 
        {
            case "Administrator":
                AdministratorMenu adminMenu = new AdministratorMenu(inventoryManagement, staffManagement, appointmentManagement, users);
                adminMenu.displayMenu();
                break;
            case "Doctor":
                DoctorMenu doctorMenu = new DoctorMenu(medicalRecordManagement, appointmentManagement);
                // doctorMenu.displayMenu();
                break;
            case "Pharmacist":
                PharmacistMenu pharmacistMenu = new PharmacistMenu(prescriptionManagement, inventoryManagement);
                // pharmacistMenu.displayMenu();
                break;
            case "Patient":
                PatientMenu patientMenu = new PatientMenu(medicalRecordManagement, appointmentManagement);
                // patientMenu.displayMenu();
                break;
            default:
                System.out.println("Invalid role. Access denied.");
                break;
        }
    }
}
