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
    }
    

    public static void main(String[] args) {
        
    }

}