package com.beprimetech.management.testleave.controllers;

import com.beprimetech.management.testleave.models.Employe;
import com.beprimetech.management.testleave.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService empService;

    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }


//    @GetMapping("/all")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//        List<Employee> employees = empService.findAllEmployees();
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }
//
//    @GetMapping("/archived")
//    public ResponseEntity<List<Employee>> getAllArchivedEmployees() {
//        List<Employee> employees = empService.findAllArchivedEmployees();
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employe> getEmployeeById(@PathVariable("id") String id) {
        Employe employee = empService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employe> addEmployee(@RequestBody Employe employee) {
        Employe newEmployee = empService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employe> updateEmployee(@RequestBody Employe employee) {
        Employe updateEmployee = empService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
//        empService.deleteEmployee(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/archive/{id}")
//    public ResponseEntity<?> archiveEmployee(@PathVariable("id") String id) {
//        empService.archiveEmployee(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/restore/{id}")
//    public ResponseEntity<?> restoreEmployee(@PathVariable("id") String id) {
//        empService.restoreEmployee(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
