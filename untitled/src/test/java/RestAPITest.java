import com.codeborne.selenide.As;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestAPITest {
    @Test(dataProvider = "dataProvider")
    public void circuitTest(Integer index, String country) {
        Response response = RestAssured.get("http://ergast.com/api/f1/2017/circuits.json");
        JsonPath jsonPath = response.jsonPath();
        String circuitId = jsonPath.getString(String.format("MRData.CircuitTable.Circuits[%s].circuitId", index));


        Response response1 = RestAssured.get("http://ergast.com/api/f1/2017/circuits/"+circuitId+".json");
        response1.then().log().all();
        JsonPath jsonPathCountry = response.jsonPath();
        String firstCountry = jsonPathCountry.getString(String.format("MRData.CircuitTable.Circuits[%s].Location.country", index));

        Assert.assertEquals(country, firstCountry);

    }

    @DataProvider(name = "dataProvider")
    public Object[][] getData()
    {
        Object[][] data = {{1, "USA"}, {5, "Hungary"}};
        return data;
    }

}
