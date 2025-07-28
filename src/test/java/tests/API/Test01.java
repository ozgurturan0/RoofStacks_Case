package tests.API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.ProfilePojo;
import pojos.UserPojo;
import testBase.TestBase;
import utilities.ConfigReader;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test01 extends TestBase {
    private static RequestSpecification spec;
    private static String token;

    @BeforeClass
    public void setup() {
        // Set base URI and reusable spec
        RestAssured.baseURI = "http://localhost:5000";
        spec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(priority = 1, description = "(POST) User create, negative")
    public void createUserNegative_01() {
        extentTest = extentReports.createTest("(POST) User create, negative \"User already exists\"");

        UserPojo userData = new UserPojo(
                ConfigReader.getProperty("name"),
                ConfigReader.getProperty("email"),
                ConfigReader.getProperty("password")
        );
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec).body(userData)
                .when()
                .post("/api/users");
        Utils.report(response);
        Utils.checkStatus(response, 400);
    }

    @Test(priority = 2, description = "(POST) User login")
    public void loginUser() {
        extentTest = extentReports.createTest("(POST) User login");
        UserPojo userData = new UserPojo(
                "",
                ConfigReader.getProperty("email"),
                ConfigReader.getProperty("password")
        );
        Response response = given()
                .spec(spec)
                .body(userData)
                .when()
                .post("/api/auth");

        response.then().statusCode(200).body("token", notNullValue());
        System.out.println(response.prettyPrint());
        token = response.jsonPath().getString("token");
        System.out.println("Stored Token: " + token);
    }

    @Test(priority = 3, dependsOnMethods = "loginUser", description = "(POST) create profile")
    public void createProfile() {
        extentTest = extentReports.createTest("(POST) create profile");

        String profile = "\"ProfilePojo{\" +\n" +
                "                \"status='\" + Developer + '\\'' +\n" +
                "                \", skills='\" + Java,Selenium,Rest + '\\'' +\n" +
                "                \", company='\" + test + '\\'' +\n" +
                "                \", website='\" + test.com + '\\'' +\n" +
                "                \", location='\" + Earth + '\\'' +\n" +
                "                \", bio='\" + Test profile + '\\'' +\n" +
                "                \", githubUserName='\" + testuser + '\\'' +\n" +
                "                '}'";
        Response response2 = given()
                .spec(spec)
                .header("Authorization", "Bearer " + token)
                .body(profile)
                .when()
                .post("/api/profile");
        response2.then().statusCode(anyOf(equalTo(200), equalTo(400)));
        System.out.println("Profile creation response: " + response2.asPrettyString());
    }

    @Test(priority = 4, dependsOnMethods = "loginUser", description = "(GET) Get current user profile")
    public void getCurrentUserProfile() {
        extentTest = extentReports.createTest("(GET) User profile");
        System.out.println("📦 Using token: " + token);
        Response response = given()
                .baseUri("http://localhost:5000")
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/profile/me");

        response.then()
                .log().all()
                .statusCode(200);

        System.out.println("👤 Profile Response: " + response.asPrettyString());
    }
}
