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


    public void setLesson(Lesson Aula)
    {
        aula=Aula;
    }

    public Lesson getLesson()
    {
        return aula;
    }


    public void setPresence_value(int value)
    {
        presence_value=value;
    }

    public int getPresence_value()
    {
        return presence_value;
    }

    public void setValid(boolean Valid)
    {
        valid=Valid;
    }

    public boolean getValid()
    {
        return valid;
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


}