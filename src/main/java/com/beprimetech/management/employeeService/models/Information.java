package com.beprimetech.management.employeeService.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Information {
    private int cin;
    private String firstName;
    private String lastName;
    private String grade;
    private String phone;
    private Address address;
    private String email;
    private String password;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate recruitDay;
    private int gotLeaveDays;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate archivedDay;
    private double sold;

}

