import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import hospitalManagement.AdministratorMenu;
import hospitalManagement.AppointmentManagement;
import hospitalManagement.DoctorMenu;
import hospitalManagement.InventoryManagement;
import hospitalManagement.MedicalRecord;
import hospitalManagement.MedicalRecordManagement;
import hospitalManagement.PharmacistMenu;
import hospitalManagement.PrescriptionManagement;
import hospitalManagement.Roles;
import hospitalManagement.Staff;
import hospitalManagement.StaffManagement;
import hospitalManagement.User;
import patient.Patient;
import patient.PatientMenu;

public class Main 
{
    private static Map<String, User> users = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    private static AppointmentManagement appointmentManagement = new AppointmentManagement();
    private static MedicalRecordManagement medicalRecordManagement = new MedicalRecordManagement();
    private static InventoryManagement inventoryManagement = new InventoryManagement();
    private static PrescriptionManagement prescriptionManagement = new PrescriptionManagement(appointmentManagement);
    private static StaffManagement staffManagement = new StaffManagement();
    public static void main(String[] args) 
    {
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
            {"admin1", "password", "a001", "sarah lee", "administrator", "female", 40},
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
                if (role.equals("doctor"))
                {
                    // Initialize doctor availability
                    appointmentManagement.intializeDoctorAvailability(hospitalId, name);
                }
                // Add to staff list using staffManagement.addStaff()
                staffManagement.addStaff(hospitalId, password, staffId, name, role, gender, age);
            }
            
        }

        // Create patient user and add to users map only
        Object[][] patientData = 
        {
            // String hospitalID, String password, String patientId, String name, String dob, String gender, String bloodGroup, String email, String contactNumber
            {"patient1", "password", "p001", "John Doe", "1990-05-22", "Male", "O+", "john.doe@example.com", "12345678"},
            {"patient2", "password", "p002", "Jane Smith", "1985-03-14", "Female", "A-", "jane.smith@example.com", "87654321"},
            {"patient3", "password", "p003", "Alice Johnson", "1992-07-30", "Female", "B+", "alice.johnson@example.com", "45678912"},
            {"patient4", "password", "p004", "Bob Brown", "1980-11-12", "Male", "AB-", "bob.brown@example.com", "98765432"},
            {"patient5", "password", "p005", "Charlie Davis", "1995-09-05", "Male", "O-", "charlie.davis@example.com", "65432198"},
        };

        for (int i = 0; i < patientData.length; i++) 
        {
            Object[] data = patientData[i];
            String hospitalId = (String) data[0];
            String password = (String) data[1];
            String patientId = (String) data[2];
            String name = (String) data[3];
            String dob = (String) data[4];
            String gender = (String) data[5];
            String bloodGroup = (String) data[6];
            String email = (String) data[7];
            String contactNumber = (String) data[8];

            if (users.containsKey(hospitalId)) 
            {
                System.out.println("Hospital ID " + hospitalId + " already exists. Please try again.");
            }
            else
            {
                // String hospitalID, String password, String patientId, String name, String dob, String gender, String bloodGroup, String email
                Patient patient = new Patient(hospitalId, password, patientId, name, dob, gender, bloodGroup, email);
                users.put(hospitalId, patient);
                System.out.println("Patient with Hospital ID " + hospitalId + " and Patient ID " + patientId + " added to users map.");
            }
            // This for testing purposes
            // String patientId, String patientName, String dateOfBirth, String gender, String bloodType, String contactNumber, String email,  List<String> pastDiagnosis, List<String> pastTreatments, List<String> currentDiagnosis, List<String> currentTreatments, List<String> prescriptions
            MedicalRecord medicalRecord = new MedicalRecord(patientId, name, dob, gender, bloodGroup, contactNumber, email);
            medicalRecordManagement.addMedicalRecord(medicalRecord);

            // // Add users to adminMenu
            // adminMenu.addUser(hospitalId, password, patient
        }
    }

    private static void login(AppointmentManagement appointmentManagement, MedicalRecordManagement medicalRecordManagement, InventoryManagement inventoryManagement, PrescriptionManagement prescriptionManagement, StaffManagement staffManagement)
    {
        System.out.println("Enter your hospital ID: ");
        String hospitalId = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        User user = users.get(hospitalId);

        if (user != null && user.checkPassword(password)) 
        {
            System.out.println("Login successful!");
            if (password.equals("password")) 
            {
                System.out.println("You are using the default password. Please change your password.");
                changePassword(user);
                navigateToMenu(user, appointmentManagement, medicalRecordManagement, inventoryManagement, prescriptionManagement, staffManagement);
            }
            else
            {
                navigateToMenu(user, appointmentManagement, medicalRecordManagement, inventoryManagement, prescriptionManagement, staffManagement);
            }
            
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
        prescriptionManagement = new PrescriptionManagement(appointmentManagement); // This is to ensure that prescriptionManagement has the latest appointment data.

        switch (user.getRole()) 
        {
            case "administrator":
                AdministratorMenu adminMenu = new AdministratorMenu(inventoryManagement, staffManagement, appointmentManagement, users);
                adminMenu.displayMenu();
                break;
            case "doctor":
                DoctorMenu doctorMenu = new DoctorMenu(medicalRecordManagement, appointmentManagement);
                doctorMenu.displayMenu();
                break;
            case "pharmacist":
                PharmacistMenu pharmacistMenu = new PharmacistMenu(prescriptionManagement, inventoryManagement);
                pharmacistMenu.displayMenu();
                break;
            case "patient":
                Patient patient = (Patient) user;
                PatientMenu patientMenu = new PatientMenu(medicalRecordManagement, appointmentManagement, patient);
                patientMenu.displayMenu();
                break;
            default:
                System.out.println("Invalid role. Access denied.");
                break;
        }
    }
}