import java.util.List;
public class Request
{
    private List<Medicine> medicine;
    private int replenishQuantity;
    private Status status;

    public Status getStatus()
    {
        return status;
    }

    public int getReplenishQuantity()
    {
        return replenishQuantity;
    }

    public List<Medicine> getMedicine()
    {
        return medicine;
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
