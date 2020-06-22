package final_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class User_Test {

    @Test
    public void test_toString() {
        // Lesson is tested
        User tester1 = new User( "joao", "001", "aluno");
        User tester2 = new User( "joao", "001", "aluno");

        boolean result =false;
        if (tester1.equals(tester2)) {
            result=true;
        }

        assertTrue(result);
    }

    @Test
    public void test_isAdmin() {
        // Lesson is tested
        User tester1 = new User( "joao", "001", "aluno");
        User tester2 = new User( "joao", "001", "docente");
        User tester3 = new User( "miguel", "002", "docente");


        boolean result =false;
        if (tester1.getIsAdmin()==false && tester2.getIsAdmin()==true) {
            result=true;
        }

        result =false;
        if (!tester1.equals(tester2)) {
            result=true;
        }

        assertTrue(result);
    }
}