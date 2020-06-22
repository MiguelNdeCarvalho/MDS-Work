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

        User aluno = new User("Joao", "003", "aluno");
        Lesson aula1 = new Lesson("2020-03-24","10:00");
        Lesson aula2 = new Lesson("2020-03-25","10:00");

        double n_valid_lessons = 0;
        double result=Presence_Manager.assiduidade_of(aluno, n_valid_lessons);

        boolean save = false;
        if (result==100) {
            save=true;
        }

        aluno.get_Presence().add(new Presence(aula1, 1, true));

        n_valid_lessons = 2;
        result=Presence_Manager.assiduidade_of(aluno, n_valid_lessons);

        save = false;
        if (result==50) {
            save=true;
        }

        assertTrue(save);
    }

    @Test
    public void test_valid_id() {

        User aluno = new User("Joao", "003", "aluno");
        Lesson aula1 = new Lesson("2020-03-24","10:00");
        Lesson aula2 = new Lesson("2020-03-25","10:00");

        double n_valid_lessons = 0;
        double result=Presence_Manager.assiduidade_of(aluno, n_valid_lessons);

        boolean save = false;
        if (result==100) {
            save=true;
        }

        aluno.get_Presence().add(new Presence(aula1, 1, true));

        n_valid_lessons = 2;
        result=Presence_Manager.assiduidade_of(aluno, n_valid_lessons);

        save = false;
        if (result==50) {
            save=true;
        }

        assertTrue(save);
    }
    
    
}