import com.google.gson.JsonObject;
import io.netty.handler.codec.http.cookie.Cookie;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.*;

public class RestAssuredBasics2 {
    @BeforeTest
    public void setup()
    {
        baseURI = "https://restful-booker.herokuapp.com/booking/22";
    }
    @Test
    public void test()
    {
        JSONObject requestParams = new JSONObject();
        JSONObject requestBookingDates = new JSONObject();

        requestBookingDates.put("checkin", Date.valueOf(LocalDate.now()));
        requestBookingDates.put("checkout", Date.valueOf(LocalDate.now()));
        requestParams.put("firstname", "ankara");
        requestParams.put("lastname", "wyaro");
        requestParams.put("totalprice", 111);
        requestParams.put("depositpaid", true);
        requestParams.put("bookingdates", requestBookingDates);
        requestParams.put("additionalneeds", "breakfast");



        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(requestParams);

        Response response =
                given()
                        .spec(request)
                        .put("");

        response.then()
                .log().ifStatusCodeIsEqualTo(201);

        int responseCode = given()
                        .header("Content-type", "application/json")
                        .when()
                        .delete("")
                        .then()
                        .extract().statusCode();
        System.out.println(responseCode);
    }

    @Test
    public void testTwo() throws ParseException {
        baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Books";

        Response response = given().when().get(baseURI);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        List<String> books = jsonPath.getList("books");
        Integer index = books.size()-1;
        String lastBookIsbn = jsonPath.getString("books["+index+"].isbn");

        Date currentTime = Date.valueOf(LocalDate.now());
        System.out.println(currentTime);
        Assert.assertEquals("9781593277574", lastBookIsbn);

        for(int i = 0; i<books.size()-1; i++)
        {
            String publishTime = jsonPath.getString("books["+i+"].publish_date").replace("-", "/");

            SimpleDateFormat date1=new SimpleDateFormat("dd/MM/yyyy");
            Assert.assertTrue(currentTime.after(date1.parse(publishTime)));
        }

    }
}
