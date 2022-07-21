package com.beprimetech.management.testleave;

import com.beprimetech.management.testleave.models.Address;
import com.beprimetech.management.testleave.models.Employe;
import com.beprimetech.management.testleave.models.Information;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class EmployeeSteps {
    private final int port = 8085;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String postUrl = "http://localhost";
    String url = postUrl + ":" + port + "/api/employee/add";
    private ValidatableResponse validatableResponse;
    private String employeeId = "";

    @ParameterType("\\d{4}\\.\\d{2}\\.\\d{4}")
    public LocalDate mydate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @ParameterType("(?:[^,]*)(?:,\\s?[^,]*)*")
    public List<String> listOfStrings(String arg){
        return Arrays.asList(arg.split(",\\s?"));
    }

    @Given("I can create a new employee")
    public void i_can_create_a_new_post() {
        String urlFind = postUrl + ":" + port + "/api/employee/all";
        List allEmployee = restTemplate.getForObject(urlFind, List.class);
        log.info(allEmployee);

    }
    @And("^I sending post to be created with post id (.*), firstName (.*), lastName (.*), city (.*), postalCode (.*), street (.*), email (.*), cin (.*), grade (.*), phone (.*), gotLeaveDays (.*), recruitDay (.*), archivedDay (.*), password (.*), enabled (.*) and gotLeaveDaysForCurrentMonth (.*)$")
    public void i_sending_post(String id, String firstName, String lastName, String city, int postalCode, String street, String email , int cin , String grade , String phone , int gotLeaveDays , String recruitDay, String archivedDay, String password, String enabled, String gotLeaveDaysForCurrentMonth) {
        Employe newEmployee = new Employe();
        newEmployee.setId(id);
        newEmployee.setInformation(Information.builder().build());
        newEmployee.getInformation().setFirstName(firstName);
        newEmployee.getInformation().setLastName(lastName);
        newEmployee.getInformation().setAddress(Address.builder().build());
        newEmployee.getInformation().getAddress().setCity(city);
        newEmployee.getInformation().getAddress().setStreet(street);
        newEmployee.getInformation().getAddress().setPostalCode(postalCode);
        newEmployee.getInformation().setEmail(email);
        newEmployee.getInformation().setCin(cin);
        newEmployee.getInformation().setPhone(phone);
        newEmployee.getInformation().setGrade(grade);
        newEmployee.getInformation().setGotLeaveDays(gotLeaveDays);
        newEmployee.getInformation().setRecruitDay(mydate(recruitDay));
        newEmployee.getInformation().setRecruitDay(mydate(archivedDay));
        newEmployee.getInformation().setPassword(password);
        newEmployee.getInformation().setPassword(enabled);
        newEmployee.getInformation().setGotLeaveDaysForCurrentMonth(listOfStrings(gotLeaveDaysForCurrentMonth));
        Employe employe = restTemplate.postForObject(url, newEmployee, Employe.class);
        assert employe != null;
        employeeId = employe.getId();
        log.info(employe);
        assertNotNull(employe);
    }

    @Then("I should be able to see my newly created employee")
    public void i_should_see_my_newly_created_post() {
        String url = postUrl + ":" + port + "/api/employee/find/" + employeeId;
        Employe myPost = restTemplate.getForObject(url, Employe.class);
        log.info(myPost);
        assertNotNull(myPost);
    }



}


