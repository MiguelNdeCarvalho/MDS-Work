import junit.framework.*;

public class Card_Reader_Test {

    @Test
    public void testMultiply() {
     // MyClass is tested
     MyClass tester = new MyClass();
    
     // check if multiply(10,5) returns 50
     assertEquals(50, tester.multiply(10, 5),"10 x 5 must be 50");
    }



    public static void main(String[] args) {
        
    

    }

}