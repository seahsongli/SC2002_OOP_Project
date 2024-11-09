
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdministratorMenu
{
    private InventoryManagement inventoryManagement;
    private StaffManagement staffManagement;
    private AppointmentManagement appointmentManagement;
    private Map<String, User> users; // Users map from Main class
    
    private Scanner sc = new Scanner(System.in);
    public AdministratorMenu(InventoryManagement inventoryManagement, StaffManagement staffManagement, AppointmentManagement appointmentManagement, Map<String, User> users)
    {
        this.inventoryManagement = inventoryManagement;
        this.staffManagement = staffManagement;
        this.appointmentManagement = appointmentManagement;


        // Debugging statement to confirm receipt
        System.out.println("AdministratorMenu initialized with " + users.size() + " users.");

    }

    public void displayMenu() 
    {
        while (true) 
        {
            System.out.println("--- Administrator Menu ---");
            System.out.println("1. View Hospital Staff");
            System.out.println("2. Manage Hospital Staff");
            System.out.println("3. View Appointment details");
            System.out.println("4. View Medication Inventory");
            System.out.println("5. Manage Medication Inventory");
            System.out.println("6. Approve Replenishment Requests");
            System.out.println("7. Logout");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline


            switch (choice)
            {
                case 1:
                    staffManagement.viewStaffsWithFiltering();
                    break;
                case 2:
                    manageStaff();
                    break;
                case 3:
                    viewAppointmentsByEnteringDetails();
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    manageMedicationInventory();
                    break;
                case 6:
                    approveReplenishmentRequests();
                    break;
                case 7:
                    System.out.println("Logging out...");
                return;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        
    }
    
    public void viewStaffs()
    {
        staffManagement.viewStaffsWithFiltering();
    }
    public void addStaff(String hospitalID, String password, String staffId,  String name, String role, String gender, int age)
    {
        staffManagement.addStaff(hospitalID, password, staffId, name, role, gender, age);
    }

    public void removeStaff(String staffId)
    {
        staffManagement.removeStaff(staffId);
    }
    
    
    public void updateStaffDetails(String staffId, String name, String role, String gender, int age)
    {
        staffManagement.updateStaffDetails(staffId, name, role, gender, age);
    }
    
    
    public void displayInventory()
    {
        inventoryManagement.displayInventory();
    }
    
    public void displayLowStocks()
    {
        inventoryManagement.displayLowStocks();
    }
    
    public void addStock(String name, int quantity)
    {
        inventoryManagement.addStock(name, quantity);
    }

    public void removeStock(String name, int quantity)
    {
        inventoryManagement.removeStock(name, quantity);
    }
    
    public void updateStock(String name, int quantity)
    {
        inventoryManagement.updateStockLevel(name, quantity);
    }
    
    public void approveReplenishmentRequest(int requestId)
    {
        inventoryManagement.approveReplenishmentRequest(requestId);
    }
    
    
    // public void displayScheduledAppointments(int patientId, String patientName, String doctorName, String doctorId)
    // {
        //     appointmentManagement.adminViewScheduledAppointments(patientId, patientName, doctorName, doctorId);
        // }
        
    public void adminViewScheduledAppointments(String patientId, String patientName, String doctorName, String staffId)
    {
        appointmentManagement.adminViewScheduledAppointments(patientId, patientName, doctorName, staffId);
    }



    // helper functions
    
    public void viewAppointmentsByEnteringDetails()
    {
        System.out.println("\n--- View Appointment Details ---");
        System.out.println("Enter the following details to filter appointments. Leave blank to skip a filter.");
        
        try 
        {
            System.out.print("Enter Patient ID: ");
            String patientId = sc.nextLine().trim().toLowerCase();
            
            System.out.print("Enter Patient Name: ");
            String patientName = sc.nextLine().trim();
            
            System.out.print("Enter staff ID: ");
            String staffId = sc.nextLine().trim();
            
            System.out.print("Enter Doctor Name: ");
            String doctorName = sc.nextLine().trim();
            doctorName = doctorName.isEmpty() ? null : doctorName;

             // Validate Patient ID
             if (patientId != null && !patientExists(patientId)) 
             {
                System.out.println("Error: Patient ID " + patientId + " does not exist.");
                return;
             }

            // Validate Doctor ID
            if (staffId != null && !staffManagement.isStaffIdMatched(staffId)) 
            {
                System.out.println("Error: staff ID " + staffId + " does not exist.");
                return;
            }

            
            // Call to view appointments
            adminViewScheduledAppointments(patientId, patientName, doctorName, staffId);
            
        } 
        catch (Exception e) {
            System.out.println("Error viewing appointments: " + e.getMessage());
        }
        
    }
    
    // Function to add, remove or update staff
    public void manageStaff()
    {
        
        
        System.out.println("--- Manage Hospital Staff ---");
        System.out.println("1. Add Staff");
        System.out.println("2. Remove Staff");
        System.out.println("3. Update Staff Details");
        System.out.println("4. Back to main menu");
        
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        switch (staffChoice) 
        {
            case 1:
            try 
            {
                System.out.print("Enter hospital ID: ");
                String hospitalID = sc.nextLine();
                
                
                System.out.print("Enter staff ID: ");
                String staffId = sc.nextLine().trim().toLowerCase();
                
                System.out.print("Enter staff name: ");
                String name = sc.nextLine();
                
                System.out.print("Enter staff role: ");
                String role = sc.nextLine();
                
                System.out.print("Enter staff gender: ");
                String gender = sc.nextLine();
                
                System.out.print("Enter staff age: ");
                int age = Integer.parseInt(sc.nextLine());
                
                // Add the staff member
                staffManagement.addStaff(hospitalID, "Password", staffId, name.toUpperCase(), role.toUpperCase(), gender.toUpperCase(), age);
                break;
                
                
                
            } catch (NumberFormatException e) 
            
            {
                System.out.println("Invalid input. Staff ID and age must be integers.");
                break;
                
                
            } catch (Exception e) 
            
            {
                System.out.println("Error adding staff: " + e.getMessage());
                break;
                
                
            }
            case 2:
            try 
            {
                System.out.print("Enter staff ID to remove: ");
                String staffId = sc.nextLine().trim().toLowerCase();
                staffManagement.removeStaff(staffId);
                break;
                
            } catch (NumberFormatException e) 
            
            {
                System.out.println("Invalid input. Staff ID must be an integer.");
                break;
                
            } catch (Exception e) 
            
            {
                System.out.println("Error removing staff: " + e.getMessage());
                break;
                
            }
            case 3:
            
            System.out.print("Enter staff ID to update: ");
            String staffId = sc.nextLine().trim().toLowerCase();
            
            System.out.print("Enter staff name: ");
            String name = sc.nextLine().trim().toLowerCase();
            
            System.out.print("Enter staff role: ");
            String role = sc.nextLine().trim().toLowerCase();
            
            System.out.print("Enter new staff gender: ");
            String gender = sc.nextLine().trim().toLowerCase();
            
            System.out.print("Enter new staff age: ");
            int age = Integer.parseInt(sc.nextLine());
            staffManagement.updateStaffDetails(staffId, name, role, gender, age);
            break;
            
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }    
        
    }
    /**
     * Helper method to manage medication inventory.
     */
    private void manageMedicationInventory() 
    {
        while (true) {
            System.out.println("\n--- Manage Medication Inventory ---");
            System.out.println("1. Add Medicine");
            System.out.println("2. Remove Medicine");
            System.out.println("3. Update Stock Level");
            System.out.println("4. Set Low Stock Alert Level");
            System.out.println("5. Back to Administrator Menu");
            System.out.print("Enter your choice: ");
            
            int choice;
            try 
            {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.nextLine(); // Clear the scanner buffer
                continue;
            }
            sc.nextLine(); // Consume newline
            
            switch (choice) 
            {
                case 1:
                    addMedicine();
                    break;
                case 2:
                    removeMedicine();
                    break;
                case 3:
                    updateStockLevel();
                    break;
                case 4:
                    setLowStockAlertLevel();
                    break;
                case 5:
                    // Return to main menu
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    /**
     * Adds a new medicine or updates the quantity if it already exists.
     */
    private void addMedicine() 
    {
        try {
            System.out.print("Enter Medicine Name: ");
            String name = sc.nextLine().trim().toLowerCase();
            if (name.isEmpty()) {
                System.out.println("Medicine name cannot be empty.");
                return;
            }
            
            System.out.print("Enter Quantity to Add: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());
            if (quantity <= 0) 
            {
                System.out.println("Quantity must be a positive integer.");
                return;
            }
            
            inventoryManagement.addStock(name, quantity);
            System.out.println("Medicine added/updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Quantity must be an integer.");
        } catch (Exception e) {
            System.out.println("Error adding medicine: " + e.getMessage());
        }
    }
    
    
    /**
     * Removes a specified quantity of a medicine or deletes it if quantity reaches zero.
     */
    private void removeMedicine() 
    {
        try {
            System.out.print("Enter Medicine Name to Remove: ");
            String name = sc.nextLine().trim().toLowerCase();
            if (name.isEmpty()) 
            {
                System.out.println("Medicine name cannot be empty.");
                return;
            }
            
            System.out.print("Enter Quantity to Remove: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());
            if (quantity <= 0) 
            {
                System.out.println("Quantity must be a positive integer.");
                return;
            }
            
            inventoryManagement.removeStock(name, quantity);
            System.out.println("Medicine removed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Quantity must be an integer.");
        } catch (Exception e) {
            System.out.println("Error removing medicine: " + e.getMessage());
        }
    }
    
    /**
     * Updates the stock level of a specific medicine by adding the specified quantity.
     */
    private void updateStockLevel() 
    {
        try 
        {
            System.out.print("Enter Medicine Name to Update: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) 
            {
                System.out.println("Medicine name cannot be empty.");
                return;
            }
            
            System.out.print("Enter Quantity to Add: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());
            // Quantity can be negative to decrease stock if needed
        inventoryManagement.updateStockLevel(name, quantity);
        System.out.println("Stock level updated successfully.");
    } catch (NumberFormatException e) 
    {
        System.out.println("Invalid input. Quantity must be an integer.");
    } catch (Exception e) 
    {
        System.out.println("Error updating stock level: " + e.getMessage());
    }
}


/**
 * Sets the low stock alert level for a specific medicine.
 */
private void setLowStockAlertLevel() 
{
    try 
    {
        System.out.print("Enter Medicine Name: ");
        String name = sc.nextLine().trim().toLowerCase();
        if (name.isEmpty()) 
        {
            System.out.println("Medicine name cannot be empty.");
            return;
        }
        
        System.out.print("Enter Low Stock Alert Level: ");
        int alertLevel = Integer.parseInt(sc.nextLine().trim());
        if (alertLevel < 0) 
        {
            System.out.println("Alert level cannot be negative.");
            return;
        }
        
        inventoryManagement.setLowStockAlertLevel(name, alertLevel);
        System.out.println("Low stock alert level set successfully.");
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Alert level must be an integer.");
    } catch (Exception e) {
        System.out.println("Error setting low stock alert level: " + e.getMessage());
    }
}

/**
 * Approves pending replenishment requests.
 */
private void approveReplenishmentRequests() 
{
    System.out.println("\n--- Pending Replenishment Requests ---");
    
    // Assuming InventoryManagement has a method to retrieve pending requests
    List<Request> pendingRequests = inventoryManagement.getPendingReplenishmentRequests();
    
    if (pendingRequests.isEmpty()) 
    {
        System.out.println("No pending replenishment requests.");
        return;
    }
    
    // Display all pending requests
    for (Request request : pendingRequests)
    {
        System.out.println("Request ID: " + request.getRequestId() +
        ", Medicine: " + request.getMedicine().getName() +
        ", Quantity: " + request.getReplenishQuantity() +
        ", Status: " + request.getStatus());
    }
    
    // Prompt admin to approve a request
    System.out.print("\nEnter the Request ID to approve (or type 'cancel' to return): ");
    String input = sc.nextLine().trim();
    
    if (input.equalsIgnoreCase("cancel")) {
        System.out.println("Returning to Administrator Menu.");
        return;
    }
    
    try 
    {
        int requestId = Integer.parseInt(input);
        boolean approved = inventoryManagement.approveReplenishmentRequest(requestId);
        
        if (approved) 
        {
            System.out.println("Replenishment request " + requestId + " approved successfully.");
        } 
        else 
        {
            System.out.println("Replenishment request " + requestId + " not found or already processed.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Request ID must be an integer.");
    } catch (Exception e) {
        System.out.println("Error approving replenishment request: " + e.getMessage());
    }
}

private boolean patientExists(String patientId) 
{
    String patientIdStr = String.valueOf(patientId);
    for (User user : users.values()) {

        if (user instanceof Patient && user.getHospitalId().equals(patientIdStr)) 
        {
            return true;
        }
    }
    return false;
}
}
        