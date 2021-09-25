package com.example.demo.model;

public class DepartmentWiseData {
    String department;
    String count;

    public DepartmentWiseData(String department, String count) {
        this.department = department;
        this.count = count;
    }

    public String getDepartment() {
        return department;
    }

    public String getCount() {
        return count;
    }
}
