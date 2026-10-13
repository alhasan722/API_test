package day_03;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PathAndQueryParameter {

    @Test
    void testQueryAndPathParameter()
    {
        ///////"https://reqres.in/api/users?page=2")
        given()
                .pathParam("mypath","users")
                .queryParam("page",2)
                .when()///pass all methods. request body
                .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
