package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employeeData")
public class EmployeeData {

    @Id
    public String id;

    public int employee_id;
    public String first_name;
    public String last_name;
    public int age;
    public int salary;
    public String city;
    public String department;

    public EmployeeData(int employee_id, String first_name, String last_name, int age, int salary, String city, String department) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.salary = salary;
        this.city = city;
        this.department = department;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name != null ? first_name : "";
    }

    public String getLast_name() {
        return last_name != null ? last_name : "";
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getCity() {
        return city != null ? city : "";
    }

    public String getDepartment() {
        return department != null ? department : "";
    }
}
