package com.na.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DesktopsPage extends AbstractPage {
    public DesktopsPage(WebDriver driver) {
        super(driver);
    }

    public ConfigureComputerPage openComputer(String productName) {
        By computerUrl = By.linkText(productName);

        wait.until(ExpectedConditions.elementToBeClickable(computerUrl)).click();
        return new ConfigureComputerPage(driver);
    }


}
