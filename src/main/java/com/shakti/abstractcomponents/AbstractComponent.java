package com.shakti.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shakti.pageobject.CartPage;
import com.shakti.pageobject.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement order;

	public void waitForVisibleElement(By findBy) {

		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForInVisibleElement(By findBy) {

		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void waitForVisibleWebElement(WebElement findBy) {

		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public OrderPage goToOderPage() {
		order.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
	
	public CartPage goToCartPage() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
}
