package final_project;
import java.time.*;
import java.time.format.*;

public class Lesson {

    LocalDateTime Date;
    int NºPresence;
    boolean valid;
    
    public Lesson(boolean set_valid)
    {
        Date = LocalDateTime.now();
        valid = set_valid;
        NºPresence=0;
    }

    

    public static void main(String[] args) {
        
    }
}