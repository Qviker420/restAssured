package ObjectMapping;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class MainTest {
    UserParams data = new UserParams();
    Serialization serialization = new Serialization();
    NameJobData nameJobData = new NameJobData();
    Deserialization deserialization = new Deserialization();
    @Test
    public void test()
    {
        Response response = RestAssured.given().when()
                .get("https://reqres.in/api/register");

        if(response.statusCode() == 200)
        {
            data.setEmail("eve.holt@reqres.in");
            data.setPass("pistol");
            Response responseSucc = serialization.SendRequest(data, "https://reqres.in/api/register");
            deserialization.deserializeSuccess(responseSucc);
            responseSucc.then().body("token", notNullValue());
            responseSucc.then().body("id", notNullValue());
        }
        else if(response.statusCode() == 400)
        {
            data.setEmail("sydney@fife");
            Response responseFail = serialization.SendRequest(data, "https://reqres.in/api/register");
            responseFail.then().log().all();
            responseFail.then().body("error", notNullValue());
            deserialization.deserializeSuccess(responseFail);
        }
    }
    @Test
    public void test2()
    {
        nameJobData.setName("morpheus");
        nameJobData.setJob("leader");

        Response response = serialization.SendRequest(nameJobData, "https://reqres.in/api/users");
        response.then().log().all();
    }

}
