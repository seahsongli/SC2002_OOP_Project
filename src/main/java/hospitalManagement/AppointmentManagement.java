package hospitalManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentManagement {
    private List<Appointment> appointments;
    private List<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private Map<String, List<String>> doctorAvailability; // Key: "doctorId_date", Value: List of available times

    public AppointmentManagement() {
        appointments = new ArrayList<>();
        appointmentOutcomeRecords = new ArrayList<>();
        doctorAvailability = new HashMap<>();
    }

    // For doctor protected methods:
    // Implement viewPersonalSchedule() method to return all appointments that are
    // scheduled but not yet completed
    // Perhaps include sorting by date and time
    protected void viewPersonalSchedule(String doctorName) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().equals(doctorName) && appointment.getStatus() == Status.CONFIRMED) {
                System.out.println("Doctor: " + capitalizeName(appointment.getDoctorName()));
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
            }
        }
    }

    // Implement viewUpcomingAppointments() method to view all upcoming appointments
    // for the next 7 days for a specific doctor
    // Perhaps include sorting by date and time
    protected void viewUpcomingAppointments(String staffId, String doctorName) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean found = false;
        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = LocalDate.parse(appointment.getDate(), formatter);
            if (appointment.getDoctorName().equals(doctorName) && appointment.getStatus() == Status.CONFIRMED
                    && (appointmentDate.isAfter(today) || appointmentDate.isEqual(today))
                    && appointmentDate.isBefore(sevenDaysFromNow)) {
                System.out.println("Your upcoming appointmets in the next 7 days are as follows: \n");
                System.out.println("Doctor: " + capitalizeName(appointment.getDoctorName()));
                System.out.println("Patient: " + capitalizeName(appointment.getPatientName()));
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No confirmed appointments are available within seven days from today");
            System.out.println();
        }
    }

    public String capitalizeName(String name) {
        // split into array of chars "\\s" matches any spaces, tabs and newlines
        // if name is john doe , then words becomes ["john", "doe"]
        String[] words = name.split("\\s");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            // avoid processing empty strings in words
            // Input: " mArY annE joHNsOn "
            // Splitting: ["", "", "", "mArY", "", "", "", "annE", "", "", "joHNsOn", "",
            // "", ""]
            if (word.length() > 0) {
                // word.substring(0,1).toUpperCase extracts the first character of each string
                // in 'words' and converts it to upper case
                // word.substring(1).toLowerCase() extracts rest of charcters of each string in
                // 'words' and converts them to lower case
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                if (formattedName.length() > 0) {
                    formattedName.append(" ");
                }
                formattedName.append(capitalizedWord);
            }
        }

        return formattedName.toString();
    }

    public void viewAppointmentOutcomeRecord(String patientId, String patientName, String doctorName) {
        boolean found = false;
        for (AppointmentOutcomeRecord appointmentOutcomeRecord : appointmentOutcomeRecords) {
            if (appointmentOutcomeRecord.getAppointment().getPatientId().equals(patientId)
                    && appointmentOutcomeRecord.getAppointment().getPatientName().equals(patientName)
                    && appointmentOutcomeRecord.getAppointment().getDoctorName().equals(doctorName)) {
                System.out.println(
                        "Doctor: " + capitalizeName(appointmentOutcomeRecord.getAppointment().getDoctorName()));
                System.out.println("Date: " + appointmentOutcomeRecord.getAppointment().getDate());
                System.out.println("Time: " + appointmentOutcomeRecord.getAppointment().getTime());
                System.out.println("Type of Service: " + appointmentOutcomeRecord.getTypeOfService());
                System.out.println("Medications Prescribed: " + appointmentOutcomeRecord.getMedicationsPrescribed());
                System.out.println("Prescription Status: " + appointmentOutcomeRecord.getPrescriptionStatus());
                System.out.println("Consultation Notes: " + appointmentOutcomeRecord.getConsultationNotes());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointment outcome record found for patient " + patientName + " with ID "
                    + patientId + " with doctor " + doctorName);
        }
    }

    // Implement updateTypeOfService() method to update the type of service for a
    // specific appointment
    protected void updateTypeOfService(String patientId, String patientName, String staffId, String doctorName,
            String date, String time, String typeOfService) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setTypeOfService(typeOfService);
            }
        }
    }

    // Consider using doctorId in design, but this also means that doctorId would
    // need ot be present in appointment, but when patient schedules an appointment,
    // they would not know the doctorId, only the doctorName.
    // Implement updatePrescribedMedications() method to update the medications
    // prescribed for a specific appointment
    protected void updatePrescribedMedications(String patientId, String patientName, String staffId, String doctorName,
            String date, String time, String medicationsPrescribed) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setMedicationsPrescribed(medicationsPrescribed);
            }
        }
    }

    // Implement updatePrescriptionStatus() method to update the prescription status
    // for a specific appointment
    protected void updatePrescriptionStatus(String patientId, String patientName, String staffId, String doctorName,
            String date, String time, Status prescriptionStatus) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setPrescriptionStatus(prescriptionStatus);
                System.out.println("Prescription status updated for patient " + patientName + " with ID " + patientId
                        + " to " + prescriptionStatus);
                return;
            }
        }
        System.out.println("No matching appointment found for patient " + patientName + " with ID " + patientId
                + " with doctor " + doctorName + " on " + date + " at " + time);
    }

    // Implement updateConsultationNotes() method to update the consultation notes
    // for a specific appointment
    protected void updateConsultationNotes(String patientId, String patientName, String staffId, String doctorName,
            String date, String time, String consultationNotes) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setConsultationNotes(consultationNotes);
            }
        }
    }

    // This function is called after every consultation is completed. It records the
    // outcome of the appointment.
    protected void recordAppointmentOutcomeRecords(String patientId, String patientName, String doctorName, String date,
            String time, String typeOfService, String medicationsPrescribed, String consultationNotes,
            Status prescriptionStatus) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getPatientName().equals(patientName)
                    && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time)) {
                appointment.setStatus(Status.COMPLETED);
                appointmentOutcomeRecords.add(new AppointmentOutcomeRecord(appointment, typeOfService,
                        medicationsPrescribed, prescriptionStatus, consultationNotes));
                return;
            }
        }
        System.out.println("No matching appointment found for patient " + patientName + " with ID " + patientId
                + " with doctor " + doctorName + " on " + date + " at " + time);
    }

    protected void editService(String patientId, String doctorName, String date, String time, String service) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setTypeOfService(service);
            }
        }
    }

    protected void editPrescription(String patientId, String patientName, String doctorName, String date, String time,
            String prescription, Status prescriptionStatus) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setMedicationsPrescribed(prescription);
                record.setPrescriptionStatus(prescriptionStatus);
            }
        }
    }

    protected void editConsultationNotes(String patientId, String patientName, String doctorName, String date,
            String time, String consultationNotes) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)
                    && record.getAppointment().getDoctorName().equals(doctorName)
                    && record.getAppointment().getDate().equals(date)
                    && record.getAppointment().getTime().equals(time)) {
                record.setConsultationNotes(consultationNotes);
            }
        }
    }

    public void intializeDoctorAvailability(String doctorId, String doctorName) {
        ArrayList<String> availableTimes = new ArrayList<>(Arrays.asList(
                "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
                "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"));

        // Initialize availability for the next 1 month
        ArrayList<String> availableDates = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.now();

        for (int i = 0; i < 30; i++) {
            availableDates.add(startDate.plusDays(i).format(dateFormatter));
        }

        for (String date : availableDates) {
            String key = doctorName + "_" + date;
            // Create a new list for each date to avoid shared references
            ArrayList<String> dailyTimes = new ArrayList<>(availableTimes);
            doctorAvailability.put(key, dailyTimes);
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

        // Check if hour is between 9 and 18 (9 AM to 6 PM) and minute is either 00 or
        // 30 (30-minute intervals)
        return (hour >= 9 && hour < 18) && (minute == 0 || minute == 30);
    }

    // Set availability for a doctor
    protected void setAvailability(String doctorId, String doctorName, String date, String time) {
        // Validate the time to ensure it's a valid 30-minute slot within the working
        // hours (9 AM to 6 PM)
        if (!isValidTime(time)) {
            System.out.println(
                    "Invalid time slot. Available time slots are from 9:00 AM to 6:00 PM in 30-minute intervals.");
            return;
        }

        String key = doctorName + "_" + date;

        // Initialize the availability list if not already present
        doctorAvailability.putIfAbsent(key, new ArrayList<>());

        // Check if the time slot is already in the list
        if (doctorAvailability.get(key).contains(time)) {
            System.out
                    .println("Time slot already set for " + capitalizeName(doctorName) + " on " + date + " at " + time);
            return;
        }

        // Add the time slot to the doctor's availability list
        doctorAvailability.get(key).add(time);
        System.out.println("Availability set for " + capitalizeName(doctorName) + " on " + date + " at " + time);
    }

    // Cancel availability for a doctor
    protected void cancelAvailability(String doctorId, String doctorName, String date, String time) {
        String key = doctorName + "_" + date;
        if (doctorAvailability.containsKey(key)) {
            // Remove the time slot from the doctor's availability
            doctorAvailability.get(key).remove(time);
            // If no more slots are available for this doctor on the given date, remove the
            // entry
            if (doctorAvailability.get(key).isEmpty()) {
                doctorAvailability.remove(key);
            }
        }
    }

    // View all requests for a specific doctor
    protected void viewAppointmentRequests(String doctorName) {
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().equals(doctorName) && appointment.getStatus() == Status.PENDING) {
                System.out.println("The request detalis are as follows: \n");
                System.out.println("PatientID: " + capitalizeName(appointment.getPatientId()));
                System.out.println("Patient: " + capitalizeName(appointment.getPatientName()));
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointment requests at the moment");
            System.out.println();
        }
    }

    // Accept an appointment by a doctor
    protected void acceptAppointment(String patientId, String patientName, String staffId, String doctorName,
            String date, String time) {
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time)) {
                appointment.setStatus(Status.CONFIRMED);
                cancelAvailability(staffId, doctorName, date, time); // Remove the time slot from availability
                System.out.println("Appointment confirmed for " + capitalizeName(patientName) + " with Dr. "
                        + capitalizeName(doctorName) + " on " + date + " at " + time);
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No appointment requests are available to accept");
        }
    }

    // Reject an appointment by the doctor
    protected void rejectAppointment(String patientId, String patientName, String doctorName, String date,
            String time) {
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)
                    && appointment.getPatientName().toLowerCase().equals(patientName)
                    && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time)) {
                appointment.setStatus(Status.REJECTED);
                // Add the time slot back to availability
                String key = doctorName + "_" + date;
                doctorAvailability.putIfAbsent(key, new ArrayList<>());
                doctorAvailability.get(key).add(time);
                System.out.println("Appointment request rejected for " + capitalizeName(patientName) + " with Dr. "
                        + capitalizeName(doctorName) + " on " + date + " at " + time);
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("There are no appointments available for you to reject");
        }
    }

    public boolean isDuplicateAppointment(String patientId, String patientName, String doctorName, String date,
            String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getPatientName().equals(patientName)
                    && appointment.getDoctorName().equals(doctorName) && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }

    public void scheduleAppointment(String patientId, String patientName, String doctorName, String date, String time) {
        // Check if the doctor has availability for the given time slot
        String key = doctorName + "_" + date; // Use doctor name and date as key

        // Check if the doctor is available at the requested time
        if (!doctorAvailability.containsKey(key) || !doctorAvailability.get(key).contains(time)) {
            System.out.println(
                    "The doctor is not available at " + time + " on " + date + ". Please choose another time.");
            return;
        }
        if (isDuplicateAppointment(patientId, patientName, doctorName, date, time)) {
            System.out.println("Appointment Requests already exists for patient " + patientName + " with Dr. "
                    + doctorName + " on " + date + " at " + time);
            return;
        }
        // Schedule the appointment
        Appointment newAppointment = new Appointment(patientId, patientName, doctorName, date, time);
        newAppointment.setStatus(Status.PENDING);
        appointments.add(newAppointment);
        System.out.println("Appointment request submitted for " + capitalizeName(patientName) + " with Dr. "
                + capitalizeName(doctorName) + " on " + date + " at " + time);
        System.out.println();
    }

    // The patient reschedule appointment
    public void rescheduleAppointment(String patientId, String patientName, String doctorName, String oldDate,
            String oldTime, String newDate, String newTime) {
        // Cancel the old appointment first
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) &&
                    appointment.getPatientName().equals(patientName) &&
                    appointment.getDoctorName().equals(doctorName) &&
                    appointment.getDate().equals(oldDate) &&
                    appointment.getTime().equals(oldTime)) {

                // Set the old appointment status to canceled or just remove it
                appointment.setStatus(Status.CANCELLED);
                appointments.remove(appointment);
                System.out.println("Old appointment canceled for " + capitalizeName(patientName) + " with Dr. "
                        + capitalizeName(doctorName) + " on " + oldDate + " at " + oldTime);
                System.out.println();

                // Schedule the new appointment
                scheduleAppointment(patientId, patientName, doctorName, newDate, newTime);
                break;
            } else {
                System.out.println("No appointment scheduled for the details entered");
                System.out.println();
                break;
            }
        }
    }

    // The patient cancel appointment
    public void cancelAppointment(String patientId, String patientName, String doctorName, String date, String time) {
        boolean removed = appointments.removeIf(appointment -> appointment.getPatientId().equals(patientId) &&
                appointment.getPatientName().equals(patientName) &&
                appointment.getDoctorName().equals(doctorName) &&
                appointment.getDate().equals(date) &&
                appointment.getTime().equals(time));

        if (removed) {
            System.out.println("Appointment canceled for patient " + capitalizeName(patientName) + " with Dr. "
                    + capitalizeName(doctorName) + " on " + date + " at " + time + ".");
        } else {
            System.out.println(
                    "No matching appointment found to cancel for patient " + capitalizeName(patientName) + ".");
        }
    }

    // The patient view scheduled appointments
    public void displayScheduledAppointments(String patientId, String patientName) {
        boolean found = false; // Flag to check if any appointments are found for the patient
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getPatientName().equals(patientName)) {

                System.out.println("Doctor: " + capitalizeName(appointment.getDoctorName()));
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println("Status: " + appointment.getStatus());
                System.out.println();
                found = true; 
            }

        }
        if (!found) {
            System.out.println("No scheduled appointments are there for you");
            System.out.println();
        }
    }

    public void displayPastAppointmentRecords(String patientId, String patientName) {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords) {
            if (record.getAppointment().getPatientId().equals(patientId)
                    && record.getAppointment().getPatientName().equals(patientName)) {
                System.out.println("Doctor: " + capitalizeName(record.getAppointment().getDoctorName()));
                System.out.println("Date: " + record.getAppointment().getDate());
                System.out.println("Time: " + record.getAppointment().getTime());
                System.out.println("Type of Service: " + record.getTypeOfService());
                System.out.println("Medications Prescribed: " + record.getMedicationsPrescribed());
                System.out.println("Prescription Status: " + record.getPrescriptionStatus());
                System.out.println("Consultation Notes: " + record.getConsultationNotes());
            }
        }
    }

    public void viewAvailableAppointmentSlots(String patientId, String patientName, String doctorName, String date) {
        String key = doctorName + "_" + date;
        if (doctorAvailability.containsKey(key)) {
            System.out.println(
                    "Available slots for Dr. " + capitalizeName(doctorName) + " on " + date + " are as follows: ");
            for (String time : doctorAvailability.get(key)) {
                System.out.println(time);
            }
            System.out.println();
        } else {
            System.out.println("No available slots for Dr. " + capitalizeName(doctorName) + " on " + date);
            System.out.println();
        }
    }

    public void adminViewScheduledAppointments(String patientId, String patientName, String doctorName,
            String staffId) {
        boolean found = false; // Flag to check if any appointments are found for the patient
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getPatientName().equals(patientName)
                    && appointment.getDoctorName().equals(doctorName)) {
                System.out.println("Doctor: " + capitalizeName(appointment.getDoctorName()));
                System.out.println("Doctor ID: " + staffId);
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println("Status: " + appointment.getStatus());
                System.out.print("\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No scheduled appointments for the patient on the date and time entered");
            System.out.println();
        }
    }
}