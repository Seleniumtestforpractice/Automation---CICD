package com.shakti.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shakti.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartIteams;
	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public boolean verifyProductDisplay(String pname) {
		boolean match = cartIteams.stream().anyMatch(iteam -> iteam.getText().equalsIgnoreCase(pname));
		return match;
	}

	public CheckOutPage checkOut() {
		checkout.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
