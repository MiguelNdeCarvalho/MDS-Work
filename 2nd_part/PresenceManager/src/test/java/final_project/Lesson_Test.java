package final_project;
import java.time.*;


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
    public void test_toString() {
        // Lesson is tested
        Lesson tester1 = new Lesson("2020-03-24","10:00");

        assertEquals(tester1.toString(),"2020-03-24 10:00");
    }

    @Test
    public void test_setDuration() {
        // Lesson is tested
        Lesson tester1 = new Lesson("2020-03-24","10:00");
        tester1.setDuration(2, 0);

        LocalDateTime end_instant = new mock(LocalDateTime.class);
        LocalDateTime result_must_be = new mock(LocalDateTime.class);

        when(end_instant).thenReturn(tester1.getDate().plus(tester1.getDuration_hours(),ChronoUnit.HOURS).plus(tester1.getDuration_mins(),ChronoUnit.MINUTES));
        when(result_must_be).thenReturn(LocalDateTime.parse("2020-03-24T12:00"));

        assertEquals(end_instant,result_must_be);
    }

    @Test
    public void test_getN_Presence() {
    // create mock
    Lesson test = mock(Lesson.class);

    when(test.getN_Presence()).thenReturn(1);

    assertEquals(test.getN_Presence(), 1);
    }
}