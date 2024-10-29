import java.util.ArrayList;
import java.util.List;

public class StaffManagement
{
    private List<Staff> staffs;
    public StaffManagement()
    {
        staffs = new ArrayList<>();
    }
    
    public void viewStaffs()
    {
        for (Staff staff : staffs)
        {
            System.out.println("Staff ID: " + staff.getStaffId() + " Name: " + staff.getName() + " Role: " + staff.getRole() + "Gender: " + staff.getGender() + " Age: " + staff.getAge());
        }
    }

    public void addStaff(String name, String role, String gender, int age)
    {
        Staff newStaff = new Staff(name, role, gender, age);
        staffs.add(newStaff);
    }

    public void removeStaff(int staffId)
    {
        for (Staff staff : staffs)
        {
            if (staff.getStaffId() == staffId)
            {
                staffs.remove(staff);
                break;
            }
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