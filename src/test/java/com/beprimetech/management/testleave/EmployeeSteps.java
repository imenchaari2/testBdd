package com.beprimetech.management.testleave;

import com.beprimetech.management.testleave.models.Employe;
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
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

    @Given("I can create a new employee")
    public void iCanCreateANewEmployee() {
        System.out.println(url);
//        List allEmployees = restTemplate.getForObject(url, List.class);
//        log.info(allEmployees);
//        assert allEmployees != null;
//        assertFalse(allEmployees.isEmpty());
    }

    //    @Given("I'm sending post to be created with id (.*), firstName (.*), lastName (.*) , email (.*) , phone (.*),cin (.*) ,grade (.*) , gotLeaveDays (.*) and recruitDay (.*)")
//    public void i_sending_post(String id, String firstName, String lastName, String email, String phone, int cin, String grade, int gotLeaveDays, LocalDate recruitDay) {
//
//        Employe newEmployee = new Employe();
//        newEmployee.setId(id);
//        newEmployee.setFirstName(firstName);
//        newEmployee.setLastName(lastName);
//        newEmployee.setEmail(email);
//        newEmployee.setPhone(phone);
//        newEmployee.setCin(cin);
//        newEmployee.setGrade(grade);
//        newEmployee.setGotLeaveDays(gotLeaveDays);
//        newEmployee.setRecruitDay(recruitDay);
//        Employe employee = restTemplate.postForObject(url, newEmployee, Employe.class);
//        assert employee != null;
//        employeeId = employee.getId();
//        log.info(employee);
//        assertNotNull(employee);
    //}
//    @Then ("I receive valid Response")
//    public void verifyPostResponse(){
//        ResponseEntity response = restTemplate.postForEntity(url, newEmployee, String.class);
//        responseBody POST = response.getBody();
//        //Write response to file
//        responseBody = response.getBody().toString();
//        System.out.println("responseBody --->" + responseBody);
//        // Get ID from the Response object
//        employeeId = getEmpIdFromResponse(responseBody);
//        System.out.println("employeeId is :" + employeeId);
//        // Check if the added Employee is present in the response body.
//        Assert.hasText(responseBody,employeeId);
//        // Check if the status code is 201
//        Assert(response.getStatusCode()== HttpStatus.OK);
//        System.out.println("Employee is Added successfully employeeId:"+employeeId); }
    @And("I'm sending post to be created with id <id>, firstName <firstName>, lastName <lastName> and email <email>")
    public void iMSendingPostToBeCreatedWithIdIdFirstNameFirstNameLastNameLastNameAndEmailEmail(String id, String firstName, String lastName, String email) {
        Employe newEmployee = new Employe();
        newEmployee.setId(id);
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        newEmployee.setEmail(email);
    }

    @Then("I should be able to see my newly created employee and the response will return status {int}")
    public void i_should_see_my_newly_created_employee() {
        String url = postUrl + ":" + port + "/api/employee/find/" + employeeId;
        Employe myEmployee = restTemplate.getForObject(url, Employe.class);
        log.info(myEmployee);
        assertNotNull(myEmployee);
//        validatableResponse.assertThat().statusCode(statusCode);

    }


//        String addURI = 'http://dummy.restapiexample.com/api/v1/create';
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "application/json");
//        headers.add("Content-Type", "application/json");
//        String jsonBody = "{'name':'zozo100','salary':'123','age':'23'}";
//        HttpEntity entity = new HttpEntity(jsonBody, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity response =restTemplate.postForEntity(addURI, entity, String.class);
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
//        Assert.assertTrue(responseBody.contains(employeeId));


}


