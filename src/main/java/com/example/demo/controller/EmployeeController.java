package com.example.demo.controller;


import com.example.demo.model.DepartmentWiseData;
import com.example.demo.model.EmployeeData;
import com.example.demo.repository.DemoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    DemoRepositoryImpl demoRepository;


    @RequestMapping(value = "/emp/{cityName}", method = RequestMethod.GET)
    private ResponseEntity<List<EmployeeData>> getMaxEmployeeForParticularCity(@PathVariable String cityName) {
        if (cityName != null && !cityName.isEmpty()) {
            return ResponseEntity.ok().body(demoRepository.getAllEmployeeFromParticularCity(cityName));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/departmentListing", method = RequestMethod.GET)
    private ResponseEntity<List<DepartmentWiseData>> getCountOfEmployeeDepartmentWise() {
        return ResponseEntity.ok().body(demoRepository.getCountOfEmployeeDepartmentWise());
    }

    @RequestMapping(value = "/employeeDetail", method = RequestMethod.POST)
    private ResponseEntity<String> insertEmployee(@RequestBody EmployeeData employeeData) {
        if (employeeData != null) {
            return ResponseEntity.ok().body(demoRepository.insertEmployee(employeeData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter valid data");
        }
    }

    @RequestMapping(value = "/employeeDetailUpdate", method = RequestMethod.PATCH)
    private ResponseEntity<String> updateEmployee(@RequestBody EmployeeData employeeData) {
        if (employeeData != null) {
            return ResponseEntity.ok().body(demoRepository.updateEmployee(employeeData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter valid data");
        }
    }


}
