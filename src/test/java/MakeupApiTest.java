import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MakeupApiTest {

    @DataProvider
    public Object[][] userData(){
        return new Object[][] {
                {"blush", "clinique", "20", "Cheek"},
                {"lipstick", "covergirl", "10", "Lipstick"},
                //{"mascara", "chanel", "100", "null"}  will fail cause of non existing values

        };
    }
        /*
          Very trivial request that searches for the particular product_type, brand..etc.
          Gives us the list of products that corresponds given parameters.
        */

    @Test (dataProvider = "userData")
    public void MakeupApiTest(String product, String brand, String priceGreater, String textPart){

        Response filterProductsBy = given()  //the output of the GET call would be stored in the REST Assured ‘Response’ object
                .when()
                .param(product)
                .param(brand)
                .param(priceGreater)
                .param("price_less_than", "100")
                .get("http://makeup-api.herokuapp.com/api/v1/products.json");

        filterProductsBy.then().log().all().statusCode(200);

        //verify if there are some textValue in the name of the product
       String textValue = filterProductsBy.then().extract().path("name").toString();

        Assert.assertTrue(textValue.contains(textPart));

    }
}
