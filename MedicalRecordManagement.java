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

    // might not need this since we can just use the viewPersonalMedicalRecord method where doctors can view their own patient medical records.
    protected void viewPatientMedicalRecord(int patientId, String patientName)
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

    protected void setPatientMedicalrecord(int patientId, String patientName, String diagnosis, String prescription)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId)
            {
                medicalRecords[i].addCurrentDiagnosis(diagnosis);
                medicalRecords[i].addPrescription(prescription);
                System.out.println("Medical record updated for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void setPatientTreatmentPlan(int patientId, String patientName, String treatmentPlan)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId)
            {
                medicalRecords[i].addCurrentTreatment(treatmentPlan);
                System.out.println("Medical record treatment plan updated for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void setPatientMedicalPrescription(int patientId, String patientName, String prescription)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId)
            {
                medicalRecords[i].addPrescription(prescription);
                System.out.println("Medical record prescription updated for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    // Below 3 methods are used to append new diagnosis and prescription and treatement plans to the existing ones.
    protected void addNewMedicalDiganosis(int patientId, String patientName, String diagnosis)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId && medicalRecords[i].getPatientName().equals(patientName))
            {
                medicalRecords[i].addCurrentDiagnosis(diagnosis);
                System.out.println("New medical record diganosis added for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void addNewMedicalPrescription(int patientId, String patientName, String prescription)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId && medicalRecords[i].getPatientName().equals(patientName))
            {
                medicalRecords[i].addPrescription(prescription);
                System.out.println("New medical record prescription added for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void addNewTreatmentPlan(int patientId, String patientName, String treatmentPlan)
    {
        for (int i = 0; i < medicalRecords.length; i++)
        {
            if (medicalRecords[i].getPatientId() == patientId && medicalRecords[i].getPatientName().equals(patientName))
            {
                medicalRecords[i].addCurrentTreatment(treatmentPlan);
                System.out.println("New medical record treatment plan added for patient " + patientName + " with ID " + patientId);
            }
        }
    }

}