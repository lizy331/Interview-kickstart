package JavaInterviewQuestions.Testing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private MyService myService;

    @GetMapping("/greeting")
    public MyCustomResponse greeting() {

        MyCustomResponse myCustomResponse = new MyCustomResponse();
        myCustomResponse.setMessage("Hello World!");
        return myCustomResponse;
    }

    @PostMapping("/posting")
    public  String posting(){
        return myService.postMessage();
    }
}
