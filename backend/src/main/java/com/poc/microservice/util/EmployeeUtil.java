package com.poc.microservice.util;

import com.poc.microservice.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil {

    private static List<Employee> employeeList = new ArrayList<>();
    private static Long lastId =0L;

    public static List<Employee> displayEmployees() {

        if (employeeList.size() < 1) {

            //for (int k = 1; k <= 2; ++k) {
                lastId =1L;
                Employee newEmployee = new Employee();
                newEmployee.setId(lastId);
                newEmployee.setName( "Joe");
                newEmployee.setCompany("HGC");
                newEmployee.setProject("Test Project");
                newEmployee.setTechnology("Front end");
                newEmployee.setUrl("men.png");
                employeeList.add(newEmployee);

                lastId ++;
                newEmployee = new Employee();
                newEmployee.setId(lastId);
                newEmployee.setName("Lori" );
                newEmployee.setCompany("HGC");
                newEmployee.setProject("Test Project");
                newEmployee.setTechnology("Front and back end");
                newEmployee.setUrl("women.png");
                employeeList.add(newEmployee);

                lastId ++;
                newEmployee = new Employee();
                newEmployee.setId(lastId);
                newEmployee.setName("Lukman");
                newEmployee.setCompany("HGC");
                newEmployee.setProject("Test Project");
                newEmployee.setTechnology("Back end");
                newEmployee.setUrl("men.png");
                employeeList.add(newEmployee);

            //}
        }

        return employeeList;
    }


    public static Employee addEmployee(Employee newEmployee) {
        lastId = lastId + 1;

        newEmployee.setId(lastId);
        employeeList.add(newEmployee);

        return  newEmployee;
    }

    public static void editEmployee(Employee newEmployee) {
        int idTemp = newEmployee.getId().intValue();
        employeeList.set(idTemp - 1, newEmployee);
    }

    public static void resetEmployee() {
        employeeList.clear();
    }

    public static void deleteEmployee( Employee employeeResponse) {
        employeeList.remove(employeeResponse);
    }


}
