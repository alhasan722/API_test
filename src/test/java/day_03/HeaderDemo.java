package day_03;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponseLogSpec;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class HeaderDemo {

    ///@Test (priority = 1)
    void testHeader()
    {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws")
                .log().all();
    }
@Test(priority = 2)
    void getHeader()
    {
       Response res= given()
                .when()
                .get("https://www.google.com/");
                ///get single header info
        /*String headervale=res.getHeader("Content-Type");
        System.out.println(headervale);*/
        ////get all header
        Headers myheader=res.headers();
        for(Header hd:myheader)
        {
            System.out.println(hd.getName()+":"+hd.getValue());
        }
    }



}
