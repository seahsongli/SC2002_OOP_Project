public class Staff extends User 
{
    
    // use staff ID to identify staff
    private String staffId;
    private String name;
    private String role;
    private String gender;
    private int age;

    //static int staffCount = 0;
    // Every user is a staff except a patient
    public Staff(String hospitalID, String password, String staffId,  String name, String role, String gender, int age) 
    {

        super(hospitalID, password, role);
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
    }

    public String getName() 
    {
        return name;
    }

    public String getRole() 
    {
        return role;
    }

    public String getGender() 
    {
        return gender;
    }

    public int getAge() 
    {
        return age;
    }

    public String getStaffId() 
    {
        return staffId;
    }

    public void updateName(String name) 
    {
        
        if (name == null) 
        {
            System.out.println("Name cannot be null.");
            return;
        }
        
        String trimmedName = name.trim();
        
        if (trimmedName.isEmpty()) 
        {
            System.out.println("Name cannot be empty or whitespace only.");
            return;
        }
        
        this.name = trimmedName;
    }

    public void updateRole(String role) 
    {
        
        if (role == null) 
        {
            System.out.println("role cannot be null.");
            return;
        }
        
        String trimmedRole = role.trim();
        
        if (trimmedRole.isEmpty()) 
        {
            System.out.println("Role cannot be empty or whitespace only.");
            return;
        }
        
        this.role = trimmedRole;
    }

    public void updateGender(String gender)
    {
        
        if (gender == null) 
        {
            System.out.println("Gender cannot be null.");
            return;
        }
        
        String trimmedGender = gender.trim();
        
        if (trimmedGender.isEmpty()) 
        {
            System.out.println("Gender cannot be empty or whitespace only.");
            return;
        }
        
        this.gender = trimmedGender;
    }

    public void updateAge(int age) 
    {
        // Age validation
        if (age > 0 && age < 150) 
        { 
            this.age = age;
        } 
        else 
        {
            System.out.println("Invalid age. Update failed.");
        }
    }

    public void updateStaffId(String staffId) 
    {
        this.staffId = staffId;
    }
    
    public static void main(String[] args) 
    {
       
    }
}