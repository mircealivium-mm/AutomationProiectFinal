package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "[data-test='inventory-item-name']")
    private WebElement productName;

    @FindBy(css = "[data-test='inventory-item-price']")
    private WebElement productPrice;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public CartPage addToCart() {
        click(addToCartButton);
        return new CartPage(driver);
    }
}
