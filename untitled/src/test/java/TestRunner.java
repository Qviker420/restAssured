import org.example.FindPersonApi;
import org.example.RestXML;
import org.testng.annotations.Test;

public class TestRunner {
    RestXML restXML = new RestXML();
    FindPersonApi findPersonApi = new FindPersonApi();

    @Test
    public void test()
    {
        restXML.getXmlResp()
                .validateNodeS()
                .validateNodeValues()
                .validateSNameNodeWithSCode()
                .validateLastTContinentSName();
    }
    @Test
    public void testFindPerson()
    {
        findPersonApi.sendRequest()
                .validateZip();
    }

}
