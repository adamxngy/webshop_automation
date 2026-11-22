package com.na.automation.tests;

import com.na.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishListTest extends BaseTest {
    @Test
    public void openJewelryAfterLogin() {
        // TC-02
        // logging in
        String email = System.getenv("DWS_EMAIL");
        String password = System.getenv("DWS_PASSWORD");

        Assert.assertNotNull(email, "DWS_EMAIL env variable is missing");
        Assert.assertNotNull(password, "DWS_PASSWORD env variable is missing");

        HomePage homePage = new HomePage(driver).open();

        Assert.assertTrue(homePage.isOpened(), "Home page should be displayed");

        LoginPage loginPage = homePage.openLoginPage();
        HomePage loggedInPage = loginPage.login(email, password);

        Assert.assertTrue(loggedInPage.isLoggedIn(), "user should be logged in");

        // opening jewelry
        String productName = "Black & White Diamond Heart";
        JewelryPage jewelryPage = loggedInPage.openJewelryCategory();
        ProductPage productPage = jewelryPage.openProduct(productName);

        Assert.assertEquals(productPage.getProductName(),productName,"correct jewelry product page should be displayed");

        String productPrice = productPage.getProductPrice();
        Assert.assertFalse(productPrice.isBlank(),"jewelry product price shouldn't be empty");

        // adding it to wishlist
        productPage.addToWishList();
        WishlistPage wishlistPage = productPage.openWishlist();

        Assert.assertTrue(wishlistPage.containsProduct(productName), "wishlist should contain" + productName);
        Assert.assertEquals(wishlistPage.getProductPrice(productName), productPrice, "wishlist price should match the product page price");
        Assert.assertEquals(wishlistPage.getProductQuantity(productName), 1, "wishlist quantity should be 1");

        // remove from wishlist
        wishlistPage.removeProduct(productName);
        Assert.assertTrue(wishlistPage.isProductAbsent(productName), "product should be removed from the wishlist");

        // logout
        HomePage afterLogoutPage = new HomePage(driver).logout();
        Assert.assertTrue(afterLogoutPage.isLoggedOut(), "user should be logged out successfully");

    }
}
