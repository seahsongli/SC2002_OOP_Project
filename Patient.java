public class Patient {
    private int id;
    private String name;

    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setContactNumber(PatientMenu patientMenu, String phoneNumber)
    {
        patientMenu.updateContactNumber(getId(), getName(), phoneNumber);
    }

    public void setEmail(PatientMenu patientMenu, String email)
    {
        patientMenu.updateEmail(getId(), getName(), email);
    }
    
    public void viewPersonalMedicalRecord(PatientMenu patientMenu) {
        patientMenu.viewPersonalMedicalRecord(getId(), getName());
    }

    public void scheduleAppointment(PatientMenu patientMenu, String doctorName, String date, String time) {
        patientMenu.scheduleAppointment(getId(), getName(), doctorName, date, time);
    }

    public void rescheduleAppointment(PatientMenu patientMenu, String doctorName, String oldDate, String oldTime, String newDate, String newTime) {
        patientMenu.rescheduleAppointment(getId(), getName(), doctorName, oldDate, oldTime, newDate, newTime);
    }

    public void cancelAppointment(PatientMenu patientMenu, String doctorName, String date, String time) {
        patientMenu.cancelAppointment(getId(), getName(), doctorName, date, time);
    }

    public void viewScheduledAppointments(PatientMenu patientMenu) {
        patientMenu.displayScheduledAppointments(getId(), getName());
    }

    public void viewPastAppointmentRecords(PatientMenu patientMenu) {
        patientMenu.displayPastAppointmentRecords(getId(), getName());
    }

}