public class Staff {
    private String name;
    private String role;
    private String gender;
    private int age;
    private int staffId;

    static int staffCount = 0;
    public Staff(String name, String role, String gender, int age) {
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.staffId = ++staffCount;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getStaffId() {
        return staffId;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateRole(String role) {
        this.role = role;
    }

    public void updateGender(String gender)
    {
        this.gender = gender;
    }

    public void updateAge(int age) {
        this.age = age;
    }

    public void updateStaffId(int staffId) {
        this.staffId = staffId;
    }
    
    public static void main(String[] args) {
       
    }
}