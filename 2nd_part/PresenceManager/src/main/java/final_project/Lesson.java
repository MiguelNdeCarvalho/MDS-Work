package final_project;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;


public class Lesson{

    String type;
    private LocalDateTime Date;
    
    int duration_hours;
    int duration_mins;

    private int N_Presence;
    private boolean valid;

    public Lesson(String data,String hora)
    {
        Date = LocalDateTime.parse(data+"T"+hora);
        duration_hours=2;
        duration_mins=0; //default duration is 2 H
        valid = false;
        N_Presence=0;
    }

    public void setDate(LocalDateTime date)
    {
        Date=date;
    }

    public LocalDateTime getDate()
    {
        return Date;
    }

    public void setDuration(int h,int m)
    {
        duration_hours=h;
        duration_mins=m;
    }

    public int getDuration_hours()
    {
        return duration_hours;
    }

    public int getDuration_mins()
    {
        return duration_mins;
    }


    public void setN_Presence(int n_Presence)
    {
        N_Presence=n_Presence;
    }

    public int getN_Presence()
    {
        return N_Presence;
    }


    public void setValid(boolean Valid)
    {
        valid=Valid;
    }

    public boolean getValid()
    {
        return valid;
    }

    public boolean equals(Lesson cmp)
    {
        boolean same_day =this.Date.toLocalDate().toString().equals(cmp.getDate().toLocalDate().toString());
        boolean same_hour = this.Date.toLocalTime().toString().equals(cmp.getDate().toLocalTime().toString());


        if (same_day && same_hour) {
            return true;
        }
        
        return false;
    }
    
    public String toString() {
        
        return Date.toLocalDate().toString()+" "+Date.toLocalTime();
    }

}