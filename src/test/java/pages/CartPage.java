package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartIcon;

    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> cartItemNames;

    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    private WebElement removeButton;

    @FindBy(css = "[data-test='cart-item']")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public boolean isProductInCart(String productName) {
        for (WebElement item : cartItemNames) {
            if (getText(item).equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void removeFirstProduct() {
        click(removeButton);
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}