package final_project;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class Lesson_Test {

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

    @Test
    public void test1() {
    // create mock
    Lesson test = mock(Lesson.class);
    // define return value for method getUniqueId()
    when(test.getN_Presence()).thenReturn(1);
    // use mock in test....
    assertEquals(test.getN_Presence(), 1);
    }
}