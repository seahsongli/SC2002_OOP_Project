package hospitalManagement;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordManagement
{
    private List<MedicalRecord> medicalRecords;

    public MedicalRecordManagement()
    {
        this.medicalRecords = new ArrayList<>();
    }

    public void updateContactNumber(String patientId, String patientName, String phoneNumber)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).setContactNumber(phoneNumber);
                System.out.println("Contact number updated for patient " + patientName + " with ID " + patientId + " to " + phoneNumber);
            }
        }
    }

    public void updateEmail(String patientId, String patientName, String email)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).setEmail(email);
                System.out.println("Email updated for patient " + patientName + " with ID " + patientId + " to " + email);
            }
        }
    }

    public void viewPersonalMedicalRecord(String patientId, String patientName)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).viewMedicalRecord();
                System.out.println("Medical record viewed for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    // The following protected methods are used for Doctor class to update the medical record
    protected void setMedicalRecords(List<MedicalRecord> medicalRecords)
    {
        this.medicalRecords = medicalRecords;
    }

    protected List<MedicalRecord> getMedicalRecords()
    {
        return medicalRecords;
    }

    protected void viewAllMedicalRecords()
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            medicalRecords.get(i).viewMedicalRecord();
        }
    }

    // might not need this since we can just use the viewPersonalMedicalRecord method where doctors can view their own patient medical records.
    protected void viewPatientMedicalRecord(String patientId, String patientName)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).viewMedicalRecord();
                System.out.println("Medical record viewed for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void setPatientMedicalrecord(String patientId, String patientName, String diagnosis, String prescription)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).addCurrentDiagnosis(diagnosis);
                medicalRecords.get(i).addPrescription(prescription);
                System.out.println("Medical record updated for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void setPatientTreatmentPlan(String patientId, String patientName, String treatmentPlan)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).addCurrentTreatment(treatmentPlan);
                System.out.println("Medical record treatment plan updated for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void setPatientMedicalPrescription(String patientId, String patientName, String prescription)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId)
            {
                medicalRecords.get(i).addPrescription(prescription);
                System.out.println("Medical record prescription updated for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    // Below 3 methods are used to append new diagnosis and prescription and treatment plans to the existing ones.
    protected void addNewMedicalDiagnosis(String patientId, String patientName, String diagnosis)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId && medicalRecords.get(i).getPatientName().equals(patientName))
            {
                medicalRecords.get(i).addCurrentDiagnosis(diagnosis);
                System.out.println("New medical record diagnosis added for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void addNewMedicalPrescription(String patientId, String patientName, String prescription)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId && medicalRecords.get(i).getPatientName().equals(patientName))
            {
                medicalRecords.get(i).addPrescription(prescription);
                System.out.println("New medical record prescription added for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void addNewTreatmentPlan(String patientId, String patientName, String treatmentPlan)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId() == patientId && medicalRecords.get(i).getPatientName().equals(patientName))
            {
                medicalRecords.get(i).addCurrentTreatment(treatmentPlan);
                System.out.println("New medical record treatment plan added for patient " + patientName + " with ID " + patientId);
            }
        }
    }
}
