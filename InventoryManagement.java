import java.util.ArrayList;
import java.util.List;
public class InventoryManagement
{
    private List<Medicine> currentStockLevels;
    private List<Request> replenishmentRequest;

    // Constructor with parameter
    public InventoryManagement() {
        this.currentStockLevels = new ArrayList<>();
        this.replenishmentRequest = new ArrayList<>();
    }

    public void displayInventory()
    {
        for (Medicine medicine : currentStockLevels)
        {
            System.out.println("Medicine: " + medicine.getName() + " Quantity: " + medicine.getQuantity());
        }
    }

    public void addStock(String name, int quantity)
    {
        Medicine newMedicine = new Medicine(name, quantity);
        currentStockLevels.add(newMedicine);
    }

    public void removeStock(String name, int quantity)
    {
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getName().equals(name))
            {
                medicine.setQuantity(medicine.getQuantity() - quantity);
            }
        }
    }

    public void requestReplenishment(String name, int quantity)
    {
        Request newRequest = new Request();
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getName().equals(name))
            {
                newRequest.getMedicine().add(medicine);
                newRequest.setStatus(Status.PENDING);
                newRequest.setReplenishQuantity(quantity);
                replenishmentRequest.add(newRequest);
            }
        }
        
    }

    public void updateStockLevel(String medicationName, int quantity)
    {
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getName().equals(medicationName))
            {
                medicine.setQuantity(medicine.getQuantity() + quantity);
            }
        }
    }

    public void submitReplenishmentRequest(String medicationName, int quantity)
    {
        requestReplenishment(medicationName, quantity);
    }

    public void displayLowStocks()
    {
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getQuantity() < medicine.getLowStockAlertLevel())
            {
                System.out.println("Medicine: " + medicine.getName() + " Quantity: " + medicine.getQuantity());
            }
        }
    }

    public int getLowStockAlertLevel(String medicationName)
    {
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getName().equals(medicationName))
            {
                return medicine.getLowStockAlertLevel();
            }
        }
        return -1;
    }

    public void setLowStockAlertLevel(String medicationName, int lowStockAlertLevel)
    {
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getName().equals(medicationName))
            {
                medicine.setLowStockAlertLevel(lowStockAlertLevel);
            }
        }
    }

    public void approveReplenishmentRequest(int requestId)
    {
        for (Request request : replenishmentRequest)
        {
            if (request.getRequestId() == requestId && request.getStatus() == Status.PENDING)
            {
                request.setStatus(Status.COMPLETED);
                for (Medicine medicine : request.getMedicine())
                {
                    medicine.setQuantity(medicine.getQuantity() + request.getReplenishQuantity());
                }
            }
        }
    }
  
}