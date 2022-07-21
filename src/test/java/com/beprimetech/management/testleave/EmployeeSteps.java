package com.beprimetech.management.testleave;

import com.beprimetech.management.testleave.models.Employe;
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

    @ParameterType("\\d{2}\\.\\d{2}\\.\\d{4}")
    public LocalDate mydate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    @Given("I can create a new employee")
    public void i_can_create_a_new_post() {
        String urlFind = postUrl + ":" + port + "/api/employee/all";
        List allEmployee = restTemplate.getForObject(urlFind, List.class);
        log.info(allEmployee);

    }

    @And("^I sending post to be created with post id (.*), firstName (.*), lastName (.*), email (.*), cin (.*), grade (.*), phone (.*), gotLeaveDays (.*) and recruitDay (.*)$")
    public void i_sending_post(String id, String firstName, String lastName , String email , int cin ,  String grade , String phone ,int gotLeaveDays , String recruitDay) {

        Employe newEmployee = new Employe();
        newEmployee.setId(id);
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        newEmployee.setEmail(email);
        newEmployee.setCin(cin);
        newEmployee.setPhone(phone);
        newEmployee.setGrade(grade);
        newEmployee.setGotLeaveDays(gotLeaveDays);
        newEmployee.setRecruitDay(mydate(recruitDay));
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


