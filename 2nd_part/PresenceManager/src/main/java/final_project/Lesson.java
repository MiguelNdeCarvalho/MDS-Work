package final_project;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;


public class Lesson{

    String type;
    private LocalDateTime Date;
    private LocalTime Duration;
    private int N_Presence;
    private boolean valid;

    public Lesson(String data,String hora)
    {
        Date = LocalDateTime.parse(data+"T"+hora);
        Duration = LocalTime.parse("00:00").plus(2,ChronoUnit.HOURS);
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

    public void setDuration(String time)
    {
        Duration = LocalTime.parse(time);
    }

    public LocalTime getDuration()
    {
        return Duration;
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

        System.out.println("Date: "+this.Date.toLocalDate().toString()+" | "+cmp.getDate().toLocalDate());
        System.out.println("Hour: "+this.Date.toLocalTime().toString()+" | "+cmp.getDate().toLocalTime());




        if (same_day && same_hour) {
            return true;
        }
        
        return false;
    }
    
    public String toString() {
        
        return Date.toLocalDate().toString()+" "+Date.toLocalTime();
    }

}