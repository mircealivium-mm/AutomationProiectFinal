package tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTestApi;

import java.util.List;

import static org.testng.Assert.*;

public class GetBooksTest extends BaseTestApi {

    // Test 5 Returneaza lista de carti din BookStore
    @Test
    public void testGetAllBooks() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 200,
                "Status code asteptat 200");

        List<?> books = response.jsonPath().getList("books");
        assertNotNull(books, "Lista de carti nu ar trebui sa fie null");
        assertFalse(books.isEmpty(), "Lista de carti nu ar trebui sa fie goala");
    }

    // Test 6 Verifica eroarea la ISBN invalid
    @Test
    public void testGetBookByIsbnInvalid() {
        Response response = RestAssured
                .given()
                .when()
                .get("/BookStore/v1/Book?ISBN=isbn_invalid_123")
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 400,
                "ISBN invalid ar trebui sa returneze 400");

        String message = response.jsonPath().getString("message");
        assertNotNull(message, "Mesajul de eroare nu ar trebui sa fie null");
    }

    // Test 7 Verifica structura raspunsului pentru o carte
    @Test
    public void testBookHasRequiredFields() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .extract().response();

        assertEquals(response.getStatusCode(), 200,
                "Status code asteptat 200");

        String firstBookIsbn = response.jsonPath().getString("books[0].isbn");
        String firstBookTitle = response.jsonPath().getString("books[0].title");
        String firstBookAuthor = response.jsonPath().getString("books[0].author");

        assertNotNull(firstBookIsbn, "ISBN nu ar trebui sa fie null");
        assertNotNull(firstBookTitle, "Titlul nu ar trebui sa fie null");
        assertNotNull(firstBookAuthor, "Autorul nu ar trebui sa fie null");
    }
}