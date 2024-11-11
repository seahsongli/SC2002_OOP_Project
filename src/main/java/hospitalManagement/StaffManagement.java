package hospitalManagement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StaffManagement
{
    List<Staff> staffs;
    private Scanner sc;

    public StaffManagement()
    {
        this.staffs = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    // Check if staff ID already exists
    boolean isStaffIdMatched(String staffId) 
    {
        for (Staff staff : staffs) 
        {
            if (staff.getStaffId() == staffId) 
            {
                return true;
            }
        }
        return false;
    }
    // check if hospital ID already exists
    boolean isHospitalIdMatched(String hospitalId) {
        for (Staff staff : staffs) {
            if (staff.getHospitalId().equals(hospitalId)) 
            {
                return true;
            }
        }
        return false;
    }

      // Method to view staff with filtering options
    public void viewStaffsWithFiltering() 
    {
        System.out.println("Choose filtering options:");
        System.out.println("1. Filter by Gender");
        System.out.println("2. Filter by Role");
        System.out.println("3. Filter by Age Range");
        System.out.println("4. View All Staff");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        List<Staff> filteredStaffs = new ArrayList<>(staffs);

        switch (choice) 
        {
            case 1:
                filteredStaffs = filterByGender(filteredStaffs);
                if(filteredStaffs == null) return;
                viewStaffs(filteredStaffs);
                break;
            case 2:
                filteredStaffs = filterByRole(filteredStaffs);
                if(filteredStaffs == null) return;
                viewStaffs(filteredStaffs);
                break;
            case 3:
                filteredStaffs = filterByAgeRange(filteredStaffs);
                if(filteredStaffs == null) return;
                viewStaffs(filteredStaffs);
                break;

            case 4:
                // No filtering, display all staff
                viewStaffs(staffs);
                break;
            default:
                System.out.println("Invalid choice.");
        }

    }

    private List<Staff> filterByGender(List<Staff> staffList) {
        System.out.print("Enter Gender to filter by (Male/Female): ");
        String gender = sc.nextLine().toLowerCase();
        if(gender == "male" || gender == "female")
        {
            return staffList.stream()
                    .filter(staff -> staff.getGender().equalsIgnoreCase(gender))
                    .collect(Collectors.toList());
        }
        // Wrong input by the user
        else
        {
            System.out.println("Invalid gender");
            System.out.print("\n");
            return null;
        }
    }

    private List<Staff> filterByRole(List<Staff> staffList) 
    {
        System.out.print("Enter Role to filter by (e.g., Doctor, Pharmacist or Administrator): ");
        String role = sc.nextLine();
        // Validate role
        boolean isValidRole = Roles.ALLOWED_ROLES.stream().anyMatch(r -> r.equalsIgnoreCase(role));
        if(isValidRole)
        {
            
            return staffList.stream()
                    .filter(staff -> staff.getRole().equalsIgnoreCase(role))
                    .collect(Collectors.toList());
        }
        // wrong input by the user
        else
        {
            System.out.println("Invalid role entered. Valid roles are: " + String.join(", ", Roles.ALLOWED_ROLES));
            System.out.print("\n");
            return null;
        }
    }

    private List<Staff> filterByAgeRange(List<Staff> staffList) 
    {
        try 
        {
            System.out.print("Enter Minimum Age: ");
            int minAge = sc.nextInt();
            System.out.print("Enter Maximum Age: ");
            int maxAge = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Logical Validation: minAge should not be greater than maxAge
            if (minAge > maxAge) 
            {
                System.out.println("Minimum age cannot be greater than maximum age. Aborting view staff operation.");
                return null; // Indicate invalid input
            }

            // Boundary Checks: Ensure ages are within a realistic range
            if (minAge < 0 || maxAge > 120) 
            {
                System.out.println("Age must be between 0 and 120. Aborting view staff operation.");
                return null; // Indicate invalid input
            }

            List<Staff> filteredList = staffList.stream()
                    .filter(staff -> staff.getAge() >= minAge && staff.getAge() <= maxAge)
                    .collect(Collectors.toList());

            if (filteredList.isEmpty()) 
            {
                System.out.println("No staff members found in the age range: " + minAge + " - " + maxAge);
                return null;
            }

            return filteredList;

        } catch (InputMismatchException e) 
        {
            System.out.println("Invalid input. Please enter numerical values for ages. Aborting view staff operation.");
            sc.nextLine(); // Clear the scanner buffer
            return null; // Indicate invalid input
        }
    }

    public void viewStaffs(List<Staff> staffList)
    {
        if(staffs.isEmpty())
        {
            System.out.println("Currently there is no staff in the Hospital!\n");
        }
        for (Staff staff : staffList)
        {
            System.out.println("Staff ID: " + staff.getStaffId().toUpperCase() + "," + " Name: " + staff.getName().toUpperCase() + "," + " Role: " + staff.getRole().toUpperCase() + "," + " Gender: " + staff.getGender().toUpperCase() + "," + " Age: " + staff.getAge());
        }
        System.out.print("\n");

    }

    public void addStaff(String hospitalID, String password, String staffId, String name, String role, String gender, int age)
    {
        // validate staffId
        if(isStaffIdMatched(staffId))
        {
            System.out.println("Staff ID already exists. Please try again.");
            return;
        }
        // validate hospital ID
        if(isHospitalIdMatched(hospitalID))
        {
            System.out.println("Hospital ID already exists. Please try again.");
            return;
        }
        Staff newStaff = new Staff(hospitalID, password, staffId, name, role, gender, age);
        staffs.add(newStaff);
        System.out.println("Staff with ID " + staffId + " added successfully.");
        return;
    }

    public void removeStaff(String staffId)
    {
        for (Staff staff : staffs)
        {
            if (staff.getStaffId() == staffId)
            {
                staffs.remove(staff);
                System.out.println("Staff with ID " + staffId.toUpperCase() + " removed successfully.");
                return;
            }
            System.out.println("Staff with ID " + staffId.toUpperCase() + " not found.");
            return;
        }
    }

    public void updateStaffDetails(String staffId, String name, String role, String gender, int age)
    {
        for (Staff staff : staffs)
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
}