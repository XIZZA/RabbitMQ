package baraka.RMQ.dto;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String employee_name;
    private String email;
    private int salary;
    private int age;
}
