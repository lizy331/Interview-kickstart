import JavaInterviewQuestions.Testing.ApplicationStarter;
import JavaInterviewQuestions.Testing.MyController;
import JavaInterviewQuestions.Testing.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MyController.class)
@ContextConfiguration(classes = ApplicationStarter.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyService myService;

    @Test
    public void greetingTest() throws Exception {
        when(myService.postMessage()).thenReturn("Hello Test!");
        this.mockMvc.perform(post("/posting")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Test!")));
    }

}