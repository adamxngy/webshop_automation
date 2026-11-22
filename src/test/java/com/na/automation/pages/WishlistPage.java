package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WishlistPage extends AbstractPage {
    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsProduct(String productName) {
        By productLocator = By.linkText(productName);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator)).isDisplayed();
    }

    private WebElement getProductRow(String productName) {
        By productRowLocator = By.xpath("//tr[.//a[normalize-space(.)='" + productName + "']]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productRowLocator));
    }

    public String getProductPrice(String productName) {
        WebElement productRow = getProductRow(productName);
        WebElement priceElement = productRow.findElement(By.cssSelector("td.unit-price span.product-unit-price"));
        return priceElement.getText().trim();
    }

    public int getProductQuantity(String productName) {
        WebElement productRow = getProductRow(productName);
        WebElement quantityInput = productRow.findElement(By.cssSelector("input.qty-input"));
        return Integer.parseInt(quantityInput.getDomProperty("value"));
    }

    @FindBy(css = "input[name='updatecart']")
    private WebElement updateWishlistButton;

    public WishlistPage removeProduct(String productName) {
        WebElement productRow = getProductRow(productName);
        WebElement removeCheckBox = productRow.findElement(By.cssSelector("input[name='removefromcart']"));

        if (!removeCheckBox.isSelected()) {
            removeCheckBox.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(updateWishlistButton)).click();
        return this;
    }

    public boolean isProductAbsent(String productName) {
        By productLocator = By.xpath("//tr[.//a[normalize-space(.)='" + productName + "']]");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(productLocator));
    }
}
