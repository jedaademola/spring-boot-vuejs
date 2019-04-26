package com.poc.microservice.service;

import com.poc.microservice.model.Employee;
import com.poc.microservice.util.EmployeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public Employee save(Employee Employee) {

        EmployeeUtil.addEmployee(Employee);
        return Employee;
    }


    public List<Employee> getEmployees() {
        return EmployeeUtil.displayEmployees();
    }

    public Employee getById(long id) {
        Employee singleEmployee = null;
        try {

            singleEmployee = getEmployees().stream()
                    .filter(employee -> employee.getId() == id)
                    .findAny().get();

        } catch (Exception ex) {
            logger.error(" [getById()]: " + ex.getMessage());

        }

        return singleEmployee;
    }

    public Employee editEmployee(Employee employee) {
        EmployeeUtil.editEmployee(employee);
        return employee;

    }

    public static void resetEmployee() {
        EmployeeUtil.resetEmployee();
    }

    public static void deleteEmployee( Employee employeeResponse) {
        EmployeeUtil.deleteEmployee( employeeResponse);
    }

}
