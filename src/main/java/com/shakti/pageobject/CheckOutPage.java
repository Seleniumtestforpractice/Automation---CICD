package com.shakti.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shakti.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder*='Select Country']")
	WebElement country;
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	By resultsBy = By.cssSelector(".ta-results");

	@FindBy(css = ".action__submit")
	WebElement placedOrder;

	public void selectCountry(String countryName) {

		Actions actions = new Actions(driver);
		actions.sendKeys(country, countryName).build().perform();

		waitForVisibleElement(resultsBy);
		selectCountry.click();
	}

	public ConfirmationPage placedOrder() {
		placedOrder.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
