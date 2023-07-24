package workbook;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class testIsNumberPalidrome {

    @Test
    public void inValidTest1(){
        assertFalse(IsNumberPalindrome.isPal(123));
    }

    @Test
    public void validTest1(){
        assertFalse(IsNumberPalindrome.isPal(545));
    }

    @Test
    public void validTest2(){
        assertTrue(IsNumberPalindrome.isPal(0));
    }

    @Test
    public void inValidTest2(){
        assertFalse(IsNumberPalindrome.isPal(-1));
    }
}
