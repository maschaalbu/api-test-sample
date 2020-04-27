import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PotterTest {

    @Test
    public void potterTest() {

        String baseURI = "https://www.potterapi.com/v1";
        Response response = given()
                .when()
                .get(baseURI + "/sortingHat");

        response.then().log().all().statusCode(200);

        Response response1 = given()
                .when().param("key", "$2a$10$6BS.t1UmxhoGpvu494xEKu5PIcK6YjvqSrpFDFKmO0j6ubsdi3eti")
                .get(baseURI + "/houses");

        response1.then().log().all().statusCode(200);

        Response getSpell = given()
                .when().param("key", "$2a$10$6BS.t1UmxhoGpvu494xEKu5PIcK6YjvqSrpFDFKmO0j6ubsdi3eti")
                .get(baseURI + "/spells");

        getSpell.then().log().all().statusCode(200);

    }
}