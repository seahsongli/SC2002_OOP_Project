package patient;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                    String date = getDateFromUser();
                    String time = getTimeFromUser();
                    scheduleAppointment(patientId, patientName, doctorName, date, time);
                    break;
                case 3:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    System.out.println("Requesting user to enter old date and time for rescheduling....");
                    String oldDate = getDateFromUser();
                    String oldTime = getTimeFromUser();
                    System.out.println("Requesting user to enter new date and time for rescheduling....");
                    String newDate = getDateFromUser();
                    String newTime = getTimeFromUser();
                    rescheduleAppointment(patientId, patientName, doctorName, oldDate, oldTime, newDate, newTime);
                    break;
                case 4:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    doctorName = sc.nextLine();
                    date = getDateFromUser();
                    time = getTimeFromUser();
                    cancelAppointment(patientId, patientName, doctorName, date, time);
                    break;
                case 5:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextLine();
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