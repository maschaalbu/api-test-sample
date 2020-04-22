import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiTest {

    /*public static void main(String[] args) {
        System.out.println("Hello Mascha!!!");
    }*/

    /*@Test
    public void firstTest() {
        System.out.println("Hello Mascha!!!"); //test passes because we are not verifying anything

    }*/

    @Test
    public void currentDateTest(){
        System.out.println("Execution of CurrentDateTest");
        String expectedDate = "21 April 2020";
        String actualDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM YYYY"));

        Assert.assertEquals(actualDate, expectedDate, "Date is wrong");
    }
}
