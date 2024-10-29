import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrator extends Staff{
    Scanner sc = new Scanner(System.in);

    private List<Staff> staffList;
    private AdministratorMenu adminstratorMenu;
    
    public Administrator()
    {
        super("", "", "", 0); 
        super.updateRole("Adminstrator");

        System.out.println("Enter Adminstrator name: ");
        String adminstratorName = sc.nextLine();
        super.updateName(adminstratorName);

        System.out.println("Enter pharmacist gender: ");
        String adminstratorGender = sc.nextLine();
        super.updateGender(adminstratorGender);

        System.out.println("Enter pharmacist age: ");
        int adminstratorAge = sc.nextInt();
        sc.nextLine();
        super.updateAge(adminstratorAge);
        this.staffList = new ArrayList<>();
    }

    public void addStaff(String name, String role, String gender, int age)
    {
        Staff newStaff = new Staff(name, role, gender, age);
        staffList.add(newStaff);
    }

    public void removeStaff(int staffId)
    {
        for (Staff staff : staffList)
        {
            if (staff.getStaffId() == staffId)
            {
                staffList.remove(staff);
                break;
            }
        }
    }

    public void updateStaffDetails(int staffId, String name, String role, String gender, int age)
    {
        for (Staff staff : staffList)
        {
            if (staff.getStaffId() == staffId)
            {
                staff.updateName(name);
                staff.updateRole(role);
                staff.updateGender(gender);
                staff.updateAge(age);   
            }
        }
    }

    // get scheduled appointments for a particular patient.
    public void viewScheduledAppointments(int patientId, String patientName, String doctorName, String doctorId)
    {
        adminstratorMenu.adminViewScheduledAppointments(patientId, patientName , doctorName, doctorId);
    }

    public void displayInventory()
    {
        adminstratorMenu.displayInventory();
    }

    public void addStock(String name, int quantity)
    {
        adminstratorMenu.addStock(name, quantity);
    }

    public void removeStock(String name, int quantity)
    {
        adminstratorMenu.removeStock(name, quantity);
    }

    public void updateStock(String name, int quantity)
    {
        adminstratorMenu.updateStock(name, quantity);
    }

    public void displayLowStocks()
    {
        adminstratorMenu.displayLowStocks();
    }
}