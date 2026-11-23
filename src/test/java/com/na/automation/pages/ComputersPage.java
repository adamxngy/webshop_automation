package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComputersPage extends AbstractPage {
    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    public DesktopsPage openDesktops() {
        By desktopsUrl = By.cssSelector("a[href='/desktops']");
        wait.until(ExpectedConditions.elementToBeClickable(desktopsUrl)).click();
        return new DesktopsPage(driver);
    }
}
