public class PharmacistMenu
{
    PrescriptionManagement prescriptionManagement;
    InventoryManagement inventoryManagement;

    public void viewAppointmentOutcomeRecord(int patientId, String patientName, String doctorName)
    {
        prescriptionManagement.viewAppointmentOutcomeRecord(patientId, patientName, doctorName);
    }

    public void setPrescription(int patientId, String patientName, String prescription)
    {
        prescriptionManagement.setPrescription(patientId, patientName, prescription);
    }

    // consider using the entire appointment to check if its the correct appointment, and update accordingly
    // public void updatePrescriptionStatus(int patientId, String patientName, String status)
    // {
    //     prescriptionManagement.updatePrescriptionStatus(patientId, patientName, status);
    // }

    public void updatePrescriptionStatus(int patientId, String patientName, int doctorId, String doctorName, String date, String time, Status prescriptionStatus) {
        // Logic to find the correct appointment and update the prescription status
        
            // Assuming Appointment has a method to set prescription status
            prescriptionManagement.updatePrescriptionStatus(patientId, patientName, doctorId, doctorName, date, time, prescriptionStatus);
            System.out.println("Prescription status updated for patient " + patientName + " with ID " + patientId + " to " + prescriptionStatus);
    }

    public void viewInventory()
    {
        inventoryManagement.viewCurrentStockLevels();
    }

    public void updateInventory(String medicationName, int quantity)
    {
        inventoryManagement.updateInventory(medicationName, quantity);
    }

    public void submitReplenishmentRequest(String medicationName, int quantity)
    {
        inventoryManagement.submitReplenishmentRequest(medicationName, quantity);
    }
}