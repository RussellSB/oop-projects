package nl.rug.oop.demoAssignment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreeterTest {

    private Greeter greeter;

    @Before
    public void setUp() throws Exception {
        this.greeter = new Greeter("Max");
    }

    @Test
    public void testHello() {
        assertEquals("Hello Max!", greeter.sayHello());
    }

    @Test
    public void testGoodBye() {
        assertEquals("See you around, Max.", greeter.sayGoodBye());
    }
}