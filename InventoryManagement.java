import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class InventoryManagement
{
    private List<Medicine> currentStockLevels;
    private List<Request> replenishmentRequest;

    // Constructor with parameter
    public InventoryManagement() 
    {
        this.currentStockLevels = new ArrayList<>();
        this.replenishmentRequest = new ArrayList<>();
    }

    public void displayInventory()
    {
        if (currentStockLevels.isEmpty()) 
        {
            System.out.println("No medicines in the inventory.");
            return;
        }
        System.out.println("\n--- Current Medication Inventory ---");
        for (Medicine medicine : currentStockLevels)
        {
            System.out.println("Medicine: " + medicine.getName() + " Quantity: " + medicine.getQuantity());
        }
    }
    
    
    
    public void addStock(String name, int quantity)
    {
        Medicine medicine = findMedicineByName(name);
        if (medicine != null) 
        {
            medicine.setQuantity(medicine.getQuantity() + quantity);
            System.out.println("Updated quantity of " + name + " to " + medicine.getQuantity() + ".");
        } 
        else 
        {
            Medicine newMedicine = new Medicine(name, quantity);
            currentStockLevels.add(newMedicine);
            System.out.println("Added new medicine: " + name + " with quantity " + quantity + ".");
        }
    }
    
    public void removeStock(String name, int quantity)
    {
        Medicine medicine = findMedicineByName(name);
        if (medicine != null) 
        {
            if (medicine.getQuantity() < quantity) 
            {
                System.out.println("Cannot remove " + quantity + " units. Only " + medicine.getQuantity() + " available.");
                return;
            }
            medicine.setQuantity(medicine.getQuantity() - quantity);
            System.out.println("Removed " + quantity + " units of " + name + ". New quantity: " + medicine.getQuantity() + ".");
            // Optionally, remove the medicine from the list if quantity reaches zero
            if (medicine.getQuantity() == 0) 
            {
                currentStockLevels.remove(medicine);
                System.out.println(name + " has been removed from the inventory as its quantity reached zero.");
            }
        } else {
            System.out.println("Medicine " + name + " not found in the inventory.");
        }
    }
    
    public List<Request> getPendingReplenishmentRequests() 
    {
       return replenishmentRequest.stream()
               .filter(request -> request.getStatus() == Status.PENDING)
               .collect(Collectors.toList());
    }
    public void requestReplenishment(String name, int quantity)
    {
        Medicine medicine = findMedicineByName(name);
        if (medicine == null) {
            System.out.println("Medicine " + name + " not found in the inventory.");
            return;
        }
        Request newRequest = new Request();
        newRequest.setMedicine(medicine);
        newRequest.setReplenishQuantity(quantity);
        newRequest.setStatus(Status.PENDING);
        replenishmentRequest.add(newRequest);
        System.out.println("Replenishment request submitted for " + name + " with quantity " + quantity + ".");
    }

    public void updateStockLevel(String medicationName, int quantity)
    {
        Medicine medicine = findMedicineByName(medicationName);
        if (medicine != null) 
        {
            int newQuantity = medicine.getQuantity() + quantity;
            if (newQuantity < 0) 
            {
                System.out.println("Cannot set stock level to " + newQuantity + ". Quantity cannot be negative.");
                return;
            }
            medicine.setQuantity(newQuantity);
            System.out.println("Stock level of " + medicationName + " updated to " + newQuantity + ".");
            // Optionally, remove the medicine if quantity is zero
            if (medicine.getQuantity() == 0) 
            {
                currentStockLevels.remove(medicine);
                System.out.println(medicationName + " has been removed from the inventory as its quantity reached zero.");
            }
        } 
        else 
        {
            System.out.println("Medicine " + medicationName + " not found in the inventory.");
        }
    }

    public void submitReplenishmentRequest(String medicationName, int quantity)
    {
        requestReplenishment(medicationName, quantity);
    }

    public void displayLowStocks()
    {
        List<Medicine> lowStockMedicines = currentStockLevels.stream()
                .filter(medicine -> medicine.getQuantity() < medicine.getLowStockAlertLevel())
                .collect(Collectors.toList());
        
        if (lowStockMedicines.isEmpty()) 
        {
            System.out.println("No medicines are currently low in stock.");
            return;
        }
        
        System.out.println("\n--- Medicines Low in Stock ---");
        for (Medicine medicine : lowStockMedicines)
        {
            System.out.println("Medicine: " + medicine.getName() + 
                               ", Quantity: " + medicine.getQuantity() + 
                               ", Alert Level: " + medicine.getLowStockAlertLevel());
        }
    }

    public int getLowStockAlertLevel(String medicationName)
    {
        Medicine medicine = findMedicineByName(medicationName);
        if (medicine != null) 
        {
            return medicine.getLowStockAlertLevel();
        }
        return -1; // Indicates medicine not found
    }

    public void setLowStockAlertLevel(String medicationName, int lowStockAlertLevel)
    {
        Medicine medicine = findMedicineByName(medicationName);
        if (medicine != null) 
        {
            medicine.setLowStockAlertLevel(lowStockAlertLevel);
            System.out.println("Low stock alert level for " + medicationName + " set to " + lowStockAlertLevel + ".");
        } 
        else 
        {
            System.out.println("Medicine " + medicationName + " not found in the inventory.");
        }
    }

    public boolean approveReplenishmentRequest(int requestId)
    {
        for (Request request : replenishmentRequest)
        {
            if (request.getRequestId() == requestId && request.getStatus() == Status.PENDING)
            {
                request.setStatus(Status.COMPLETED);
                Medicine medicine = request.getMedicine();
                if (medicine != null) 
                {
                    medicine.setQuantity(medicine.getQuantity() + request.getReplenishQuantity());
                    System.out.println("Approved replenishment of " + request.getReplenishQuantity() + " units for " + medicine.getName() + ".");
                }
                return true;
            }
        }
        return false;
    }

    private Medicine findMedicineByName(String name) 
    {
        for (Medicine medicine : currentStockLevels) 
        {
            if (medicine.getName().equalsIgnoreCase(name)) 
            {
                return medicine;
            }
        }
        // not found
        return null;
    }
  
}