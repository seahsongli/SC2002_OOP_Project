public class PatientMenu
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;

    public PatientMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement)
    {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
    }

    public void updateContactNumber(int patientId, String patientName, String phoneNumber)
    {
        medicalRecordManagement.updateContactNumber(patientId, patientName, phoneNumber);
    }

    public void updateEmail(int patientId, String patientName, String email)
    {
        medicalRecordManagement.updateEmail(patientId, patientName, email);
    }

    public void viewPersonalMedicalRecord(int patientId, String patientName)
    {
        medicalRecordManagement.viewPersonalMedicalRecord(patientId, patientName);
    }

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