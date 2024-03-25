package com.shakti.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shakti.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
   //WebElement email=driver.findElement(By.cssSelector("#userEmail"));
	@FindBy(css = "#userEmail")
	WebElement email;
	@FindBy(css = "#userPassword")
	WebElement password;
	@FindBy(css ="#login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue loginApplication(String id,String pwd) {
		email.sendKeys(id);
		password.sendKeys(pwd);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public String getErrorMessage() {
		waitForVisibleWebElement(errorMessage);
		return errorMessage.getText();
	}

}
