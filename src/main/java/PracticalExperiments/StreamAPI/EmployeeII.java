package PracticalExperiments.StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeII {

    private String name;
    private List<String> phoneNumbers;
    private String role;

    public EmployeeII(String name, List<String> phoneNumbers, String role) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getRole() {
        return role;
    }

    public static void main(String[] args) {
        List<EmployeeII> peoples = new ArrayList<>();
        peoples.add(new EmployeeII("John", Arrays.asList("555-1123", "555-3389"), "Engineer"));
        peoples.add(new EmployeeII("Mary", Arrays.asList("555-2243", "555-5264"), "Engineer"));
        peoples.add(new EmployeeII("Steve", Arrays.asList("555-6654", "555-3242"), "Product"));
        peoples.add(new EmployeeII("Mani", Arrays.asList("963-5643"), "Engineer"));
        peoples.add(new EmployeeII("John", Arrays.asList("555-1123", "555-3389"), "Employee"));
        peoples.add(new EmployeeII("Mani", Arrays.asList("540-4356", "450-6336"), "Product"));
        peoples.add(new EmployeeII("Ignazio", Arrays.asList("342-3445", "323-6754"), "Manager"));
        peoples.add(new EmployeeII("Annie", Arrays.asList("333-4523", "123-3242"), "Product"));
        peoples.add(new EmployeeII("Sai", Arrays.asList("923-5643", "923-5643"), "QA Engineer"));

//        Map<String, List<String>> phoneNumbersByRole = peoples.stream()
//                .collect(Collectors.groupingBy(EmployeeII::getRole,
//                        Collectors.flatMapping(e -> e.getPhoneNumbers().stream(), Collectors.toList())));

        Map<String, List<String>> phoneNumbersByRole = peoples.stream()
                .collect(Collectors.groupingBy(EmployeeII::getRole,Collectors.mapping(e->e.getName(),Collectors.toList())));

        System.out.println(phoneNumbersByRole);
    }

    /*
    Here's a step-by-step explanation of what the code does:

    1.  peoples.stream(): This converts the peoples list into a stream of Employee objects.

    2.  .collect(...): This initiates the process of collecting elements from the stream into a result container. It takes a Collector as an argument.

    3.  Collectors.groupingBy(...): This is a collector that groups the elements of the stream based on a classification function. In this case, it groups the employees by their roles. The result is a Map<String, List<Employee>> where the keys are the roles and the values are lists of employees with the same role.

    4.  EmployeeII::getRole: This is a method reference that specifies the classification function. It extracts the role of each employee.

    5.  Collectors.flatMapping(...): This is a downstream collector used in conjunction with groupingBy. It applies a flat mapping function to each element in a group. Here, it takes the phone numbers of each employee and converts them into a stream of phone numbers.

    6.  e -> e.getPhoneNumbers().stream(): This lambda expression represents the flat mapping function. For each employee, it returns a stream of phone numbers by calling getPhoneNumbers() and converting the resulting list into a stream.

    7.  Collectors.toList(): This is another downstream collector that collects the flattened phone numbers into a list.

    8.  The final result is a Map<String, List<String>> named phoneNumbersByRole, where the keys are the roles, and the values are lists of phone numbers associated with each role.
     */
}
