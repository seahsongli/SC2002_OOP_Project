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
            if (staff.getStaffId().equalsIgnoreCase(staffId)) 
            {
                return true;
            }
        }
        return false;
    }
    // check if hospital ID already exists
    boolean isHospitalIdMatched(String hospitalId) 
    {
        for (Staff staff : staffs) 
        {
            if (staff.getHospitalId().equalsIgnoreCase(hospitalId)) 
            {
                return true;
            }
        }
        return false;
    }

    public String capitalizeName(String name) 
    {
        // split into array of chars "\\s" matches any spaces, tabs and newlines
        // if name is john doe , then words becomes ["john", "doe"]
        String[] words = name.split("\\s");
        // StringBuilder allows in-place modifications, meaning no new objects are created, which saves memory and improves performance.   
        StringBuilder formattedName = new StringBuilder();
    
        for (String word : words) 
        {
            // avoid processing empty strings in words
            // Input: "   mArY    annE   joHNsOn   "
            // Splitting: ["", "", "", "mArY", "", "", "", "annE", "", "", "joHNsOn", "", "", ""]
            if (word.length() > 0) 
            {
                // word.substring(0,1).toUpperCase extracts the first character of each string in 'words' and converts it to upper case
                // word.substring(1).toLowerCase() extracts rest of charcters of each string in 'words' and converts them to lower case
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                if (formattedName.length() > 0) {
                    formattedName.append(" ");
                }
                formattedName.append(capitalizedWord);
            }
        }
    
        return formattedName.toString();
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
        sc.nextLine(); 

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

    private List<Staff> filterByGender(List<Staff> staffList) 
    {
        System.out.print("Enter Gender to filter by (Male/Female): ");
        String gender = sc.nextLine().trim().toLowerCase();
        // validate gender
        if(gender.equals("male") || gender.equals("female") || gender.equals("m") || gender.equals("f"))
        {
            return staffList.stream()
                    .filter(staff -> staff.getGender().equals(gender))
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
            // String.join(", ", Roles.ALLOWED_ROLES) extracts the valid roles from Roles.java seperated by comma and space
            System.out.println("Invalid role entered. Valid roles are: " + capitalizeName(String.join(", ", Roles.ALLOWED_ROLES)));
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
            sc.nextLine(); 

            // minAge should not be greater than maxAge
            if (minAge > maxAge) 
            {
                System.out.println("Minimum age cannot be greater than maximum age.");
                return null; 
            }

            // Ensure ages are within a realistic range
            // The oldest doctor on earth is 104 years old
            if (minAge < 0 || maxAge > 107) 
            {
                System.out.println("Age must be between 0 and 120.");
                return null; 
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
            System.out.println("Invalid input. Please enter numerical values for ages.");
            System.out.println();
            return null; 
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
            System.out.println("Staff ID: " + staff.getStaffId().toUpperCase() + "," + " Name: " + capitalizeName(staff.getName()) + "," + " Role: " + capitalizeName(staff.getRole()) + "," + " Gender: " + capitalizeName(staff.getGender()) + "," + " Age: " + staff.getAge());
        }
        System.out.print("\n");

    }

    public List<Staff> getStaffData()
    {
        return staffs;
    }

    public void addStaff(String hospitalID, String password, String staffId, String name, String role, String gender, int age)
    {
        Staff newStaff = new Staff(hospitalID, password, staffId, name, role, gender, age);
        staffs.add(newStaff);
        return;
    }

    public void removeStaff(String staffId)
    {
        for (Staff staff : staffs)
        {
            if (staff.getStaffId().equals(staffId))
            {
                staffs.remove(staff);
                System.out.println("Staff with ID " + capitalizeName(staffId) + " removed successfully.");
                return;
            }
        }
        System.out.println("Staff with ID " + capitalizeName(staffId) + " not found.");
        return;
    }

    public void updateStaffDetails(String oldStaffId, String newStaffId, String name, String role, String gender, Integer age)
    {
        for (Staff staff : staffs) {
            if (staff.getStaffId().equalsIgnoreCase(oldStaffId)) 
            {
                if (newStaffId != null && !newStaffId.isEmpty()) 
                {
                    // Check if newStaffId already exists
                    if (isStaffIdMatched(newStaffId)) 
                    {
                        System.out.println("Staff ID already exists. Please try again.");
                        return;
                    }
                    staff.updateStaffId(newStaffId);
                }
                if (name != null && !name.isEmpty()) 
                {
                    staff.updateName(name);
                }
                if (role != null && !role.isEmpty()) 
                {
                    staff.updateRole(role);
                }
                if (gender != null && !gender.isEmpty()) 
                {
                    staff.updateGender(gender);
                }
                if (age != null) 
                {
                    staff.updateAge(age);
                }
                System.out.println("Staff details updated successfully.");
                return;
            }
        }
        System.out.println("Staff with ID " + oldStaffId + " not found.");
    }
}