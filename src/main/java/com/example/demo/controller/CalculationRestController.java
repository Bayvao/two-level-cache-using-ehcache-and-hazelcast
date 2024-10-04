package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.KYCCommQstView;
import com.example.demo.service.NumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/calculate")
public class CalculationRestController {
    private final NumberService calculationService;
    @Autowired
    public CalculationRestController(NumberService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping(path = "/areaOfCircle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> areaOfCircle(@RequestParam int radius) {
        double result = calculationService.areaOfCircle(radius);
        return ResponseEntity.ok(result);
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
    @GetMapping("/cache")
    public ResponseEntity<KYCCommQstView> getCache(@RequestParam int id) {
        KYCCommQstView employee = calculationService.getKYCCommQstView(id);
        return ResponseEntity.ok(employee);
    }
}