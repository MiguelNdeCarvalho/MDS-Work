package final_project;
import java.time.*;
import java.time.format.*;

public class Lesson{

    private LocalDateTime Date;
    private int NºPresence;
    private boolean valid;

    public Lesson()
    {
        Date = LocalDateTime.now();
        valid = true;
        NºPresence=0;
    }

    public void setDate(LocalDateTime date)
    {
        Date=date;
    }

    public LocalDateTime getDate()
    {
        return Date;
    }


    public void setN_Presence(int N_Presence)
    {
        NºPresence=N_Presence;
    }

    public int getN_Presence()
    {
        return NºPresence;
    }


    public void setValid(boolean Valid)
    {
        valid=Valid;
    }

    public boolean getValid()
    {
        return valid;
    }
    

}