package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends AbstractPage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getProductRow(String productName) {
        By rowLocator = By.xpath("//tr[.//a[contains(@class, 'product-name') " + "and normalize-space(.)='" + productName + "']]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
    }

    public String getProductName(String productName) {
        return getProductRow(productName).findElement(By.cssSelector("a.product-name")).getText().trim();
    }

    public String getProductAttributes(String productName) {
        return getProductRow(productName).findElement(By.cssSelector("div.attributes")).getText();
    }

    public int getProductQuantity(String productName) {
        WebElement quantityInput = getProductRow(productName).findElement(By.cssSelector("input.qty-input"));
        return Integer.parseInt(quantityInput.getDomProperty("value"));
    }

    public String getProductUnitPrice(String productName) {
        return getProductRow(productName).findElement(By.cssSelector("span.product-unit-price")).getText().trim();
    }

    public String getProductSubtotal(String productName) {
        return getProductRow(productName).findElement(By.cssSelector("span.product-subtotal")).getText().trim();
    }

    public ShoppingCartPage changeQuantity(String productName, int quantity) {
        WebElement quantityInput = getProductRow(productName).findElement(By.cssSelector("input.qty-input"));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));

        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
        return this;
    }

    @FindBy(css = "input[name='updatecart']")
    private WebElement updateCartButton;

    public ShoppingCartPage updateCart() {
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
        return this;
    }

    public ShoppingCartPage removeProduct(String productName) {
        WebElement productRow = getProductRow(productName);
        WebElement removeCheckbox = productRow.findElement(By.cssSelector("input[name='removefromcart']"));

        if (!removeCheckbox.isSelected()) {
            wait.until(ExpectedConditions.elementToBeClickable(removeCheckbox)).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();

        return this;
    }

    public boolean isProductAbsent(String productName) {
        By productLocator = By.xpath("//tr[.//a[contains(@class, 'product-name') " + "and normalize-space(.)='" + productName + "']]");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(productLocator));
    }

}
