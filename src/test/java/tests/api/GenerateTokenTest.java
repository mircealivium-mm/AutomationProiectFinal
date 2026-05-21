package tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GenerateTokenTest {

    private static final String BASE_URL = "https://demoqa.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    // Test 3 Genereaza token de autentificare
    @Test
    public void testGenerateTokenSuccess() {
        String body = "{\n" +
                "    \"userName\": \"testUser_mihailescu_123\",\n" +
                "    \"password\": \"Test@1234!\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 200,
                "Status code asteptat 200");

        String token = response.jsonPath().getString("token");
        String status = response.jsonPath().getString("status");

        assertNotNull(token, "Token-ul nu ar trebui sa fie null");
        assertEquals(status, "Success", "Status-ul ar trebui sa fie Success");
    }

    // Test 4 Verifica esuarea cu credentiale invalide
    @Test
    public void testGenerateTokenInvalidCredentials() {
        String body = "{\n" +
                "    \"userName\": \"utilizatorFals\",\n" +
                "    \"password\": \"ParolaGresita@1\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 200,
                "Status code asteptat 200");

        String token = response.jsonPath().getString("token");
        String status = response.jsonPath().getString("status");

        assertNull(token, "Token-ul ar trebui sa fie null pentru credentiale invalide");
        assertEquals(status, "Failed", "Status-ul ar trebui sa fie Failed");
    }
}