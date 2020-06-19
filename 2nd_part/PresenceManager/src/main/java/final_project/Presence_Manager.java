package final_project;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

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
    private static List<Lesson> LESSONS;
    //Lista de alunos
    private static List<User> STUDENTS;
    //Lista de profs
    private static List<User> TEACHERS;
    
    //alunos em risco
    private static Warnings WARNINGS;


    public Presence_Manager()
    {
        LESSONS = new ArrayList<Lesson>();
        STUDENTS = new ArrayList<User>();
        TEACHERS = new ArrayList<User>();
        WARNINGS = new Warnings();
            
        import_users(); //import from ue website
        

    }

    private static void parseUserObject(JSONObject user) {
        String name = (String) user.get("nome"); 
        String card = (String) user.get("cartao"); 
        String role = (String) user.get("papel");
          
        System.out.println(name); 
        System.out.println(card); 
        System.out.println(role); 
	}

    public static boolean import_users() 
    {
        //este nao pode afetar o progresso da turma depois da nova sicronização
        //o ficheiro representa a informação que se encontra na base de dados
        
        try {

            FileReader reader = new FileReader("import_alunos.json");
            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            userList.forEach( user -> parseUserObject( (JSONObject) user ) );
             

            /*JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("/tmp/users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;
            System.out.println(userList);
             
            //Iterate over users array
            userList.forEach( user -> parseUserObject( (JSONObject) user ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    
    
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //ciclo while



 

        //
        

        return true;
    }

    public static void student_justify(int ID)
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

    public static boolean student_status(int ID)
    {
        for(int i = 0; i<STUDENTS.size(); i++)
        {
            if (STUDENTS.get(i).getNumber()==ID) {
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
        int student_number;

        while (option!=5) {
            
            System.out.println("------------------------------------------");
            System.out.println("-                                        -");
            System.out.println("- PRESENCE MANAGER v1.0                  -");
            System.out.println("-                                        -");
            System.out.println("- (1) Importar dados dos utilizadores    -");
            System.out.println("- (2) Justificar faltas                  -");
            System.out.println("- (3) Mostrar Relatório de Faltas        -");
            System.out.println("- (4) Consultar faltas por aluno         -");
            System.out.println("- (5) Exit                               -");
            System.out.println("-                                        -");
            System.out.println("------------------------------------------");  //-");
            
            System.out.println("- Insira a opção: ");
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
                
            }
            else if(option==2)
            {
                System.out.println("- Insira o numero do user:");
                student_number=scan.nextInt();
                System.out.println(" -");

                student_justify(student_number);

            }
            else if(option==3)
            {
                get_report();
            }
            else if(option==4)
            {
                System.out.println("- Insira o numero do user:");
                student_number=scan.nextInt();
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

