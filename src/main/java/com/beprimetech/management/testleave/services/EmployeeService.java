package com.beprimetech.management.testleave.services;

import com.beprimetech.management.testleave.models.Employe;
import com.beprimetech.management.testleave.repository.EmployeeRepository;
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
    public Employe addEmployee(Employe employee) {
        List<Employe> employeeList = empRepository.findEmployeByEmail(employee.getEmail());
        if (employeeList.isEmpty()) {
            return empRepository.save(employee);
        } else return null;

    }

    public List<Employe> findAllEmployees() {
        return Lists.newArrayList();
    }
//
//    public List<Employee> findAllArchivedEmployees() {
//        List<Employee> employees = Lists.newArrayList();
//        employees.addAll(empRepository.findEmployeesByIsArchived(true));
//        return employees;
//    }

    public Employe updateEmployee(Employe employee) {
        Employe employee1 = this.findEmployeeById(employee.getId());
        employee1 = employee;
        return empRepository.save(employee1);
    }

    public Employe findEmployeeById(String id) {
        return empRepository.findEmployeById(id).orElseThrow(
                () -> new UserNotFoundException("user by id" + id + "not found"));
    }

//    public void deleteEmployee(String id) {
//        Employee employee = this.findEmployeeById(id);
//        empRepository.delete(employee);
//    }
//
//    public void archiveEmployee(String id) {
//        Employee employee = this.findEmployeeById(id);
//        employee.setIsArchived(true);
//        empRepository.save(employee);
//    }
//
//    public void restoreEmployee(String id) {
//        Employee employee = this.findEmployeeById(id);
//        employee.setIsArchived(false);
//        empRepository.save(employee);
//    }


}
