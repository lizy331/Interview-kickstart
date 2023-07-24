package JavaInterviewQuestions.JsonValidation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ListIterator;

public class JsonValidator {
    public static void main(String[] args) throws Exception {
        String jsonString = "{\"name\": \"John\", \"age\": -30}";

        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(jsonString, Person.class);

        System.out.println(person);

        // Perform validation
        javax.validation.Validator validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
        var violations = validator.validate(person);
        for (var violation : violations) {
            System.out.println(violation.getMessage());
        }


    }


}
