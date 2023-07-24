package Json;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapJsonTest {

    public MapJson mapJson;

    @Before
    public void init(){
        mapJson = new MapJson();
    }

    @Test
    public void testParse(){

        String input = "{\"firstName\": \"Allen\", \"lastName\": \"Li\", \"age\": \"30\"}";
        Person person = mapJson.parse(input);
//        System.out.println(input);

        assertEquals("Allen",person.getFirstName());
        assertEquals("Li",person.getLastName());
        assertEquals(30,person.getAge());

    }


}
