package com.shakti.selenium.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shakti.pageobject.CartPage;
import com.shakti.pageobject.LandingPage;
import com.shakti.pageobject.ProductCatalogue;
import com.shakti.selenium.basecomponent.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void  loginValidation() throws FileNotFoundException, IOException {
		
		
		landingPage.loginApplication("shakti@gmail.com", "Test12345");
		Assert.assertEquals(landingPage.getErrorMessage(),"Login Successfully");
		

	}
	@Test
	public void verifyProduct() {
		
		String PRODUCT_NAME = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("shakti@gmail.com", "Test1234");
		productCatalogue.addProductToCart(PRODUCT_NAME);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(PRODUCT_NAME);
		Assert.assertTrue(match);
	}
}
