import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MedicalRecordManagement medicalRecordManagement = new MedicalRecordManagement();
        AppointmentManagement appointmentManagement = new AppointmentManagement();
        DoctorMenu doctorMenu = new DoctorMenu(medicalRecordManagement, appointmentManagement);

        // Create a Doctor instance
        Doctor doctor = new Doctor();

        // Set availability for the doctor
        System.out.println("Setting availability for the doctor...");
        doctorMenu.setAvailability(doctor.getStaffId(), doctor.getName(), "10-01-2023", "10:00");
        doctorMenu.setAvailability(doctor.getStaffId(), doctor.getName(), "10-01-2023", "11:00");

        // Schedule an appointment
        System.out.println("Scheduling an appointment...");
        appointmentManagement.scheduleAppointment(1, "John Doe", doctor.getName(), "10-01-2023", "10:00");

        // View upcoming appointments for the doctor
        System.out.println("Viewing upcoming appointments for the doctor...");
        doctor.viewUpcomingAppointments(doctorMenu);

        // Accept the appointment
        System.out.println("Accepting the appointment...");
        doctorMenu.acceptAppointment(1, "John Doe", doctor.getName(), "10-01-2023", "10:00");

        // View upcoming appointments again
        System.out.println("Viewing upcoming appointments for the doctor after accepting the appointment...");
        doctor.viewUpcomingAppointments(doctorMenu);

        // Add a new diagnosis
        System.out.println("Adding a new diagnosis...");
        doctor.addNewDiagnosis(doctorMenu);

        // Reject an appointment
        System.out.println("Rejecting an appointment...");
        doctor.rejectAppointment(doctorMenu);

        // View past appointment records
        System.out.println("Viewing past appointment records...");
        appointmentManagement.displayPastAppointmentRecords(1, "John Doe");

        sc.close();
    }
}