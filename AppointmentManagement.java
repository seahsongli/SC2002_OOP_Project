import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentManagement
{
    private List<Appointment> appointments;
    private List<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private Map<String, List<String>> doctorAvailability; // Key: "doctorName_date", Value: List of available times


    public AppointmentManagement()
    {
        appointments = new ArrayList<>();
        appointmentOutcomeRecords = new ArrayList<>();
        doctorAvailability = new HashMap<>();
    }

    // For doctor protected methods:
    // Implement viewPersonalSchedule() method to return all appointments that are scheduled but not yet completed
    // Perhaps include sorting by date and time
    protected void viewPersonalSchedule(String doctorName)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getDoctorName().equals(doctorName) && appointment.getStatus() == Status.SCHEDULED)
            {
                System.out.println("Doctor: " + appointment.getDoctorName());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
            }
        }
    }

    // Implement viewUpcomingAppointments() method to view all upcoming appointments for the next 7 days for a specific doctor
    // Perhaps include sorting by date and time
    protected void viewUpcomingAppointments(int doctorId, String doctorName)
    {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Appointment appointment : appointments)
        {
            LocalDate appointmentDate = LocalDate.parse(appointment.getDate(), formatter);
            if (appointment.getDoctorName().equals(doctorName) && appointment.getStatus() == Status.SCHEDULED && (appointmentDate.isAfter(today) || appointmentDate.isEqual(today)) && appointmentDate.isBefore(sevenDaysFromNow))
            {
                System.out.println("Doctor: " + appointment.getDoctorName());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
            }
        }
    }

    public void viewAppointmentOutcomeRecord(int patientId, String patientName, String doctorName)
    {
        for(AppointmentOutcomeRecord appointmentOutcomeRecord : appointmentOutcomeRecords)
        {
            if (appointmentOutcomeRecord.getAppointment().getPatientId() == patientId && appointmentOutcomeRecord.getAppointment().getPatientName().equals(patientName) && appointmentOutcomeRecord.getAppointment().getDoctorName().equals(doctorName))
            {
                System.out.println("Doctor: " + appointmentOutcomeRecord.getAppointment().getDoctorName());
                System.out.println("Date: " + appointmentOutcomeRecord.getAppointment().getDate());
                System.out.println("Time: " + appointmentOutcomeRecord.getAppointment().getTime());
                System.out.println("Type of Service: " + appointmentOutcomeRecord.getTypeOfService());
                System.out.println("Medications Prescribed: " + appointmentOutcomeRecord.getMedicationsPrescribed());
                System.out.println("Prescription Status: " + appointmentOutcomeRecord.getPrescriptionStatus());
                System.out.println("Consultation Notes: " + appointmentOutcomeRecord.getConsultationNotes());
            }
        }
    }
    // Implement updateTypeOfService() method to update the type of service for a specific appointment
    protected void updateTypeOfService(int patientId, String patientName, int doctorId, String doctorName, String date, String time, String typeOfService)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getPatientName().equals(patientName) && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setTypeOfService(typeOfService);
            }
        }
    }
    // Consider using doctorId in design, but this also means that doctorId would need ot be present in appointment, but when patient schedules an appointment, they would not know the doctorId, only the doctorName.
    // Implement updatePrescribedMedications() method to update the medications prescribed for a specific appointment
    protected void updatePrescribedMedications(int patientId, String patientName, int doctorId, String doctorName, String date, String time, String medicationsPrescribed)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getPatientName().equals(patientName) && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setMedicationsPrescribed(medicationsPrescribed);
            }
        }
    }
    // Implement updatePrescriptionStatus() method to update the prescription status for a specific appointment
    protected void updatePrescriptionStatus(int patientId, String patientName, int doctorId, String doctorName, String date, String time, Status prescriptionStatus)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getPatientName().equals(patientName) && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setPrescriptionStatus(prescriptionStatus);
            }
        }
    }

    // Implement updateConsultationNotes() method to update the consultation notes for a specific appointment
    protected void updateConsultationNotes(int patientId, String patientName, int doctorId, String doctorName, String date, String time, String consultationNotes)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getPatientName().equals(patientName) && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setConsultationNotes(consultationNotes);
            }
        }
    }

    // Need to implement displayAvailableSlots() method

    // This function is called after every consultation is completed. It records the outcome of the appointment.
    protected void recordAppointmentOutcomeRecords(int patientId, String patientName, String doctorName, String date, String time, String typeOfService, String medicationsPrescribed, String consultationNotes, Status prescriptionStatus)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date) && appointment.getTime().equals(time))
            {
                appointment.setStatus(Status.COMPLETED);
                appointmentOutcomeRecords.add(new AppointmentOutcomeRecord(appointment, typeOfService, medicationsPrescribed, prescriptionStatus, consultationNotes));
            }
        }
    }

    protected void editService(int patientId, String doctorName, String date, String time, String service)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setTypeOfService(service);
            }
        }
    }

    protected void editPrescription(int patientId, String patientName, String doctorName, String date, String time, String prescription, Status prescriptionStatus)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getPatientName().equals(patientName) && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setMedicationsPrescribed(prescription);
                record.setPrescriptionStatus(prescriptionStatus);
            }
        }
    }

    protected void editConsultationNotes(int patientId, String patientName, String doctorName, String date, String time, String consultationNotes)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (record.getAppointment().getPatientId() == patientId && record.getAppointment().getPatientName().equals(patientName) && record.getAppointment().getDoctorName().equals(doctorName) && record.getAppointment().getDate().equals(date) && record.getAppointment().getTime().equals(time))
            {
                record.setConsultationNotes(consultationNotes);
            }
        }
    }

    private boolean isValidTime(String time) {
        // Check if the time is in the correct format (HH:mm)
        String[] timeParts = time.split(":");
        if (timeParts.length != 2) {
            return false;
        }
    
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
    
        // Check if hour is between 9 and 18 (9 AM to 6 PM) and minute is either 00 or 30 (30-minute intervals)
        return (hour >= 9 && hour < 18) && (minute == 0 || minute == 30);
    }
    
    protected  void setAvailability(int doctorId, String doctorName, String date, String time) {
        // Validate the time to ensure it's a valid 30-minute slot within the working hours (9 AM to 6 PM)
        if (!isValidTime(time)) {
            System.out.println("Invalid time slot. Available time slots are from 9:00 AM to 6:00 PM in 30-minute intervals.");
            return;
        }
    
        String key = doctorName + "_" + time;
    
        // Initialize the availability list if not already present
        doctorAvailability.putIfAbsent(key, new ArrayList<>());
    
        // Add the time slot to the doctor's availability list
        doctorAvailability.get(key).add(time);
        System.out.println("Availability set for " + doctorName + " on " + date + " at " + time);
    }

    // Cancel availability for a doctor
    protected void cancelAvailability(int doctorId, String doctorName, String date, String time) {
        String key = doctorName + "_" + date;
        if (doctorAvailability.containsKey(key)) {
            // Remove the time slot from the doctor's availability
            doctorAvailability.get(key).remove(time);
            System.out.println("Availability canceled for " + doctorName + " on " + date + " at " + time);
            // If no more slots are available for this doctor on the given date, remove the entry
            if (doctorAvailability.get(key).isEmpty()) {
                doctorAvailability.remove(key);
            }
        }
    }

    // Accept an appointment
    protected void acceptAppointment(int patientId, String patientName, int doctorId, String doctorName, String date, String time)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date) && appointment.getTime().equals(time))
            {
                appointment.setStatus(Status.CONFIRMED);
                cancelAvailability(doctorId, doctorName, date, time); // Remove the time slot from availability
                System.out.println("Appointment confirmed for " + patientName + " with Dr. " + doctorName);
                return;
            }
        }
        System.out.println("Appointment not found for confirmation.");
    }

    // Reject an appointment
    protected void rejectAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date) && appointment.getTime().equals(time))
            {
                appointment.setStatus(Status.REJECTED);
                System.out.println("Appointment rejected for " + patientName + " with Dr. " + doctorName);
                return;
            }
        }
        System.out.println("Appointment not found for rejection.");
    }

    public void scheduleAppointment(int patientId, String patientName, String doctorName, String date, String time) {
        // Check if the doctor has availability for the given time slot
        String key = doctorName + "_" + date; // Use doctor name and date as key
        
        // Check if the doctor is available at the requested time
        if (!doctorAvailability.containsKey(key) || !doctorAvailability.get(key).contains(time)) {
            System.out.println("The doctor is not available at " + time + " on " + date + ". Please choose another time.");
            return; // Reject the appointment if the slot is not available
        }

        // Check if patient already has an appointment at that time
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId() == patientId && appointment.getDate().equals(date) && appointment.getTime().equals(time)) {
                System.out.println("Patient already has an appointment at this time.");
                return;
            }
        }
        
        // If the slot is available, schedule the appointment
        appointments.add(new Appointment(patientId, patientName, doctorName, date, time));
        System.out.println("Appointment scheduled for " + patientName + " with Dr. " + doctorName + " on " + date + " at " + time);
    }

    public void cancelAppointment(int patientId, String patientName, String doctorName, String date, String time) {
        appointments.removeIf(appointment -> 
            appointment.getPatientId() == patientId && 
            appointment.getPatientName().equals(patientName) && 
            appointment.getDoctorName().equals(doctorName) && 
            appointment.getDate().equals(date) && 
            appointment.getTime().equals(time)
        );
        System.out.println("Appointment canceled for " + patientName + " with Dr. " + doctorName);
    }

    public void rescheduleAppointment(int patientId, String patientName, String doctorName, String oldDate, String oldTime, String newDate, String newTime) {
        // Cancel the old appointment first
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId() == patientId && 
                appointment.getPatientName().equals(patientName) && 
                appointment.getDoctorName().equals(doctorName) && 
                appointment.getDate().equals(oldDate) && 
                appointment.getTime().equals(oldTime)) {
                
                // Set the old appointment status to canceled or just remove it
                appointment.setStatus(Status.CANCELLED);  // Assuming you have a CANCELLED status
                appointments.remove(appointment);
                System.out.println("Old appointment canceled for " + patientName + " with Dr. " + doctorName + " on " + oldDate + " at " + oldTime);
                break;
            }
        }
    
        // Now, try to schedule the new appointment
        scheduleAppointment(patientId, patientName, doctorName, newDate, newTime); // Call the existing scheduleAppointment method to book the new appointment
    }

        // Method to display available appointments for a doctor on a specific date
    public void displayAvailableAppointments(String doctorName, String date) {
        // Create a key based on the doctor name and the date
        String key = doctorName + "_" + date;
        
        // Check if the doctor has availability for the given date
        if (doctorAvailability.containsKey(key)) {
            // Get the list of available time slots for the doctor on that date
            List<String> availableTimes = doctorAvailability.get(key);
            
            // Display the available time slots
            System.out.println("Available appointments for Dr. " + doctorName + " on " + date + ":");
            for (String time : availableTimes) {
                System.out.println("Time: " + time);
            }
        } else {
            // If there are no available slots for the doctor on that date
            System.out.println("No available appointments for Dr. " + doctorName + " on " + date + ".");
        }
    }

    public void displayScheduledAppointments(int patientId, String patientName)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName))
            {
                System.out.println("Doctor: " + appointment.getDoctorName());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
            }
        }
    }

    public void displayPastAppointmentRecords(int patientId, String patientName)
    {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords)
        {
            if (patientId == record.getAppointment().getPatientId() && patientName.equals(record.getAppointment().getPatientName()))
            {
                System.out.println("Doctor: " + record.getAppointment().getDoctorName());
                System.out.println("Date: " + record.getAppointment().getDate());
                System.out.println("Time: " + record.getAppointment().getTime());
                System.out.println("Type of Service: " + record.getTypeOfService());
                System.out.println("Medications Prescribed: " + record.getMedicationsPrescribed());
                System.out.println("Prescription Status: " + record.getPrescriptionStatus());
                System.out.println("Consultation Notes: " + record.getConsultationNotes());
            }
        }
    }

    public void adminViewScheduledAppointments(int patientId, String patientName, String doctorName, String doctorId)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName))
            {
                System.out.println("Doctor: " + appointment.getDoctorName());
                System.out.println("Doctor ID: " + doctorId);
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println("Status: " + appointment.getStatus());
            }
        }
    }
}