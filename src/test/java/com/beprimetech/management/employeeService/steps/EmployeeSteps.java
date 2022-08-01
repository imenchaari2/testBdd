package com.beprimetech.management.employeeService.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class EmployeeSteps {
    private final int port = 8085;
    private ResponseEntity<String> response;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String postUrl = "http://localhost";
    private final String uri = postUrl + ":" + port + "/api/employee";
    HttpHeaders headers = new HttpHeaders();

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) throws Throwable {
        response = restTemplate.getForEntity(uri + url, String.class);
    }

    @Then("The employees are listed")
    public void theEmployeesAreListed() {
        log.info(response);
        assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Given("^I ensure to Perform POST operation with body as$")
    public void i_ensure_to_Perform_POST_operation_with_body_as(DataTable dataTable) {
        var data = dataTable.asLists();
        Map<String, Object> informationBody = new HashMap<>();
        Map<String, String> addressBody = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        informationBody.put("firstName", data.get(1).get(1));
        informationBody.put("lastName", data.get(1).get(2));
        informationBody.put("email", data.get(1).get(3));
        informationBody.put("password", data.get(1).get(4));
        informationBody.put("cin", data.get(1).get(5));
        informationBody.put("grade", data.get(1).get(6));
        informationBody.put("phone", data.get(1).get(7));
        informationBody.put("gotLeaveDays", data.get(1).get(8));
        informationBody.put("recruitDay", (data.get(1).get(9)));
        informationBody.put("archivedDay", (data.get(1).get(13)));
        addressBody.put("city", (data.get(1).get(10)));
        addressBody.put("street", (data.get(1).get(11)));
        addressBody.put("postalCode", (data.get(1).get(12)));
        body.put("isArchived", data.get(1).get(14));
        body.put("information", informationBody);
        informationBody.put("address", addressBody);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        response = restTemplate.postForEntity(uri + "/add", request, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Then("I should be able to see my newly created employee to ensure my post operation for id {string}")
    public void i_should_see_my_newly_created_employee(String employeeId) {
        response = restTemplate.getForEntity(uri + "/all", String.class);
        if (Objects.requireNonNull(response.getBody()).contains(employeeId)) {
            response = restTemplate.getForEntity(uri + "/find/" + employeeId, String.class);
            log.info(response);
            assertNotNull(response);
            Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        }

    }

    @And("I Perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable table) throws Throwable {
        var data = table.asLists();
        String entityUrl = uri + url + data.get(1).get(0);
        response = restTemplate.getForEntity(uri + "/all", String.class);
        if (Objects.requireNonNull(response.getBody()).contains(data.get(1).get(0))) {
            restTemplate.delete(entityUrl);
        } else {
            log.info("user by id " + data.get(1).get(0) + " not found ");
        }

    }

    @And("I perform GET operation after DELETE")
    public void iPerformGETOperationAfterDELETE(DataTable table) {
        var data = table.asLists();
        response = restTemplate.getForEntity(uri + "/all", String.class);
        assert (!Objects.requireNonNull(response.getBody()).contains(data.get(1).get(0)));

    }

    @Then("^if the Post operation is done then I should not see the body with email as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithEmailAs(String email, DataTable table) throws Throwable {
        var data = table.asLists();
        if (!Objects.requireNonNull(response.getBody()).contains(data.get(1).get(0)) && (response.getBody()).contains(email)) {
            assert (Objects.requireNonNull(response.getBody()).contains(email));
        } else {
            assert (!Objects.requireNonNull(response.getBody()).contains(email));

        }
    }


    @And("^I Perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable table) throws Throwable {
        var data = table.asLists();
        Map<String, Object> informationBody = new HashMap<>();
        Map<String, String> addressBody = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        informationBody.put("firstName", data.get(1).get(1));
        informationBody.put("lastName", data.get(1).get(2));
        informationBody.put("email", data.get(1).get(3));
        informationBody.put("password", data.get(1).get(4));
        informationBody.put("cin", data.get(1).get(5));
        informationBody.put("grade", data.get(1).get(6));
        informationBody.put("phone", data.get(1).get(7));
        informationBody.put("gotLeaveDays", data.get(1).get(8));
        informationBody.put("recruitDay", (data.get(1).get(9)));
        informationBody.put("archivedDay", (data.get(1).get(13)));
        addressBody.put("city", (data.get(1).get(10)));
        addressBody.put("street", (data.get(1).get(11)));
        addressBody.put("postalCode", (data.get(1).get(12)));
        body.put("isArchived", data.get(1).get(14));
        body.put("information", informationBody);
        informationBody.put("address", addressBody);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        String resourceUrl = uri + url;
        restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, Void.class);
    }

    @And("I perform GET operation after PUT with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) throws Throwable {
        var data = table.asLists();
        String entityUrl = uri + url + data.get(1).get(0);
        response = restTemplate.getForEntity(entityUrl, String.class);
        log.info(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);


    }

    @Then("I should see the body with email as {string} and firstName as {string}")
    public void iShouldSeeTheBodyWithEmailAsAndFirstNameAs(String email, String firstName) {
        assert (Objects.requireNonNull(response.getBody()).contains(email));
        assert (Objects.requireNonNull(response.getBody()).contains(firstName));

    }

    @Given("I ensure to Perform archive operation for {string}")
    public void iEnsureToPerformArchiveOperationFor(String url, DataTable dataTable) {
        var data = dataTable.asLists();
        String entityUrl = uri + url + data.get(1).get(0);
        restTemplate.delete(entityUrl);
    }

    @Then("I should not see the archived employee after GET all operation")
    public void iShouldNotSeeTheArchivedEmployeeAfterGETAllOperation(DataTable dataTable) {
        var data = dataTable.asLists();
        response = restTemplate.getForEntity(uri + "/all", String.class);
        assert (!Objects.requireNonNull(response.getBody()).contains(data.get(1).get(0)));
    }

    @And("I Perform restore operation for {string}")
    public void iPerformRestoreOperationFor(String url, DataTable dataTable) {
        var data = dataTable.asLists();
        String entityUrl = uri + url + data.get(1).get(0);
        restTemplate.delete(entityUrl);
    }

    @And("I should see the restored employee after GET all operation")
    public void iShouldSeeTheRestoredEmployeeAfterGETAllOperation(DataTable dataTable) {
        var data = dataTable.asLists();
        response = restTemplate.getForEntity(uri + "/all", String.class);
        assert (Objects.requireNonNull(response.getBody()).contains(data.get(1).get(0)));

    }
}
