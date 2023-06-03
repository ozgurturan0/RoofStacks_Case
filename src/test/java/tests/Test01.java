package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UpdateUserPojo;
import pojos.UserPojo;
import testBase.TestBase;
import utils.Utils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Test01 extends TestBase {

    @Test
    public void getUserList(){ extentTest=extentReports.createTest("(GET) Kullanıcı listesi");
        Response response = given().accept("application/json").spec(spec01).when().get();
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void getUserWithId(){ extentTest=extentReports.createTest("(GET) ID ile kullanıcı sorgulama");
        Response response = given().accept("application/json").spec(spec01).when().get(userId01);
        Utils.checkStatus(response);
        response.then().assertThat().
                body("username", equalTo("doejj"),
                        "firstName", equalTo("jane"),
                        "lastName", equalTo("doe"));
        Utils.report(response);
    }
    @Test
    public void getUserWithId_Negative() { extentTest=extentReports.createTest("(GET) ID ile kullanıcı sorgulama, Negatif (geçersiz ID)");
        Response response = given().accept("application/json").spec(spec01).when().get("123456-gecersiz-id-123456");
        response.then().assertThat().statusCode(404).
                body("error.name", equalTo("mockRequestNotFoundError"),
                        "error.message", equalTo("Double check your method and the request path and try again."),
                        "error.header", equalTo("No matching requests"));
        Utils.report(response);
    }
    @Test
    public void postCreateUser(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma");
        UserPojo userData = new UserPojo("test","user", "test01","Password01*" );
        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void postCreateUser_Negative01(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma, Negatif (firstname olmadan)");
        //userData.put("firstname","roof");      fistname alanı RequestBody'ye eklenmedi.
        userData.put("lastname","stacks");
        userData.put("username","roofstacks");
        userData.put("password","roofstacks");

        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    @Test
    public void postCreateUser_Negative02(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma, Negatif (lastname olmadan)");
        userData.put("firstname","roof");
        //userData.put("lastname","stacks");   lastname alanı RequestBody'ye eklenmedi.
        userData.put("username","roofstacks");
        userData.put("password","roofstacks");

        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    @Test
    public void postCreateUser_Negative03(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma, Negatif (username olmadan)");
        userData.put("firstname","roof");
        userData.put("lastname","stacks");
        //userData.put("username","roofstacks");      username alanı RequestBody'ye eklenmedi.
        userData.put("password","roofstacks");

        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    @Test
    public void postCreateUser_Negative04(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma, Negatif (geçersiz username ile)");
        userData.put("firstname","roof");
        userData.put("lastname","stacks");
        userData.put("username","roofstacks123456789"); // karakter sınırı olan 12 aşıldı.
        userData.put("password","roofstacks");

        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    /*
        NOT : Yeni kullanıcı oluştururken Required olan firstname/lastname/username alanları olmadığında
              veya belirtilen kriterlere uymadığında da SUCCESS dönüyor.
    */
    @Test
    public void delRemoveUser(){ extentTest=extentReports.createTest("(DELETE) Kullanıcı silme");
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().delete(userId02);
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void delRemoveUser_Negative(){ extentTest=extentReports.createTest("(DELETE) Kullanıcı silme, Negatif (geçersiz ID)");
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().delete("123456-gecersiz-id-123456");
        response.then().assertThat().statusCode(404).
                body("error.name", equalTo("mockRequestNotFoundError"),
                        "error.message", equalTo("Double check your method and the request path and try again."),
                        "error.header", equalTo("No matching requests"));
        Utils.report(response);
    }
    @Test
    public void patchSwitchUserActivity(){ extentTest=extentReports.createTest("(PATCH) Kullanıcı aktiflik durumunu değiştirme");
        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive",false);
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().patch(userId01+"/activity");
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void patchSwitchUserActivity_Negative01(){ extentTest=extentReports.createTest("(PATCH) Kullanıcı aktiflik durumunu değiştirme, Negatif (geçersiz ID ile)");
        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive",false);
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().patch("123456-gecersiz-id-123456"+"/activity");
        response.then().assertThat().statusCode(404);
        Utils.report(response);
    }
    @Test
    public void patchSwitchUserActivity_Negative02(){ extentTest=extentReports.createTest("(PATCH) Kullanıcı aktiflik durumunu değiştirme, Negatif (geçersiz değer ile)");
        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive","false"); // String olarak gönderildi.
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().patch(userId01+"/activity");
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    @Test
    public void putUpdateUser(){ extentTest=extentReports.createTest("(PUT) Kullanıcı bilgilerini güncelleme");
        UpdateUserPojo userData = new UpdateUserPojo("roof", "stacks");
        Response response = given().contentType(ContentType.JSON).spec(spec01).body(userData).when().put(userId01);
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void putUpdateUser_Negative(){ extentTest=extentReports.createTest("(PUT) Kullanıcı bilgilerini güncelleme, Negatif (geçersiz ID ile)");
        UpdateUserPojo userData = new UpdateUserPojo("roof", "stacks");
        Response response = given().contentType(ContentType.JSON).spec(spec01).body(userData).when().put("123456-gecersiz-id-123456");
        response.then().assertThat().statusCode(404);
        Utils.report(response);
    }
}
