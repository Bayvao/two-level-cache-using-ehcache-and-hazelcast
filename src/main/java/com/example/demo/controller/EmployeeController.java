package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService calculationService;
    @Autowired
    public EmployeeController(EmployeeService calculationService) {
        this.calculationService = calculationService;
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return calculationService.getAllEmployee();
    }

    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployee(@RequestParam int id) {
        Employee employee = calculationService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }
}