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
    protected static double n_valid_lessons=0;
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


    public static void get_report()
    {
        /*- Listagem de alunos com o número de presenças e respectiva percentagem;
        	- Gráfico de presenças por aula, ao longo do tempo;
    - Lista de alunos com entre 25% e 50% de faltas;
    	- Lista de alunos com mais de 50% de faltas;


*/      System.out.println("NºTotal_Vailid_Lessons: "+n_valid_lessons);

        for(int i = 0; i<STUDENTS.size(); i++)
        {
            System.out.print("Num: "+STUDENTS.get(i).getNumber());
            System.out.print("| NºPresenças: "+STUDENTS.get(i).get_Presence().size());
            System.out.println("| Assiduidade: "+assiduidade_of(STUDENTS.get(i),n_valid_lessons));
        }

        System.out.println();

        for(int i = 0; i<LESSONS.size() ; i++)
        {
            if (LESSONS.get(i).getValid()==true) {
                System.out.print("Lesson: "+LESSONS.get(i).getDate().toLocalDate()+" "+LESSONS.get(i).getDate().toLocalTime());
                System.out.println(" -----> NºPresences: "+LESSONS.get(i).getN_Presence());   
            }
        }

        System.out.println();

        System.out.println(WARNINGS.get_XXV());   
        System.out.println(WARNINGS.get_L());   


    }

    public static double assiduidade_of(User aluno,double n_valid_lessons)
    {
        if (n_valid_lessons==0) {
            
            return 100;
        }

        double n_valid_presences=0;

        for(int i = 0; i<aluno.get_Presence().size(); i++)
        {
            if (aluno.get_Presence().get(i).getValid()==true) {
                n_valid_presences=n_valid_presences+aluno.get_Presence().get(i).getPresence_value();
            }
        }

        return (n_valid_presences/n_valid_lessons)*100;
    }

    public static User student_status(String ID)
    {
        for(int i = 0; i<STUDENTS.size(); i++)
        {
            if (STUDENTS.get(i).getNumber().equals(ID)) {
                System.out.println("Num: "+STUDENTS.get(i).toString());
                return STUDENTS.get(i);
            }
        }
        return null;
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
            
            LocalDateTime end_instant = hora.getDate().plus(hora.getDuration_hours(),ChronoUnit.HOURS).plus(hora.getDuration_mins(),ChronoUnit.MINUTES);

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


    public  static void invalidate_Lesson_for_students(Lesson aula)
    {
        for (int i = 0; i < STUDENTS.size(); i++) {
                        
            for (int j = 0; j < STUDENTS.get(i).get_Presence().size(); j++) {
                                
                if (STUDENTS.get(i).get_Presence().get(j).getLesson().equals(aula) ) {
                    STUDENTS.get(i).get_Presence().get(j).setValid(false);
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
            
            System.out.println("--------------------------------------------------");
            System.out.println("-                                                -");
            System.out.println("- PRESENCE MANAGER v1.0                          -");
            System.out.println("-                                                -");
            System.out.println("- (1) Importar dados do SIIUE                    -");
            System.out.println("- (2) Justificar/injustificar alunos ou aulas    -");
            System.out.println("- (3) Mostrar Relatório de Faltas                -");
            System.out.println("- (4) Consultar faltas por aluno                 -");
            System.out.println("- (5) Leitor de cartões                          -");
            System.out.println("- (6) Exit                                       -");
            System.out.println("-                                                -");
            System.out.println("--------------------------------------------------");  //-");
            
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
                String day,hour,validade;
                double valor;

                int option_j=0;
                while (option_j!=3) {
                    
                    System.out.println("----------------------------------------------------");
                    System.out.println("-                                                  -");
                    System.out.println("- ||Justificar/injustificar alunos ou aulas||      -");
                    System.out.println("-                                                  -");
                    System.out.println("- (1) Alunos                                       -");
                    System.out.println("- (2) Aulas                                        -");
                    System.out.println("- (3) Go Back                                      -");
                    System.out.println("-                                                  -");
                    System.out.println("----------------------------------------------------");

                    System.out.print("- Insira a opção: ");
                    option_j = scan.nextInt();
                
                    if (option_j==1) {
                        
                        if (STUDENTS.size()==0) {
                            
                            System.out.println("- ERROR: Não existem Aulas na BD -");

                        }else{
                            
                            System.out.print("- Insira o numero do aluno: ");
                            student_number=scan.next();
    
                            User actual_student=student_status(student_number);
    
                            if(actual_student==null)
                            {
                                System.out.print("- ERROR: Aluno inexistente na BD -");
                            }
                            else
                            {

                                if (actual_student.get_Presence().size()==0) {
                            
                                    System.out.println("- ERROR: Não existem Aulas na BD -");
                                }
                                else
                                {
                                    System.out.print("- Selecione o dia: ");
                                    day=scan.next();
                                    System.out.print("- Selecione a hora: ");
                                    hour=scan.next();
                                    
                                    boolean existe=false;
                                
                                    for (int i = 0; i < actual_student.get_Presence().size(); i++) {
                                                        
                                        if (actual_student.get_Presence().get(i).getLesson().getDate().toLocalDate().toString().equals(day)){
                                            
                                            if (actual_student.get_Presence().get(i).getLesson().getDate().toLocalTime().toString().equals(hour)){
                                                
                                                if (actual_student.get_Presence().get(i).getLesson().getValid()==true) {
                                                    
                                                    boolean apply;
        
                                                    System.out.print("- Insira a validade (true or false): ");
                                                    validade=scan.next();
                                                    System.out.print("- Insira o valor (0|0.5|1): ");
                                                    valor=scan.nextDouble();
        
                                                    if (validade.equals("true")){
                                                        
                                                        apply=true;
                                                        actual_student.get_Presence().get(i).getLesson().setN_Presence(actual_student.get_Presence().get(i).getLesson().getN_Presence()+1);
                                                        if (valor==1 || valor==0.5 || valor==0) {
                                                        
                                                            actual_student.get_Presence().get(i).setPresence_value(valor);
                                                            actual_student.get_Presence().get(i).setValid(apply);
            
            
                                                        }else{
            
                                                            System.out.print("- ERROR: Wrong input --> (0|0.5|1) -");
            
                                                        }
        
                                                    }
                                                    else if (validade.equals("false")){
                                                        
                                                        apply=false;
                                                        actual_student.get_Presence().get(i).getLesson().setN_Presence(actual_student.get_Presence().get(i).getLesson().getN_Presence()-1);
                                                        if (valor==1 || valor==0.5 || valor==0) {
                                                        
                                                            actual_student.get_Presence().get(i).setPresence_value(valor);
                                                            actual_student.get_Presence().get(i).setValid(apply);
            
            
                                                        }else{
            
                                                            System.out.print("- ERROR: Wrong input --> (0|0.5|1) -");
            
                                                        }
                                                    }
                                                    else
                                                    {
                                                        System.out.print("- ERROR: Wrong input --> (true or false) -");
                                                    }
        
        
                                                }else{
        
                                                    System.out.print("- ERROR: Aula invalida (não lecionada) -");
                                                }
        
                                                existe=true;
                                            }
                                        } 
                        
                                    }
        
                                    if (!existe) {
                                        System.out.println("- ERROR: Aula inexistente para "+actual_student.getNumber() +" -");
                                    }
                                }
    
                            }
                        }

                        


                    }else if(option_j==2){

                        if (LESSONS.size()==0) {
                            
                            System.out.println("- ERROR: Não existem Aulas na BD -");
                        }
                        else
                        {
                        
                            System.out.println(LESSONS);

                            System.out.print("- Selecione o dia: ");
                            day=scan.next();
                            System.out.print("- Selecione a hora: ");
                            hour=scan.next();
    
                            boolean existe=false;
    
                            for(int i = 0; i<LESSONS.size() ; i++)
                            {
                                if (LESSONS.get(i).getDate().toLocalDate().toString().equals(day) && LESSONS.get(i).getDate().toLocalTime().toString().equals(hour)) {
    
                                    System.out.print("- Insira a validade que deja atribuir á aula (true or false): ");
                                    validade=scan.next();
    
                                    if (validade.equals("true")){
                                                    
                                       validate_Lesson_for_students(LESSONS.get(i));
                                       n_valid_lessons++;
    
                                    }
                                    else if (validade.equals("false")){
                                        
                                        invalidate_Lesson_for_students(LESSONS.get(i));
                                        n_valid_lessons--;
    
                                    }
                                    else
                                    {
                                        System.out.print("- ERROR: Wrong input --> (true or false) -");
                                    }
                                    existe=true;
                                }
                            }
    
                            if (!existe) {
                                
                                System.out.println("- ERROR: Aula inexistente na BD -");
    
                            }
                        }

                        

                    }
                    else if(option_j==3){
                        break;
                    }
                    else
                    {
                        System.out.println("- Invalid Option -");

                    }
                
                
                }
                

            }
            else if(option==3)
            {
                get_report();
            }
            else if(option==4)
            {
                System.out.print("- Insira o numero do user:");
                student_number=scan.next();

                if(student_status(student_number)==null)
                {
                    System.out.print("- ERROR: Aluno inexistente na BD -");
                };
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

                            WARNINGS.update(STUDENTS,n_valid_lessons);
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

