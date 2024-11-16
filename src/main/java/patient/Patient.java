package patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hospitalManagement.User;

public class Patient extends User 
{
    private String patientId;
    private String name;
    private LocalDate dob; // Date of Birth
    private String gender;
    private String bloodGroup;
    private String contactNumber;

    // Define a DateTimeFormatter for yyyy-MM-dd format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Patient(String hospitalID, String password, String patientId, String name,  String dob, String gender, String bloodGroup, String email, String phoneNumber) 
    {
        super(hospitalID, password, "patient");
        this.patientId = patientId;
        this.name = name;
        setDob(dob);
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.contactNumber = ""; // Initialize with empty string or pass it as a parameter

    }

    // Getters
    public String getId() 
    {
        return patientId;
    }

    public String getName() 
    {
        return name;
    }

    public String getGender()
    {
        return gender;
    }

    public String getBloodGroup()
    {
        return bloodGroup;
    }

    public String getEmail()
    {
        return email;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public LocalDate getDob()
    {
        return dob;
    }

    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setBloodGroup(String bloodGroup)
    {
        this.bloodGroup = bloodGroup;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public void setDob(String dobStr) 
    {
        try 
        {
            this.dob = LocalDate.parse(dobStr, DATE_FORMATTER);
        } 
        catch (DateTimeParseException e) 
        {
            throw new IllegalArgumentException("Invalid DOB format. Please use yyyy-MM-dd.");
        }
    }
}
