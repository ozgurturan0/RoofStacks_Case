package tests;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import pojos.UpdateUserPojo;
import pojos.UserPojo;
import testBase.RoofBase;
import utils.Utils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Test01 extends RoofBase {

    @Test
    public void getUserList(){
        //Tüm kullanıcılar sorgulanır.

        Response response = given().accept("application/json").when().get(baseUrl+"/users");
        Utils.checkStatus(response);
        response.prettyPrint();
    }
    @Test
    public void getUserWithId(){
        //kullanıcı id ile sorgulanır, bilgileri kontrol edilir.

        Response response = given().accept("application/json").when().get(baseUrl+"/users/"+userId01);
        Utils.checkStatus(response);
        response.prettyPrint();
        response.then().assertThat().
                body("username", equalTo("doejj"),
                        "firstName", equalTo("jane"),
                        "lastName", equalTo("doe"));
    }
    @Test
    public void getUserWithId_Negative() {
        //Kayıtlı olmayan bir kullanıcı id'si ile istek atılır, hata vermesi beklenir.

        Response response = given().accept("application/json").when().get(baseUrl + "/users/123456-gecersiz-id-123456");
        response.prettyPrint();
        response.then().assertThat().statusCode(404).
                body("error.name", equalTo("mockRequestNotFoundError"),
                        "error.message", equalTo("Double check your method and the request path and try again."),
                        "error.header", equalTo("No matching requests"));
    }
    @Test
    public void postCreateUser(){
        //Yeni kullanıcı oluşturulur.

        UserPojo userData = new UserPojo("test","user", "test01","Password01*" );
        Response response =given().contentType(ContentType.JSON).body(userData).when().post(baseUrl+"/users");
        Utils.checkStatus(response);
        response.prettyPrint();
    }
    @Test
    public void postCreateUser_Negative(){
        //Eksik alanlar ile yeni kullanıcı oluşturma denenir, hata alması beklenir.

        Map<String,String> userData = new HashMap<String, String>();

        //userData.put("firstname","roof");      fistname alanı RequestBody'ye eklenmedi.
        userData.put("lastname","stacks");
        userData.put("username","roofstacks123456");
        userData.put("password","roofstacks");

        Response response =given().contentType(ContentType.JSON).body(userData).when().post(baseUrl+"/users");
        response.then().assertThat().statusCode(400);
        response.prettyPrint();
    }
    /*
        NOT : Yeni kullanıcı oluştururken Required olan firstname/lastname/username alanları olmadığında
              veya kriterlere uymadığında da SUCCESS dönüyor.
    */


    @Test
    public void delRemoveUser(){
        //Var olan bir kullanıcı silinir.

        Response response = given().contentType(ContentType.JSON).when().delete(baseUrl+"/users/"+userId02);
        Utils.checkStatus(response);
    }
    @Test
    public void delRemoveUser_Negative(){
        //Var olmayan bir id ile kullanıcı silme denenir, hata alması beklenir.

        Response response = given().contentType(ContentType.JSON).when().delete(baseUrl+"/users/123456-gecersiz-id-123456");
        response.prettyPrint();
        response.then().assertThat().statusCode(404).
                body("error.name", equalTo("mockRequestNotFoundError"),
                        "error.message", equalTo("Double check your method and the request path and try again."),
                        "error.header", equalTo("No matching requests"));
    }
    @Test
    public void patchSwitchUserActivity(){
        //Kullanıcının aktiflik durumu değiştirilir.

        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive",false);
        Response response = given().contentType(ContentType.JSON).when().patch(baseUrl+"/users/"+userId01+"/activity");
        Utils.checkStatus(response);
        response.prettyPrint();
    }
    @Test
    public void patchSwitchUserActivity_Negative(){
        //Kullanıcının aktiflik durumunu geçersiz bir değerle değiştirilme denenir, hata alması beklenir.

        Map<String,Object> activity = new HashMap<String, Object>();
        activity.put("isActive","false"); // String olarak gönderildi.
        Response response = given().contentType(ContentType.JSON).when().patch(baseUrl+"/users/"+userId01+"/activity");
        response.then().assertThat().statusCode(400);
        response.prettyPrint();
    }
    @Test
    public void putUpdateUser(){
        //Var olan bir kullanıcının bilgileri değiştirilir

        UpdateUserPojo userData = new UpdateUserPojo("roof", "stacks");
        Response response = given().contentType(ContentType.JSON).body(userData).when().put(baseUrl+"/users/"+userId01);
        Utils.checkStatus(response);
        response.prettyPrint();
    }
}
