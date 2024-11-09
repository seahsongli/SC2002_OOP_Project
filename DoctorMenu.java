public class DoctorMenu 
{
    private MedicalRecordManagement medicalRecordManagement;
    private AppointmentManagement appointmentManagement;

    public DoctorMenu(MedicalRecordManagement medicalRecordManagement, AppointmentManagement appointmentManagement)
    {
        this.medicalRecordManagement = medicalRecordManagement;
        this.appointmentManagement = appointmentManagement;
    }

    // public void updateContactNumber(String staffId, String doctorName, String contactNumber)
    // {
    //     medicalRecordManagement.updateContactNumber(staffId, doctorName, contactNumber);
    // }

    // public void updateEmail(String staffId, String doctorName, String email)
    // {
    //     medicalRecordManagement.updateEmail(staffId, doctorName, email);
    // }

    // public void viewPersonalMedicalRecord(String staffId, String doctorName)
    // {
    //     medicalRecordManagement.viewPersonalMedicalRecord(staffId, doctorName);
    // }

    public void viewPatientMedicalRecord(String patientId, String patientName)
    {
        medicalRecordManagement.viewPatientMedicalRecord(patientId, patientName);
    }

    // To set the patient's medical record, the doctor must provide the patient's ID, name, diagnosis, and prescription.
    // This is to be done after every consultation.
    public void setPatientMedicalrecord(String patientId, String patientName, String diagnosis, String prescription)
    {
        medicalRecordManagement.setPatientMedicalrecord(patientId, patientName, diagnosis, prescription);
    }

    // Consider adding doctorId to extract personal schedule too.
    public void viewPersonalSchedule(String doctorName)
    {
        appointmentManagement.viewPersonalSchedule(doctorName);
    }

    public void setAvailability(String staffID, String doctorName, String date, String time)
    {
        appointmentManagement.setAvailability(staffID, doctorName, date, time);
    }

    public void cancelAvailability(String staffId, String doctorName, String date, String time)
    {
        appointmentManagement.cancelAvailability(staffId, doctorName, date, time);
    }

    public void acceptAppointment(String patientId, String patientName, String staffId, String doctorName, String date, String time)
    {
        appointmentManagement.acceptAppointment(patientId, patientName, staffId, doctorName, date, time);
    }

    public void rejectAppointment(String patientId, String patientName, String doctorName, String date, String time)
    {
        appointmentManagement.rejectAppointment(patientId, patientName, doctorName, date, time);
    }

    public void displayScheduledAppointments(String staffId, String doctorName)
    {
        appointmentManagement.displayScheduledAppointments(staffId, doctorName);
    }

    public void viewUpcomingAppointments(String staffId, String doctorName)
    {
        appointmentManagement.viewUpcomingAppointments(staffId, doctorName);
    }

    public void recordAppointmentOutcomeRecords(String patientId, String patientName, String doctorName, String date, String time, String service, String prescription, String consultationNotes, Status prescriptionStatus)
    {
        appointmentManagement.recordAppointmentOutcomeRecords(patientId, patientName, doctorName, date, time, service, prescription, consultationNotes, prescriptionStatus);
    }

    public void addTreatmentPlan(String patientId, String patientName, String treatmentPlan)
    {
        medicalRecordManagement.addNewTreatmentPlan(patientId, patientName, treatmentPlan);
    }

    public void addNewDiagnosis(String patientId, String patientName, String diagnosis)
    {
        medicalRecordManagement.addNewMedicalDiagnosis(patientId, patientName, diagnosis);
    }

    // Consider whether other doctors should be able to addNewPrescription on behalf of another doctor.
    // Remove the doctorName from parameter if this isn't the case.
    public void addNewPrescription(String patientId, String patientName, String doctorName, String prescription)
    {
        medicalRecordManagement.addNewMedicalPrescription(patientId, patientName, prescription);
    }

    // Individual functions to edit specific fields so that the doctor can edit the appointment outcome in case of errata.
    public void editService(String patientId, String doctorName, String date, String time, String service)
    {
        appointmentManagement.editService(patientId, doctorName, date, time, service);
    }

    // Consider whether other doctors should be able to editPrescription on behalf of another doctor.
    public void editPrescription(String patientId, String patientName, String doctorName, String date, String time, String prescription, Status prescriptionStatus)
    {
        appointmentManagement.editPrescription(patientId, patientName, doctorName, date, time, prescription, prescriptionStatus);
    }

    public void editConsultationNotes(String patientId, String patientName, String doctorName, String date, String time, String consultationNotes)
    {
        appointmentManagement.editConsultationNotes(patientId, patientName, doctorName, date, time, consultationNotes);
    }
}