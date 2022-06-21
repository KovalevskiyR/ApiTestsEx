import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class TestUsers {
    private final String URl = "https://jsonplaceholder.typicode.com/users";

    @Test
    public void GetUsers(){
        List<ResponseItem> users = RestAssured.given() /*Pojo's class list*/
                .when()
                .get(URl)
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getList("email"); /*Parsing using POJO class*/
        System.out.println(users.get(0)); /*Get a certain object by index*/
        Assert.assertEquals("Sincere@april.biz", users.get(0)); /*Checking first email accordance*/
    }
    @Test
    public void GetEmails(){
        Response response = given()
                .when()
                .get(URl)
                .then().log().all()
                .body("email[0]", equalTo("Sincere@april.biz"))  /*Checking first email accordance*/
                .extract().response();
        JsonPath jsonPath = response.jsonPath(); /*How to parse response without POJO Class*/
        List<String> emails = jsonPath.get("email"); /*How to parse response without POJO Class*/
    }
}
