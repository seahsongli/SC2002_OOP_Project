public class MedicalRecordManagement
{
    private MedicalRecord[] medicalRecords;
    public void updateContactNumber(int patientId, String patientName, String phoneNumber)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId)
            {
                medicalRecords[i].setContactNumber(phoneNumber);
                System.out.println("Contact number updated for patient " + patientName + " with ID " + patientId + " to " + phoneNumber);
            }
        }
        
    }

    public void updateEmail(int patientId, String patientName, String email)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId)
            {
                medicalRecords[i].setEmail(email);
                System.out.println("Email updated for patient " + patientName + " with ID " + patientId + " to " + email);
            }
        }
       
    }

    public void viewPersonalMedicalRecord(int patientId, String patientName)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId)
            {
                medicalRecords[i].viewMedicalRecord();
                System.out.println("Medical record viewed for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    // The following protected methods are used for Doctor class to update the medical record
    protected void setMedicalRecords(MedicalRecord[] medicalRecords)
    {
        this.medicalRecords = medicalRecords;
    }

    protected MedicalRecord[] getMedicalRecords()
    {
        return medicalRecords;
    }

    protected void viewAllMedicalRecords()
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            medicalRecords[i].viewMedicalRecord();
        }
    }

}