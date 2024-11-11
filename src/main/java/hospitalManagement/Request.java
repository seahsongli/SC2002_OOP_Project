package hospitalManagement;

public class Request
{
    static int totalRequests = 0;
    private int requestId = totalRequests++;
    private Medicine medicine;
    private int replenishQuantity;
    private Status status;

    public int getRequestId()
    {
        return requestId;
    }
    
    public Status getStatus()
    {
        return status;
    }

    public int getReplenishQuantity()
    {
        return replenishQuantity;
    }

    public Medicine getMedicine()
    {
        return medicine;
    }

    public void setMedicine(Medicine medicine) 
    {
        this.medicine = medicine;
    }
    public void setStatus(Status status)
    {
        this.status = status;
    }

    public void setReplenishQuantity(int replenishQuantity)
    {
        this.replenishQuantity = replenishQuantity;
    }
}
