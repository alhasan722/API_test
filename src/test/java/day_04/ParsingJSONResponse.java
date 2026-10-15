package day_04;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;


public class ParsingJSONResponse {

    @Test(priority = 1)
    void testJSONResponse() {
        ////Apporch 01
        /*given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"));*/
        ////aproach 02
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students");
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.getHeader("Content-Type"), "application/json");
        String placeName = res.jsonPath().getString("[2].location");
        Assert.assertEquals(placeName,"brazil");
    }
    @Test (priority = 2)
    void testJSONResponseBodydata()
    {
        Response res = given()
        .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students");
        /////JSONArray class
        JSONArray jo = new JSONArray(res.asString());
        ////print all titles of book
       /* for (int i=0;i<jo.length();i++)
            {
            System.out.println(jo.getJSONObject(i).getString("location"));
            }*/
        ////search for the location in json
        boolean status = false;
        for (int i=0;i<jo.length();i++)
        {
           String lo=jo.getJSONObject(i).getString("location");
           if (lo.equals("brazil"))
           {
               status=true;
               break;
           }
        }
        Assert.assertTrue(status);
    }
}
