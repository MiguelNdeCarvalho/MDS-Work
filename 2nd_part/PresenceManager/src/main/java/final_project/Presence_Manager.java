package final_project;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class	
import java.time.temporal.ChronoUnit;

import java.util.Scanner;


import java.io.FileReader;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
/**
 * Card_Reader
 */
 //
public class Presence_Manager {

    //Lista de aulas 
    protected static List<Lesson> LESSONS = new ArrayList<Lesson>();
    protected static int n_valid_lessons=0;
    //Lista de alunos
    protected static List<User> STUDENTS = new ArrayList<User>();
    //Lista de profs
    protected static List<User> TEACHERS = new ArrayList<User>();
    
    //alunos em risco
    protected static Warnings WARNINGS = new Warnings();


    protected static void parseUserObject(JSONObject user) {
        
        String name = (String) user.get("nome"); 
        String card = (String)user.get("cartao"); 
        String role = (String) user.get("papel");
        
        User new_user = new User(name,card, role);
        boolean existe;

        if (role.equals("aluno")) {
            
            if (STUDENTS.size()==0) {
                STUDENTS.add(new_user);
            }
            else
            {

                existe=false;

                for (int i = 0; i < STUDENTS.size(); i++) {
            
                    if (STUDENTS.get(i).equals(new_user)) {       
                        
                        existe=true;
                        
                    }
                }

                if (!existe) {
                
                    STUDENTS.add(new_user);
                }
            }
            
            
        }

        if (role.equals("docente")) {

            if (TEACHERS.size()==0) {
                TEACHERS.add(new_user);
            }
            else
            {

                existe=false;

                for (int i = 0; i < TEACHERS.size(); i++) {
            
                    if (TEACHERS.get(i).equals(new_user)) {       
                        
                        existe=true;
                        
                    }
                }

                if (!existe) {
                
                    TEACHERS.add(new_user);
                }
            }
        }

	}

    public static boolean import_users() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Localização do Ficheiro (users): ");
        String location = scanner.nextLine();
        //este nao pode afetar o progresso da turma depois da nova sicronização
        //o ficheiro representa a informação que se encontra na base de dados
        
        try {

            FileReader reader = new FileReader(location);
            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            userList.forEach( user -> parseUserObject( (JSONObject) user ) );

        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }


    protected static void parseLessonObject(JSONObject user) {
        
        
        String data = (String) user.get("data"); 
        String hora = (String) user.get("hora"); 
        
        Lesson new_lesson = new Lesson(data,hora);

        if (LESSONS.size()==0) {
            
            LESSONS.add(new_lesson);
            n_valid_lessons++;
        }
        else
        {
            boolean existe=false;

            for (int i = 0; i < LESSONS.size(); i++) {
            
                if (LESSONS.get(i).equals(new_lesson)) {
                    
                    existe=true;
                }
            }

            if (!existe) {
                
                LESSONS.add(new_lesson);
                n_valid_lessons++;
            }
        }



       
        

	}

    public static boolean import_lessons() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Localização do Ficheiro (lessons): ");
        String location = scanner.nextLine();
        //este nao pode afetar o progresso da turma depois da nova sicronização
        //o ficheiro representa a informação que se encontra na base de dados
        
        try {

            FileReader reader = new FileReader(location);
            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            userList.forEach( user -> parseLessonObject( (JSONObject) user ) );

        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }


    public static void student_justify(String ID)
    {

    }

    public static void get_report()
    {
        /*- Listagem de alunos com o número de presenças e respectiva percentagem;
        	- Gráfico de presenças por aula, ao longo do tempo;
    - Lista de alunos com entre 25% e 50% de faltas;
    	- Lista de alunos com mais de 50% de faltas;


*/ 

        for(int i = 0; i<STUDENTS.size(); i++)
        {
            System.out.print("Num: "+STUDENTS.get(i).getNumber());
            System.out.print("| NºPresenças: "+STUDENTS.get(i).get_Presence().size());
            System.out.println("| Assiduidade: "+assiduidade_of(STUDENTS.get(i),n_valid_lessons));
        }

        for(int i = 0; i<LESSONS.size() ; i++)
        {
            if (LESSONS.get(i).getValid()==true) {
                System.out.print("Lesson: "+LESSONS.get(i).getDate().toLocalDate()+" "+LESSONS.get(i).getDate().toLocalTime());
                System.out.println(" -----> NºPresences: "+LESSONS.get(i).getN_Presence());   
            }
        }

        System.out.println(WARNINGS.get_XXV());   
        System.out.println(WARNINGS.get_L());   


    }

