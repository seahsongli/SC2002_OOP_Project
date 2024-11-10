import java.util.Arrays;
import java.util.List;

public class Roles 
{
    public static final String DOCTOR = "doctor";
    public static final String PHARMACIST = "pharmacist";
    public static final String ADMINISTRATOR = "administrator";

    // List of allowed roles for validation
    public static final List<String> ALLOWED_ROLES = Arrays.asList(DOCTOR, PHARMACIST, ADMINISTRATOR);
}