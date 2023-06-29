package RestAssured5;

import org.example.RestAssured5.ApiSteps.Steps.Steps;
import org.testng.annotations.Test;

public class MainTest {


    Steps steps = new Steps();
    @Test
    public void test()
    {
        steps.setData()
                .getToken()
                .sendRequest()
                .deserilize()
                .validateStatusCode();
    }
}
