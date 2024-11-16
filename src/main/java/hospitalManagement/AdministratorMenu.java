package hospitalManagement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import patient.Patient;

public class AdministratorMenu
{
    private InventoryManagement inventoryManagement;
    private StaffManagement staffManagement;
    private AppointmentManagement appointmentManagement;
    private Map<String, User> users = new HashMap<>(); // Users map from Main class
    
    private Scanner sc = new Scanner(System.in);
    public AdministratorMenu(InventoryManagement inventoryManagement, StaffManagement staffManagement, AppointmentManagement appointmentManagement, Map<String, User> users)
    {
        this.inventoryManagement = inventoryManagement;
        this.staffManagement = staffManagement;
        this.appointmentManagement = appointmentManagement;
        this.users = users;

        // Debugging statement to confirm receipt
        System.out.println("AdministratorMenu initialized with " + users.size() + " users.");

    }

    public void displayMenu() 
    {
        while (true) 
        {
            System.out.println("--- Administrator Menu ---");
            System.out.println("1. View Hospital Staff"); // done
            System.out.println("2. Manage Hospital Staff"); // done
            System.out.println("3. View Appointment details"); 
            System.out.println("4. View Medication Inventory"); // done
            System.out.println("5. Manage Medication Inventory"); // done
            System.out.println("6. Approve Replenishment Requests"); // done
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
    //testing purpose => think of how to implement this
    public void initializeUsers(StaffManagement staffManagement) 
    {
        // Staff data: hospitalId, password, staffId, name, role
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
    
    public void updateStaffDetails(String oldStaffId, String newStaffId, String name, String role, String gender, Integer age)
    {
        staffManagement.updateStaffDetails(oldStaffId, newStaffId, name, role, gender, age);
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
            String patientId = sc.nextLine().trim();
            
            System.out.print("Enter Patient Name: ");
            String patientName = sc.nextLine().trim();
            // doctor Id and staff Id are the same
            System.out.print("Enter Doctor ID: ");
            String staffId = sc.nextLine().trim();
            
            System.out.print("Enter Doctor Name: ");
            String doctorName = sc.nextLine().trim();
            doctorName = doctorName.isEmpty() ? null : doctorName;
            System.out.print("\n");

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
    private void updateStaffDetailsInteractive() 
    {
        System.out.print("Enter current staff ID: ");
        String oldStaffId = sc.nextLine().trim().toLowerCase();
        
        // Check if staff exists
        if (!staffManagement.isStaffIdMatched(oldStaffId)) {
            System.out.println("Staff with ID " + oldStaffId + " not found.");
            return;
        }
        
        System.out.println("Select the fields you want to update:");
        System.out.println("1. Staff ID");
        System.out.println("2. Name");
        System.out.println("3. Role");
        System.out.println("4. Gender");
        System.out.println("5. Age");
        System.out.println("6. Back to Manage Staff Menu");
        System.out.print("Enter your choices separated by commas (e.g., 1,3,5): ");
        String choicesLine = sc.nextLine().trim();
        
        if (choicesLine.equals("6")) {
            return; // Return to Manage Staff Menu
        }
        
        String[] choices = choicesLine.split(",");
        String newStaffId = null;
        String name = null;
        String role = null;
        String gender = null;
        Integer age = null;
        
        for (String choiceStr : choices) {
            int choice;
            try 
            {
                choice = Integer.parseInt(choiceStr.trim());
            } catch (NumberFormatException e) 
            {
                System.out.println("Invalid input: " + choiceStr.trim());
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new Staff ID: ");
                    newStaffId = sc.nextLine().trim().toLowerCase();
                    break;
                case 2:
                    System.out.print("Enter new Name: ");
                    name = sc.nextLine().trim().toLowerCase();
                    break;
                case 3:
                    System.out.print("Enter new Role: ");
                    role = sc.nextLine().trim().toLowerCase();
                    break;
                case 4:
                    System.out.print("Enter new Gender: ");
                    gender = sc.nextLine().trim().toLowerCase();
                    break;
                case 5:
                    System.out.print("Enter new Age: ");
                    try {
                        age = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age input. Skipping age update.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice: " + choice);
                    break;
            }
        }
        
        // Call the update method with the collected data
        staffManagement.updateStaffDetails(oldStaffId, newStaffId, name, role, gender, age);
    }
    // Function to add, remove or update staff
    public void manageStaff()
    {
        
       while(true) 
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
                       // validate hospital Id
                       if(staffManagement.isHospitalIdMatched(hospitalID))
                       {
                           System.out.println("Hospital ID already exists. Please try again.");
                           break;
                       }
                       
                       System.out.print("Enter staff ID: ");
                       String staffId = sc.nextLine().trim().toLowerCase();
   
                       // validate staffId
                       if(staffManagement.isStaffIdMatched(staffId))
                       {
                           System.out.println("Staff ID already exists. Please try again.");
                           break;
                       }
                       
                       System.out.print("Enter staff name: ");
                       String name = sc.nextLine();
                       
                       System.out.print("Enter staff role: ");
                       String role = sc.nextLine();
                       
                       System.out.print("Enter staff gender: ");
                       String gender = sc.nextLine();
                       
                       System.out.print("Enter staff age: ");
                       int age = Integer.parseInt(sc.nextLine());
                       
                       // Add the staff member
                       staffManagement.addStaff(hospitalID, "Password", staffId.toLowerCase(), name.toLowerCase(), role.toLowerCase(), gender.toLowerCase(), age);
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
                    //    String staffId = sc.nextLine();
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
                   
                   
                   updateStaffDetailsInteractive();
                   break;
               case 4:
                   return;
               default:
                   System.out.println("Invalid choice. Please try again.");
                   break;
           }    
       }
        
    }
    /**
     * Helper method to manage medication inventory.
     */
    private void manageMedicationInventory() 
    {
        while (true) {
            System.out.println("\n--- Manage Medication Inventory ---");
            System.out.println("1. Add Medicine"); // done
            System.out.println("2. Remove Medicine"); // done
            System.out.println("3. Update Stock Level"); // done
            System.out.println("4. Set Low Stock Alert Level"); // done
            System.out.println("5. Back to Administrator Menu"); // done
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
    for (User user : users.values()) 
    {
        if (user instanceof Patient && user.getHospitalId().equals(patientIdStr)) 
        {
            Patient patient = (Patient) user;
            if (patient.getId().equalsIgnoreCase(patientId)) 
            {
                return true;
            }
        }
    }
    return false;
}
}
        
