package com.na.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {
    private static final String HOME_URL = "https://demowebshop.tricentis.com";

    @FindBy(id = "nivo-slider")
    private WebElement nivoSlider;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(HOME_URL);
        return this;
    }

    public boolean isOpened() {
        return wait.until(ExpectedConditions.visibilityOf(nivoSlider)).isDisplayed();
    }

    @FindBy(id = "small-searchterms")
    private WebElement searchInput;

    @FindBy(css = "input.search-box-button")
    private WebElement searchButton;

    public SearchResultsPage searchFor(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchInput)).sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        return new SearchResultsPage(driver);
    }
}
