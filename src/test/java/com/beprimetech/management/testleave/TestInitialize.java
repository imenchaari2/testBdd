package com.beprimetech.management.testleave;


import io.cucumber.java.Before;

public class TestInitialize {

    @Before
    public void TestSetup(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
