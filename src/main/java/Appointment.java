public class Appointment
{
    private String patientId;
    private String patientName;
    private String doctorName;
    private String date;
    private String time;
    private Status consultationStatus;
    
    public Appointment(String patientId, String patientName, String doctorName, String date, String time)
    {
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.consultationStatus = Status.PENDING;
    }
    
    public String getPatientId()
    {
        return patientId;
    }
    
    public String getPatientName()
    {
        return patientName;
    }
    
    public String getDoctorName()
    {
        return doctorName;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public String getTime()
    {
        return time;
    }

    public Status getStatus()
    {
        return consultationStatus;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }

    public void setStatus(Status status)
    {
        this.consultationStatus = status;
    }
   
}
