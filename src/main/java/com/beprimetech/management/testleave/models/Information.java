package com.beprimetech.management.testleave.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Information {
    private String firstName ;
    private String lastName;
    private Address address;
    private int cin;
    private String grade;
    private String phone;
    private String email;
    private int gotLeaveDays;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate recruitDay;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate archivedDay;
    private String password;
    private boolean enabled;
//    private List<Integer> gotLeaveDaysForCurrentMonth = Lists.newArrayList();

}
