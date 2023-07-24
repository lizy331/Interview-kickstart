package workbook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import javax.validation.constraints.AssertTrue;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertThrows;

public class SecondMaxTest {

    SecondMax secondMax;
    @Before
    public void init(){
        secondMax = new SecondMax();
    }

    @Test
    public void validTest1(){
        try{
            int[] validInput = new int[]{1,2,3,4,5};
            assertEquals(secondMax.secMax(validInput),4);
        }catch (Exception e){

        }
    }

    @Test
    public void validTest2(){
        try{
            int[] validInput = new int[]{1,2};
            assertEquals(secondMax.secMax(validInput),1);
        }catch (Exception e){

        }
    }

    @Test
    public void inValidTest(){
        try{
            int[] inValidInput = new int[]{1};
            assertThrows(IllegalArgumentException.class, () -> this.secondMax.secMax(inValidInput));
//            assertEquals("input length should at least 2",exception.getMessage());
        }catch (IllegalArgumentException e){
            assertEquals("input length should at least 2",e.getMessage());
        }
    }

}
