public class AppointmentManagement
{
    private Appointment[] appointments;
    private int appointmentCount;
    private AppointmentOutcomeRecord[] appointmentOutcomeRecords;
    
    public AppointmentManagement()
    {
        appointments = new Appointment[100];
        appointmentCount = 0;
    }
    
    public void scheduleAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        appointments[appointmentCount] = new Appointment(patientId, patientName, doctorName, date, time);
        appointmentCount++;
    }
    
    public void rescheduleAppointment(int patientId, String patientName, String doctorName, String oldDate, String oldTime, String newDate, String newTime)
    {
        for(int i = 0; i < appointmentCount; i++)
        {
            if(appointments[i].getPatientId() == patientId && appointments[i].getPatientName().equals(patientName) && appointments[i].getDoctorName().equals(doctorName) && appointments[i].getDate().equals(oldDate) && appointments[i].getTime().equals(oldTime))
            {
                appointments[i].setDate(newDate);
                appointments[i].setTime(newTime);
            }
        }
    }
    
    public void cancelAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        for(int i = 0; i < appointmentCount; i++)
        {
            if(appointments[i].getPatientId() == patientId && appointments[i].getPatientName().equals(patientName) && appointments[i].getDoctorName().equals(doctorName) && appointments[i].getDate().equals(date) && appointments[i].getTime().equals(time))
            {
                appointments[i] = null;
                for(int j = i; j < appointmentCount - 1; j++)
                {
                    appointments[j] = appointments[j + 1];
                }
                appointmentCount--;
            }
        }
    }
    
    public void displayScheduledAppointments(int patientId, String patientName)
    {
        for(int i = 0; i < appointmentCount; i++)
        {
            if(appointments[i].getPatientId() == patientId && appointments[i].getPatientName().equals(patientName))
            {
                System.out.println("Doctor: " + appointments[i].getDoctorName());
                System.out.println("Date: " + appointments[i].getDate());
                System.out.println("Time: " + appointments[i].getTime());
            }
        }
    }
    
    public void displayPastAppointmentRecords(int patientId, String patientName)
    {
        for(int i = 0; i < appointmentOutcomeRecords.length; i++)
        {
            if (patientId == appointmentOutcomeRecords[i].getAppointment().getPatientId() && patientName.equals(appointmentOutcomeRecords[i].getAppointment().getPatientName()))
            {
                System.out.println("Doctor: " + appointmentOutcomeRecords[i].getAppointment().getDoctorName());
                System.out.println("Date: " + appointmentOutcomeRecords[i].getAppointment().getDate());
                System.out.println("Time: " + appointmentOutcomeRecords[i].getAppointment().getTime());
                System.out.println("Type of Service: " + appointmentOutcomeRecords[i].getTypeOfService());
                System.out.println("Medications Prescribed: " + appointmentOutcomeRecords[i].getMedicationsPrescribed());
                System.out.println("Prescription Status: " + appointmentOutcomeRecords[i].getPrescriptionStatus());
                System.out.println("Consultation Notes: " + appointmentOutcomeRecords[i].getConsultationNotes());
            }
        }
    }
}