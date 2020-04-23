import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class potterTest {

    @Test
    public void timelineEventbride() {
        //post auth token by using client key and client secret
        Response generateToken = given().auth().preemptive().basic("5R633HTOCYGJJR6PB6",
                "V46KD5QGARQFXYJNMFQFDFEPCGNIYHXWBS2UFA2ZB6XBNF5Q24")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .when()
                .post("https://www.eventbrite.com/oauth/token");
        //.then().statusCode(200);
        generateToken.then().log().all().statusCode(200);

        String accessToken = generateToken.then().extract().path("access_token");
        System.out.println(accessToken);
    }
}

       /* //get user timeline by using token

        Response getTimeline = given()
                .header("Authorization", "Bearer " + accessToken )
                .when()
                .param("screen_name", "hello")
                .param("count", "1")
                .get("https://api.twitter.com/1.1/statuses/user_timeline.json");

        getTimeline.then().log().all().statusCode(200);

        //verify text
        String userTwit = getTimeline.then().extract().path("text").toString();

        Assert.assertTrue(userTwit.contains("Hello"));



    }

}

//with the 1st request we are generating Bearer Token using api key,secret key, grant type and request url



//get user timeline by using token

        Response getTimeline = given()
                .header("Authorization", "Bearer" + accessToken)
                .when()
                .param("screen_name", "hello")
                .param("count", "1")
                .get("https://api.twitter.com/1.1/statuses/user_timeline.json");

        getTimeline.then().log().all().statusCode(200);

    //verify text

*/
