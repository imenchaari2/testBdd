package com.beprimetech.management.testleave.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EmployeeDb")
@Builder(toBuilder = true)
public class Employe {
    @Id
    private String id;
    private String firstName ;
    private String lastName;
    private String cin;
    private String grade;
    private String phone;
    private String email;
    private String gotLeaveDays;
//    @JsonFormat(locale = "MM/DD/YYYY")
    private String recruitDay;
}
