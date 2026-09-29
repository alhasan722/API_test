package day_02;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*
How many ways we create request body

HashMap

Using org.json

Using POJO (plain Old java Object)

Using external json file
 */
public class diffwaytocreatePostreques {
    ////01 by using hashMap
///@Test (priority=1)
    void testPostwithHashMap()
    {
        HashMap data=new HashMap();
        data.put("name","alo");
        data.put("location","France");
        data.put("phone","1234567");
        String coursArr[]={"C","C++"};
        data.put("courses",coursArr);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3001/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("alo"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("1234567"))
                .body("courses", equalTo(Arrays.asList("C", "C++")))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-type",equalTo("application/json"))
                .log().all();


    }
    /////test with json object
    @Test (priority=1)
    void testPostwithJsonobject()
    {
        JSONObject data1=new JSONObject();
        data1.put("name","alo");
        data1.put("location","France");
        data1.put("phone","1234567");
        String coursArr[]={"C","C++"};
        data1.put("courses",coursArr);

        given()
                .contentType("application/json")
                .body(data1.toString())
                .when()
                .post("http://localhost:3001/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("alo"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("1234567"))
                .body("courses", equalTo(Arrays.asList("C", "C++")))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-type",equalTo("application/json"))
                .log().all();


    }

    @Test(priority = 2)
    void testDelete()
    {
        given()
                .when()
                .delete("http://localhost:3001/students/mDF3M_H5Hkk")
                .then()
                .statusCode(200);
    }
}
