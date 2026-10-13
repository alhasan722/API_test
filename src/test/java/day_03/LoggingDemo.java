package day_03;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class LoggingDemo {
    @Test (priority = 1)
    void testLogs()
    {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .log().body();

    }
}