    public static float assiduidade_of(User aluno,int n_valid_lessons)
    {
        if (n_valid_lessons==0) {
            
            return 100;
        }

        return (aluno.get_Presence().size()/n_valid_lessons)*100;
    }

    public static boolean student_status(String ID)
    {
        for(int i = 0; i<STUDENTS.size(); i++)
        {
            if (STUDENTS.get(i).getNumber().equals(ID)) {
                System.out.println(STUDENTS.get(i).toString());
                System.out.print(" "+assiduidade_of(STUDENTS.get(i),n_valid_lessons));
                return true;
            }
        }

        return false;
    }

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
            
            LocalDateTime end_instant = hora.getDate().plus(2,ChronoUnit.HOURS);

            boolean is_before = instant.isBefore(hora.getDate());
            boolean is_after = instant.isAfter(end_instant);



            if (!is_after && !is_before) {
                
                return hora;
            }

        }

        return null;
    }

    public  static void validate_Lesson_for_students(Lesson aula)
    {
        for (int i = 0; i < STUDENTS.size(); i++) {
                        
            for (int j = 0; j < STUDENTS.get(i).get_Presence().size(); j++) {
                                
                if (STUDENTS.get(i).get_Presence().get(j).getLesson().equals(aula) ) {
                    STUDENTS.get(i).get_Presence().get(j).setValid(true);
                } 

            }
                    
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int option = 0;
        boolean status;
        String student_number;

        while (option!=6) {
            
            System.out.println("------------------------------------------");
            System.out.println("-                                        -");
            System.out.println("- PRESENCE MANAGER v1.0                  -");
            System.out.println("-                                        -");
            System.out.println("- (1) Importar dados do SIIUE            -");
            System.out.println("- (2) Justificar faltas                  -");
            System.out.println("- (3) Mostrar Relatório de Faltas        -");
            System.out.println("- (4) Consultar faltas por aluno         -");
            System.out.println("- (5) Leitor de cartões                  -");
            System.out.println("- (6) Exit                               -");
            System.out.println("-                                        -");
            System.out.println("------------------------------------------");  //-");
            
            System.out.print("- Insira a opção: ");
            option = scan.nextInt();
            System.out.println(" -");

            if (option==1) {
                
                
                status=import_users();
                if(status==true)
                {
                    System.out.println("- Sucesso na importação dos utilizadores -");
                
                }else
                {
                    System.out.println("- Falha na importação dos utilizadores -");
                }

                status=import_lessons();
                if(status==true)
                {
                    System.out.println("- Sucesso na importação dos horarios -");
                
                }else
                {
                    System.out.println("- Falha na importação dos horarios -");
                }

                
            }
            else if(option==2)
            {
                System.out.print("- Insira o numero do user:");
                student_number=scan.next();

                student_justify(student_number);

            }
            else if(option==3)
            {
                get_report();
            }
            else if(option==4)
            {
                System.out.print("- Insira o numero do user:");
                student_number=scan.next();

                student_status(student_number);
            }
            else if(option==5)
            {
                DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                String user_ID = new String();

                LocalDateTime instant = LocalDateTime.now();

                
                //check if its time for a class
                Lesson actual_lesson=itsTime(LESSONS,instant);
                //System.out.print(actual_lesson.getDate().toLocalDate());


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


                                        validate_Lesson_for_students(actual_lesson);
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

                                        System.out.println("lesson time:"+instant.toLocalTime());
                                        System.out.println("half lesson:"+actual_lesson.getDate().toLocalTime().plus(60,ChronoUnit.MINUTES));

                                        if(on_time)
                                        {
                                            Presence_Value=1;
                                        }
                                        else
                                        {
                                            Presence_Value=0.5;
                                        }

                                        Presence new_presence = new Presence(actual_lesson, Presence_Value, actual_lesson.getValid());
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
                        else if(user_ID.equals("exit")==true){

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
            else if(option==6)
            {
                break;
            }
            else 
            {
                System.out.println("- Invalid Option -");
            }
        }

    }

}

