package testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RoofBase {
    protected RequestSpecification spec01;
    protected String baseUrl = "https://3e3d2990-3fca-4144-8b26-1538cf135a09.mock.pstmn.io";
    protected String userId01 = "c4f6c088-f91b-494e-b7f0-a08f48df3180";
    protected String userId02 = "c3e140a4-99db-44c2-a9ea-896904745993";
    @Before
    public void setUp(){
        spec01 = new RequestSpecBuilder().setBaseUri("https://3e3d2990-3fca-4144-8b26-1538cf135a09.mock.pstmn.io").build();
    }

}
