package com.shakti.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shakti.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By productBy=By.cssSelector(".mb-3");
	@FindBy(css = ".mb-3")
	List<WebElement> productWebElements;
	
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForVisibleElement(productBy);
		return productWebElements;
	}

	public WebElement getProductByName(String pname) {
		WebElement productIteam=productWebElements.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(pname)).findFirst().orElse(null);
		return productIteam;
	}
	
	public void addProductToCart(String pname) {
		WebElement iteam=getProductByName(pname);
		iteam.findElement(addToCart).click();
		waitForVisibleElement(toastMessage);
		waitForInVisibleElement(toastMessage);
	}
}
