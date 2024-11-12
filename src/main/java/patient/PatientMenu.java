package patient;
import java.util.Scanner;

import hospitalManagement.AppointmentManagement;
import hospitalManagement.MedicalRecordManagement;
public class PatientMenu
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;

    private Scanner sc = new Scanner(System.in);

    public PatientMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement)
    {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Patient Menu:");
            System.out.println("1. View Personal Medical Record"); // keep
            System.out.println("2. Schedule Appointment"); // keep
            System.out.println("3. Reschedule Appointment"); // keep
            System.out.println("4. Cancel Appointment"); // keep
            System.out.println("5. View Scheduled Appointments"); // keep
            System.out.println("6. Update Personal Information");
            System.out.println("7. View available appointment slots");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Logout"); // keep
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter patient ID: ");
                    String patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    String patientName = sc.nextLine();
                    viewPersonalMedicalRecord(patientId, patientName);
                    break;
                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    String doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    String time = sc.nextLine();
                    scheduleAppointment(patientId, patientName, doctorName, date, time);
                    break;
                case 3:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter old date (yyyy-mm-dd): ");
                    String oldDate = sc.nextLine();
                    System.out.println("Enter old time (HH:mm): ");
                    String oldTime = sc.nextLine();
                    System.out.println("Enter new date (yyyy-mm-dd): ");
                    String newDate = sc.nextLine();
                    System.out.println("Enter new time (HH:mm): ");
                    String newTime = sc.nextLine();
                    rescheduleAppointment(patientId, patientName, doctorName, oldDate, oldTime, newDate, newTime);
                    break;
                case 4:
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
                    cancelAppointment(patientId, patientName, doctorName, date, time);
                    break;
                case 5:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    displayScheduledAppointments(patientId, patientName);
                    break;
                case 6: // Update personal information
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("1. Update contact number");
                    System.out.println("2. Update email");
                    int updateChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    switch (updateChoice) {
                        case 1:
                            System.out.println("Enter new contact number: ");
                            String contactNumber = sc.nextLine();
                            updateContactNumber(patientId, patientName, contactNumber);
                            break;
                        case 2:
                            System.out.println("Enter new email: ");
                            String email = sc.nextLine();
                            updateEmail(patientId, patientName, email);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 7: // View available appointment slots 
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    viewAvailableAppointmentSlots(patientId, patientName, doctorName, date);
                    //scheduleAppointment(patientId, patientName, doctorName, date, time);
                    break;
                case 8: // View Past Appointment Outcome Records
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    displayPastAppointmentRecords(patientId, patientName);
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void updateContactNumber(String patientId, String patientName, String contactNumber)
    {
        medicalRecordManagement.updateContactNumber(patientId, patientName, contactNumber);
    }

    public void updateEmail(String patientId, String patientName, String email)
    {
        medicalRecordManagement.updateEmail(patientId, patientName, email);
    }

    public void viewPersonalMedicalRecord(String patientId, String patientName)
    {
        medicalRecordManagement.viewPersonalMedicalRecord(patientId, patientName);
    }

    public void viewAvailableAppointmentSlots(String patientId, String patientName, String doctorName, String date)
    {
        appointmentManagement.viewAvailableAppointmentSlots(patientId, patientName, doctorName, date);
    }

    public void scheduleAppointment(String patientId, String patientName, String doctorName, String date, String time)
    {
        appointmentManagement.scheduleAppointment(patientId, patientName, doctorName, date, time);
    }

    public void rescheduleAppointment(String patientId, String patientName, String doctorName, String oldDate, String oldTime, String newDate, String newTime)
    {
        appointmentManagement.rescheduleAppointment(patientId, patientName, doctorName, oldDate, oldTime, newDate, newTime);
    }

    public void cancelAppointment(String patientId, String patientName, String doctorName, String date, String time)
    {
        appointmentManagement.cancelAppointment(patientId, patientName, doctorName, date, time);
    }

    public void displayScheduledAppointments(String patientId, String patientName)
    {
        appointmentManagement.displayScheduledAppointments(patientId, patientName);
    }

    public void displayPastAppointmentRecords(String patientId, String patientName)
    {
        appointmentManagement.displayPastAppointmentRecords(patientId, patientName);
    }

}