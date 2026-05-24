package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.ProductPage;
import pages.CartPage;
import utils.BaseTest;

public class ProductTest extends BaseTest {

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";

    // Test 3 - Adauga un produs in cos din pagina de detaliu
    @Test
    public void testAddProductToCart() {
        // ✅ Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        ProductPage productPage = inventoryPage.clickOnProduct(PRODUCT_NAME);

        String savedName = productPage.getProductName();

        CartPage cartPage = productPage.addToCart();
        cartPage.goToCart();

        Assert.assertTrue(cartPage.isProductInCart(savedName),
                "Produsul nu se afla in cart!");
    }

    // Test 4 - Sorteaza produsele alfabetic A-Z
    @Test
    public void testSortProductsAZ() {
        // ✅ Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortByNameAZ();

        Assert.assertEquals(inventoryPage.getFirstProductName(), "Sauce Labs Backpack",
                "Sortarea A-Z nu functioneaza corect!");
    }
}