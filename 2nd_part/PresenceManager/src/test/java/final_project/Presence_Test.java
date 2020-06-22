package final_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Presence_Test{

    public void test_toString() {
        
        // Lesson is tested
        Lesson Aula = new mock(Lesson.class);
        

        Presence tester1 = new Presence(Aula, 1, true);
        String must_return = "Lesson: "+tester1.getLesson()+", Value: "+presence_value+", Valid: ";

        assertEquals(tester1.toString(),"2020-03-24 10:00");
    }

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