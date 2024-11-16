package hospitalManagement;
import java.util.Scanner;

public class Doctor extends Staff
{
    private Scanner sc = new Scanner(System.in); // Define the Scanner object
    
    public Doctor() 
    {
        super("", "", "", "", "", "", 0); // super() has to be at the first statement of constructor.

        System.out.println("Enter doctor name: ");
        String doctorName = sc.nextLine();
        super.updateName(doctorName);

        System.out.println("Enter Gender: ");
        String gender = sc.nextLine();
        super.updateGender(gender);

        System.out.println("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        super.updateAge(age);

        super.updateRole("Doctor");
    }

    
    public void addNewDiagnosis(DoctorMenu doctorMenu)
    {
        System.out.println("Enter patient ID: ");
        String patientId = sc.nextLine();
        sc.nextLine(); // Consume the newline character
        System.out.println("Enter patient name: ");
        String patientName = sc.nextLine();
        System.out.println("Enter diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.println("Enter prescription: ");
        String prescription = sc.nextLine();
        doctorMenu.setPatientMedicalrecord(patientId, patientName, diagnosis, prescription);
    }

    public void addNewPrescription(DoctorMenu doctorMenu)
    {
        System.out.println("Enter patient ID: ");
        String patientId = sc.nextLine();
        sc.nextLine(); // Consume the newline character
        System.out.println("Enter patient name: ");
        String patientName = sc.nextLine();
        System.out.println("Enter prescription: ");
        String prescription = sc.nextLine();
        // public void editPrescription(int patientId, String patientName, String doctorName, String date, String time, String prescription, Status prescriptionStatus
        doctorMenu.addNewPrescription(patientId, patientName, prescription);
    }

    public void addTreatmentPlans(DoctorMenu doctorMenu)
    {
        System.out.println("Enter patient ID: ");
        String patientId = sc.nextLine().trim();
        sc.nextLine(); // Consume the newline character
        System.out.println("Enter patient name: ");
        String patientName = sc.nextLine().trim();
        System.out.println("Enter treatment plan: ");
        String treatmentPlan = sc.nextLine().trim();
        doctorMenu.addTreatmentPlan(patientId, patientName, treatmentPlan);
    }

    public void viewPatientMedicalRecord(DoctorMenu doctorMenu)
    {
        System.out.println("Enter patient ID: ");
        String patientId = sc.nextLine().trim();
        sc.nextLine(); // Consume the newline character
        System.out.println("Enter patient name: ");
        String patientName = sc.nextLine().trim();
        doctorMenu.viewPatientMedicalRecord(patientId, patientName);
    }

    public void viewPersonalSchedule(DoctorMenu doctorMenu)
    {
        doctorMenu.viewPersonalSchedule(super.getName());
    }

    //  public void acceptAppointment(int patientId, String patientName, int doctorId, String doctorName, String date, String time)
    public void acceptAppointment(DoctorMenu doctorMenu)
    {
        System.out.println("Enter patient ID: ");
        String patientId = sc.nextLine().trim();
        sc.nextLine(); // Consume the newline character
        System.out.println("Enter patient name: ");
        String patientName = sc.nextLine();
        System.out.println("Enter date: ");
        String date = sc.nextLine();
        System.out.println("Enter time: ");
        String time = sc.nextLine();
        doctorMenu.acceptAppointment(patientId, patientName, super.getStaffId(), super.getName(), date, time);
    }

    public void rejectAppointment(DoctorMenu doctorMenu)
    {
        System.out.println("Enter patient ID: ");
        String patientId = sc.nextLine().trim();
        sc.nextLine(); // Consume the newline character
        System.out.println("Enter patient name: ");
        String patientName = sc.nextLine();
        System.out.println("Enter date: ");
        String date = sc.nextLine();
        System.out.println("Enter time: ");
        String time = sc.nextLine();
        doctorMenu.rejectAppointment(patientId, patientName, super.getName(), date, time);
    }

    public void viewUpcomingAppointments(DoctorMenu doctorMenu)
    {
        doctorMenu.viewUpcomingAppointments(super.getStaffId(), super.getName());
    }
}