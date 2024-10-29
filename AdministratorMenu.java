
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

    public void displayMenu() {
        while (true) {
            System.out.println("Administrator Menu:");
            System.out.println("1. Display Inventory");
            System.out.println("2. Display Low Stocks");
            System.out.println("3. Add Stock");
            System.out.println("4. Remove Stock");
            System.out.println("5. Update Stock");
            System.out.println("6. Approve Replenishment Request");
            System.out.println("7. Logout");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayInventory();
                    break;
                case 2:
                    displayLowStocks();
                    break;
                case 3:
                    System.out.println("Enter medicine name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    addStock(name, quantity);
                    break;
                case 4:
                    System.out.println("Enter medicine name: ");
                    name = sc.nextLine();
                    System.out.println("Enter quantity: ");
                    quantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    removeStock(name, quantity);
                    break;
                case 5:
                    System.out.println("Enter medicine name: ");
                    name = sc.nextLine();
                    System.out.println("Enter quantity: ");
                    quantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    updateStock(name, quantity);
                    break;
                case 6:
                    System.out.println("Enter request ID: ");
                    int requestId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    approveReplenishmentRequest(requestId);
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
        staffManagement.viewStaffs();
    }

    public void addStaff(String name, String role, String gender, int age)
    {
        staffManagement.addStaff(name, role, gender, age);
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