package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> productNames;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void sortByNameAZ() {
        // ✅ Asteapta ca dropdown-ul sa fie vizibil
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByValue("az");
    }

    public String getFirstProductName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return getText(productNames.get(0));
    }

    public void logout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Click meniu prin JavaScript
        js.executeScript("arguments[0].click();", menuButton);

        // Asteapta ca link-ul de logout sa fie vizibil
        wait.until(ExpectedConditions.visibilityOf(logoutLink));

        // Click logout prin JavaScript
        js.executeScript("arguments[0].click();", logoutLink);
    }

    public ProductPage clickOnProduct(String productName) {
        // ✅ Asteapta ca produsele sa fie vizibile
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        for (WebElement product : productNames) {
            if (getText(product).equals(productName)) {
                click(product);
                return new ProductPage(driver);
            }
        }
        throw new RuntimeException("Produsul nu a fost gasit: " + productName);
    }

    public void sortByNameZA() {
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByValue("za");
    }

    public String getCartBadgeCount() {
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        return getText(cartBadge);
    }

    public void addFirstProductToCart() {
        WebElement addToCartButton = driver.findElement(
                org.openqa.selenium.By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")
        );
        click(addToCartButton);
    }
}