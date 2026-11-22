package com.na.automation.tests;

import com.na.automation.pages.ComparisonPage;
import com.na.automation.pages.HomePage;
import com.na.automation.pages.ProductPage;
import com.na.automation.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductComparisonTest extends BaseTest {

    @Test
    public void findLaptopInResults() {
        // first product
        String productName = "14.1-inch Laptop";

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isOpened(), "Home page should be displayed");

        SearchResultsPage searchResults = homePage.searchFor(productName);
        Assert.assertTrue(searchResults.isProductDisplayed(productName), "Product should be visible in search result");

        ProductPage productPage = searchResults.openProduct(productName);
        Assert.assertTrue(productPage.isProductDisplayed(productName), "Correct product page should be displayed");

        String actualProductName = productPage.getProductName();
        String actualProductPrice = productPage.getProductPrice();
        Assert.assertEquals(actualProductName, productName, "Product page should disply the correct name");
        Assert.assertFalse(actualProductPrice.isBlank(), "Product price shouldn't be empty");

        ComparisonPage comparisonPage = productPage.addToCompareList();
        Assert.assertTrue(comparisonPage.isOpened(), "Comparison page should be open after adding product");
        Assert.assertTrue(comparisonPage.containsProduct(actualProductName), "comparison page should contain: " + actualProductName);

        Assert.assertEquals(comparisonPage.getProductPrice(actualProductName), actualProductPrice, "comparison price should match the product page price");

        // second product
        String secondProductName = "Desktop PC with CDRW";

        homePage.open();
        Assert.assertTrue(homePage.isOpened(), "home page should be opened for the second search");

        SearchResultsPage secondSearchResult = homePage.searchFor(secondProductName);
        Assert.assertTrue(secondSearchResult.isProductDisplayed(secondProductName), "Product should appear in search results");

        ProductPage secondProductPage = searchResults.openProduct(secondProductName);
        Assert.assertEquals(secondProductPage.getProductName(), secondProductName, "Correct desktop page should be displayed");

        String secondProductPrice = secondProductPage.getProductPrice();
        Assert.assertFalse(secondProductPrice.isBlank(), "desktop product price shouldnt be empty");

        ComparisonPage compareTwoProducts = secondProductPage.addToCompareList();
        Assert.assertTrue(compareTwoProducts.isOpened(), "comparison page should open after adding the desktop PC");

        // asserting the two products
        Assert.assertTrue(compareTwoProducts.containsProduct(actualProductName), "comparison page should contain laptop still");
        Assert.assertTrue(compareTwoProducts.containsProduct(secondProductName), "comparison page should contain PC still");
        Assert.assertEquals(compareTwoProducts.getProductPrice(actualProductName), actualProductPrice, "laptop comparison list price should match the product page price");
        Assert.assertEquals(compareTwoProducts.getProductPrice(secondProductName), secondProductPrice, "PC comparison list price should match the product page price");

        // clearing list
        compareTwoProducts.clearList();
        Assert.assertTrue(compareTwoProducts.isProductAbsent(actualProductName), "laptop should be removed after clearing the list");
        Assert.assertTrue(compareTwoProducts.isProductAbsent(secondProductName), "Desktop should be removed after clearing the list");
    }
}
