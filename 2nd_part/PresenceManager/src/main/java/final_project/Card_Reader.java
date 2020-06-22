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

    
    public static Lesson itsTime(List<Lesson> horario,LocalDateTime instant)
    {

        for (Lesson hora : horario) {
            
            LocalDateTime end_instant = instant.plus(2,ChronoUnit.HOURS);

            boolean is_before = instant.toLocalTime().isBefore(hora.getDate().toLocalTime());
            boolean is_after = instant.toLocalTime().isAfter(end_instant.toLocalTime());

            if (!is_after || !is_before) {
                
                return hora;
            }

        }

        return null;
    }

    public  static void validate_Lesson(Lesson aula)
    {
        for (int i = 0; i < STUDENTS.size(); i++) {
                        
            for (int j = 0; j < STUDENTS.get(i).get_Presence().size(); j++) {
                                
                if (STUDENTS.get(i).get_Presence().get(j).getLesson().equals(aula) ) {
                    STUDENTS.get(i).get_Presence().get(j).getLesson().setValid(true);
                } 

            }
                    
        }
    }

    


    public static void main(String[] args) {
        
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Scanner scan = new Scanner(System.in);
        String user_ID = new String();

        LocalDateTime instant = LocalDateTime.now();

        
        //check if its time for a class
        Lesson actual_lesson=itsTime(LESSONS,instant);

        if(actual_lesson!=null)
        {
            
            while (true) {
            
                System.out.print("Pass card: ");
                user_ID = scan.next();
    
                instant = LocalDateTime.now();

                if (valid_id(user_ID)) {
    
                    //marcar presença
                    for (int i = 0; i < TEACHERS.size(); i++) {
                        
                        User actual_prof = TEACHERS.get(i);

                        if (user_ID.equals(actual_prof.getNumber())){
                            
                            boolean presente=false; 

                            for (int j = 0; j < actual_prof.get_Presence().size(); j++) {
                                
                                if (actual_prof.get_Presence().get(j).getLesson().equals(actual_lesson)) {
                                    presente=true;
                                } 

                            }

                            if (!presente) {
                                
                                double Presence_Value;
                                boolean on_time = instant.toLocalTime().isBefore(actual_lesson.getDate().toLocalTime().plus(60,ChronoUnit.MINUTES));

                                actual_lesson.setValid(true);



                                Presence new_presence = new Presence(actual_lesson, 1, true);
                                actual_lesson.setN_Presence(actual_lesson.getN_Presence()+1);

                                
                                actual_prof.add_Presence(new_presence);
                                System.out.println("Number: "+user_ID+", valid class");
                            
                            }else{
                                System.out.println("Number: "+user_ID+" already registed");
                            }
                                
                            
                        }
                    }


                    for (int i = 0; i < STUDENTS.size(); i++) {
                        
                        User actual_student = STUDENTS.get(i);

                        if (user_ID.equals(actual_student.getNumber())){
                            
                            boolean presente=false; 

                            for (int j = 0; j < actual_student.get_Presence().size(); j++) {
                                
                                if (actual_student.get_Presence().get(j).getLesson().equals(actual_lesson)) {
                                    presente=true;
                                } 

                            }

                            if (!presente) {
                                
                                double Presence_Value;
                                boolean on_time = instant.toLocalTime().isBefore(actual_lesson.getDate().toLocalTime().plus(60,ChronoUnit.MINUTES));

                                if(on_time)
                                {
                                    Presence_Value=1;
                                }
                                else
                                {
                                    Presence_Value=0.5;
                                }

                                Presence new_presence = new Presence(actual_lesson, Presence_Value, true);
                                actual_lesson.setN_Presence(actual_lesson.getN_Presence()+1);

                                String time_registed = instant.format(date_format);

                                
                                actual_student.add_Presence(new_presence);
                                System.out.println("Number: "+user_ID+" registed at "+time_registed);
                            
                            }else{
                                System.out.println("Number: "+user_ID+" already registed");
                            }
                                
                            
                        }
                    }

    

                }
                else if(user_ID.equals("sair")==true){

                    break;
                }
                else
                {
                    System.out.println("-  Invalid Number   -");
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

