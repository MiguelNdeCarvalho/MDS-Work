import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
/**
 * Card_Reader
 */
 //System.out.println("Ola"+variable);
 //
public class Presence_Manager {

    //Lista de aulas 
    private List<Lesson> LESSONS;
    //Lista de alunos
    private List<Student> STUDENTS;
    //Lista de profs
    private List<Teacher> TEACHERS;


    public static boolean import_users()
    {
        //este nao pode aftar o progresso da turma depois da nova sicronização
        return true;
    }

    public static void student_justify(int ID)
    {

    }

    public static void get_report()
    {

    }

    public static void student_status(int ID)
    {

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
            
            option = scan.nextInt();


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

