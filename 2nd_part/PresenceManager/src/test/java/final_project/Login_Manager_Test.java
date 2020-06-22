package final_project;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

public class Login_Manager_Test {

    @Test
    public void test_login_acess()
    {
        boolean result =false;

        List<User> usuarios = new ArrayList<User>();

        User aluno1 = new User("joao", "003", "aluno");
        User aluno2 = new User("miguel", "002", "docente");

        aluno1.setPassword("ola");
        aluno2.setPassword("1234");

        usuarios.add(aluno1);
        usuarios.add(aluno2);

        Login_Manager sistema = new Login_Manager();
        sistema.registation(usuarios);

        result=sistema.login("joao","ola");
        assertTrue(result);

        result=sistema.login("miguel","ola");
        assertTrue(!result);

    }

    @Test
    public void test_login_denie()
    {
        boolean result =false;

        List<User> usuarios = new ArrayList<User>();

        User aluno1 = new User("joao", "003", "aluno");
        User aluno2 = new User("miguel", "002", "docente");

        aluno1.setPassword("ola");
        aluno2.setPassword("1234");

        usuarios.add(aluno1);
        usuarios.add(aluno2);

        Login_Manager sistema = new Login_Manager();
        sistema.registation(usuarios);

        result=sistema.login("miguel","ola");
        assertTrue(!result);

    }


}