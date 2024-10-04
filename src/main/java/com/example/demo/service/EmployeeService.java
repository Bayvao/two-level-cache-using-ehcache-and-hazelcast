package com.example.demo.service;


import com.example.demo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getAllEmployee() {

        for (int i = 1; i <= 100; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setFirstName("Bayvao");
            employee.setLastName("Verma");
            employee.setSalary(20000.00);

            employees.add(employee);
        }
        return employees;
    }

    @Caching(cacheable = {
            @Cacheable(cacheNames = "employeeCache", cacheManager = "ehCacheManager", key = "#id"),
            @Cacheable(cacheNames = "employeeCache", cacheManager = "hazelcastCacheManager", key = "#id")
    })
    public Employee getEmployee(int id) {
        LOG.info("fetching data from service");
        return employees.get(id);
    }
}