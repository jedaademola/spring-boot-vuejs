package com.poc.microservice.controller;

import com.poc.microservice.exception.BadRequestException;
import com.poc.microservice.exception.NotFoundException;
import com.poc.microservice.model.Employee;
import com.poc.microservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
@Api("Set of endpoints for Employee operations")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add Employee Details")
    @ResponseStatus(value = HttpStatus.OK)
    public Employee
    saveEmployee (@ApiParam("Employee request")
                     @Valid @RequestBody Employee request) {
        LOGGER.error("in EmployeeController.saveEmployee");
        return employeeService.save(request);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Returns a specific Employee by their identifier. 404 if does not exist.")
    @ResponseStatus(value = HttpStatus.OK)
    public Employee getById(
            @ApiParam("Id of the Employee detail. Cannot be empty.")
            @PathVariable String id) {
        LOGGER.error("in EmployeeController.getById:" + id);
        if (id == null || id.isEmpty()) {
            LOGGER.error("Returning getById with Exception as no result Found");
            throw new BadRequestException("001", "Not found");
        }
        Employee employeeResponse = null;
        employeeResponse = employeeService.getById(Long.parseLong(id));

        if (null == employeeResponse) {
            throw new NotFoundException("002",
                    "Employee Details for Person not found with id: " + id);
        }
        LOGGER.error("in EmployeeController.getById: returning response:" + employeeResponse.toString() );
        return employeeResponse ;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Updates Employee Detail from the system. " +
            "404 if the Employee Details's identifier is not found.")
    @ResponseStatus(value = HttpStatus.OK)
    public Employee updateDetail(
            @ApiParam("Employee Detail to be updated. Cannot be empty.")
            @Valid @RequestBody Employee request) {

        LOGGER.error("in EmployeeController.updateDetail");

        LOGGER.error(
                "in EmployeeController.updateDetail: invoking updateDetail");
        LOGGER.error("in EmployeeController.updateDetail: returning response");
        return employeeService.editEmployee(request);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation("Retrieve all Employee Details")
    public List<Employee> getEmployees() {
        LOGGER.error("in EmployeeController.getEmployees");
        return employeeService.getEmployees();
    }

    @DeleteMapping(path = "/reset",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Resets Employee Detail from the system. " )
    @ResponseStatus(value = HttpStatus.OK)
    public void resetEmployee() {
        LOGGER.error("in EmployeeController.resetEmployee");
        LOGGER.error(
                "in EmployeeController.resetEmployee: invoking resetEmployee");
        LOGGER.error("in EmployeeController.resetEmployee: returning response");
        employeeService.resetEmployee();
    }

    @DeleteMapping(path = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Deletes Employee Detail from the system. " )
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEmployee(@ApiParam("Id of the Employee detail. Cannot be empty.")
                                   @PathVariable String id) {
        LOGGER.error("in EmployeeController.deleteEmployee");
        LOGGER.error("in EmployeeController.deleteEmployee:" + id);
        if (id == null || id.isEmpty()) {
            LOGGER.error("Returning getById with Exception as no result Found");
            throw new BadRequestException("001", "Not found");
        }
        Employee employeeResponse = null;
        employeeResponse = employeeService.getById(Long.parseLong(id));

        if (null == employeeResponse) {
            throw new NotFoundException("002",
                    "Employee Details for Person not found with id: " + id);
        }
        LOGGER.error(
                "in EmployeeController.deleteEmployee: invoking deleteEmployee");
        LOGGER.error("in EmployeeController.deleteEmployee: returning response");
        employeeService.deleteEmployee(employeeResponse);
    }

}
