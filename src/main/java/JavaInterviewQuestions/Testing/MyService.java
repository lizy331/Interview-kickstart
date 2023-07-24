package JavaInterviewQuestions.Testing;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public MyCustomResponse getMessage() {

        MyCustomResponse myCustomResponse = new MyCustomResponse();
        myCustomResponse.setMessage("Hello World!");
        return myCustomResponse;
    }

    public String postMessage(){
        return "Post World!";
    }
}
