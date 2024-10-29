public class AdminstratorMenu
{
    private InventoryManagement inventoryManagement;
    private StaffManagement staffManagement;
    private AppointmentManagement appointmentManagement;
    
    public AdminstratorMenu(InventoryManagement inventoryManagement, StaffManagement staffManagement, AppointmentManagement appointmentManagement)
    {
        this.inventoryManagement = inventoryManagement;
        this.staffManagement = staffManagement;
        this.appointmentManagement = appointmentManagement;
    }
    {
        this.inventoryManagement = inventoryManagement;
        this.staffManagement = staffManagement;
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