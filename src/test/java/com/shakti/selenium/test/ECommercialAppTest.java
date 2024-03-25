package com.shakti.selenium.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shakti.pageobject.CartPage;
import com.shakti.pageobject.CheckOutPage;
import com.shakti.pageobject.ConfirmationPage;
import com.shakti.pageobject.LandingPage;
import com.shakti.pageobject.OrderPage;
import com.shakti.pageobject.ProductCatalogue;
import com.shakti.selenium.basecomponent.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ECommercialAppTest extends BaseTest {
	String PRODUCT_NAME = "ZARA COAT 3";
	@Test(dataProvider = "getData",groups = {"purchase"})
	public void submitOrder(HashMap<String,String> data) throws FileNotFoundException, IOException {
		
		String COUNTRY = "INDIA";
		ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("email"),data.get("password"));

		List<WebElement> productWebElement = productCatalogue.getProductList();
		productCatalogue.addProductToCart(data.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(data.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage =cartPage.checkOut();

		checkOutPage.selectCountry(COUNTRY);
		ConfirmationPage confirmationPage = checkOutPage.placedOrder();

		
		String actual = confirmationPage.orderConfirmationMessage();
		System.out.println(actual);
		AssertJUnit.assertEquals(actual, "THANKYOU FOR THE ORDER.");
		

	}
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("shakti@gmail.com", "Test1234");
		OrderPage orderPage=productCatalogue.goToOderPage();
		Assert.assertTrue(orderPage.verifyProductName(PRODUCT_NAME));
	}
	
	//172 - Parameterisation HashMap
	@DataProvider(name = "getData")
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data=getJsonToMap();
	
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
