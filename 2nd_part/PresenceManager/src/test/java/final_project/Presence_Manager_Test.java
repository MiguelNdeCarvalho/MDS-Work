package final_project;
import java.time.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Presence_Manager_Test {
    
    // Find the tests here

    @Test
    public void test_assiduidade_of() {

        double n_valid_lessons =0;
        User aluno = new User("Joao", "003", "aluno");

        double result=Presence_Manager.assiduidade_of(aluno, n_valid_lessons);

        boolean save = false;
        if (result==100) {
            save=true;
        }

        assertTrue(save);
    }
    
    
}