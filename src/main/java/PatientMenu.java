import java.util.Scanner;
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
            System.out.println("1. View Personal Medical Record");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Reschedule Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View Scheduled Appointments");
            System.out.println("6. Logout");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter patient ID: ");
                    int patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter patient name: ");
                    String patientName = sc.nextLine();
                    viewPersonalMedicalRecord(patientId, patientName);
                    break;
                case 2:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
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
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
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
                    cancelAppointment(patientId, patientName, doctorName, date, time);
                    break;
                case 5:
                    System.out.println("Enter patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter patient name: ");
                    patientName = sc.nextLine();
                    displayScheduledAppointments(patientId, patientName);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void updateContactNumber(int patientId, String patientName, String contactNumber)
    {
        medicalRecordManagement.updateContactNumber(patientId, patientName, contactNumber);
    }

    public void updateEmail(int patientId, String patientName, String email)
    {
        medicalRecordManagement.updateEmail(patientId, patientName, email);
    }

    public void viewPersonalMedicalRecord(int patientId, String patientName)
    {
        medicalRecordManagement.viewPersonalMedicalRecord(patientId, patientName);
    }

    //Implement viewAvailableAppointmentSlots method here.

    public void scheduleAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        appointmentManagement.scheduleAppointment(patientId, patientName, doctorName, date, time);
    }

    public void rescheduleAppointment(int patientId, String patientName, String doctorName, String oldDate, String oldTime, String newDate, String newTime)
    {
        appointmentManagement.rescheduleAppointment(patientId, patientName, doctorName, oldDate, oldTime, newDate, newTime);
    }

    public void cancelAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        appointmentManagement.cancelAppointment(patientId, patientName, doctorName, date, time);
    }

    public void displayScheduledAppointments(int patientId, String patientName)
    {
        appointmentManagement.displayScheduledAppointments(patientId, patientName);
    }

    public void displayPastAppointmentRecords(int patientId, String patientName)
    {
        appointmentManagement.displayPastAppointmentRecords(patientId, patientName);
    }

}