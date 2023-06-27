package PracticalExperiments.StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private String state;

    public Employee(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static void main(String[] args) {
        // create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "TX"));
        employees.add(new Employee("Jane", "CA"));
        employees.add(new Employee("Mike", "TX"));
        employees.add(new Employee("Lisa", "NY"));




        // use stream API to filter employees in TX
        List<Employee> txEmployees = employees
                .stream()
                .filter((a->a.getState().equals("TX")))
                .collect(Collectors.toList());


        // print the result
        System.out.println("Employees in TX:");
        txEmployees.forEach(employee -> System.out.println(employee.getName()));
    }
}
