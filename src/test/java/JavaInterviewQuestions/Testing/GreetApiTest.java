package JavaInterviewQuestions.Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.NotNull;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class GreetApiTest {


    @Before
    public void init(){
        RestAssured.baseURI = "http://localhost:8080/api";

    }

    @Test
    public void testGreet(){
        Response response = given().contentType("application/json").when().get("/greeting");

        response.then().statusCode(200);

        MyCustomResponse myCustomResponse = response.getBody().as(MyCustomResponse.class);

        assertEquals("Hello World!",myCustomResponse.getMessage());
    }

}
