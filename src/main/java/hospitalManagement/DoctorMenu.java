package hospitalManagement;
import java.util.Scanner;

public class DoctorMenu {
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;
    private Scanner sc = new Scanner(System.in);

    public DoctorMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement) {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
    }

    // public void updateContactNumber(int doctorId, String doctorName, String contactNumber)
    // {

    //     medicalRecordManagement.updateContactNumber(doctorId, doctorName, contactNumber);
    // }

    // public void updateEmail(int doctorId, String doctorName, String email)
    // {
    //     medicalRecordManagement.updateEmail(doctorId, doctorName, email);
    // }

    // public void viewPersonalMedicalRecord(int doctorId, String doctorName)
    // {
    //     medicalRecordManagement.viewPersonalMedicalRecord(doctorId, doctorName);
    // }

    public void displayMenu() {
        while (true) {
            System.out.println("Doctor Menu:");
            System.out.println("1. View Upcoming Appointments"); // Done
            System.out.println("2. View Patient medical records"); // Done
            System.out.println("3. Update Patient medical records"); // Done
            System.out.println("4. View Personal schedule records"); // Done
            System.out.println("5. Add New Diagnosis"); // For medicalRecords
            System.out.println("6. Add New Prescription"); // For medicalRecords
            System.out.println("7. Edit Service"); // For AppointmentOutcomeRecord
            System.out.println("8. Edit Prescription"); // For AppointmentOutcomeRecord
            System.out.println("9. Edit Consultation Notes"); // For AppointmentOutcomeRecord
            System.out.println("10. Set availability for Appointment"); // Done
            System.out.println("11. Cancel availability for Appointment"); // Done
            System.out.println("12. View Appointment requests"); // Done
            System.out.println("13. Accept Appointment requests"); // Done
            System.out.println("14. Decline Appointment requests"); // Done
            System.out.println("15. Record Appointment Outcome"); // Done
            System.out.println("16. Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            String patientId;
            String patientName;
            String doctorName;
            String doctorId;
            String diagnosis;
            String prescription;
            switch (choice) {
                case 1:
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    viewUpcomingAppointments(doctorId, doctorName);
                    break;


                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    viewPatientMedicalRecord(patientId, patientName);
                    break;

                case 3: // Update Patient Medical Record
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter diagnosis: ");
                    diagnosis = sc.nextLine();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    setPatientMedicalrecord(patientId, patientName, diagnosis, prescription);
                    break;

                case 4:
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    viewPersonalSchedule(doctorName);
                    break;
                case 5:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter diagnosis: ");
                    diagnosis = sc.nextLine();
                    addNewDiagnosis(patientId, patientName, diagnosis);
                    break;
                case 6: // add new prescription
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    addNewPrescription(patientId, patientName, prescription);
                    break;
                case 7:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    String time = sc.nextLine();
                    System.out.println("Enter service: ");
                    String service = sc.nextLine();
                    editService(patientId, doctorName, date, time, service);
                    break;
                case 8:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    System.out.println("Enter prescription status: ");
                    String status = sc.nextLine();
                    editPrescription(patientId, patientName, doctorName, date, time, prescription, Status.valueOf(status.toUpperCase()));
                    break;
                case 9:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    System.out.println("Enter consultation notes: ");
                    String consultationNotes = sc.nextLine();
                    editConsultationNotes(patientId, patientName, doctorName, date, time, consultationNotes);
                    break;

                case 10:
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    setAvailability(doctorId,doctorName,date,time);
                    break;

                case 11:
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    cancelAvailability(doctorId,doctorName,date,time);
                    break;

                case 12: // View Appointment requests
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    viewAppointmentRequests(doctorName);
                    break;

                case 13:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    acceptAppointment(patientId, patientName, doctorId, doctorName, date, time);
                    break;

                case 14:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    rejectAppointment(patientId, patientName,doctorName, date, time);
                    break;


                case 15:  // New case for recording appointment outcome
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    System.out.println("Enter service: ");
                    service = sc.nextLine();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    System.out.println("Enter consultation notes: ");
                    consultationNotes = sc.nextLine();
                    System.out.println("Enter prescription status (e.g., APPROVED, PENDING): ");
                    Status prescriptionStatus = Status.valueOf(sc.nextLine().toUpperCase());

                    recordAppointmentOutcomeRecords(patientId, patientName, doctorName, date, time, service, prescription, consultationNotes, prescriptionStatus);
                    break;

                case 16:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void viewPatientMedicalRecord(String patientId, String patientName) {
        medicalRecordManagement.viewPatientMedicalRecord(patientId, patientName);
    }

    // To set the patient's medical record, the doctor must provide the patient's ID, name, diagnosis, and prescription.
    // This is to be done after every consultation.
    public void setPatientMedicalrecord(String patientId, String patientName, String diagnosis, String prescription) {
        medicalRecordManagement.setPatientMedicalrecord(patientId, patientName, diagnosis, prescription);
    }

    // Consider adding doctorId to extract personal schedule too.
    public void viewPersonalSchedule(String doctorName) {
        appointmentManagement.viewPersonalSchedule(doctorName);
    }

    public void setAvailability(String doctorId, String doctorName, String date, String time) {
        appointmentManagement.setAvailability(doctorId, doctorName, date, time);
    }

    public void cancelAvailability(String doctorId, String doctorName, String date, String time) {
        appointmentManagement.cancelAvailability(doctorId, doctorName, date, time);
    }

    public void viewAppointmentRequests(String doctorName) {
        appointmentManagement.viewAppointmentRequests(doctorName);
    }

    public void acceptAppointment(String patientId, String patientName, String doctorId, String doctorName, String date, String time) {
        appointmentManagement.acceptAppointment(patientId, patientName, doctorId, doctorName, date, time);
    }

    public void rejectAppointment(String patientId, String patientName, String doctorName, String date, String time) {
        appointmentManagement.rejectAppointment(patientId, patientName, doctorName, date, time);
    }

    public void displayScheduledAppointments(String doctorId, String doctorName) {
        appointmentManagement.displayScheduledAppointments(doctorId, doctorName);
    }

    public void viewUpcomingAppointments(String doctorId, String doctorName) {
        appointmentManagement.viewUpcomingAppointments(doctorId, doctorName);
    }

    public void recordAppointmentOutcomeRecords(String patientId, String patientName, String doctorName, String date, String time, String service, String prescription, String consultationNotes, Status prescriptionStatus) {
        appointmentManagement.recordAppointmentOutcomeRecords(patientId, patientName, doctorName, date, time, service, prescription, consultationNotes, prescriptionStatus);
    }

    public void addTreatmentPlan(String patientId, String patientName, String treatmentPlan) {
        medicalRecordManagement.addNewTreatmentPlan(patientId, patientName, treatmentPlan);
    }

    public void addNewDiagnosis(String patientId, String patientName, String diagnosis) {
        medicalRecordManagement.addNewMedicalDiagnosis(patientId, patientName, diagnosis);
    }

    // Consider whether other doctors should be able to addNewPrescription on behalf of another doctor.
    // Remove the doctorName from parameter if this isn't the case.
    public void addNewPrescription(String patientId, String patientName, String prescription) {
        medicalRecordManagement.addNewMedicalPrescription(patientId, patientName, prescription);
    }

    // Individual functions to edit specific fields so that the doctor can edit the appointment outcome in case of errata.
    public void editService(String patientId, String doctorName, String date, String time, String service) {
        appointmentManagement.editService(patientId, doctorName, date, time, service);
    }

    // Consider whether other doctors should be able to editPrescription on behalf of another doctor.
    public void editPrescription(String patientId, String patientName, String doctorName, String date, String time, String prescription, Status prescriptionStatus) {
        appointmentManagement.editPrescription(patientId, patientName, doctorName, date, time, prescription, prescriptionStatus);
    }

    public void editConsultationNotes(String patientId, String patientName, String doctorName, String date, String time, String consultationNotes) {
        appointmentManagement.editConsultationNotes(patientId, patientName, doctorName, date, time, consultationNotes);
    }
}