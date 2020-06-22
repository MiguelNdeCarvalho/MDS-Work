package final_project;

public class Presence {

    Lesson aula;
    double presence_value;
    boolean valid;

    public Presence(Lesson Aula,double Presence_Value,boolean Valid)
    {
        aula=Aula;
        presence_value=Presence_Value;
        valid=Valid;
    }


    public void setLesson(Lesson Aula)
    {
        aula=Aula;
    }

    public Lesson getLesson()
    {
        return aula;
    }


    public void setPresence_value(double value)
    {
        presence_value=value;
    }

    public double getPresence_value()
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
            result=result+"Yes";
        }
        else
        {
            result=result+"No";
        }


        return result;
    }


}