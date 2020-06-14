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

    public static void main(String[] args) {
        

    }
}