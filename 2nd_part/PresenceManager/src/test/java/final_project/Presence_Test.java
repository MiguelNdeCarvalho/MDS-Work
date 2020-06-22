package final_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class Presence_Test{

    @Mock
    Lesson Aula;
    Lesson tester1;
    Lesson tester2;

    @Test
    public void test_toString() {
        
        
        // Lesson is tested
        when(Aula.toString()).thenReturn("2020-03-24 10:00");

        Presence tester1 = new Presence(Aula, 1, true);
        String must_return = "Lesson: 2020-03-24 10:00, Value: 1, Valid: Yes";

        assertEquals(tester1.toString(),must_return);
    }

    
    @Test
    public void testEqual() {
        // Lesson is tested
        Lesson tester1 = new Lesson("2020-03-24","10:00");
        Lesson tester2 = new Lesson("2020-03-24","10:00");

        boolean save = false;
        if (tester1.equals(tester2)) {
            save=true;
        }

        //assertEquals(tester1, tester2);
        assertTrue(save);
    }
}