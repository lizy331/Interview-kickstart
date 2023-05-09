package PracticalExperiments.Testing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyService myService;

    @GetMapping("/greeting")
    public String greeting() {
        return myService.getMessage();
    }

    @PostMapping("/posting")
    public  String posting(){
        return myService.postMessage();
    }
}
