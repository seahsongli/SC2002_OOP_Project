public class DoctorMenu 
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;

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