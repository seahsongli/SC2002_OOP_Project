package hospitalManagement;

public class PrescriptionManagement {
    private AppointmentManagement appointmentManagement;

    public PrescriptionManagement(AppointmentManagement appointmentManagement) {
        this.appointmentManagement = appointmentManagement;
    }

    // Consider using doctorId on top of doctorName to ensure that the correct
    // doctor is selected
    public void viewAppointmentOutcomeRecord(String patientId, String patientName, String doctorName) {
        appointmentManagement.viewAppointmentOutcomeRecord(patientId, patientName, doctorName);
        // System.out.println("Prescription for patient " + patientName + " with ID " +
        // patientId + " is: ");

    }

    public void setPrescription(String patientId, String patientName, String prescription) {

        System.out.println(
                "Prescription set for patient " + patientName + " with ID " + patientId + " is: " + prescription);
    }

    public void updatePrescriptionStatus(String patientId, String patientName, String staffId, String doctorName,
            String date, String time, Status prescriptionStatus) {
        appointmentManagement.updatePrescriptionStatus(patientId, patientName, staffId, doctorName, date, time,
                prescriptionStatus);
    }
}