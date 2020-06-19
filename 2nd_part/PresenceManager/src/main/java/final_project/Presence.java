package final_project;

public class Presence {

    Lesson aula;
    int presence_value;
    boolean valid;

    public Presence(Lesson Aula,int Presence_Value)
    {
        aula=Aula;
        presence_value=Presence_Value;
        valid=true;
    }

    public String toString()
    {
        String result="Lesson: "+aula.toString()+", Value: "+presence_value+", Valid: ";
        
        if (valid) {
            result=result+"Yes ";
        }
        else
        {
            result=result+"No ";
        }


        return result;
    }

    public static void main(String[] args) {
        

    }
}