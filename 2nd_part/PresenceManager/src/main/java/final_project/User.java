package final_project;

import java.util.*;

public class User {


    protected String username;
    protected String password;
    protected boolean isAdmin;
    
    protected int number;
    protected String email;
    protected List<Presence> presences;

    public User(String nome,int id,String role)
    {
        username = nome;
        number = id;
        password = null;
        if (role.equals("aluno")){
                
            isAdmin = false;
        
        }else if(role.equals("docente")){

            isAdmin = true;
        }else
        {
            isAdmin = false;
        }
        
    }

    public String getUsername()
    {
        return username;
    }

    protected void setUsername(String new_name)
    {
        username = new_name;
    }

    protected String getPassword()
    {
        return password;
    }

    protected void setPassword(String new_pass)
    {
        password = new_pass;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    protected void setIsAdmin(boolean value)
    {
        isAdmin = value;
    }

    //-----------------------------------------------



    public int getNumber()
    {
        return number;
    }

    protected void setNumber(int new_number)
    {
        number = new_number;
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

    public boolean equals(User user)
    {
        if (this.username.equals(user.getUsername())){ 
            
            return true;
        
        }

        return false;
    }

    public String toString()
    {
        String result="number: "+number;
        
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