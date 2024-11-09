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
    private Map<String, List<String>> doctorAvailability; // Key: "doctorId_date", Value: List of available times   

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

    
    // Set availability for a doctor
    protected void setAvailability(int doctorId, String doctorName, String date, String time)
    {
        String key = doctorId + "_" + date;
        doctorAvailability.putIfAbsent(key, new ArrayList<>());
        doctorAvailability.get(key).add(time);
    }

    // Cancel availability for a doctor
    protected void cancelAvailability(int doctorId, String doctorName, String date, String time)
    {
        String key = doctorId + "_" + date;
        if (doctorAvailability.containsKey(key))
        {
            doctorAvailability.get(key).remove(time);
            if (doctorAvailability.get(key).isEmpty())
            {
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
                break;
            }
        }
    }

    // Reject an appointment
    protected void rejectAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date) && appointment.getTime().equals(time))
            {
                appointment.setStatus(Status.REJECTED);
                break;
            }
        }
    }

    public void scheduleAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        appointments.add(new Appointment(patientId, patientName, doctorName, date, time));
    }

    public void rescheduleAppointment(int patientId, String patientName, String doctorName, String oldDate, String oldTime, String newDate, String newTime)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(oldDate) && appointment.getTime().equals(oldTime))
            {
                appointment.setDate(newDate);
                appointment.setTime(newTime);
            }
        }
    }

    public void cancelAppointment(int patientId, String patientName, String doctorName, String date, String time)
    {
        appointments.removeIf(appointment -> appointment.getPatientId() == patientId && appointment.getPatientName().equals(patientName) && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date) && appointment.getTime().equals(time));
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
                System.out.println("Status: " + appointment.getStatus());
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