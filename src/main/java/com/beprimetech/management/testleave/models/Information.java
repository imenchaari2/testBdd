package com.beprimetech.management.testleave.models;

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
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate recruitDay;

}

