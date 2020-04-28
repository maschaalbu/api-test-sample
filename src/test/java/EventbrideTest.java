import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 *  String accessToken = "2RP36W7I3GILDUCFJCPP";
 *         Response authorizeMe = given()
 *                 .header("Authorization", "Bearer " + accessToken )
 *                 .when()
 *                 .get("https://www.eventbriteapi.com/v3/events/103069752404/"); // gets information about the event
 *                 (103069752404 - music band, 441236037992 ; 102967653022 - beauty, organization_id": "441236037992";
 *                 "Home film festival" my event - organization_id 283564553800; "Yoga" - event_id: 103816098746)
 *                 1) Gives information about particular event by event_id (eventInfo)
 *                 2) Gives information about my events with status "draft" (getMyEvents)
 *                 3) Gives me the list of my organizations (getOrganizationsList)
 *                 4) Create an event
 *                 5) String name = yogaEventInfo.then().extract().path("id");
 *                 System.out.println(name); - as an experiment it will print me out event id
 *                 String name = yogaEventInfo.then().extract().path("name").toString();
 *                 System.out.println(name); - print out the name of early created event
 *                 6) Gives me information about earlier created Yoga event (yogaEventInfo)
 *                 7) Check if given id is equal to the id of "Yoga" event (checkIfEventIdExist)
 *         
 */

public class EventbrideTest {
    @Test
    public void eventbrideTest(){

        String accessToken = "2RP36W7I3GILDUCFJCPP";

        Response eventInfo = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("https://www.eventbriteapi.com/v3/events/103069752404/");
        eventInfo.then().log().all().statusCode(200);

        Response getMyEvents = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .param("status", "draft")
                .get("https://www.eventbriteapi.com/v3/users/me/events");
        getMyEvents.then().log().all().statusCode(200);

        Response getOrganizationsList = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("https://www.eventbriteapi.com/v3/users/me/organizations/");
        getOrganizationsList.then().log().all().statusCode(200);*/

        Response createEvent = given()
                .header("Authorization", "Bearer " + accessToken)
                .header("Accept", "application/json")
                .when()
                .param("event.name.html", "Yoga with Yoda")
                .param("event.start.timezone", "America/Los_Angeles")
                .param("event.start.utc", "2020-07-04T05:00:00Z")
                .param("event.end.timezone", "America/Los_Angeles")
                .param("event.end.utc", "America/Los_Angeles", "2020-07-07T05:00:00Z")
                .param("event.currency", "USD")
                .post("https://www.eventbriteapi.com/v3/organizations/283564553800/events/");
        createEvent.then().log().all().statusCode(200);

        Response yogaEventInfo = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("https://www.eventbriteapi.com/v3/events/103816098746/");
        yogaEventInfo.then().log().all().statusCode(200);

        String checkIfEventIdExists = yogaEventInfo.then().extract().path("id").toString();
        Assert.assertTrue(checkIfEventIdExists.equals("103816098746"));
    }

}
