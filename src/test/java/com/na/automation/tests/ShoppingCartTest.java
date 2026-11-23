package com.na.automation.tests;

import com.na.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ShoppingCartTest  extends BaseTest {
    @Test
    public void shouldOpenConfigurableComputer() {
        // TC-03
        // select computer to configure
        String productName = "Build your own computer";
        HomePage homePage = new HomePage(driver).open();

        Assert.assertTrue(homePage.isOpened(), "home page should be open");

        ComputersPage computersPage = homePage.openComputersCategory();
        DesktopsPage desktopsPage = computersPage.openDesktops();
        ConfigureComputerPage computerPage = desktopsPage.openComputer(productName);

        Assert.assertEquals(computerPage.getProductName(), productName, "correct computer page should be displayed");

        // configure computer
        String processor = "2.5 GHz Intel Pentium Dual-Core E2200 [+15.00]";
        String ram = "8GB [+60.00]";

        computerPage.selectProcessor(processor);
        computerPage.selectRam(ram);

        Assert.assertEquals(computerPage.getSelectedProcessor(), processor, "incorrect processor selected");
        Assert.assertEquals(computerPage.getSelectedRam(), ram, "incorrect RAM selected");

        // selecting radio buttons
        computerPage.selectHdd400();
        computerPage.selectWindows10();

        Assert.assertTrue(computerPage.isHDD400GbSelected(), "400 GB HDD should be selected");
        Assert.assertTrue(computerPage.isWindows10Selected(), "windows 10 should be selected");

        // selecting checkboxes
        computerPage.selectSoftware();

        Assert.assertTrue(computerPage.areSOftwaresSelected(), "all software should be selected");

        // add to cart
        computerPage.addToCart();
        ShoppingCartPage shoppingCartPage = computerPage.openShoppingCart();

        Assert.assertEquals(shoppingCartPage.getProductName(productName), productName, "incorrect product in shopping cart");
        Assert.assertEquals(shoppingCartPage.getProductQuantity(productName), 1, "quantity should be 1");

        String attributes = shoppingCartPage.getProductAttributes(productName);

        Assert.assertTrue(attributes.contains("2.5 GHz Intel Pentium Dual-Core E2200 [+15.00]"), "incorrect processor in cart");
        Assert.assertTrue(attributes.contains("8GB"), "incorrect RAM in cart");
        Assert.assertTrue(attributes.contains("400 GB"), "incorrect HDD in cart");
        Assert.assertTrue(attributes.contains("Windows 10"), "incorrect OS in cart");
        Assert.assertTrue(attributes.contains("Microsoft Office"), "Office missing from cart");
        Assert.assertTrue(attributes.contains("Acrobat Reader"), "Acrobart Reader missing from cart");
        Assert.assertTrue(attributes.contains("Total Commander"), "Total Commander missing from cart");

        // updating quantity
        shoppingCartPage.changeQuantity(productName, 2);
        shoppingCartPage.updateCart();
        Assert.assertEquals(shoppingCartPage.getProductQuantity(productName), 2, "cart quantity should be 2");

        BigDecimal unitPrice = parsePrice(shoppingCartPage.getProductUnitPrice(productName));
        BigDecimal expectedTotal = unitPrice.multiply(BigDecimal.valueOf(2));
        BigDecimal actualTotal = parsePrice(shoppingCartPage.getProductSubtotal(productName));
        Assert.assertEquals(actualTotal.compareTo(expectedTotal), 0, "cart total should be equal tp unit price multiplied by quantity");

        // remove product
        shoppingCartPage.removeProduct(productName);
        Assert.assertTrue(shoppingCartPage.isProductAbsent(productName), "computer should be removed from the cart");
    }

    private BigDecimal parsePrice(String price) {
        String numericPrice = price.replaceAll("[^0-9.]", "");
        return new BigDecimal(numericPrice);
    }
}
