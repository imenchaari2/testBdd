package com.beprimetech.management.employeeService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EmployeeDb")
@Builder(toBuilder = true)
public class Employee {
    @Id
    private String id;
    Information information;
    private Boolean isArchived;
}
