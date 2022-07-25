package com.beprimetech.management.testleave;

import com.beprimetech.management.testleave.models.Employe;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredExtension {

    public static RequestSpecification Request;

    public RestAssuredExtension() {
        //Arrange
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:8085/api/employee");
        builder.setContentType("application/json");
        builder.addHeader("Cache-Control", "no-cache");
        builder.addHeader("Connection", "keep-alive");
        RequestSpecification requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }


    public static void GetOpsWithPathParameter(String url, Map<String, String> pathParams) {
        //Act
        Request.pathParams(pathParams);
        try {
            Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static ResponseOptions<Response> GetOps(String url) {
        System.out.println("request get ========>" + Request.get(url).getBody().print());
        return Request.get(url);

    }

    public static ResponseOptions<Response> GetOpsWithToken(String url, String token) {
        //Act
        try {
            Request.header(new Header("Authorization", "Bearer " + token));
            return Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> PUTOpsWithBodyAndPathParams(String url, Map<String,String> body, Map<String,String> pathParams) {
        Request.pathParams(pathParams);
        Request.body(body);
        return Request.put(url);
    }
    public static ResponseOptions<Response> PostOpsWithBodyAndPathParams(String url, Map<String, String> pathParams, Map<String, String> body)  {
        Request.pathParams(pathParams);
        Request.body(body);
        return Request.post(url);
    }
    public static ResponseOptions<Response> DeleteOpsWithPathParams(String url,Map<String, String> pathParams)  {
        Request.pathParams(pathParams);
        System.out.println("request delete ========>" + Request.delete(url).getStatusCode());
        return Request.delete(url);

    }
    public static ResponseOptions<Response> GetWithPathParams(String url,Map<String, String> pathParams)  {
        Request.pathParams(pathParams);
        System.out.println("request get ========>" + Request.get(url).getBody().print());
        System.out.println("request get ========>" + Request.get(url).getStatusCode());

        return Request.get(url);
    }

    public static ResponseOptions<Response> GetWithQueryParamsWithToken(String url,Map<String, String> pathParams, String token)  {
        Request.header(new Header("Authorization", "Bearer " + token));
        Request.queryParams(pathParams);
        return Request.get(url);
    }

    public static void PostOpsWithBody(String url, Map<String, String> body)  {
        Request.body(body);
        Request.post(url);
        System.out.println("request post ========>" + Request.post(url).getBody().print());
        System.out.println("request post ========>" + Request.post(url).getStatusCode());

    }




}
