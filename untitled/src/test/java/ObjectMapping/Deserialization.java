package ObjectMapping;

import io.qameta.allure.Step;
import io.restassured.response.Response;


public class Deserialization {
    @Step
    public SuccessMessage deserializeSuccess(Response response)
    {
        SuccessMessage message = response.as(SuccessMessage.class);
        return message;
    }
    @Step
    public FailMessage deserializeFail(Response response)
    {
        FailMessage message = response.as(FailMessage.class);
        return message;
    }
}
