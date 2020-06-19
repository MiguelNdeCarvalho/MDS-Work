package final_project;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.time.temporal.ChronoUnit;
/*
*
 * Card_Reader
 */
 //System.out.println("Ola"+variable);
 //

public class Card_Reader extends Presence_Manager{

    public static boolean valid_id(String id){
        
        if (id.length()!=3) {
            
            return false;
        }
        
        for(int i=0;i<id.length();i++)
        {
            if ((int) id.charAt(i)<48 || (int) id.charAt(i)>57)
            {
                return false;
            }
        }
    
        return true;
    }

    public static boolean itsTime(List<Lesson> horario,LocalDateTime instant)
    {

        for (Lesson hora : horario) {
            
            LocalDateTime end_instant = instant.plus(1,ChronoUnit.HOURS).plus(30,ChronoUnit.MINUTES);

            boolean is_before = instant.toLocalTime().isBefore(hora..toLocalTime());
            boolean is_after = instant.toLocalTime().isAfter(end_instant.toLocalTime());

            if (!is_after || !is_before) {
                
                return true;
            }

        }

        return false;
    }


    public static void main(String[] args) {
        
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Scanner scan = new Scanner(System.in);
        String student_ID = new String();

        LocalDateTime instant = LocalDateTime.now();
        String time_registed = instant.format(date_format);
        
        //check if its time for a class
        if(itsTime(LESSONS,instant))
        {
            for (int i = 0; i < LESSONS.size(); i++) {
                
                LESSONS.get(i).
            }
            
            while (student_ID.equals("exit")!=true) {
            
                System.out.print("Pass card: ");
                student_ID = scan.next();
    
                instant = LocalDateTime.now();

                if (valid_id(student_ID)) {
    
                    //marcar presença
                    
    
    
                    System.out.println("Number: "+student_ID+" registed at "+time_registed);
                }
            }

        }
        else
        {
            System.out.println("ERROR: At this moment, there is no Lesson");
            //break;
        }


       

    }

}

