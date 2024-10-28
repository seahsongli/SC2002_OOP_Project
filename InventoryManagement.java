import java.util.ArrayList;
import java.util.List;
public class InventoryManagement
{
    private List<Medicine> currentStockLevels;
    private List<Request> replenishmentRequest;
    private int lowStockAlertLevel;

    // Constructor with parameter
    public InventoryManagement(int lowStockAlertLevel) {
        this.currentStockLevels = new ArrayList<>();
        this.replenishmentRequest = new ArrayList<>();
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    // Constructor with default value = 5
    public InventoryManagement() {
        this(5); 
    }

    public void viewCurrentStockLevels()
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

    public void requestReplenishment(String name, int quantity)
    {
        Request newRequest = new Request();
        newRequest.setStatus(Status.PENDING);
        newRequest.setReplenishQuantity(quantity);
        for (Medicine medicine : currentStockLevels)
        {
            if (medicine.getName().equals(name))
            {
                newRequest.getMedicine().add(medicine);
            }
        }
        replenishmentRequest.add(newRequest);
    }

    public void updateInventory(String medicationName, int quantity)
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
            if (medicine.getQuantity() < lowStockAlertLevel)
            {
                System.out.println("Medicine: " + medicine.getName() + " Quantity: " + medicine.getQuantity());
            }
        }
    }

    public int getLowStockAlertLevel()
    {
        return lowStockAlertLevel;
    }

    public void setLowStockAlertLevel(int lowStockAlertLevel)
    {
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

  
}