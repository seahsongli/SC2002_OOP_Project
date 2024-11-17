import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            int choice = -1; // Default invalid choice
            while (choice == -1) 
            {
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) 
                {
                    choice = sc.nextInt();
                    sc.nextLine();
                } 
                else 
                {
                    System.out.println("Invalid input. Please enter a valid number (1 or 2).");
                    sc.nextLine(); // Clear the invalid input
                }
            }

            if (choice == 1) 
            {
                login(appointmentManagement, medicalRecordManagement, inventoryManagement, prescriptionManagement, staffManagement);
            } 
            else if (choice == 2) 
            {
                saveContext(medicalRecordManagement, staffManagement, inventoryManagement, prescriptionManagement);
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
        String staffExcelPath = "src/main/resources/Staff_List.xlsx"; 
        String patientExcelPath = "src/main/resources/Patient_List.xlsx";
        
         // Staff data: hospitalId, password, staffId, name, role, gender, age
        Object[][] staffData = ExcelReader.readExcelData(staffExcelPath);

        // Patient data: username, password, patientId, name, dob, gender, bloodtype, email, phoneNumber
        Object[][] patientData = ExcelReader.readExcelData(patientExcelPath);
       
        // Object[][] staffData = 
        // {
        //     {"doctor1", "password", "d001", "john smith", "doctor", "male", 45},
        //     {"doctor2", "password", "d002", "emily clarke", "doctor", "female", 38},
        //     {"pharmacist1", "password", "p001", "mark Lee", "pharmacist", "male", 29},
        //     {"admin1", "password", "a001", "sarah lee", "administrator", "female", 40},
        // };

        for (int i = 0; i < staffData.length; i++) 
        {
            Object[] data = staffData[i];
            String hospitalId = (String) data[0];
            String password = (String) data[1];
            String staffId = ((String) data[2]).trim().toLowerCase();
            String name = ((String) data[3]).trim().toLowerCase();
            String role = (String) data[4];
            String gender = (String) data[5];
            int age = (data[6] instanceof Double) ? ((Double) data[6]).intValue() : Integer.parseInt(data[6].toString());

            if (!Roles.ALLOWED_ROLES.contains(role)) 
            {
                System.out.println("Invalid role '" + role + "' for staff member " + name + ". Staff not added.");
            }
            else
            {
                Staff staff = new Staff(hospitalId, password, staffId, name, role, gender, age);
                users.put(hospitalId, staff);
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
        // Object[][] patientData = 
        // {
        //     // String hospitalID, String password, String patientId, String name, String dob, String gender, String bloodGroup, String email, String contactNumber
        //     {"patient1", "password", "pa001", "john doe", "1990-05-22", "male", "O+", "john.doe@example.com", "12345678"},
        //     {"patient2", "password", "pa002", "jane smith", "1985-03-14", "female", "A-", "jane.smith@example.com", "87654321"},
        //     {"patient3", "password", "pa003", "alice johnson", "1992-07-30", "female", "B+", "alice.johnson@example.com", "45678912"},
        //     {"patient4", "password", "pa004", "bob brown", "1980-11-12", "male", "AB-", "bob.brown@example.com", "98765432"},
        //     {"patient5", "password", "pa005", "charlie davis", "1995-09-05", "male", "O-", "charlie.davis@example.com", "65432198"},
        // };

        for (int i = 0; i < patientData.length; i++) 
        {
            Object[] data = patientData[i];
            String hospitalId = (String) data[0];
            String password = (String) data[1];
            String patientId = ((String) data[2]).trim().toLowerCase();
            String name = ((String) data[3]).trim().toLowerCase();
            String dob = (String) data[4];
            String gender = (String) data[5];
            String bloodGroup = (String) data[6];
            String email = (String) data[7];
            String contactNumber = String.valueOf((long) Double.parseDouble(data[8].toString()));

            if (users.containsKey(hospitalId)) 
            {
                System.out.println("Hospital ID " + hospitalId + " already exists. Please try again.");
            }
            else
            {
                // String hospitalID, String password, String patientId, String name, String dob, String gender, String bloodGroup, String email
                Patient patient = new Patient(hospitalId, password, patientId, name, dob, gender, bloodGroup, email, contactNumber);
                users.put(hospitalId, patient);
                
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
        System.out.println();

        User user = users.get(hospitalId);

        if (user != null && user.checkPassword(password)) 
        {
            System.out.println("Login successful!");
            if (password.equals("password")) 
            {
                System.out.println("You are using the default password. Please change your password.");
                System.out.println();
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
            System.out.println();
        }
    }

    private static void saveContext(MedicalRecordManagement medicalRecordManagement, StaffManagement staffManagement, InventoryManagement inventoryManagement, PrescriptionManagement prescriptionManagement)
    {
       // Prepare lists to hold staff and patient data
        List<Staff> staffList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();
        for (User user : users.values()) {
            // Check if the user is an instance of Patient or Staff
            if (user instanceof Patient) {
                Patient patient = (Patient) user; // Cast to Patient
                // Add to the patient list
                patientList.add(patient);
            } 
            else if (user instanceof Staff) 
            {
                Staff staff = (Staff) user; // Cast to Staff
                // Add to the staff list
                staffList.add(staff);
            }
        }

        // ExcelWriter.writeExcelData("src/main/resources/Staff_List.xlsx", staffList);
        // ExcelWriter.writeExcelData("src/main/resources/Patient_List.xlsx", patientList);

    }
    private static void changePassword(User user) 
    {
        System.out.println("Enter new password: ");
        String newPassword = sc.nextLine();
        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
        System.out.println();
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
                Staff doctor = (Staff) user;
                DoctorMenu doctorMenu = new DoctorMenu(medicalRecordManagement, appointmentManagement, doctor);
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