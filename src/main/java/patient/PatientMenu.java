package patient;
import java.util.Scanner;

import hospitalManagement.AppointmentManagement;
import hospitalManagement.MedicalRecordManagement;
public class PatientMenu
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;
    private Patient loggedInPatient;

    private Scanner sc = new Scanner(System.in);

    public PatientMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement, Patient loggedInPatient)
    {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
        this.loggedInPatient = loggedInPatient;
    }
    public void displayMenu() {
        while (true) {
            System.out.println("Patient Menu:");
            System.out.println("1. View Personal Medical Record"); // done
            System.out.println("2. Schedule Appointment"); // done
            System.out.println("3. Reschedule Appointment"); // done
            System.out.println("4. Cancel Appointment"); // done
            System.out.println("5. View Scheduled Appointments"); // done
            System.out.println("6. Update Personal Information"); // done
            System.out.println("7. View available appointment slots"); // done
            System.out.println("8. View Past Appointment Outcome Records"); // done
            System.out.println("9. Logout"); // keep
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewPersonalMedicalRecord();
                    break;
                case 2:
                    System.out.println("Enter doctor name: ");
                    String doctorName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    String time = sc.nextLine();
                    System.out.println();
                    scheduleAppointment(doctorName, date, time);
                    break;
                case 3:
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter old date (yyyy-mm-dd): ");
                    String oldDate = sc.nextLine();
                    System.out.println("Enter old time (HH:mm): ");
                    String oldTime = sc.nextLine();
                    System.out.println("Enter new date (yyyy-mm-dd): ");
                    String newDate = sc.nextLine();
                    System.out.println("Enter new time (HH:mm): ");
                    String newTime = sc.nextLine();
                    System.out.println();
                    rescheduleAppointment(doctorName, oldDate, oldTime, newDate, newTime);
                    break;
                case 4:
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println("Enter time (HH:mm): ");
                    time = sc.nextLine();
                    System.out.println();
                    cancelAppointment(doctorName, date, time);
                    break;
                case 5:
                    System.out.println("The appointment details for you are as follows: \n");
                    displayScheduledAppointments();
                    break;
                case 6: // Update personal information
                    System.out.println("1. Update contact number");
                    System.out.println("2. Update email");
                    int updateChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    switch (updateChoice) {
                        case 1:
                            System.out.println("Enter new contact number: ");
                            String contactNumber = sc.nextLine();
                            updateContactNumber(contactNumber);
                            break;
                        case 2:
                            System.out.println("Enter new email: ");
                            String email = sc.nextLine().trim().toLowerCase();
                            updateEmail(email);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 7: // View available appointment slots 
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    date = sc.nextLine();
                    System.out.println();
                    viewAvailableAppointmentSlots(doctorName, date);
                    break;
                case 8: // View Past Appointment Outcome Records
                    displayPastAppointmentRecords();
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

    public void updateContactNumber(String contactNumber)
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        medicalRecordManagement.updateContactNumber(patientId, patientName, contactNumber);
    }

    public void updateEmail(String email)
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        medicalRecordManagement.updateEmail(patientId, patientName, email);
    }

    public void viewPersonalMedicalRecord()
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        medicalRecordManagement.viewPersonalMedicalRecord(patientId, patientName);
    }

    public void viewAvailableAppointmentSlots(String doctorName, String date)
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        appointmentManagement.viewAvailableAppointmentSlots(patientId, patientName, doctorName, date);
    }

    public void scheduleAppointment(String doctorName, String date, String time)
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        appointmentManagement.scheduleAppointment(patientId, patientName, doctorName, date, time);
    }

    public void rescheduleAppointment(String doctorName, String oldDate, String oldTime, String newDate, String newTime)
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        appointmentManagement.rescheduleAppointment(patientId, patientName, doctorName, oldDate, oldTime, newDate, newTime);
    }

    public void cancelAppointment(String doctorName, String date, String time)
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        appointmentManagement.cancelAppointment(patientId, patientName, doctorName, date, time);
    }

    public void displayScheduledAppointments()
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        appointmentManagement.displayScheduledAppointments(patientId, patientName);
    }

    public void displayPastAppointmentRecords()
    {
        String patientId = loggedInPatient.getId();
        String patientName = loggedInPatient.getName();
        appointmentManagement.displayPastAppointmentRecords(patientId, patientName);
    }

}