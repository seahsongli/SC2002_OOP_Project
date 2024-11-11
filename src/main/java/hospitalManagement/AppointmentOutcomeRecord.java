package hospitalManagement;
enum serviceType
{
    CONSULTATION,
    XRAY,
    BLOODTEST
}

public class AppointmentOutcomeRecord
{
    private Appointment appointment;
    private String typeOfService;
    private String medicationsPrescribed;
    private Status prescriptionStatus;
    private String consultationNotes;
    
    public AppointmentOutcomeRecord(Appointment appointment, String typeOfService, String medicationsPrescribed, Status prescriptionStatus, String consultationNotes)
    {
        this.appointment = appointment;
        this.typeOfService = typeOfService;
        this.medicationsPrescribed = medicationsPrescribed;
        this.prescriptionStatus = prescriptionStatus;
        this.consultationNotes = consultationNotes;
    }
    
    public Appointment getAppointment()
    {
        return appointment;
    }

    public String getTypeOfService()
    {
        return typeOfService;
    }

    public String getMedicationsPrescribed()
    {
        return medicationsPrescribed;
    }

    public Status getPrescriptionStatus()
    {
        return prescriptionStatus;
    }

    public String getConsultationNotes()
    {
        return consultationNotes;
    }

    public void setTypeOfService(String typeOfService)
    {
        this.typeOfService = typeOfService;
    }

    public void setMedicationsPrescribed(String medicationsPrescribed)
    {
        this.medicationsPrescribed = medicationsPrescribed;
    }

    public void setPrescriptionStatus(Status prescriptionStatus)
    {
        this.prescriptionStatus = prescriptionStatus;
    }

    public void setConsultationNotes(String consultationNotes)
    {
        this.consultationNotes = consultationNotes;
    }
}