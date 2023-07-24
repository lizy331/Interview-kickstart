package Json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapJson {

    public Person parse(String str){

        Person person = null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            person = objectMapper.readValue(str, Person.class);
            System.out.println(person);

        }catch(Exception e){
            e.printStackTrace();
        }

        return person;

    }

    public static void main(String[] args) {
        String input = "{\"firsName\": \"Allen\", \"lastName\": \"Li\", \"age\": \"30\"}";
        MapJson mapJson = new MapJson();

//        System.out.println(mapJson.parse(input));
    }
}
