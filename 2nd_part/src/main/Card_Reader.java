import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
/**
 * Card_Reader
 */
 //System.out.println("Ola"+variable);
 //

public class Card_Reader {

    public static boolean valid_id(String id){
        
        if (id.length()!=5) {
            
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


    public static void main(String[] args) {
        
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Scanner scan = new Scanner(System.in);
        String student_ID = new String();

        while (student_ID.equals("exit")!=true) {
            
            LocalDateTime instant = LocalDateTime.now();
            String time_registed = instant.format(date_format);
            
            System.out.print("Pass card: ");
            student_ID = scan.next();

            if (valid_id(student_ID)) {


                //marcar presença
                System.out.println("ID: "+student_ID+" registed at "+time_registed);
            }


        }

    }

}

