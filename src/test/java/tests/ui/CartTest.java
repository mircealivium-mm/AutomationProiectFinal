package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import utils.BaseTest;

public class CartTest extends BaseTest {

    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    // Test 5 - Verifica delogare prin meniul lateral
    @Test
    public void testLogout() {
        // ✅ Login mai intai
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.logout();

        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"),
                "Logout-ul nu a functionat!");
    }

    // Test 6 - Verifica badge-ul cosului dupa adaugare produs
    @Test
    public void testCartBadgeAfterAddProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();

        String badgeCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "1",
                "Badge-ul cosului ar trebui sa arate 1 dupa adaugarea unui produs!");
    }

    // Test 7 - Elimina un produs din cos
    @Test
    public void testRemoveProductFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.goToCart();
        cartPage.removeFirstProduct();

        Assert.assertTrue(cartPage.isCartEmpty(),
                "Cosul ar trebui sa fie gol dupa eliminarea produsului!");
    }

    // Test 8 - Sorteaza produsele alfabetic Z-A
    @Test
    public void testSortProductsZA() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortByNameZA();

        Assert.assertEquals(inventoryPage.getFirstProductName(), "Test.allTheThings() T-Shirt (Red)",
                "Sortarea Z-A nu functioneaza corect!");
    }
}