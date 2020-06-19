package final_project;
import java.util.*;

public class Student extends User implements Comparable{

    protected int ID;
    protected String email;
    protected List<Presence> presences;

    public Student(String nome,int id)
    {
        username=nome;
        ID = id;
        presences = new ArrayList<Presence>();
        isAdmin=false;
    }

    public int getID()
    {
        return ID;
    }

    protected void setID(int new_id)
    {
        ID = new_id;
    }

    public String getEmail()
    {
        return email;
    }

    protected void setEmail(String new_email)
    {
        email = new_email;
    }

    public void add_Presence(Presence new_presence)
    {
        presences.add(new_presence);
    }

    public List<Presence> get_Presence()
    {
        return presences;
    }

    public int compareTo(User user)
    {

    }

    public String toString()
    {
        String result="ID: "+ID;
        
        if (presences.size()==0) {
            
            return result+"sem presenças";
        }

        for(int i=0; i<presences.size(); i++)
        {
            result=result+" | "+presences.get(i).toString();
        }

        return result;
    }



}