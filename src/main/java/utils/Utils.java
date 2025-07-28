package utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBase.TestBase;

public class Utils extends TestBase {
    public static void checkStatus(Response response, int expectedCode ){
        System.out.println("\n\nStatus Code : "+response.getStatusCode());
        response.then().assertThat().statusCode(expectedCode).contentType(ContentType.JSON);
    }
    public static void report(Response response){
        extentTest.info(response.prettyPrint());
        extentTest.info(("Status Code : "+response.getStatusCode()));
    }
}
