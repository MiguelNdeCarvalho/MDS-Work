import java.time.*;
import java.time.format.*;

public class Lesson {

    LocalDateTime Date;
    boolean valid;
    
    public void Lesson(boolean set_valid)
    {
        Date = LocalDateTime.now();
        valid = set_valid;
    }

    

    public static void main(String[] args) {
        
    }
}