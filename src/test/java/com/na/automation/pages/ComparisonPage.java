package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ComparisonPage extends AbstractPage {
    public ComparisonPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return wait.until(ExpectedConditions.urlContains("/compareproducts"));
    }

    public boolean containsProduct(String productName) {
        By productLocator = By.xpath("//tr[contains(@class,'product-name')]//a[normalize-space(.)='"+ productName + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator)).isDisplayed();
    }

    public String getProductPrice(String productName) {
        By productNameLocator = By.xpath("//tr[contains(@class,'product-name')]//a[normalize-space(.)='" + productName + "']");
        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));

        WebElement productCell = productLink.findElement(By.xpath("./.."));
        List<WebElement> nameCells = driver.findElements(By.cssSelector("tr.product-name td"));

        int productColumnIndex = nameCells.indexOf(productCell);
        List<WebElement> priceCells = driver.findElements(By.cssSelector("tr.product-price td"));

        return priceCells.get(productColumnIndex).getText().trim();
    }

    @FindBy(css = "a.clear-list")
    private WebElement clearListButton;

    public ComparisonPage clearList() {
        wait.until(ExpectedConditions.elementToBeClickable(clearListButton)).click();
        return this;
    }
    public boolean isProductAbsent(String productName) {
        By productLocator = By.xpath("//tr[contains(@class,'product-name')]//a[normalize-space(.)='" + productName + "']");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(productLocator));
    }
}
