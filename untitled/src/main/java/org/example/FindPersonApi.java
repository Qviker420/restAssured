package org.example;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FindPersonApi {
    Response response;
    @Step
    public FindPersonApi sendRequest()
    {
         response = given()
                .contentType("text/xml")
                .body(new File("src/main/java/data.xml"))
                .when()
                .post("/csp/samples/SOAP.Demo.CLS")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return this;
    }
    @Step
    public FindPersonApi validateZip()
    {
        String mainPath = "SOAP-ENV:Envelope.SOAP-ENV:Body.FindPersonResponse.FindPersonResult";
        System.out.println(response.then().extract().path(mainPath+"Home.Zip").toString());
        System.out.println(response.then().extract().path(mainPath+"Office.Zip").toString());
        return this;
    }
}
