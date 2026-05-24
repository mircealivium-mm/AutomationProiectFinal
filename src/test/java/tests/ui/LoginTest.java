package tests.ui;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {


    // Test 1  Verifica login reusit cu credentiale valide
    @Test
    public void testLoginValid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Login valid a esuat!");
    }

    // Test 2 - Verifica mesajul de eroare la credentiale invalide
    @Test
    public void testLoginInvalid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "parolaGresita");

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Mesajul de eroare nu apare!"
        );
    }
}