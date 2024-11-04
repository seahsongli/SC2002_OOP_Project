import java.util.ArrayList;
import java.util.List;

public class StaffManagement
{
    private List<Staff> staffs;
    public StaffManagement()
    {
        staffs = new ArrayList<>();
    }
    // Check if staff ID already exists
    boolean isStaffIdMatched(int staffId) 
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
    public void viewStaffs()
    {
        if(staffs.isEmpty())
        {
            System.out.println("Currently there is no staff in the Hospital!\n");
        }
        for (Staff staff : staffs)
        {
            System.out.println("Staff ID: " + staff.getStaffId() + "," + " Name: " + staff.getName() + "," + " Role: " + staff.getRole() + "," + " Gender: " + staff.getGender() + "," + " Age: " + staff.getAge());
        }
        System.out.print("\n");

    }

    public void addStaff(String hospitalID, String password, int staffId, String name, String role, String gender, int age)
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

    public void removeStaff(int staffId)
    {
        for (Staff staff : staffs)
        {
            if (staff.getStaffId() == staffId)
            {
                staffs.remove(staff);
                System.out.println("Staff with ID " + staffId + " removed successfully.");
                return;
            }
            System.out.println("Staff with ID " + staffId + " not found.");
            return;
        }
    }

    public void updateStaffDetails(int staffId, String name, String role, String gender, int age)
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