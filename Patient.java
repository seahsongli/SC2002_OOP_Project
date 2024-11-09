import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Patient extends User {
    private String patientId;
    private String name;
    private LocalDate dob; // Date of Birth
    private String gender;
    private String bloodGroup;
    private String email;

    // Define a DateTimeFormatter for yyyy-MM-dd format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");



    public Patient(String hospitalID, String password, String patientId, String name,  String dob, String gender, String bloodGroup, String email) {
        super(hospitalID, password, "Patient");
        this.patientId = patientId;
        this.name = name;
        setDob(dob);
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.email = email;
    }

    public String getId() {
        return patientId;
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
    public void setDob(String dobStr) {
        try {
            this.dob = LocalDate.parse(dobStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid DOB format. Please use yyyy-MM-dd.");
        }
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