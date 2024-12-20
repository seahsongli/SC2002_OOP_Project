package hospitalManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrator extends Staff {
    Scanner sc = new Scanner(System.in);

    private List<Staff> staffList;
    private AdministratorMenu adminstratorMenu;

    public Administrator(String hospitalId, String password, String staffId, String name, String role, String gender,
            int age) {
        super(hospitalId, password, staffId, name, role, gender, age);
        super.updateRole("Adminstrator");

        System.out.println("Enter Adminstrator name: ");
        String adminstratorName = sc.nextLine();
        super.updateName(adminstratorName);

        System.out.println("Enter administrator gender: ");
        String adminstratorGender = sc.nextLine();
        super.updateGender(adminstratorGender);

        System.out.println("Enter administrator age: ");
        int adminstratorAge = sc.nextInt();
        sc.nextLine();
        super.updateAge(adminstratorAge);
        this.staffList = new ArrayList<>();
    }

    public void addStaff(String hospitalID, String password, String staffId, String name, String role, String gender,
            int age) {
        Staff newStaff = new Staff(hospitalID, password, staffId, name, role, gender, age);
        staffList.add(newStaff);
    }

    public void removeStaff(String staffId) {
        for (Staff staff : staffList) {
            if (staff.getStaffId() == staffId) {
                staffList.remove(staff);
                break;
            }
        }
    }

    public void updateStaffDetails(String staffId, String name, String role, String gender, int age) {
        for (Staff staff : staffList) {
            if (staff.getStaffId() == staffId) {
                staff.updateName(name);
                staff.updateRole(role);
                staff.updateGender(gender);
                staff.updateAge(age);
            }
        }
    }

    // get scheduled appointments for a particular patient.
    public void viewScheduledAppointments(String patientId, String patientName, String doctorName, String doctorId) {
        adminstratorMenu.adminViewScheduledAppointments(patientId, patientName, doctorName, doctorId);
    }

    public void displayInventory() {
        adminstratorMenu.displayInventory();
    }

    public void addStock(String name, int quantity) {
        adminstratorMenu.addStock(name, quantity);
    }

    public void removeStock(String name, int quantity) {
        adminstratorMenu.removeStock(name, quantity);
    }

    public void updateStock(String name, int quantity) {
        adminstratorMenu.updateStock(name, quantity);
    }

    public void displayLowStocks() {
        adminstratorMenu.displayLowStocks();
    }
}