import java.util.Scanner;
public class DoctorMenu 
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;
    private Scanner sc = new Scanner(System.in);

    public DoctorMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement)
    {
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
            System.out.println("1. View Upcoming Appointments");
            System.out.println("2. Add New Diagnosis");
            System.out.println("3. Add New Prescription");
            System.out.println("4. Add Available Time for appointment");
            System.out.println("5. Edit Service");
            System.out.println("6. Edit Prescription");
            System.out.println("7. Edit Consultation Notes");
            System.out.println("8. Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            int patientId;
            String patientName;
            int doctorId;
            String doctorName;
            String date;
            String time;
            switch (choice) {
                case 1:
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    viewUpcomingAppointments(doctorId, doctorName);
                    break;
                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter diagnosis: ");
                    String diagnosis = sc.nextLine();
                    addNewDiagnosis(patientId, patientName, diagnosis);
                    break;
                case 3:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter prescription: ");
                    String prescription = sc.nextLine();
                    addNewPrescription(patientId, patientName, doctorName, prescription);
                    break;
                case 4:
                    System.out.println("Enter doctor ID: ");
                    doctorId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                
                    System.out.println("Enter date (e.g., 2024-11-01): ");
                    date = sc.nextLine();
                
                    System.out.println("Enter time (in 30-minute intervals from 9:00 AM to 6:00 PM, e.g., 09:30): ");
                    time = sc.nextLine();
                
                    // Call setAvailability on the AppointmentManagement instance
                    appointmentManagement.setAvailability(doctorId, doctorName, date, time);
                    break;
                case 5:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    System.out.println("Enter service: ");
                    String service = sc.nextLine();
                    editService(patientId, doctorName, date, time, service);
                    break;
                case 6:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
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
                case 7:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
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
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void viewPatientMedicalRecord(int patientId, String patientName)
    {
        medicalRecordManagement.viewPatientMedicalRecord(patientId, patientName);
    }

    // To set the patient's medical record, the doctor must provide the patient's ID, name, diagnosis, and prescription.
    // This is to be done after every consultation.
    public void setPatientMedicalrecord(int patientId, String patientName, String diagnosis, String prescription)
    {
        medicalRecordManagement.setPatientMedicalrecord(patientId, patientName, diagnosis, prescription);
    }

    // Consider adding doctorId to extract personal schedule too.
    public void viewPersonalSchedule(String doctorName)
    {
        appointmentManagement.viewPersonalSchedule(doctorName);
    }

    public void setAvailability(int doctorId, String doctorName, String date, String time)
    {
        appointmentManagement.setAvailability(doctorId, doctorName, date, time);
    }

    public void cancelAvailability(int doctorId, String doctorName, String date, String time)
    {
        appointmentManagement.cancelAvailability(doctorId, doctorName, date, time);
    }

    public void acceptAppointment(int patientId, String patientName, int doctorId, String doctorName, String date, String time)
    {
        appointmentManagement.acceptAppointment(patientId, patientName, doctorId, doctorName, date, time);
    }

    public void rejectAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        appointmentManagement.rejectAppointment(patientId, patientName, doctorName, date, time);
    }

    public void displayScheduledAppointments(int doctorId, String doctorName)
    {
        appointmentManagement.displayScheduledAppointments(doctorId, doctorName);
    }

    public void viewUpcomingAppointments(int doctorId, String doctorName)
    {
        appointmentManagement.viewUpcomingAppointments(doctorId, doctorName);
    }

    public void recordAppointmentOutcomeRecords(int patientId, String patientName, String doctorName, String date, String time, String service, String prescription, String consultationNotes, Status prescriptionStatus)
    {
        appointmentManagement.recordAppointmentOutcomeRecords(patientId, patientName, doctorName, date, time, service, prescription, consultationNotes, prescriptionStatus);
    }

    public void addTreatmentPlan(int patientId, String patientName, String treatmentPlan)
    {
        medicalRecordManagement.addNewTreatmentPlan(patientId, patientName, treatmentPlan);
    }

    public void addNewDiagnosis(int patientId, String patientName, String diagnosis)
    {
        medicalRecordManagement.addNewMedicalDiagnosis(patientId, patientName, diagnosis);
    }

    // Consider whether other doctors should be able to addNewPrescription on behalf of another doctor.
    // Remove the doctorName from parameter if this isn't the case.
    public void addNewPrescription(int patientId, String patientName, String doctorName, String prescription)
    {
        medicalRecordManagement.addNewMedicalPrescription(patientId, patientName, prescription);
    }

    // Individual functions to edit specific fields so that the doctor can edit the appointment outcome in case of errata.
    public void editService(int patientId, String doctorName, String date, String time, String service)
    {
        appointmentManagement.editService(patientId, doctorName, date, time, service);
    }

    // Consider whether other doctors should be able to editPrescription on behalf of another doctor.
    public void editPrescription(int patientId, String patientName, String doctorName, String date, String time, String prescription, Status prescriptionStatus)
    {
        appointmentManagement.editPrescription(patientId, patientName, doctorName, date, time, prescription, prescriptionStatus);
    }

    public void editConsultationNotes(int patientId, String patientName, String doctorName, String date, String time, String consultationNotes)
    {
        appointmentManagement.editConsultationNotes(patientId, patientName, doctorName, date, time, consultationNotes);
    }
}