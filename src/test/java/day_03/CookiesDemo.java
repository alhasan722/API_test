package day_03;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponseLogSpec;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CookiesDemo {

    @Test(priority = 1)
    void testCookies()
    {
        given()
        .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC","Aaa9EJqVhf27GMrBxuMthkKQEnWeAAyngcgVV1UaEfcTFW_b3OFXtoUnSzw")
                .log().all();

    }
    @Test(priority = 2)
   void getCookiesInfo()
    {
        Response res=given()
                .when()
        .get("https://www.google.com/");
        ///get single cookie info
       /* String cookie_value=res.getCookie("AEC");
        System.out.println(cookie_value);*/
        ///get all cookies
        Map<String,String> cookies_value=res.getCookies();
        for (String K:cookies_value.keySet())
        {
            System.out.println(K+": "+cookies_value);
        }
    }
}
