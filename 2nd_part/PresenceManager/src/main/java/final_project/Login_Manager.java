package final_project;

import java.util.*;

public class Login_Manager{

    protected static User Actual_User;
    protected static List<User> Sistema = new ArrayList<User>();


    protected boolean login(String username,String passwaord) {
        
        User usuario = acess_garanted(username, passwaord);

        if (usuario!=null) {
            
            Actual_User=usuario;
            System.out.println("Acess Garanted");
            return true;
        }
        return false;

    }

    public String encript(String password)
    {
        //processo de encriptação
        return password;
    }

    protected void registation(List<User> usuarios)
    {
        Sistema=usuarios;
    }

    protected User acess_garanted(String username,String passwaord)
    {

        boolean existe = false;
        for (int j = 0; j < Sistema.size(); j++) {
        
            
            if (Sistema.get(j).getUsername().equals(username)) {
                
                if (Sistema.get(j).getPassword().equals(encript(passwaord))) { 
                    
                    //Esperamos que o SIUE tenha encriptado as passwords :)
                    existe=true;
                    return Sistema.get(j);

                }
                else
                {
                    System.out.println("ERROR: Wrong password");
                    return null;
                }
                
            } 
        }

        if (!existe) {
            //adicionar o utilizador com a respetiva pass fornecida pelo SIUE
            System.out.println("ERROR: Username não existe");
        }
        return null;


    }
}

    

