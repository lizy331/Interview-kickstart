package PracticalExperiments.Testing;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String getMessage() {
        return "Hello World!";
    }

    public String postMessage(){
        return "Post World!";
    }
}
