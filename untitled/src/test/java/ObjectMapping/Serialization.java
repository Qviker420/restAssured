package ObjectMapping;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.notNullValue;

public class Serialization {
    @Step
    public Response SendRequest(Object data, String URL)
    {
        Response response = RestAssured.given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post();
        return response;
    }
}
