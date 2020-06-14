import java.util.*;

public class Teacher extends User {

    private int ID;
    private String email;
    private List<Presence> presences;

    public void Student(int id, String mail)
    {
        
        ID = id;
        email = mail;
        presences = new ArrayList<Presence>();
        this.setIsAdmin(true);
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

    /*
    public void change_Presence(Presence new_presence)
    {
        presences.add(new_presence);
    }
    */

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