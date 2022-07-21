package com.beprimetech.management.testleave.repository;


import com.beprimetech.management.testleave.models.Employe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends MongoRepository<Employe, String> {
    Optional<Employe> findEmployeById(String id);
    /*List<Employe> findEmployeByEmail(String email);*/
//    List<Employee> findEmployeesByIsArchived(boolean isArchived);

}
