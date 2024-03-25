package com.shakti.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shakti.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
   public boolean verifyProductName(String pname) {
	   productNames.stream().forEach(p->System.out.println(p.getText()));
	   boolean match=productNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(pname));
	   return match;
   }
	
	

}
