package hospitalManagement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PharmacistMenu
{
    private PrescriptionManagement prescriptionManagement;
    private InventoryManagement inventoryManagement;

    private Scanner sc = new Scanner(System.in);
    public PharmacistMenu(PrescriptionManagement prescriptionManagement, InventoryManagement inventoryManagement)
    {
        this.prescriptionManagement = prescriptionManagement;
        this.inventoryManagement = inventoryManagement;
    }
    
    
    public void displayMenu() {
        while (true) {
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record"); // done
            System.out.println("2. Update Prescription Status"); // done
            System.out.println("3. View Inventory"); // done
            System.out.println("4. Submit Replenishment Request"); // done
            System.out.println("5. Logout");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter patient ID: ");
                    String patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    String patientName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter doctor name: ");
                    String doctorName = sc.nextLine().trim().toLowerCase();
                    viewAppointmentOutcomeRecord(patientId, patientName, doctorName);
                    break;
               
                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter doctor id: "); 
                    String doctorId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine().trim().toLowerCase();
                    String date = getDateFromUser();
                    String time = getTimeFromUser();
                    System.out.println("Enter prescription status: ");
                    Status status = getStatusFromUser();
                    if (status != null) {
                        updatePrescriptionStatus(patientId, patientName, doctorId, doctorName, date, time, status);
                    }
                    break;

                case 3:
                    viewInventory();
                    break;

                case 4:
                    System.out.println("Enter medication name ");
                    String medicationName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter quantity to restock: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    submitReplenishmentRequest(medicationName, quantity);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    
    private Status getStatusFromUser() {
        System.out.println("Select prescription status:");
        System.out.println("1. PENDING");
        System.out.println("2. COMPLETED");
        System.out.println("3. REJECTED");
        int statusChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        switch (statusChoice) {
            case 1:
                return Status.PENDING;
            case 2:
                return Status.COMPLETED;
            case 3:
                return Status.REJECTED;
            default:
                System.out.println("Invalid choice. Please try again.");
                return null;
            }
    }

    private String getTimeFromUser() {
        String timeInput;
        while (true) {
            System.out.println("Enter time (HH:mm): ");
            timeInput = sc.nextLine();
            try {
                // Parse input to ensure it's valid
                LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
                break; // Exit loop if the input is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please try again (e.g., 14:30).");
            }
        }
        return timeInput;
    }

    private String getDateFromUser() {
        String dateInput;
        while (true) {
            System.out.println("Enter date (yyyy-mm-dd): ");
            dateInput = sc.nextLine();
            try {
                // Parse input to ensure it's valid
                LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break; // Exit loop if the input is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again (e.g., 2024-11-16).");
            }
        }
        return dateInput;
    }

    public void viewAppointmentOutcomeRecord(String patientId, String patientName, String doctorName)
    {
        prescriptionManagement.viewAppointmentOutcomeRecord(patientId, patientName, doctorName);
    }

    public void setPrescription(String patientId, String patientName, String prescription)
    {
        prescriptionManagement.setPrescription(patientId, patientName, prescription);
    }

    public void updatePrescriptionStatus(String patientId, String patientName, String doctorId, String doctorName, String date, String time, Status prescriptionStatus) {
        // Logic to find the correct appointment and update the prescription status
        
            // Assuming Appointment has a method to set prescription status
            prescriptionManagement.updatePrescriptionStatus(patientId, patientName, doctorId, doctorName, date, time, prescriptionStatus);
    }

    public void viewInventory()
    {
        inventoryManagement.displayInventory();
    }

    public void updateStockLevel(String medicationName, int quantity)
    {
        inventoryManagement.updateStockLevel(medicationName, quantity);
    }

    public void submitReplenishmentRequest(String medicationName, int quantity)
    {
        inventoryManagement.submitReplenishmentRequest(medicationName, quantity);
    }
}