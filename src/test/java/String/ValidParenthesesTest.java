package String;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class ValidParenthesesTest {

    // ({()}) ture
    // {[]()} false
    // {(}) false
    // ){}{} false
    // "" true

    @Test
    public void testForInValid(){
        String inValidString = "(())}";
        assertFalse(ValidParentheses.isValid(inValidString));
    }

    @Test
    public void testForValid(){
        String inValidString = "({()})";
        assertTrue(ValidParentheses.isValid(inValidString));
    }
}
