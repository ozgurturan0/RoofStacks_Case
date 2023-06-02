package utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBase.TestBaseReport;

import static io.restassured.RestAssured.given;

public class Utils extends TestBaseReport {
    public static Response get(String url){
        return given().accept("application/json").when().get(url);
    }

    public static void checkStatus(Response response){
        System.out.println("\n\nStatus Code : "+response.getStatusCode()+"\nTime : "+response.getTime()+ " ms");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
    }
    public static void report(Response response){
        extentTest.info(response.prettyPrint());
        extentTest.info(("Status Code : "+response.getStatusCode()));
    }
}
