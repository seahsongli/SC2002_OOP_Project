
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
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Set Prescription");
            System.out.println("3. Update Prescription Status");
            System.out.println("4. View Inventory");
            System.out.println("5. Logout");
            System.out.println("6. test");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter patient ID: ");
                    String patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    String patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    String doctorName = sc.nextLine();
                    viewAppointmentOutcomeRecord(patientId, patientName, doctorName);
                    break;
                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter prescription: ");
                    String prescription = sc.nextLine();
                    setPrescription(patientId, patientName, prescription);
                    break;
                case 3:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor id: "); 
                    String doctorId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    String time = sc.nextLine();
                    System.out.println("Enter prescription status: ");
                    Status status = getStatusFromUser();
                    if (status != null) {
                        updatePrescriptionStatus(patientId, patientName, doctorId, doctorName, date, time, status);
                    }
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                case 6:
                    System.out.println("enter medication name ");
                    String medicationName = sc.nextLine();
                    System.out.println(" quantity ");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    submitReplenishmentRequest(medicationName, quantity);
                    break;
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

    public void viewAppointmentOutcomeRecord(String patientId, String patientName, String doctorName)
    {
        prescriptionManagement.viewAppointmentOutcomeRecord(patientId, patientName, doctorName);
    }

    public void setPrescription(String patientId, String patientName, String prescription)
    {
        prescriptionManagement.setPrescription(patientId, patientName, prescription);
    }

    // consider using the entire appointment to check if its the correct appointment, and update accordingly
    // public void updatePrescriptionStatus(int patientId, String patientName, String status)
    // {
    //     prescriptionManagement.updatePrescriptionStatus(patientId, patientName, status);
    // }

    public void updatePrescriptionStatus(String patientId, String patientName, String doctorId, String doctorName, String date, String time, Status prescriptionStatus) {
        // Logic to find the correct appointment and update the prescription status
        
            // Assuming Appointment has a method to set prescription status
            prescriptionManagement.updatePrescriptionStatus(patientId, patientName, doctorId, doctorName, date, time, prescriptionStatus);
            System.out.println("Prescription status updated for patient " + patientName + " with ID " + patientId + " to " + prescriptionStatus);
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