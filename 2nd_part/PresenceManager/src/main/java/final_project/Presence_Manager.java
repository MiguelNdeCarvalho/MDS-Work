package final_project;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class	
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


    public static void student_justify(String ID)
    {

    }

    public static void get_report()
    {
        for(int i = 0; i<STUDENTS.size(); i++)
        {

            System.out.print(assiduidade_of(STUDENTS.get(i)));
            
        }
    }

    public static float assiduidade_of(User aluno)
    {
        if (LESSONS.size()==0) {
            return 100;
        }

        return aluno.get_Presence().size()/LESSONS.size();
    }

    public static boolean student_status(String ID)
    {
        for(int i = 0; i<STUDENTS.size(); i++)
        {
            if (STUDENTS.get(i).getNumber().equals(ID)) {
                System.out.println(STUDENTS.get(i).toString());
                System.out.print(" "+assiduidade_of(STUDENTS.get(i)));
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int option = 0;
        boolean status;
        String student_number;

        while (option!=5) {
            
            System.out.println("------------------------------------------");
            System.out.println("-                                        -");
            System.out.println("- PRESENCE MANAGER v1.0                  -");
            System.out.println("-                                        -");
            System.out.println("- (1) Importar dados do SIIUE            -");
            System.out.println("- (2) Justificar faltas                  -");
            System.out.println("- (3) Mostrar Relatório de Faltas        -");
            System.out.println("- (4) Consultar faltas por aluno         -");
            System.out.println("- (5) Exit                               -");
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
                System.out.println(" -");

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
                System.out.println(" -");

                student_status(student_number);
            }
            else if(option==5)
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

