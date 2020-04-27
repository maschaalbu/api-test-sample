import com.sun.codemodel.JVar;
import io.restassured.response.Response;
import jdk.nashorn.internal.ir.CallNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class twitterTest {
    @Test
    public void twitterTest(){
        /**post auth token by using client id and client secret
         *
         *  //get user timeline by using token (getTimeline)
         *
         *  //verify text with userTwit
         */
        Response generateBearerToken = given().auth().preemptive().basic("UMV83FX5oCMIKSPOVW5yxkWwO",
                "E1dvO1nrWhPJzGgbph94DHpbOBDDB37m4thiCCCvjIl28YXCoR")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .when()
                .post("https://api.twitter.com/oauth2/token");
        generateBearerToken.then().log().all().statusCode(200);

        String accessToken = generateBearerToken.then().extract().path("access_token");
        System.out.println(accessToken);

        Response getTimeline = given()
                .header("Authorization", "Bearer " + accessToken )
                .when()
                .param("screen_name", "hello")
                .param("count", "1")
                .get("https://api.twitter.com/1.1/statuses/user_timeline.json");

        getTimeline.then().log().all().statusCode(200);

        String userTwit = getTimeline.then().extract().path("text").toString();
        Assert.assertTrue(userTwit.contains("Hello"));



    }

}
//with the 1st request we are generating Bearer Token using api key,secret key, grant type and request url


//get user timeline by using token

        /*Response getTimeline = given()
                .header("Authorization", "Bearer" + accessToken)
                .when()
                .param("screen_name", "hello")
                .param("count", "1")
                .get("https://api.twitter.com/1.1/statuses/user_timeline.json");

        getTimeline.then().log().all().statusCode(200);

        //verify text*/


