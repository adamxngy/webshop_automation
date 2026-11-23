package com.na.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ConfigureComputerPage extends ProductPage {
    public ConfigureComputerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "product_attribute_16_5_4")
    private WebElement processorDropDown;

    @FindBy(id = "product_attribute_16_6_5")
    private WebElement ramDropDown;

    public ConfigureComputerPage selectProcessor(String processor) {
        Select select = new Select(processorDropDown);
        select.selectByVisibleText(processor);
        return this;
    }

    public ConfigureComputerPage selectRam(String ram) {
        Select select = new Select(ramDropDown);
        select.selectByVisibleText(ram);
        return this;
    }

    public String getSelectedProcessor() {
        return new Select(processorDropDown).getFirstSelectedOption().getText().trim();
    }
    public String getSelectedRam() {
        return new Select(ramDropDown).getFirstSelectedOption().getText().trim();
    }

    @FindBy(id = "product_attribute_16_3_6_19")
    private WebElement hdd400GbButton;

    @FindBy(id = "product_attribute_16_4_7_21")
    private WebElement windows10Button;

    public ConfigureComputerPage selectHdd400() {
        if (!hdd400GbButton.isSelected()) {
            wait.until(ExpectedConditions.elementToBeClickable(hdd400GbButton)).click();
        }
        return this;
    }

    public ConfigureComputerPage selectWindows10() {
        if (!windows10Button.isSelected()) {
            wait.until(ExpectedConditions.elementToBeClickable(windows10Button)).click();
        }
        return this;
    }

    public boolean isHDD400GbSelected() {
        return hdd400GbButton.isSelected();
    }

    public boolean isWindows10Selected() {
        return windows10Button.isSelected();
    }

    @FindBy(id = "product_attribute_16_8_8_22")
    private WebElement officeCheckbox;

    @FindBy(id = "product_attribute_16_8_8_23")
    private WebElement acrobatReaderCheckbox;

    @FindBy(id = "product_attribute_16_8_8_24")
    private WebElement totalCommanderCheckbox;

    private void selectCheckbox(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        }
    }

    public ConfigureComputerPage selectSoftware() {
        selectCheckbox(officeCheckbox);
        selectCheckbox(acrobatReaderCheckbox);
        selectCheckbox(totalCommanderCheckbox);
        return this;
    }

    public boolean areSOftwaresSelected() {
        return officeCheckbox.isSelected() && acrobatReaderCheckbox.isSelected() && totalCommanderCheckbox.isSelected();
    }

    @FindBy(id = "add-to-cart-button-16")
    private WebElement addToCartButton;

    @FindBy(css = "div.bar-notification.success")
    private WebElement successNotification;

    @FindBy(css = "a.ico-cart")
    private WebElement shoppingCartUrl;

    public ConfigureComputerPage addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.visibilityOf(successNotification));
        return this;
    }

    public ShoppingCartPage openShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartUrl)).click();
        return new ShoppingCartPage(driver);
    }
}
