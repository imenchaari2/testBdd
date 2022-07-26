package com.beprimetech.management.employeeService.repository;


import com.beprimetech.management.employeeService.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findEmployeById(String id);
    /*List<Employe> findEmployeByEmail(String email);*/
    List<Employee> findEmployeesByIsArchived(boolean isArchived);

}
