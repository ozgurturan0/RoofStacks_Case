package tests;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import pojos.UpdateUserPojo;
import pojos.UserPojo;
import testBase.TestBaseReport;
import utils.Utils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Test01 extends TestBaseReport {

    @Test
    public void getUserList(){ extentTest=extentReports.createTest("(GET) Kullanıcı listesi");

        Response response = given().accept("application/json").spec(spec01).when().get();
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void getUserWithId(){ extentTest=extentReports.createTest("(GET) ID ile kullanıcı sorgulama");
        //kullanıcı id ile sorgulanır, bilgileri kontrol edilir.

        Response response = given().accept("application/json").spec(spec01).when().get(userId01);
        Utils.checkStatus(response);
        response.prettyPrint();
        response.then().assertThat().
                body("username", equalTo("doejj"),
                        "firstName", equalTo("jane"),
                        "lastName", equalTo("doe"));
        Utils.report(response);
    }
    @Test
    public void getUserWithId_Negative() { extentTest=extentReports.createTest("(GET) ID ile kullanıcı sorgulama, Negatif (geçersiz ID)");
        //Kayıtlı olmayan bir kullanıcı id'si ile istek atılır, hata vermesi beklenir.

        Response response = given().accept("application/json").spec(spec01).when().get("123456-gecersiz-id-123456");
        response.prettyPrint();
        response.then().assertThat().statusCode(404).
                body("error.name", equalTo("mockRequestNotFoundError"),
                        "error.message", equalTo("Double check your method and the request path and try again."),
                        "error.header", equalTo("No matching requests"));
        Utils.report(response);
    }
    @Test
    public void postCreateUser(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma");
        //Yeni kullanıcı oluşturulur.

        UserPojo userData = new UserPojo("test","user", "test01","Password01*" );
        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void postCreateUser_Negative(){ extentTest=extentReports.createTest("(POST) Kullanıcı oluşturma, Negatif (eksik alan ile)");
        //Eksik alanlar ile yeni kullanıcı oluşturma denenir, hata alması beklenir.
        //userData.put("firstname","roof");      fistname alanı RequestBody'ye eklenmedi.
        userData.put("lastname","stacks");
        userData.put("username","roofstacks123456");
        userData.put("password","roofstacks");

        Response response =given().contentType(ContentType.JSON).spec(spec01).body(userData).when().post();
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    /*
        NOT : Yeni kullanıcı oluştururken Required olan firstname/lastname/username alanları olmadığında
              veya kriterlere uymadığında da SUCCESS dönüyor.
    */


    @Test
    public void delRemoveUser(){ extentTest=extentReports.createTest("(DELETE) Kullanıcı silme");
        //Var olan bir kullanıcı silinir.

        Response response = given().contentType(ContentType.JSON).spec(spec01).when().delete(userId02);
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void delRemoveUser_Negative(){ extentTest=extentReports.createTest("(DELETE) Kullanıcı silme, Negatif (geçersiz ID)");
        //Var olmayan bir id ile kullanıcı silme denenir, hata alması beklenir.

        Response response = given().contentType(ContentType.JSON).spec(spec01).when().delete("123456-gecersiz-id-123456");
        response.prettyPrint();
        response.then().assertThat().statusCode(404).
                body("error.name", equalTo("mockRequestNotFoundError"),
                        "error.message", equalTo("Double check your method and the request path and try again."),
                        "error.header", equalTo("No matching requests"));
        Utils.report(response);
    }
    @Test
    public void patchSwitchUserActivity(){ extentTest=extentReports.createTest("(PATCH) Kullanıcı aktiflik durumunu değiştirme");
        //Kullanıcının aktiflik durumu değiştirilir.

        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive",false);
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().patch(userId01+"/activity");
        Utils.checkStatus(response);
        Utils.report(response);
    }
    @Test
    public void patchSwitchUserActivity_Negative(){ extentTest=extentReports.createTest("(PATCH) Kullanıcı aktiflik durumunu değiştirme, Negatif (geçersiz değer ile)");
        //Kullanıcının aktiflik durumunu geçersiz bir değerle değiştirilme denenir, hata alması beklenir.

        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive","false"); // String olarak gönderildi.
        Response response = given().contentType(ContentType.JSON).spec(spec01).when().patch(userId01+"/activity");
        response.then().assertThat().statusCode(400);
        Utils.report(response);
    }
    @Test
    public void putUpdateUser(){ extentTest=extentReports.createTest("(PUT) Kullanıcı bilgilerini güncelleme");
        //Var olan bir kullanıcının bilgileri değiştirilir

        UpdateUserPojo userData = new UpdateUserPojo("roof", "stacks");
        Response response = given().contentType(ContentType.JSON).spec(spec01).body(userData).when().put(userId01);
        Utils.checkStatus(response);
        Utils.report(response);
    }
}
