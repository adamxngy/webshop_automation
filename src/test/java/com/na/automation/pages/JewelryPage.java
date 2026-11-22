package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JewelryPage extends AbstractPage {
    public JewelryPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage openProduct(String productName) {
        By productLocator = By.linkText(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();

        return new ProductPage(driver);
    }
}
