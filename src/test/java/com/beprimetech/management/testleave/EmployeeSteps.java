package com.beprimetech.management.testleave;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.core.IsNot;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class EmployeeSteps {
    private final int port = 8085;
    private Scenario scenario;
    private Response response;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String postUrl = "http://localhost";


    public static ResponseSpecification status200Ok() {
        return new ResponseSpecBuilder()
                .expectContentType("application/json")
                .expectStatusCode(200)
                .build();
    }

    @ParameterType("\\d{2}\\.\\d{2}\\.\\d{4}")
    public LocalDate mydate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

//    @Given("I can create a new employee")
//    public void i_can_create_a_new_post() {
//        String urlFind = postUrl + ":" + port + "/api/employee/all";
//        List allEmployee = restTemplate.getForObject(urlFind, List.class);
//        log.info(allEmployee);
//
//    }
//
//    @And("^I sending post to be created with post id (.*), firstName (.*), lastName (.*), email (.*), cin (.*), grade (.*), phone (.*), gotLeaveDays (.*) and recruitDay (.*)$")
//    public void i_sending_post(String id, String firstName, String lastName, String email, int cin, String grade, String phone, int gotLeaveDays, String recruitDay) {
//
//        Employe newEmployee = new Employe();
//        newEmployee.setId(id);
//        newEmployee.setFirstName(firstName);
//        newEmployee.setLastName(lastName);
//        newEmployee.setEmail(email);
////        newEmployee.setCin(cin);
//        newEmployee.setPhone(phone);
//        newEmployee.setGrade(grade);
////        newEmployee.setGotLeaveDays(gotLeaveDays);
////        newEmployee.setRecruitDay(mydate(recruitDay));
//        Employe employe = restTemplate.postForObject(postUrl + ":" + port + "/api/employee/add", newEmployee, Employe.class);
//        assert employe != null;
//        employeeId = employe.getId();
//        log.info(employe);
//        assertNotNull(employe);
//    }
//
//    @Then("I should be able to see my newly created employee")
//    public void i_should_see_my_newly_created_post() {
//        String url = postUrl + ":" + port + "/api/employee/find/" + employeeId;
//        Employe employee = restTemplate.getForObject(url, Employe.class);
//        log.info(employee);
//        assertNotNull(employee);
//    }

// get step def req spec

    //    @Given("There are employees")
//    public void thereAreEmployees() {
//        requestSpecification = RestAssured.given();
//    }
//
//    @When("I fetch all employees")
//    public void iFetchTheEmployees() {
//        response = requestSpecification.get(postUrl + ":" + port + "/api/employee/all")
//                .then().assertThat().spec(status200Ok()).extract().response();
//    }
//
//    @Then("The employees are listed")
//    public void theEmployeesAreListed() {
//        System.out.println("Response Body is =>  " + response.asString());
//    }

    // get with test assured
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) throws Throwable {
        response = (Response) RestAssuredExtension.GetOps(url);

    }

    @Then("The employees are listed")
    public void theEmployeesAreListed() {
        System.out.println(response.getBody().print());
    }

// post and get by id and delete with test assured
    @Given("^I ensure to Perform POST operation with body as$")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(DataTable table) throws Throwable {
        var data = table.asLists();
        Map<String, Object> informationBody = new HashMap<>();
        Map<String, String> addressBody = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        informationBody.put("firstName", data.get(1).get(1));
        informationBody.put("lastName", data.get(1).get(2));
        informationBody.put("email", data.get(1).get(3));
        informationBody.put("cin", data.get(1).get(4));
        informationBody.put("grade", data.get(1).get(5));
        informationBody.put("phone", data.get(1).get(6));
        informationBody.put("gotLeaveDays", data.get(1).get(7));
        informationBody.put("recruitDay", (data.get(1).get(8)));
        addressBody.put("city",(data.get(1).get(9)));
        addressBody.put("street",(data.get(1).get(10)));
        addressBody.put("postalCode",(data.get(1).get(11)));
        body.put("information", informationBody);
        informationBody.put("address", addressBody);


        //Perform post operation
        RestAssuredExtension.PostOpsWithBody(postUrl + ":" + port + "/api/employee/add", body);
    }
    @Then("I should be able to see my newly created employee to ensure my post operation for id {string}")
    public void i_should_see_my_newly_created_employee(String employeeId) {
        String url = postUrl + ":" + port + "/api/employee/find/"+employeeId;
        RestAssuredExtension.GetOps(url);

    }

    @And("I Perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable table) throws Throwable {
        var data = table.asLists();

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", data.get(1).get(0));

        //Perform Delete operation
        RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);

    }

    @And("I perform GET operation with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) throws Throwable {
        var data = table.asLists();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", data.get(1).get(0));
        response = (Response) RestAssuredExtension.GetWithPathParams(url, pathParams);
    }

    @Then("^I should not see the body with email as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String email) throws Throwable {
        assertThat(response.getBody().jsonPath().get("email"), IsNot.not(email));
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
        informationBody.put("cin", data.get(1).get(4));
        informationBody.put("grade", data.get(1).get(5));
        informationBody.put("phone", data.get(1).get(6));
        informationBody.put("gotLeaveDays", data.get(1).get(7));
        informationBody.put("recruitDay", (data.get(1).get(8)));
        addressBody.put("city",(data.get(1).get(9)));
        addressBody.put("street",(data.get(1).get(10)));
        addressBody.put("postalCode",(data.get(1).get(11)));
        body.put("information", informationBody);
        informationBody.put("address", addressBody);

        //Perform post operation
        RestAssuredExtension.PUTOpsWithBody(url, body);

    }

    @Then("^I should see the body with email as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyWithEmailAs(String email) throws Throwable {
        System.out.println(response.getBody().jsonPath().get("information.phone").equals(email));
        assertThat(response.getBody().jsonPath().get("information.email"), equalTo(email));
    }
}
