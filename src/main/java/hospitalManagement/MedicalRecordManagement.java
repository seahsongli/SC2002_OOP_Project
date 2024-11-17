package hospitalManagement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordManagement
{
    private List<MedicalRecord> medicalRecords;
    private Scanner sc;

    public MedicalRecordManagement()
    {
        this.medicalRecords = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void updateContactNumber(String patientId, String patientName, String phoneNumber)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId().equals(patientId))
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
            if (medicalRecords.get(i).getPatientId().equals(patientId))
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
            if (medicalRecords.get(i).getPatientId().equals(patientId))
            {
                medicalRecords.get(i).viewMedicalRecord();
                System.out.println("Medical record viewed for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    // this for testing purposes
    public void addMedicalRecord(MedicalRecord newMedicalRecord)
    {
        medicalRecords.add(newMedicalRecord);
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
            if (medicalRecords.get(i).getPatientId().equals(patientId))
            {
               
                medicalRecords.get(i).viewMedicalRecord();
                System.out.println("Medical record viewed for patient " + patientName + " with ID " + patientId);
                return;
            }
        }
        System.out.println("Patient " + patientName + " with ID " + patientId + " not found.");
    }

    protected void setPatientMedicalrecord(String patientId, String patientName, String diagnosis, String prescription, String treatmentPlan)
    {
        for (int i = 0; i < medicalRecords.size(); i++)
        {
            if (medicalRecords.get(i).getPatientId().equals(patientId))
            {
                medicalRecords.get(i).addCurrentDiagnosis(diagnosis);
                medicalRecords.get(i).addPrescription(prescription);
                medicalRecords.get(i).addCurrentTreatment(treatmentPlan);
                System.out.println("Medical record updated for patient " + patientName + " with ID " + patientId);
                return;
            }
        }
        System.out.println("Patient " + patientName + " with ID " + patientId + " not found.");
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
            if (medicalRecords.get(i).getPatientId().equals(patientId))
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
            if (medicalRecords.get(i).getPatientId().equals(patientId) && medicalRecords.get(i).getPatientName().equals(patientName))
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
            if (medicalRecords.get(i).getPatientId().equals(patientId) && medicalRecords.get(i).getPatientName().equals(patientName))
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
            if (medicalRecords.get(i).getPatientId().equals(patientId) && medicalRecords.get(i).getPatientName().equals(patientName))
            {
                medicalRecords.get(i).addCurrentTreatment(treatmentPlan);
                System.out.println("New medical record treatment plan added for patient " + patientName + " with ID " + patientId);
            }
        }
    }

    protected void setOldPatientRecords(String patientId, String patientName) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getPatientId().equals(patientId) && medicalRecords.get(i).getPatientName().equals(patientName)) {
                System.out.println("Choose the following options to update the patient's medical record: ");
                System.out.println("1. Add past diagnosis");
                System.out.println("2. Add past treatment plan");
                System.out.println("3. Remove past diagnosis");
                System.out.println("4. Remove past treatment plan");
                
                int choice = -1; // Default invalid choice
                
                try {
                    choice = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    return; // Exit the method to avoid further processing with invalid input
                }
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter past diagnosis: ");
                        String pastDiagnosis = sc.nextLine().trim();
                        if (medicalRecords.get(i).getPastDiagnoses().contains(pastDiagnosis)) {
                            System.out.println("Past diagnosis already exists for patient " + patientName + " with ID " + patientId);
                            return;
                        }
                        medicalRecords.get(i).addPastDiagnoses(pastDiagnosis);
                        System.out.println("Past diagnosis added for patient " + patientName + " with ID " + patientId);
                        break;
                    case 2:
                        System.out.println("Enter past treatment plan: ");
                        String pastTreatmentPlan = sc.nextLine().trim();
                        if (medicalRecords.get(i).getPastTreatments().contains(pastTreatmentPlan)) {
                            System.out.println("Past treatment plan already exists for patient " + patientName + " with ID " + patientId);
                            return;
                        }
                        medicalRecords.get(i).addPastTreatments(pastTreatmentPlan);
                        System.out.println("Past treatment plan added for patient " + patientName + " with ID " + patientId);
                        break;
                    case 3:
                        System.out.println("Enter past diagnosis to remove: ");
                        pastDiagnosis = sc.nextLine().trim();
                        if (!medicalRecords.get(i).getPastDiagnoses().contains(pastDiagnosis)) {
                            System.out.println("Past diagnosis not found for patient " + patientName + " with ID " + patientId);
                            return;
                        }
                        medicalRecords.get(i).removePastDiagnosis(pastDiagnosis);
                        System.out.println("Past diagnosis removed for patient " + patientName + " with ID " + patientId);
                        break;
                    case 4:
                        System.out.println("Enter past treatment plan to remove: ");
                        pastTreatmentPlan = sc.nextLine().trim();
                        if (!medicalRecords.get(i).getPastTreatments().contains(pastTreatmentPlan)) {
                            System.out.println("Past treatment plan not found for patient " + patientName + " with ID " + patientId);
                            return;
                        }
                        medicalRecords.get(i).removePastTreatment(pastTreatmentPlan);
                        System.out.println("Past treatment plan removed for patient " + patientName + " with ID " + patientId);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        break;
                }
            }
        }
    }
    
}