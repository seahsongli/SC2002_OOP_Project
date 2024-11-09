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
            {"doctor1", "password", 102, "John Smith", "Doctor", "Male", 45},
            {"doctor2", "password", 103, "Emily Clarke", "Doctor", "Female", 38},
            {"pharmacist1", "password", 104, "Mark Lee", "Pharmacist", "Male", 29},
            {"admin1", "passoword", 105, "Sarah Lee", "Administrator", "Female", 40}
        };

        for (int i = 0; i < staffData.length; i++) 
        {
            Object[] data = staffData[i];
            String hospitalId = (String) data[0];
            String password = (String) data[1];
            int staffId = (int) data[2];
            String name = (String) data[3];
            String role = (String) data[4];
            String gender = (String) data[5];
            int age = (int) data[6];

          
            Staff staff = new Staff(hospitalId, password, staffId, name, role, gender, age);
            users.put(hospitalId, staff);
            System.out.println("Staff with Hospital ID " + hospitalId + " and Staff ID " + staffId + " added to users map.");
            // Add to staff list using staffManagement.addStaff()
            staffManagement.addStaff(hospitalId, password, staffId, name, role, gender, age);
            
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
        // users.put("admin1", new User("admin1", "password", "Administrator"));
        // users.put("doctor1", new User("doctor1", "password", "Doctor"));
        // users.put("pharmacist1", new User("pharmacist1", "password", "Pharmacist"));
        // users.put("patient1", new User("patient1", "password", "Patient"));
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