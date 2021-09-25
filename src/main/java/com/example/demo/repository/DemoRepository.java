package com.example.demo.repository;

import com.example.demo.model.DepartmentWiseData;
import com.example.demo.model.EmployeeData;

import java.util.List;

public interface DemoRepository {

    List<EmployeeData> getAllEmployeeFromParticularCity(String cityName);

    List<DepartmentWiseData> getCountOfEmployeeDepartmentWise();

    String insertEmployee(EmployeeData employeeData);

    String updateEmployee(EmployeeData employeeData);

}
