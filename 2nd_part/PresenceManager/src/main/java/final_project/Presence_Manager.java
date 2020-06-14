import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
/**
 * Card_Reader
 */
 //
public class Presence_Manager {

    //Lista de aulas 
    private static List<Lesson> LESSONS;
    //Lista de alunos
    private static List<Student> STUDENTS;
    //Lista de profs
    private static List<Teacher> TEACHERS;

    public void Presence_Manager()
    {
        LESSONS = new ArrayList<Lesson>();
        STUDENTS = new ArrayList<Student>();
        TEACHERS = new ArrayList<Teacher>();

        try {
            
            import_users(); //import from ue website
        
        } catch (IOException e) {
            
            System.out.println("An error occurred.");
        }
    }

    public static boolean import_users() throws IOException
    {
        //este nao pode afetar o progresso da turma depois da nova sicronização
        //o ficheiro representa a informação que se encontra na base de dados
        try {
            
            Path BD = Paths.get("import_alunos"); //reprsenta uma ligação á BD, se nao tiver sucesso, return false
            Scanner scan = new Scanner(BD);

            String user;

            while (scan.hasNextLine()) {
                user=scan.nextLine();
                System.out.println(user);
            }

        } catch (IOException e) {
            
            System.out.println("An error occurred.");
            return false;
        }



        return true;
    }

    public static void student_justify(int ID)
    {

    }

    public static void get_report()
    {

    }

    public static boolean student_status(int ID)
    {
        for(int i = 0; i<STUDENTS.size(); i++)
        {
            if (STUDENTS.get(i).getID()==ID) {
                System.out.println(STUDENTS.get(i).toString());
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        
        // (1) Importar dados dos utilizadores
        // (2) Justificar faltas
        // (3) Mostrar Relatório de Faltas
        // (4) Consultar faltas por aluno
        // (5) Exit

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
                
                try {
            
                    status=import_users();
                
                } catch (IOException e) {
                    
                    System.out.println("No Connection to BD");
                }
                
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

