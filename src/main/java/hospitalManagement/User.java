package hospitalManagement;
import org.mindrot.jbcrypt.BCrypt;

public class User 
{
    private String hospitalId;
    private String hashedPassword;
    private String role;

    public User(String hospitalId, String password, String role) 
    {
        this.hospitalId = hospitalId;
        setPassword(password);  // Hashes the password before setting it
        this.role = role;
    }

    public String getHospitalId() 
    {
        return hospitalId;
    }

    // Returns the hashed password, as storing plain text passwords is insecure
    public String getPassword() 
    {
        return hashedPassword;
    }

    // Sets the password with hashing
    public void setPassword(String password) 
    {
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getRole() 
    {
        return role;
    }

    // Method to check if a provided password matches the stored hashed password
    public boolean checkPassword(String password) 
    {
        return BCrypt.checkpw(password, this.hashedPassword);
    }
}
