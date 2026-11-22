package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends AbstractPage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDisplayed(String productName) {
        By productTitle = By.xpath("//div[contains(@class,'product-name')]/h1");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText().equals(productName);
    }

    @FindBy(css = "div.product-name h1")
    private WebElement productName;

    @FindBy(css = "div.product-price span")
    private WebElement productPrice;

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public String getProductPrice() {
        return wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
    }

    @FindBy(css = "input[value='Add to compare list']")
    private WebElement addToCompareListButton;

    public ComparisonPage addToCompareList() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCompareListButton)).click();
        return new ComparisonPage((driver));
    }
}
