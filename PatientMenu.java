public class PatientMenu
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;

    public PatientMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement)
    {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
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

    //Implement viewAvailableAppointmentSlots method here.

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