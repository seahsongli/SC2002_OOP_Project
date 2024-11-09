public class Medicine
{
    private String name;
    private int quantity;
    private int lowStockAlertLevel;
    Medicine(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
        this.lowStockAlertLevel = 10;
    }

    public String getName()
    {
        return name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getLowStockAlertLevel()
    {
        return lowStockAlertLevel;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public void setLowStockAlertLevel(int lowStockAlertLevel)
    {
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

   

}