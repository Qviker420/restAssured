package org.example.RestAssured5.ApiSteps.Steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.RestAssured5.lombok.BookingData;
import org.example.RestAssured5.lombok.BookingDateDesrilize;
import org.example.RestAssured5.lombok.Dates;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Steps {

    //Runner - test/java/RestAssured5
    String token;
    BookingData bookingData;
    BookingDateDesrilize desBooking;
    Response response;
    @Step("Set Data for Serialization Request")
    public Steps setData() {
        bookingData = BookingData.builder()
                .firstName("duta")
                .lastName("chaligava")
                .additionalneeds("Nan")
                .bookingDates(new Dates("12/1/2222", "31/2/2022"))
                .depositPaid(true)
                .build();
        return this;
    }
    @Step("Get Authorication Token")
    public Steps getToken()
    {
        RequestSpecification request = given()
                .header("Content-type", "application/json")
                .body("{\"username\": \"admin\", \"password\": \"password123\"} ");

        response = given()
                .spec(request)
                .post("https://restful-booker.herokuapp.com/auth");

        token = response.jsonPath().get("token");

        return this;
    }
    @Step("Send Serialized Request")
    public Steps sendRequest()
    {
        response =  given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .header("Cookie", "token=" + token)
                .body(bookingData)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/83");
        response.then().log().all();
        return this;
    }
    @Step("Deserialize Response")
    public Steps deserilize()
    {
        desBooking = response.as(BookingDateDesrilize.class);
        return this;
    }
    @Step("Validate Status Code")
    public Steps validateStatusCode()
    {
        assertThat(response.statusCode(), equalTo(200));
        return this;
    }

}
