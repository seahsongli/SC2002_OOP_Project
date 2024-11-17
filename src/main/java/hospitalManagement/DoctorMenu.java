package hospitalManagement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DoctorMenu {
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;
    private Scanner sc = new Scanner(System.in);
    private Staff loggedInDoctor;

    public DoctorMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement, Staff loggedInDoctor) {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
        this.loggedInDoctor = loggedInDoctor;
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
            System.out.println("4. View Personal schedule"); // Done
            System.out.println("5. Add New Diagnosis"); // For medicalRecords Done
            System.out.println("6. Add New Prescription"); // For medicalRecords Done
            System.out.println("7. Edit Service"); // For AppointmentOutcomeRecord Done
            System.out.println("8. Edit Prescription"); // For AppointmentOutcomeRecord Done
            System.out.println("9. Edit Consultation Notes"); // For AppointmentOutcomeRecord Done
            System.out.println("10. Edit patient's past medical records"); // Done
            System.out.println("11. Set availability for Appointment"); // Done
            System.out.println("12. Cancel availability for Appointment"); // Done
            System.out.println("13. View Appointment requests"); // Done
            System.out.println("14. Accept Appointment requests"); // Done
            System.out.println("15. Decline Appointment requests"); // Done
            System.out.println("16. Record Appointment Outcome"); // Done
            System.out.println("17. Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            String patientId;
            String patientName;
            String diagnosis;
            String prescription;
            switch (choice) {
                case 1:
                    viewUpcomingAppointments(loggedInDoctor.getStaffId(), loggedInDoctor.getName());
                    break;

                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    viewPatientMedicalRecord(patientId, patientName);
                    break;

                case 3: // Update Patient Medical Record
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter diagnosis: ");
                    diagnosis = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    System.out.println("Enter current treatment plan: ");
                    String treatmentPlan = sc.nextLine();
                    setPatientMedicalrecord(patientId, patientName, diagnosis, prescription, treatmentPlan);
                    break;

                case 4:
                    viewPersonalSchedule(loggedInDoctor.getName());
                    break;
                case 5:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter diagnosis: ");
                    diagnosis = sc.nextLine();
                    addNewDiagnosis(patientId, patientName, diagnosis);
                    break;
                case 6: // add new prescription
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    addNewPrescription(patientId, patientName, prescription);
                    break;
                case 7:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    String date = getDateFromUser();
                    String time = getTimeFromUser();
                    System.out.println("Enter service: ");
                    String service = sc.nextLine();
                    editService(patientId, loggedInDoctor.getName(), date, time, service);
                    break;
                case 8:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine().trim().toLowerCase();
                    Status prescriptionStatus = null;
                    while (true) {
                        System.out.println("Enter prescription status (e.g., PENDING, CONFIRMED, CANCELLED, COMPLETED, REJECTED): ");
                        String statusInput = sc.nextLine().toUpperCase();
                        try {
                            prescriptionStatus = Status.valueOf(statusInput);
                            break;  // Exit loop if valid status is entered
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid status entered. Please enter a valid status from the following options:");
                            for (Status curr_status : Status.values()) {
                                System.out.println("- " + curr_status);
                            }
                        }
                    }
                    editPrescription(patientId, patientName, loggedInDoctor.getName(), date, time, prescription, prescriptionStatus);
                    break;
                case 9:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    System.out.println("Enter consultation notes: ");
                    String consultationNotes = sc.nextLine();
                    editConsultationNotes(patientId, patientName, loggedInDoctor.getName(), date, time, consultationNotes);
                    break;
                
                case 10:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    editOldMedicalRecord(patientId, patientName);
                    break;
                case 11:
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    setAvailability(loggedInDoctor.getStaffId(),loggedInDoctor.getName(),date,time);
                    break;

                case 12:
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    cancelAvailability(loggedInDoctor.getStaffId(),loggedInDoctor.getName(),date,time);
                    break;

                case 13: // View Appointment requests
                    viewAppointmentRequests(loggedInDoctor.getName());
                    break;

                case 14:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    acceptAppointment(patientId, patientName, loggedInDoctor.getStaffId(), loggedInDoctor.getName(), date, time);
                    break;

                case 15:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    rejectAppointment(patientId, patientName,loggedInDoctor.getName(), date, time);
                    break;

                case 16:  // New case for recording appointment outcome
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine().trim().toLowerCase();
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    System.out.println("Enter service: ");
                    service = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter prescription: ");
                    prescription = sc.nextLine();
                    System.out.println("Enter consultation notes: ");
                    consultationNotes = sc.nextLine();
                    prescriptionStatus = null;
                    while (true) {
                        System.out.println("Enter prescription status (e.g., PENDING, CONFIRMED, CANCELLED, COMPLETED, REJECTED): ");
                        String statusInput = sc.nextLine().toUpperCase();
                        try {
                            prescriptionStatus = Status.valueOf(statusInput);
                            break;  // Exit loop if valid status is entered
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid status entered. Please enter a valid status from the following options:");
                            for (Status curr_status : Status.values()) {
                                System.out.println("- " + curr_status);
                            }
                        }
                    }
                    recordAppointmentOutcomeRecords(patientId, patientName, loggedInDoctor.getName(), date, time, service, prescription, consultationNotes, prescriptionStatus);
                    break;
                case 17:
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

    // To set the patient's medical record, the doctor must provide the patient's ID, name, diagnosis, and prescription and treatment.
    // This is to be done after every consultation.
    public void setPatientMedicalrecord(String patientId, String patientName, String diagnosis, String prescription, String treatmentPlan) {
        medicalRecordManagement.setPatientMedicalrecord(patientId, patientName, diagnosis, prescription, treatmentPlan);
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

    public void editOldMedicalRecord(String patientId, String patientName) {
        medicalRecordManagement.setOldPatientRecords(patientId, patientName);
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

    // private methods
        private String getTimeFromUser() {
        String timeInput;
        while (true) {
            System.out.println("Enter time (HH:mm): ");
            timeInput = sc.nextLine();
            try {
                // Parse input to ensure it's valid
                LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
                break; // Exit loop if the input is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please try again (e.g., 14:30).");
            }
        }
        return timeInput;
    }

    private String getDateFromUser() {
        String dateInput;
        while (true) {
            System.out.println("Enter date (yyyy-mm-dd): ");
            dateInput = sc.nextLine();
            try {
                // Parse input to ensure it's valid
                LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break; // Exit loop if the input is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again (e.g., 2024-11-16).");
            }
        }
        return dateInput;
    }
}