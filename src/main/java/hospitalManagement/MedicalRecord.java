package hospitalManagement;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String patientId;
    private String patientName;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String contactNumber;
    private String email;
    private List<String> pastDiagnosis;
    private List<String> pastTreatments;
    private List<String> currentDiagnosis;
    private List<String> currentTreatments;
    private List<String> prescriptions;

    public MedicalRecord(String patientId, String patientName, String dateOfBirth, String gender, String bloodType,
            String contactNumber, String email, List<String> pastDiagnosis, List<String> pastTreatments,
            List<String> currentDiagnosis, List<String> currentTreatments, List<String> prescriptions) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactNumber = contactNumber;
        this.email = email;

        this.pastDiagnosis = pastDiagnosis;
        this.pastTreatments = pastTreatments;
        this.currentDiagnosis = currentDiagnosis;
        this.currentTreatments = currentTreatments;
        this.prescriptions = prescriptions;

    }

    // Overload the MedicalRecord constructor to allow for creation of a
    // MedicalRecord object without any past diagnoses, past treatments, current
    // diagnoses, current treatments, or prescriptions.
    // This is useful when creating a new MedicalRecord object for a new patient,
    // especially when the new patient has no medical history.
    public MedicalRecord(String patientId, String patientName, String dateOfBirth, String gender, String bloodType,
            String contactNumber, String email) {
        this(patientId, patientName, dateOfBirth, gender, bloodType, contactNumber, email, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // The following protected methods are used for doctor class to update the
    // medical record
    protected void addPastDiagnoses(String diagnosis) {
        this.pastDiagnosis.add(diagnosis);
    }

    protected void addPastTreatments(String treatment) {
        this.pastTreatments.add(treatment);
    }

    protected void removePastDiagnosis(String diagnosis) {
        this.pastDiagnosis.remove(diagnosis);
    }

    protected void removePastTreatment(String treatment) {
        this.pastTreatments.remove(treatment);
    }

    protected void addCurrentTreatment(String treatment) {
        this.currentTreatments.add(treatment);
    }

    protected void addCurrentDiagnosis(String diagnosis) {
        this.currentDiagnosis.add(diagnosis);
    }

    protected void addPrescription(String prescription) {
        this.prescriptions.add(prescription);
    }

    protected List<String> getPastDiagnoses() {
        return pastDiagnosis;
    }

    protected List<String> getPastTreatments() {
        return pastTreatments;
    }

    public void viewMedicalRecord() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient Date of Birth: " + dateOfBirth);
        System.out.println("Patient Gender: " + gender);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email: " + email);
        System.out.println("Blood Type: " + bloodType);
        System.out.println("Past Diagnoses: ");
        for (int i = 0; i < pastDiagnosis.size(); i++) {
            System.out.println(pastDiagnosis.get(i));
        }
        System.out.println("Past Treatments: ");
        for (int i = 0; i < pastTreatments.size(); i++) {
            System.out.println(pastTreatments.get(i));
        }
        System.out.println("Current Diagnoses: ");
        for (int i = 0; i < currentDiagnosis.size(); i++) {
            System.out.println(currentDiagnosis.get(i));
        }
        System.out.println("Current Treatments: ");
        for (int i = 0; i < currentTreatments.size(); i++) {
            System.out.println(currentTreatments.get(i));
        }
        System.out.println("Prescriptions: ");
        for (int i = 0; i < prescriptions.size(); i++) {
            System.out.println(prescriptions.get(i));
        }
    }
}