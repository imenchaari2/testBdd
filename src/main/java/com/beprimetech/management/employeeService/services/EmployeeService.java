package com.beprimetech.management.employeeService.services;

import com.beprimetech.management.employeeService.models.Employee;
import com.beprimetech.management.employeeService.repository.EmployeeRepository;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository empRepository;


    public EmployeeService(EmployeeRepository empRepository) {
        this.empRepository = empRepository;
    }

    // employee crud
    public Employee addEmployee(Employee employee) {
        List<Employee> employeeList = empRepository.findEmployeeByInformation_Email(employee.getInformation().getEmail());
        if (employeeList.isEmpty()) {
            return empRepository.save(employee);
        } else return null;

    }

    public List<Employee> findAllEmployees() {
        List<Employee> employees = Lists.newArrayList();
        employees.addAll(empRepository.findEmployeesByIsArchived(false));

        return employees;
    }

    public List<Employee> findAllArchivedEmployees() {
        List<Employee> employees = Lists.newArrayList();
        employees.addAll(empRepository.findEmployeesByIsArchived(true));
        return employees;
    }

    public Employee updateEmployee(Employee employee) {
        Employee employee1 = this.findEmployeeById(employee.getId());
        employee1 = employee;
        if (empRepository.findEmployeeByInformation_Email(employee1.getInformation().getEmail()).size() == 1) {
            return empRepository.save(employee1);
        } else {
            return null;
        }
    }

    public Employee findEmployeeById(String id) {
        return empRepository.findEmployeById(id).orElseThrow(
                () -> new UserNotFoundException("user by id" + id + "not found"));
    }

    public void deleteEmployee(String id) {
        Employee employee = this.findEmployeeById(id);
        empRepository.delete(employee);
    }

    public void archiveEmployee(String id) {
        Employee employee = this.findEmployeeById(id);
        employee.setIsArchived(true);
        empRepository.save(employee);
    }

    public void restoreEmployee(String id) {
        Employee employee = this.findEmployeeById(id);
        employee.setIsArchived(false);
        empRepository.save(employee);
    }


}
