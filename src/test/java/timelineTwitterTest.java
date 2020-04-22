import com.sun.codemodel.JVar;
import io.restassured.response.Response;
import jdk.nashorn.internal.ir.CallNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class timelineTwitterTest {

    @DataProvider
    public Object[][] userData(){
        return new Object[][] {
                {"Hello", "Hello"},
                {"n26", "thank you"}

        };
    }

    @Test(dataProvider = "userData")
    public void timelineTwitterTest(String userAccount, String expectedPartOfText){
        //post auth token by using client id and client secret
        
    TwitterConfig twitterConfig = new TwitterConfig();
    TwitterManager twitterApiManager = new TwitterManager(twitterConfig);
    Response response = twitterApiManager.getUserTimeline("Hello", "1");

        //verify text
        String userTwit = response.then().extract().path("text").toString();
        Assert.assertTrue(userTwit.contains("Hello"));
    }

}
 //with the 1st request we are generating Bearer Token using api key,secret key, grant type and request url