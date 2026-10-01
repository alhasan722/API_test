package day_02;

import io.restassured.http.ContentType;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    /////02. test with json object
    //////@Test (priority=1)
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

    ///03 test by using POJO class
/*@Test (priority=1)
    void testPostwithPOJO()
    {
        Pojo_postRequest data2= new Pojo_postRequest();
        data2.setName("alo");
        data2.setLocation("France");
        data2.setPhone("1234567");
        String [] coursArr={"C","C++"};
        data2.setCourses(coursArr);

        given()
                .contentType("application/json")
                .body(data2)
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
    }*/

    @Test (priority=1)
    void testPostwithexternalfile() throws FileNotFoundException {
      File f=new File("./body.json");///file capture
        FileReader fr=new FileReader(f);///open the file
      JSONTokener jt=new JSONTokener(fr);///pass the token
      JSONObject data3=new JSONObject(jt);///after token get the json object

        given()
                .contentType("application/json")
                .body(data3.toString())
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
                .delete("http://localhost:3001/students/dGLDaSQkGjw")
                .then()
                .statusCode(200);
    }
}
