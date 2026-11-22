package com.na.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = "input.login-button")
    private WebElement loginButton;

    public HomePage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        return new HomePage(driver);
    }
}
