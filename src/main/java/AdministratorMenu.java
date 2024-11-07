
import java.util.Scanner;

public class AdministratorMenu
{
    private InventoryManagement inventoryManagement;
    private StaffManagement staffManagement;
    private AppointmentManagement appointmentManagement;
    
    private Scanner sc = new Scanner(System.in);
    public AdministratorMenu(InventoryManagement inventoryManagement, StaffManagement staffManagement, AppointmentManagement appointmentManagement)
    {
        this.inventoryManagement = inventoryManagement;
        this.staffManagement = staffManagement;
        this.appointmentManagement = appointmentManagement;
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
                    staffManagement.viewStaffs();
                    break;
                case 2:
                    manageStaff();
                    break;
                


                
                
                
                
                case 7:
                    System.out.println("Logging out...");
                    return;
                
                
            }
        }
        
    }
    
    public void viewStaffs()
    {
        staffManagement.viewStaffs();
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
                    int staffId = Integer.parseInt(sc.nextLine());
        
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
                    int staffId = Integer.parseInt(sc.nextLine());
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
                int staffId = Integer.parseInt(sc.nextLine());
                
                System.out.print("Enter staff name: ");
                String name = sc.nextLine();
                
                System.out.print("Enter staff role: ");
                String role = sc.nextLine();
                
                System.out.print("Enter new staff gender: ");
                String gender = sc.nextLine();
                
                System.out.print("Enter new staff age: ");
                int age = Integer.parseInt(sc.nextLine());
                staffManagement.updateStaffDetails(staffId, name, role, gender, age);
                break;
                
            case 4:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                 break;
        }    
            
    }
        
        public void addStaff(String hospitalID, String password, int staffId,  String name, String role, String gender, int age)
        {
            staffManagement.addStaff(hospitalID, password, staffId, name, role, gender, age);
        }

    public void removeStaff(int staffId)
    {
        staffManagement.removeStaff(staffId);
    }


    public void updateStaffDetails(int staffId, String name, String role, String gender, int age)
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
    

    public void displayScheduledAppointments(int patientId, String patientName, String doctorName, String doctorId)
    {
        appointmentManagement.adminViewScheduledAppointments(patientId, patientName, doctorName, doctorId);
    }

    public void adminViewScheduledAppointments(int patientId, String patientName, String doctorName, String doctorId)
    {
        appointmentManagement.adminViewScheduledAppointments(patientId, patientName, doctorName, doctorId);
    }

}