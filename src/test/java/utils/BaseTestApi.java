package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTestApi {

    protected static final String BASE_URL = "https://demoqa.com";

    @BeforeClass
    public void setupApi() {
        RestAssured.baseURI = BASE_URL;
    }
}