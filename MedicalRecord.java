public class MedicalRecord
{
    private int patientId;
    private String patientName;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String contactNumber;
    private String email;
    private String[] pastDiagnoses;
    private String[] pastTreatments;
    private String[] currentDiagonoses;
    private String[] currentTreatments;
    private String[] prescriptions;
    
    public MedicalRecord(int patientId, String patientName, String dateOfBirth, String gender, String bloodType, String contactNumber, String email, String[] pastDiagnoses, String[] pastTreatments, String[] currentDiagonoses, String[] currentTreatments, String[] prescriptions)
    {
        this.patientId = patientId;
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactNumber = contactNumber;
        this.email = email;
        this.pastDiagnoses = pastDiagnoses;
        this.pastTreatments = pastTreatments;
        this.currentDiagonoses = currentDiagonoses;
        this.currentTreatments = currentTreatments;
        this.prescriptions = prescriptions;
    }

    public int getPatientId()
    {
        return patientId;
    }
    
    public String getPatientName()
    {
        return patientName;
    }
    
    public String getContactNumber()
    {
        return contactNumber;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    // The following protected methods are used for doctor class to update the medical record
    protected void setPastDiagnoses(String[] pastDiagnoses)
    {
        this.pastDiagnoses = pastDiagnoses;
    }
    
    protected void setPastTreatments(String[] pastTreatments)
    {
        this.pastTreatments = pastTreatments;
    }
    
    protected String[] getPastDiagnoses()
    {
        return pastDiagnoses;
    }
    
    protected String[] getPastTreatments()
    {
        return pastTreatments;
    }

    public void viewMedicalRecord()
    {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient Date of Birth: " + dateOfBirth);
        System.out.println("Patient Gender: " + gender);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email: " + email);
        System.out.println("Blood Type: " + bloodType);
        System.out.println("Past Diagnoses: ");
        for (int i = 0; i < pastDiagnoses.length; i++)
        {
            System.out.println(pastDiagnoses[i]);
        }
        System.out.println("Past Treatments: ");
        for (int i = 0; i < pastTreatments.length; i++)
        {
            System.out.println(pastTreatments[i]);
        }
    }
}