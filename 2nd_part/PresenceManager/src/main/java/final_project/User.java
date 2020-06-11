import java.util.*;

public class User {


    private String username;
    private String password;
    private boolean isAdmin;

    public void User()
    {
        username = new String("");
        password = new String("");
        isAdmin = false;
    }

    public String getUsername()
    {
        return username;
    }

    private void setUsername(String new_name)
    {
        username = new_name;
    }

    private String getPassword()
    {
        return password;
    }

    private void setPassword(String new_pass)
    {
        password = new_pass;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    private void setIsAdmin(boolean value)
    {
        isAdmin = value;
    }

    public static void main(String[] args) {
        

    }
}