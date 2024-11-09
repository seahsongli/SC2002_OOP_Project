import java.util.ArrayList;
import java.util.List;
public class Request
{
    static int totalRequests = 0;
    private int requestId = totalRequests++;
    private List<Medicine> medicine;
    private int replenishQuantity;
    private Status status;

    public Request(){
        this.medicine = new ArrayList<>();
    }

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
