package tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTestApi;

import static org.testng.Assert.*;

public class CreateAccountTest extends BaseTestApi {

    // Test 1 Creeaza un cont nou cu date valide
    @Test
    public void testCreateAccountSuccess() {
        String body = "{\n" +
                "    \"userName\": \"testUser_mihailescu_123\",\n" +
                "    \"password\": \"Test@1234!\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/Account/v1/User")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        assertTrue(statusCode == 201 || statusCode == 406,
                "Status code asteptat 201 sau 406, dar a fost: " + statusCode);
    }

    // Test 2 Verifica respingerea parolei slabe
    @Test
    public void testCreateAccountInvalidPassword() {
        String body = "{\n" +
                "    \"userName\": \"testUser_weak\",\n" +
                "    \"password\": \"123456\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/Account/v1/User")
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 400,
                "Parola slaba ar trebui sa returneze 400");
    }
}