package JavaInterviewQuestions.JsonValidation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
public class Person {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Age must be a positive number")
    private int age;

    // Constructors, getters, and setters (omitted for brevity)


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
