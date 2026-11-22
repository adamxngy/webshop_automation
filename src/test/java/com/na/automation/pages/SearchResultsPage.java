package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends AbstractPage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDisplayed(String productName) {
        By productLocator = By.linkText(productName);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator)).isDisplayed();
    }

    public ProductPage openProduct(String productName) {
        By productLocator = By.linkText(productName);

        wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();

        return new ProductPage(driver);
    }
}
