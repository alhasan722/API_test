

package day01_restAssure;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
/*
given ()
 content type,set cookies, add auth, add param, set header info etc
    when()
    get, post, put, delete
        then()
        validate status code, extract response, extract headers cookies and response body
 */

public class HTTPRequest {
    int id;
    @Test(priority = 1)
    public void getUsers() {
        ////this is get request
        given()////if we do not have anything in given section we can remove it. in that case we can remove (.) from when
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", Matchers.equalTo(2))
                .log().all();
    }

    @Test (priority = 2)
    void createUser()
    {
        HashMap bd= new HashMap();
        bd.put("username", "Bob");
        bd.put("job","teacher");

        id=given()
                ///.contentType(ContentType.JSON)
                .contentType("application/json")
                .body(bd)/////id 84
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                /*.then()
                .statusCode(201)
                .log().all();*/
    }
    @Test(priority = 3,dependsOnMethods ={"createUser"} )
    void updateUser()
    {
        HashMap bd= new HashMap();
        bd.put("username", "Bob");
        bd.put("job","teacher");

                given()
                ///.contentType(ContentType.JSON)
                .contentType("application/json")
                .body(bd)/////id 84


                .when()
                .put("https://reqres.in/api/users/"+id)


                        .then()
                        .statusCode(200)
                .log().all();
    }
    @Test(priority = 4)
    void deleteUser()
    {
       given()
               .when()
               .delete("https://reqres.in/api/users/"+id)
               .then()
               .statusCode(204)
               .log().all();
    }

}
